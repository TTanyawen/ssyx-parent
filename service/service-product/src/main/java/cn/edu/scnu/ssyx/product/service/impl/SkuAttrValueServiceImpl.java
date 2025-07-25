package cn.edu.scnu.ssyx.product.service.impl;
import cn.edu.scnu.ssyx.model.product.SkuAttrValue;
import cn.edu.scnu.ssyx.product.mapper.SkuAttrValueMapper;
import cn.edu.scnu.ssyx.product.service.SkuAttrValueService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import java.util.List;
//spu属性值 服务实现类
@Service
public class SkuAttrValueServiceImpl extends ServiceImpl<SkuAttrValueMapper, SkuAttrValue> implements SkuAttrValueService {
    //根据id查询商品属性信息列表
    @Override
    public List<SkuAttrValue> getAttrValueListBySkuId(Long skuId) {
        LambdaQueryWrapper<SkuAttrValue> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(SkuAttrValue::getSkuId,skuId);
        return baseMapper.selectList(wrapper);
    }
}