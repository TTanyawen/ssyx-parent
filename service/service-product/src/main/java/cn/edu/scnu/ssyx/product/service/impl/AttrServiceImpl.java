package cn.edu.scnu.ssyx.product.service.impl;
import cn.edu.scnu.ssyx.model.product.Attr;
import cn.edu.scnu.ssyx.product.mapper.AttrMapper;
import cn.edu.scnu.ssyx.product.service.AttrService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import java.util.List;
@Service
public class AttrServiceImpl  extends ServiceImpl<AttrMapper, Attr> implements AttrService {
    //根据属性分组id 获取属性列表
    @Override
    public List<Attr> findByAttrGroupId(Long attrGroupId) {
        LambdaQueryWrapper<Attr> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Attr::getAttrGroupId,attrGroupId);
        List<Attr> attrList = baseMapper.selectList(wrapper);
        return attrList;
    }
}