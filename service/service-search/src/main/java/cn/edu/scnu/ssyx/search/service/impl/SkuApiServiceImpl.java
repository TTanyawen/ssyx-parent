package cn.edu.scnu.ssyx.search.service.impl;

import cn.edu.scnu.ssyx.client.product.ProductFeignClient;
import cn.edu.scnu.ssyx.enums.SkuType;
import cn.edu.scnu.ssyx.model.product.Category;
import cn.edu.scnu.ssyx.model.product.SkuInfo;
import cn.edu.scnu.ssyx.model.search.SkuEs;
import cn.edu.scnu.ssyx.search.repository.SkuRepository;
import cn.edu.scnu.ssyx.search.service.SkuApiService;
import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Slf4j
@Service
public class SkuApiServiceImpl implements SkuApiService {

    @Resource
    private SkuRepository skuRepository;
    @Resource
    private RedisTemplate redisTemplate;
    @Resource
    private ProductFeignClient productFeignClient;
    /**
     * 上架
     * @param skuId
     */
    @Override
    public void upperSku(Long skuId) {
        log.info("upperSku："+skuId);
        SkuEs skuEs = new SkuEs();

        //查询sku信息
        SkuInfo skuInfo = productFeignClient.getSkuInfo(skuId);
        if(null == skuInfo) return;

        // 查询分类
        Category category = productFeignClient.getCategory(skuInfo.getCategoryId());
        if (category != null) {
            skuEs.setCategoryId(category.getId());
            skuEs.setCategoryName(category.getName());
        }
        skuEs.setId(skuInfo.getId());
        skuEs.setKeyword(skuInfo.getSkuName()+","+skuEs.getCategoryName());
        skuEs.setWareId(skuInfo.getWareId());
        skuEs.setIsNewPerson(skuInfo.getIsNewPerson());
        skuEs.setImgUrl(skuInfo.getImgUrl());
        skuEs.setTitle(skuInfo.getSkuName());
        if(skuInfo.getSkuType() == SkuType.COMMON.getCode()) {
            skuEs.setSkuType(0);
            skuEs.setPrice(skuInfo.getPrice().doubleValue());
            skuEs.setStock(skuInfo.getStock());
            skuEs.setSale(skuInfo.getSale());
            skuEs.setPerLimit(skuInfo.getPerLimit());
        } else {
            //TODO 待完善-秒杀商品
        }
        SkuEs save = skuRepository.save(skuEs);
        log.info("upperSku："+ JSON.toJSONString(save));
    }

    /**
     * 下架
     * @param skuId
     */
    @Override
    public void lowerSku(Long skuId) {
        skuRepository.deleteById(skuId);
    }
}