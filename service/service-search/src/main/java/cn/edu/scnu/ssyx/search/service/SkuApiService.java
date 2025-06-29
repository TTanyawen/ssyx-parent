package cn.edu.scnu.ssyx.search.service;
import cn.edu.scnu.ssyx.model.search.SkuEs;
import cn.edu.scnu.ssyx.vo.search.SkuEsQueryVo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.List;
public interface SkuApiService {
    /**
     * 上架
     * @param skuId
     */
    void upperSku(Long skuId);

    /**
     * 下架
     * @param skuId
     */
    void lowerSku(Long skuId);

    //获取爆款商品
    //查询分类商品
    //更新商品热度
}