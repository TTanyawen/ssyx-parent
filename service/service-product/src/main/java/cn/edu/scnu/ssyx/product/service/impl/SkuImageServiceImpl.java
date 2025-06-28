package cn.edu.scnu.ssyx.product.service.impl;
import cn.edu.scnu.ssyx.model.product.SkuImage;
import cn.edu.scnu.ssyx.product.mapper.SkuImageMapper;
import cn.edu.scnu.ssyx.product.service.SkuImageService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import java.util.List;
//商品图片 服务实现类
@Service
public class SkuImageServiceImpl extends ServiceImpl<SkuImageMapper, SkuImage> implements SkuImageService {
    //根据id查询商品图片列表
    @Override
    public List<SkuImage> getImageListBySkuId(Long skuId) {
        LambdaQueryWrapper<SkuImage> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(SkuImage::getSkuId,skuId);
        List<SkuImage> skuImageList = baseMapper.selectList(wrapper);
        return skuImageList;
    }
}