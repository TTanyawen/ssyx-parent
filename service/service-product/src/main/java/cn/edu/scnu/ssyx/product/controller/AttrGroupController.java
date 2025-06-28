package cn.edu.scnu.ssyx.product.controller;
import cn.edu.scnu.ssyx.product.service.AttrGroupService;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;

//属性分组 前端控制器
@RestController
@RequestMapping("/admin/product/attrGroup")
@CrossOrigin
public class AttrGroupController {
    @Resource
    private AttrGroupService attrGroupService;
}