package cn.edu.scnu.ssyx.sys.controller;
import cn.edu.scnu.ssyx.common.result.Result;
import cn.edu.scnu.ssyx.sys.service.WareService;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.annotation.Resource;
import java.util.List;
/**
 * 仓库表 前端控制器
 */
@RestController
@RequestMapping("/admin/sys/ware")
@CrossOrigin
public class WareController {
    @Resource
    private WareService wareService;

    @ApiOperation(value = "获取全部仓库")
    @GetMapping("findAllList")
    public Result findAllList() {
        return Result.ok(wareService.list());
    }
}