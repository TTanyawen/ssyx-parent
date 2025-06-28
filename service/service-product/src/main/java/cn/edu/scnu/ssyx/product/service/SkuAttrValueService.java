package cn.edu.scnu.ssyx.product.service;
import cn.edu.scnu.ssyx.model.product.SkuAttrValue;
import com.baomidou.mybatisplus.extension.service.IService;
import java.util.List;
//spu属性值 服务类
public interface SkuAttrValueService extends IService<SkuAttrValue> {
    //根据id查询商品属性信息列表
    List<SkuAttrValue> getAttrValueListBySkuId(Long skuId);
}