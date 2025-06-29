package cn.edu.scnu.ssyx.product.api;
import cn.edu.scnu.ssyx.model.product.Category;
import cn.edu.scnu.ssyx.model.product.SkuInfo;
import cn.edu.scnu.ssyx.product.service.CategoryService;
import cn.edu.scnu.ssyx.product.service.SkuInfoService;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
@RestController
@RequestMapping("/api/product")
@SuppressWarnings({"unchecked", "rawtypes"})//抑制警告类型
//通过指定警告类型（如unchecked、rawtypes、deprecation等），告诉编译器忽略相关警告
public class ProductInnnerController {
    @Resource
    private CategoryService categoryService;
    @Resource
    private SkuInfoService skuInfoService;
    //根据分类id获取分类信息
    @ApiOperation(value = "根据分类id获取分类信息")
    @GetMapping("inner/getCategory/{categoryId}")
    public Category getCategory(@PathVariable Long categoryId) {
        Category category = categoryService.getById(categoryId);
        return category;
    }
    //根据skuid获取sku信息
    @ApiOperation(value = "根据skuId获取sku信息")
    @GetMapping("inner/getSkuInfo/{skuId}")
    public SkuInfo getSkuInfo(@PathVariable Long skuId) {
        return skuInfoService.getById(skuId);
    }

    @ApiOperation(value = "批量获取sku信息")
    @PostMapping("inner/findSkuInfoList")
    public List<SkuInfo> findSkuInfoList(@RequestBody List<Long> skuIdList) {
        return skuInfoService.findSkuInfoList(skuIdList);
    }
    //根据关键字匹配sku列表
    @GetMapping("inner/findSkuInfoByKeyword/{keyword}")
    public List<SkuInfo> findSkuInfoByKeyword(@PathVariable("keyword") String keyword) {
        return skuInfoService.findSkuInfoByKeyword(keyword);
    }

}