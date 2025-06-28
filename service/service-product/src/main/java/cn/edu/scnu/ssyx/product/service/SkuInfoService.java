package cn.edu.scnu.ssyx.product.service;
import cn.edu.scnu.ssyx.model.product.SkuInfo;
import cn.edu.scnu.ssyx.vo.product.SkuInfoQueryVo;
import cn.edu.scnu.ssyx.vo.product.SkuInfoVo;
import cn.edu.scnu.ssyx.vo.product.SkuStockLockVo;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import java.util.List;
// sku信息 服务类
public interface SkuInfoService extends IService<SkuInfo> {
    //获取sku分页列表
    IPage<SkuInfo> selectPage(Page<SkuInfo> pageParam, SkuInfoQueryVo skuInfoQueryVo);
    //添加商品
    void saveSkuInfo(SkuInfoVo skuInfoVo);

    SkuInfoVo getSkuInfoVo(Long id);

    //修改sku
    void updateSkuInfo(SkuInfoVo skuInfoVo);
    //商品审核
    void check(Long skuId, Integer status);

    //商品上下架
    void publish(Long skuId, Integer status);
    //新人专享
    void isNewPerson(Long skuId, Integer status);
}