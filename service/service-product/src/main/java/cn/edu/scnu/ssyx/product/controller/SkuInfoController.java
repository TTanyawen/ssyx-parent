package cn.edu.scnu.ssyx.product.controller;
import cn.edu.scnu.ssyx.common.result.Result;
import cn.edu.scnu.ssyx.model.product.SkuInfo;
import cn.edu.scnu.ssyx.product.service.SkuInfoService;
import cn.edu.scnu.ssyx.vo.product.SkuInfoQueryVo;
import cn.edu.scnu.ssyx.vo.product.SkuInfoVo;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import java.util.List;
// sku信息 前端控制器
@Api(value = "SkuInfo管理", tags = "商品Sku管理")
@RestController
@RequestMapping("/admin/product/skuInfo")
@CrossOrigin
public class SkuInfoController {
    @Resource
    private SkuInfoService skuInfoService;

    @ApiOperation(value = "获取sku分页列表")
    @GetMapping("{page}/{limit}")
    public Result<IPage<SkuInfo>> index(
            @ApiParam(name = "page", value = "当前页码", required = true)
            @PathVariable Long page,
            @ApiParam(name = "limit", value = "每页记录数", required = true)
            @PathVariable Long limit,
            @ApiParam(name = "skuInfoQueryVo", value = "查询对象", required = false)
            SkuInfoQueryVo skuInfoQueryVo) {
        Page<SkuInfo> pageParam = new Page<>(page, limit);
        IPage<SkuInfo> pageModel = skuInfoService.selectPage(pageParam, skuInfoQueryVo);
        return Result.ok(pageModel);
    }


    //添加商品sku信息
// url: `${api_name}/save`,
// method: 'post',
//    data: role
    @ApiOperation("添加商品sku信息")
    @PostMapping("save")
    public Result save(@RequestBody SkuInfoVo skuInfoVo) {
        skuInfoService.saveSkuInfo(skuInfoVo);
        return Result.ok(null);
    }


    // url: `${api_name}/get/${id}`,
// method: 'get'
    @ApiOperation("获取sku信息")
    @GetMapping("get/{id}")
    public Result<SkuInfoVo> get(@PathVariable Long id) {
        SkuInfoVo skuInfoVo = skuInfoService.getSkuInfoVo(id);
        return Result.ok(skuInfoVo);
    }

    // url: `${api_name}/update`,
    // method: 'put',
// data: role
    @ApiOperation("修改sku")
    @PutMapping("update")
    public Result update(@RequestBody SkuInfoVo skuInfoVo) {
        skuInfoService.updateSkuInfo(skuInfoVo);
        return Result.ok(null);
    }

    // url: `${api_name}/remove/${id}`,
// method: 'delete',
// data: null
    @ApiOperation("删除sku")
    @DeleteMapping("remove/{id}")
    public Result remove(@PathVariable Long id) {
        skuInfoService.removeById(id);
        return Result.ok(null);
    }

    // url: `${api_name}/batchRemove`,
// method: 'delete',
// data: null
    @ApiOperation("根据id列表删除")
    @DeleteMapping("batchRemove")
    public Result batchRemove(@RequestBody List<Long> idList) {
        skuInfoService.removeByIds(idList);
        return Result.ok(null);
    }

    //  url: `${api_name}/check/${id}/${status}`,
//  method: 'get'
    @ApiOperation("商品审核")
    @GetMapping("check/{skuId}/{status}")
    public Result check(@PathVariable Long skuId,
                        @PathVariable Integer status) {
        skuInfoService.check(skuId,status);
        return Result.ok(null);
    }


    //  url: `${api_name}/publish/${id}/${status}`,
//  method: 'get'
    @ApiOperation("商品上下架")
    @GetMapping("publish/{skuId}/{status}")
    public Result publish(@PathVariable Long skuId,
                          @PathVariable Integer status) {
        skuInfoService.publish(skuId,status);
        return Result.ok(null);
    }
    //新人专享
//  url: `${api_name}/isNewPerson/${id}/${status}`,
//  method: 'get'
    @GetMapping("isNewPerson/{skuId}/{status}")
    public Result isNewPerson(@PathVariable("skuId") Long skuId,
                              @PathVariable("status") Integer status) {
        skuInfoService.isNewPerson(skuId, status);
        return Result.ok(null);
    }
}