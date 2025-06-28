package cn.edu.scnu.ssyx.product.controller;
import cn.edu.scnu.ssyx.product.service.SkuInfoService;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;

//sku信息 前端控制器
@RestController
@RequestMapping("/admin/product/skuInfo")
//@CrossOrigin
public class SkuInfoController {
    @Resource
    private SkuInfoService skuInfoService;
}