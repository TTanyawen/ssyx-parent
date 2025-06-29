package cn.edu.scnu.ssyx.activity.service;
import cn.edu.scnu.ssyx.model.activity.ActivityInfo;
import cn.edu.scnu.ssyx.model.activity.ActivityRule;
import cn.edu.scnu.ssyx.model.product.SkuInfo;
import cn.edu.scnu.ssyx.vo.activity.ActivityRuleVo;
import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import java.util.List;
import java.util.Map;
// 活动表 服务类
public interface ActivityInfoService  extends IService<ActivityInfo> {
    /**
     * 分页查询
     * @param pageParam
     * @return
     */
    IPage<ActivityInfo> selectPage(Page<ActivityInfo> pageParam);

    /**
     * 获取活动规则id
     * @param activityId
     * @return
     */
    Map<String, Object> findActivityRuleList(Long activityId);

    //保存活动规则信息
    void saveActivityRule(ActivityRuleVo activityRuleVo);

    //根据关键字获取sku信息列表
    List<SkuInfo> findSkuInfoByKeyword(String keyword);

    /**
     * 根据skuId获取促销规则信息
     * @param skuId
     * @return
     */
    List<ActivityRule> findActivityRule(Long skuId);
}