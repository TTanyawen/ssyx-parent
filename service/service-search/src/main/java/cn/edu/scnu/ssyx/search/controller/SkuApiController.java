package cn.edu.scnu.ssyx.search.controller;
import cn.edu.scnu.ssyx.common.result.Result;
import cn.edu.scnu.ssyx.search.service.SkuApiService;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.annotation.Resource;

// 商品搜索列表接口
@RestController
@RequestMapping("api/search/sku")
public class SkuApiController {
    @Resource
    private SkuApiService skApiService;
    @ApiOperation(value = "上架商品")
    @GetMapping("inner/upperSku/{skuId}")
    public Result upperGoods(@PathVariable("skuId") Long skuId) {
        skApiService.upperSku(skuId);
        return Result.ok(null);
    }

    @ApiOperation(value = "下架商品")
    @GetMapping("inner/lowerSku/{skuId}")
    public Result lowerGoods(@PathVariable("skuId") Long skuId) {
        skApiService.lowerSku(skuId);
        return Result.ok(null);
    }

}