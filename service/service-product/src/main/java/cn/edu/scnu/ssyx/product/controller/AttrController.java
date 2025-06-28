package cn.edu.scnu.ssyx.product.controller;
import cn.edu.scnu.ssyx.product.service.AttrService;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;

//商品属性 前端控制器
@RestController
@RequestMapping("/admin/product/attr")
@CrossOrigin //跨域
public class AttrController {
    @Resource
    private AttrService attrService;
}