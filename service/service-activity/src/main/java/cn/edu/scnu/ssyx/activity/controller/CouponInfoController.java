package cn.edu.scnu.ssyx.activity.controller;
import cn.edu.scnu.ssyx.activity.service.CouponInfoService;
import cn.edu.scnu.ssyx.common.result.Result;
import cn.edu.scnu.ssyx.model.activity.CouponInfo;
import cn.edu.scnu.ssyx.vo.activity.CouponRuleVo;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import java.util.List;

// 优惠券信息 前端控制器
@RestController
@RequestMapping("/admin/activity/couponInfo")
//@CrossOrigin
public class CouponInfoController {
    @Resource
    private CouponInfoService couponInfoService;

    @ApiOperation(value = "获取优惠券分页列表")
    @GetMapping("{page}/{limit}")
    public Result index(
            @ApiParam(name = "page", value = "当前页码", required = true)
            @PathVariable Long page,
            @ApiParam(name = "limit", value = "每页记录数", required = true)
            @PathVariable Long limit
    ) {
        Page<CouponInfo> pageParam = new Page<>(page, limit);
        IPage<CouponInfo> pageModel = couponInfoService.selectPage(pageParam);
        return Result.ok(pageModel);
    }

    @ApiOperation(value = "根据id获取优惠券")
    @GetMapping("get/{id}")
    public Result get(@PathVariable String id) {
        CouponInfo couponInfo = couponInfoService.getCouponInfo(id);
        return Result.ok(couponInfo);
    }

    @ApiOperation(value = "新增优惠券")
    @PostMapping("save")
    public Result save(@RequestBody CouponInfo couponInfo) {
        couponInfoService.save(couponInfo);
        return Result.ok(null);
    }

    @ApiOperation(value = "修改优惠券")
    @PutMapping("update")
    public Result updateById(@RequestBody CouponInfo couponInfo) {
        couponInfoService.updateById(couponInfo);
        return Result.ok(null);
    }

    @ApiOperation(value = "删除优惠券")
    @DeleteMapping("remove/{id}")
    public Result remove(@PathVariable String id) {
        couponInfoService.removeById(id);
        return Result.ok(null);
    }

    @ApiOperation(value="根据id列表删除优惠券")
    @DeleteMapping("batchRemove")
    public Result batchRemove(@RequestBody List<String> idList){
        couponInfoService.removeByIds(idList);
        return Result.ok(null);
    }
    //根据优惠卷id获取优惠券规则列表
    @ApiOperation(value = "根据id获取优惠券规则列表")
    @GetMapping("findCouponRuleList/{id}")
    public Result findActivityRuleList(@PathVariable Long id) {
        return Result.ok(couponInfoService.findCouponRuleList(id));
    }

    @ApiOperation(value = "新增优惠券规则")
    @PostMapping("saveCouponRule")
    public Result saveCouponRule(@RequestBody CouponRuleVo couponRuleVo) {
        couponInfoService.saveCouponRule(couponRuleVo);
        return Result.ok(null);
    }

    /**
     * 根据关键字获取sku列表，活动使用
     * @param keyword
     * @return
     */
    @GetMapping("findCouponByKeyword/{keyword}")
    public Result findCouponByKeyword(@PathVariable("keyword") String keyword) {
        return Result.ok(couponInfoService.findCouponByKeyword(keyword));
    }
}