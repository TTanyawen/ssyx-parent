package cn.edu.scnu.ssyx.product.api;
import cn.edu.scnu.ssyx.model.product.Category;
import cn.edu.scnu.ssyx.model.product.SkuInfo;
import cn.edu.scnu.ssyx.product.service.CategoryService;
import cn.edu.scnu.ssyx.product.service.SkuInfoService;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.annotation.Resource;
@RestController
@RequestMapping("/api/product")
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
}