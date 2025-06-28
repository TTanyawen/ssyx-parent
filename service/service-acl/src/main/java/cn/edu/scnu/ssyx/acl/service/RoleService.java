package cn.edu.scnu.ssyx.acl.service;
import cn.edu.scnu.ssyx.model.acl.Role;
import cn.edu.scnu.ssyx.vo.acl.RoleQueryVo;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

public interface RoleService  extends IService<Role> {
    IPage<Role> selectPage(Page<Role> pageParam, RoleQueryVo roleQueryVo);


    Map<String, Object> findRoleByUserId(Long adminId);

    @Transactional(rollbackFor = Exception.class)
    void saveUserRoleRealtionShip(Long adminId, Long[] roleIds);
}