package cn.edu.scnu.ssyx.model.order;

import cn.edu.scnu.ssyx.model.base.BaseEntity;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import java.util.Date;

@Data
@ApiModel(description = "RefundInfo")
@TableName("refund_info")
public class RefundInfo extends BaseEntity {

	private static final long serialVersionUID = 1L;

	@ApiModelProperty(value = "对外业务编号")
	@TableField("out_trade_no")
	private String outTradeNo;

	@ApiModelProperty(value = "订单编号")
	@TableField("order_id")
	private Long orderId;

	@ApiModelProperty(value = "skuId")
	@TableField("sku_id")
	private Long skuId;

	@ApiModelProperty(value = "支付类型（微信 支付宝）")
	@TableField("payment_type")
	private String paymentType;

	@ApiModelProperty(value = "交易编号")
	@TableField("trade_no")
	private String tradeNo;

	@ApiModelProperty(value = "退款金额")
	@TableField("total_amount")
	private String totalAmount;

	@ApiModelProperty(value = "交易内容")
	@TableField("subject")
	private String subject;

	@ApiModelProperty(value = "退款状态")
	@TableField("refund_status")
	private String refundStatus;

	@ApiModelProperty(value = "回调时间")
	@TableField("callback_time")
	private Date callbackTime;

	@ApiModelProperty(value = "回调信息")
	@TableField("callback_content")
	private String callbackContent;

}