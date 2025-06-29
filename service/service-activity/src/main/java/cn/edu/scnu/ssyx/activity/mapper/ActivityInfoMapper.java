package cn.edu.scnu.ssyx.activity.mapper;
import cn.edu.scnu.ssyx.model.activity.ActivityInfo;
import cn.edu.scnu.ssyx.model.activity.ActivityRule;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PathVariable;
import java.util.List;

//活动表 Mapper 接口
@Mapper
public interface ActivityInfoMapper extends BaseMapper<ActivityInfo> {

    //根据skuId进行查询，查询sku对应活动里面规则列表
    List<ActivityRule> selectActivityRuleList(@Param("skuId") Long skuId);
    // 如果之前参加过，活动正在进行中，排除商品
    List<Long> selectSkuIdListExist(@Param("skuIdList") List<Long> skuIdList);
}