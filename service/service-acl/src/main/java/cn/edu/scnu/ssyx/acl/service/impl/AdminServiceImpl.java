package cn.edu.scnu.ssyx.acl.service.impl;
import cn.edu.scnu.ssyx.acl.mapper.AdminMapper;
import cn.edu.scnu.ssyx.acl.service.AdminService;
import cn.edu.scnu.ssyx.acl.service.RoleService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import cn.edu.scnu.ssyx.model.acl.Admin;
import cn.edu.scnu.ssyx.vo.acl.AdminQueryVo;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import javax.annotation.Resource;
/**
 * 用户角色服务实现类
 */
@Service
public class AdminServiceImpl extends ServiceImpl<AdminMapper,Admin> implements AdminService {
    @Resource
    private AdminMapper userMapper;
    @Resource
    private RoleService roleService;
    @Override
    public IPage<Admin> selectPage(Page<Admin> pageParam, AdminQueryVo userQueryVo) {
        //获取用户名称条件值
        String name = userQueryVo.getName();
        //创建条件构造器
        LambdaQueryWrapper<Admin> wrapper = new LambdaQueryWrapper<>();
        if(!StringUtils.isEmpty(name)) {
            //封装条件
            wrapper.like(Admin::getName,name);
        }
        //调用mapper方法
        IPage<Admin> pageModel = baseMapper.selectPage(pageParam,wrapper);
        return pageModel;
    }
}