package cn.edu.scnu.ssyx.activity.mapper;
import cn.edu.scnu.ssyx.model.activity.CouponInfo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.web.bind.annotation.PathVariable;
import java.util.List;
//优惠券信息 Mapper 接口
@Mapper
public interface CouponInfoMapper  extends BaseMapper<CouponInfo> {

}