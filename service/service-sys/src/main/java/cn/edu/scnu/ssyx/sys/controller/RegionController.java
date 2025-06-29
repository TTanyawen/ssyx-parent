package cn.edu.scnu.ssyx.sys.controller;

import cn.edu.scnu.ssyx.common.result.Result;
import cn.edu.scnu.ssyx.sys.service.RegionService;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import java.util.List;
/**
 * 地区表 前端控制器
 */
@RestController
@RequestMapping("/admin/sys/region")
//@CrossOrigin
public class RegionController {
    @Resource
    private RegionService regionService;

    @ApiOperation(value = "根据关键字获取地区列表")
    @GetMapping("findRegionByKeyword/{keyword}")
    public Result findSkuInfoByKeyword(@PathVariable("keyword") String keyword) {
        return Result.ok(regionService.findRegionByKeyword(keyword));
    }
}