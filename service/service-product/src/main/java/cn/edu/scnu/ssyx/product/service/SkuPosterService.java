package cn.edu.scnu.ssyx.product.service;
import cn.edu.scnu.ssyx.model.product.SkuPoster;
import com.baomidou.mybatisplus.extension.service.IService;
import java.util.List;
//商品海报表 服务类
public interface SkuPosterService extends IService<SkuPoster> {
    //根据id查询商品海报列表
    List<SkuPoster> getPosterListBySkuId(Long id);
}