package cn.edu.scnu.ssyx.activity.mapper;
import cn.edu.scnu.ssyx.model.activity.ActivityRule;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

// 优惠规则 Mapper 接口
@Mapper
public interface ActivityRuleMapper extends BaseMapper<ActivityRule> {
}