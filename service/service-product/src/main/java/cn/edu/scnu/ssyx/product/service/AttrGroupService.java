package cn.edu.scnu.ssyx.product.service;
import cn.edu.scnu.ssyx.model.product.AttrGroup;
import cn.edu.scnu.ssyx.vo.product.AttrGroupQueryVo;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import java.util.List;
//属性分组 服务类
public interface AttrGroupService extends IService<AttrGroup> {
    //平台属性分组列表
    IPage<AttrGroup> selectPage(Page<AttrGroup> pageParam, AttrGroupQueryVo attrGroupQueryVo);
    //查询所有属性分组
    List<AttrGroup> findAllList();
}