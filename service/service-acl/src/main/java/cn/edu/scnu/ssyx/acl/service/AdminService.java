package cn.edu.scnu.ssyx.acl.service;
import cn.edu.scnu.ssyx.model.acl.Admin;
import cn.edu.scnu.ssyx.vo.acl.AdminQueryVo;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
/**
 * 用户服务接口
 */
public interface AdminService extends IService<Admin> {
    /**
     * 用户分页列表
     * @param pageParam
     * @param userQueryVo
     * @return
     */
    IPage<Admin> selectPage(Page<Admin> pageParam, AdminQueryVo userQueryVo);
}