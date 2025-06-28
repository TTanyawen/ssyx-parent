package cn.edu.scnu.ssyx.product.service;
import cn.edu.scnu.ssyx.model.product.Attr;
import com.baomidou.mybatisplus.extension.service.IService;
import java.util.List;
//商品属性 服务类
public interface AttrService extends IService<Attr> {
    //根据属性分组id 获取属性列表
    List<Attr> findByAttrGroupId(Long attrGroupId);
}