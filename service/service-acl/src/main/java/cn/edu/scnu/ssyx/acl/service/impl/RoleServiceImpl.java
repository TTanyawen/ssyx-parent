package cn.edu.scnu.ssyx.acl.service.impl;
import cn.edu.scnu.ssyx.acl.service.RoleService;
import cn.edu.scnu.ssyx.model.acl.Role;
import cn.edu.scnu.ssyx.vo.acl.RoleQueryVo;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import cn.edu.scnu.ssyx.acl.mapper.RoleMapper;
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements RoleService {
    @Override
    public IPage<Role> selectPage(Page<Role> pageParam, RoleQueryVo roleQueryVo) {
        //获取条件值：角色名称
        String roleName = roleQueryVo.getRoleName();
        //创建条件构造器对象
        LambdaQueryWrapper<Role> wrapper = new LambdaQueryWrapper<>();
        //判断条件值是否为空
        if(!StringUtils.isEmpty(roleName)) {
            //封装条件
            wrapper.like(Role::getRoleName,roleName);
        }
        //调用mapper方法实现条件分页查询
        IPage<Role> pageModel = baseMapper.selectPage(pageParam, wrapper);
        return pageModel;
    }
}
