package cn.edu.scnu.ssyx.product.service;
import org.springframework.web.multipart.MultipartFile;
public interface FileUploadService {
    String uploadFile(MultipartFile file);
}