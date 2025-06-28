package cn.edu.scnu.ssyx.product.service.impl;

import cn.edu.scnu.ssyx.model.product.AttrGroup;
import cn.edu.scnu.ssyx.product.mapper.AttrGroupMapper;
import cn.edu.scnu.ssyx.product.service.AttrGroupService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

//属性分组 服务实现类
@Service
public class AttrGroupServiceImpl extends ServiceImpl<AttrGroupMapper, AttrGroup> implements AttrGroupService {
}