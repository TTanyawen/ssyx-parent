package cn.edu.scnu.ssyx.product.service.impl;
import cn.edu.scnu.ssyx.common.exception.SsyxException;
import cn.edu.scnu.ssyx.common.result.ResultCodeEnum;
import cn.edu.scnu.ssyx.model.product.SkuAttrValue;
import cn.edu.scnu.ssyx.model.product.SkuImage;
import cn.edu.scnu.ssyx.model.product.SkuInfo;
import cn.edu.scnu.ssyx.model.product.SkuPoster;
import cn.edu.scnu.ssyx.mq.constant.MqConst;
import cn.edu.scnu.ssyx.mq.service.RabbitService;
import cn.edu.scnu.ssyx.product.mapper.SkuInfoMapper;
import cn.edu.scnu.ssyx.product.service.SkuAttrValueService;
import cn.edu.scnu.ssyx.product.service.SkuImageService;
import cn.edu.scnu.ssyx.product.service.SkuInfoService;
import cn.edu.scnu.ssyx.product.service.SkuPosterService;
import cn.edu.scnu.ssyx.vo.product.SkuInfoQueryVo;
import cn.edu.scnu.ssyx.vo.product.SkuInfoVo;
import cn.edu.scnu.ssyx.vo.product.SkuStockLockVo;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.Collections;
import java.util.List;

// sku信息 服务实现类
@Service
public class SkuInfoServiceImpl extends ServiceImpl<SkuInfoMapper, SkuInfo> implements SkuInfoService {

    @Resource
    private RabbitService rabbitService;
    @Resource
    private SkuInfoMapper skuInfoMapper;


    @Resource
    private SkuPosterService skuPosterService;
    @Resource
    private SkuImageService skuImageService;
    @Resource
    private SkuAttrValueService skuAttrValueService;

    //添加商品
    @Transactional(rollbackFor = {Exception.class})
    @Override
    public void saveSkuInfo(SkuInfoVo skuInfoVo) {
   //保存sku信息
        SkuInfo skuInfo = new SkuInfo();
        BeanUtils.copyProperties(skuInfoVo, skuInfo);
        this.save(skuInfo);

        //保存sku海报
        List<SkuPoster> skuPosterList = skuInfoVo.getSkuPosterList();
        if(!CollectionUtils.isEmpty(skuPosterList)) {
            int sort = 1;
            for(SkuPoster skuPoster : skuPosterList) {
                skuPoster.setSkuId(skuInfo.getId());
                sort++;
            }
            skuPosterService.saveBatch(skuPosterList);
        }

        //保存sku图片
        List<SkuImage> skuImagesList = skuInfoVo.getSkuImagesList();
        if(!CollectionUtils.isEmpty(skuImagesList)) {
            int sort = 1;
            for(SkuImage skuImages : skuImagesList) {
                skuImages.setSkuId(skuInfo.getId());
                skuImages.setSort(sort);
                sort++;
            }
            skuImageService.saveBatch(skuImagesList);
        }

        //保存sku平台属性
        List<SkuAttrValue> skuAttrValueList = skuInfoVo.getSkuAttrValueList();
        if(!CollectionUtils.isEmpty(skuAttrValueList)) {
            int sort = 1;
            for(SkuAttrValue skuAttrValue : skuAttrValueList) {
                skuAttrValue.setSkuId(skuInfo.getId());
                skuAttrValue.setSort(sort);
                sort++;
            }
            skuAttrValueService.saveBatch(skuAttrValueList);
        }
    }

    @Override
    public SkuInfoVo getSkuInfoVo(Long id) {
        SkuInfo skuInfo = baseMapper.selectById(id);
        SkuInfoVo skuInfoVo = new SkuInfoVo();
        BeanUtils.copyProperties(skuInfo,skuInfoVo);
        skuInfoVo.setSkuPosterList(skuPosterService.getPosterListBySkuId( id));
        skuInfoVo.setSkuAttrValueList(skuAttrValueService.getAttrValueListBySkuId(id));
        skuInfoVo.setSkuImagesList(skuImageService.getImageListBySkuId(id));
        return skuInfoVo;
    }

    //修改sku
    @Override
    public void updateSkuInfo(SkuInfoVo skuInfoVo) {
        //修改sku基本信息
        SkuInfo skuInfo = new SkuInfo();
        BeanUtils.copyProperties(skuInfoVo,skuInfo);
        baseMapper.updateById(skuInfo);

        Long skuId = skuInfoVo.getId();
        //海报信息
        LambdaQueryWrapper<SkuPoster> wrapperSkuPoster = new LambdaQueryWrapper<>();
        wrapperSkuPoster.eq(SkuPoster::getSkuId,skuId);
        skuPosterService.remove(wrapperSkuPoster);

        List<SkuPoster> skuPosterList = skuInfoVo.getSkuPosterList();
        if(!CollectionUtils.isEmpty(skuPosterList)) {
            //遍历，向每个海报对象添加商品skuid
            for (SkuPoster skuPoster:skuPosterList) {
                skuPoster.setSkuId(skuId);
            }
            skuPosterService.saveBatch(skuPosterList);
        }

        //商品图片
        skuImageService.remove(new LambdaQueryWrapper<SkuImage>().eq(SkuImage::getSkuId,skuId));
        List<SkuImage> skuImagesList = skuInfoVo.getSkuImagesList();
        if(!CollectionUtils.isEmpty(skuImagesList)) {
            for (SkuImage skuImage:skuImagesList) {
                //设置商品skuid
                skuImage.setSkuId(skuId);
            }
            skuImageService.saveBatch(skuImagesList);
        }

        //商品属性
        skuAttrValueService.remove(new LambdaQueryWrapper<SkuAttrValue>().eq(SkuAttrValue::getSkuId,skuId));
        List<SkuAttrValue> skuAttrValueList = skuInfoVo.getSkuAttrValueList();
        if(!CollectionUtils.isEmpty(skuAttrValueList)) {
            for (SkuAttrValue skuAttrValue:skuAttrValueList) {
                //设置商品skuid
                skuAttrValue.setSkuId(skuId);
            }
            skuAttrValueService.saveBatch(skuAttrValueList);
        }
    }

    //获取sku分页列表
    @Override
    public IPage<SkuInfo> selectPage(Page<SkuInfo> pageParam, SkuInfoQueryVo skuInfoQueryVo) {
        //获取条件值
        String keyword = skuInfoQueryVo.getKeyword();
        String skuType = skuInfoQueryVo.getSkuType();
        Long categoryId = skuInfoQueryVo.getCategoryId();
        //封装条件
        LambdaQueryWrapper<SkuInfo> wrapper = new LambdaQueryWrapper<>();
        if(!StringUtils.isEmpty(keyword)) {
            wrapper.like(SkuInfo::getSkuName,keyword);
        }
        if(!StringUtils.isEmpty(skuType)) {
            wrapper.eq(SkuInfo::getSkuType,skuType);
        }
        if(!StringUtils.isEmpty(categoryId)) {
            wrapper.eq(SkuInfo::getCategoryId,categoryId);
        }
        //调用方法查询
        IPage<SkuInfo> skuInfoPage = baseMapper.selectPage(pageParam, wrapper);
        return skuInfoPage;
    }

    // 商品上下架
    @Override
    public void publish(Long skuId, Integer status) {
        if(status == 1) { //上架
            SkuInfo skuInfo = baseMapper.selectById(skuId);
            skuInfo.setPublishStatus(status);
            baseMapper.updateById(skuInfo);
            //TODO 整合mq把数据同步到es里面
            //商品上架：发送mq消息同步es
            rabbitService.sendMessage(MqConst.EXCHANGE_GOODS_DIRECT, MqConst.ROUTING_GOODS_UPPER, skuId);

        } else { //下架
            SkuInfo skuInfo = baseMapper.selectById(skuId);
            skuInfo.setPublishStatus(status);
            baseMapper.updateById(skuInfo);
            //TODO 整合mq把数据同步到es里面
            //商品下架：发送mq消息同步es
            rabbitService.sendMessage(MqConst.EXCHANGE_GOODS_DIRECT, MqConst.ROUTING_GOODS_LOWER, skuId);
        }
    }

    //新人专享
    @Override
    public void isNewPerson(Long skuId, Integer status) {
        SkuInfo skuInfoUp = new SkuInfo();
        skuInfoUp.setId(skuId);
        skuInfoUp.setIsNewPerson(status);
        baseMapper.updateById(skuInfoUp);
    }

    //商品审核
    @Override
    public void check(Long skuId, Integer status) {
        SkuInfo skuInfo = baseMapper.selectById(skuId);
        skuInfo.setCheckStatus(status);
        baseMapper.updateById(skuInfo);
    }
}