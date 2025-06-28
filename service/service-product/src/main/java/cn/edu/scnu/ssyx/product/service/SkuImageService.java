package cn.edu.scnu.ssyx.product.service;
import cn.edu.scnu.ssyx.model.product.SkuImage;
import com.baomidou.mybatisplus.extension.service.IService;
import java.util.List;
//商品图片 服务类
public interface SkuImageService  extends IService<SkuImage> {
    //根据id查询商品图片列表
    List<SkuImage> getImageListBySkuId(Long skuId);
}