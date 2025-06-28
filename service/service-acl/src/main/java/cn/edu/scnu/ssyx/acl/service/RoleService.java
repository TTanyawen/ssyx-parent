package cn.edu.scnu.ssyx.acl.service;
import cn.edu.scnu.ssyx.model.acl.Role;
import cn.edu.scnu.ssyx.vo.acl.RoleQueryVo;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
public interface RoleService  extends IService<Role> {
    IPage<Role> selectPage(Page<Role> pageParam, RoleQueryVo roleQueryVo);
}