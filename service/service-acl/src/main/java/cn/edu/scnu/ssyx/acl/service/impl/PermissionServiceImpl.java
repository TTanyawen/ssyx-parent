package cn.edu.scnu.ssyx.acl.service.impl;
import cn.edu.scnu.ssyx.acl.mapper.PermissionMapper;
import cn.edu.scnu.ssyx.acl.service.PermissionService;
import cn.edu.scnu.ssyx.acl.utils.PermissionHelper;
import cn.edu.scnu.ssyx.model.acl.Permission;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * 权限服务实现类
 */
@Service
public class PermissionServiceImpl extends ServiceImpl<PermissionMapper, Permission>
        implements PermissionService {

    //获取所有菜单
    @Override
    public List<Permission> queryAllMenu() {
        //获取全部权限数据
        List<Permission> allPermissionList = baseMapper.selectList(new QueryWrapper<Permission>().orderByAsc("CAST(id AS SIGNED)"));

        //把权限数据构建成树形结构数据
        List<Permission> result = PermissionHelper.bulid(allPermissionList);
        return result;
    }

    //递归删除菜单
    @Override
    public void removeChildById(Long id) {
        List<Long> idList = new ArrayList<>();
        this.selectChildListById(id, idList);
        idList.add(id);
        baseMapper.deleteBatchIds(idList);
    }

    /**
     *  递归获取子节点
     * @param id
     * @param idList
     */
    private void selectChildListById(Long id, List<Long> idList) {
        List<Permission> childList = baseMapper.selectList(new QueryWrapper<Permission>().eq("pid", id).select("id"));
        childList.stream().forEach(item -> {
            idList.add(item.getId());
            this.selectChildListById(item.getId(), idList);
        });
    }
}