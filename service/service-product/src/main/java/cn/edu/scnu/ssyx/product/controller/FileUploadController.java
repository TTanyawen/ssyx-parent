package cn.edu.scnu.ssyx.product.controller;

import cn.edu.scnu.ssyx.common.result.Result;
import cn.edu.scnu.ssyx.product.service.FileUploadService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import javax.annotation.Resource;

@Api(tags = "文件上传接口")
@RestController
@RequestMapping("admin/product")
//@CrossOrigin
public class FileUploadController {
    @Resource
    private FileUploadService fileUploadService;

    //图片上传的方法
    @ApiOperation("图片上传")
    @PostMapping("fileUpload")
    public Result fileUpload(MultipartFile file) {
        String url = fileUploadService.uploadFile(file);
        return Result.ok(url);
    }
}