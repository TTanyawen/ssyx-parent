package cn.edu.scnu.ssyx.product.service;
import cn.edu.scnu.ssyx.model.product.Category;
import cn.edu.scnu.ssyx.vo.product.CategoryQueryVo;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import java.util.List;
//商品三级分类 服务类
public interface CategoryService extends IService<Category> {
    //商品分类分页列表
    IPage<Category> selectPage(Page<Category> pageParam, CategoryQueryVo categoryQueryVo);

    //查询所有商品分类
    List<Category> findAllList();
}