package cn.edu.scnu.ssyx.acl.service;
import cn.edu.scnu.ssyx.model.acl.Permission;
import com.baomidou.mybatisplus.extension.service.IService;
import java.util.List;
/**
 * 权限服务接口
 */
public interface PermissionService extends IService<Permission> {
    List<Permission> queryAllMenu();
    void removeChildById(Long id);
}