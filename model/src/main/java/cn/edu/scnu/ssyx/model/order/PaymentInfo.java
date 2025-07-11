package cn.edu.scnu.ssyx.model.order;

import cn.edu.scnu.ssyx.enums.PaymentStatus;
import cn.edu.scnu.ssyx.enums.PaymentType;
import cn.edu.scnu.ssyx.model.base.BaseEntity;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
@ApiModel(description = "PaymentInfo")
@TableName("payment_info")
public class PaymentInfo extends BaseEntity {

	private static final long serialVersionUID = 1L;

	@ApiModelProperty(value = "对外业务编号")
	@TableField("order_no")
	private String orderNo;

	@ApiModelProperty(value = "订单编号")
	@TableField("order_id")
	private Long orderId;

	@ApiModelProperty(value = "用户id")
	@TableField("user_id")
	private Long userId;

	@ApiModelProperty(value = "支付类型（微信 支付宝）")
	@TableField("payment_type")
	private PaymentType paymentType;

	@ApiModelProperty(value = "交易编号")
	@TableField("trade_no")
	private String tradeNo;

	@ApiModelProperty(value = "支付金额")
	@TableField("total_amount")
	private BigDecimal totalAmount;

	@ApiModelProperty(value = "交易内容")
	@TableField("subject")
	private String subject;

	@ApiModelProperty(value = "支付状态")
	@TableField("payment_status")
	private PaymentStatus paymentStatus;

	@ApiModelProperty(value = "回调时间")
	@TableField("callback_time")
	private Date callbackTime;

	@ApiModelProperty(value = "回调信息")
	@TableField("callback_content")
	private String callbackContent;

}