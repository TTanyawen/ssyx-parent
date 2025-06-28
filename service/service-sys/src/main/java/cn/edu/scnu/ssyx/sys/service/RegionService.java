package cn.edu.scnu.ssyx.sys.service;
import cn.edu.scnu.ssyx.model.sys.Region;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

public interface RegionService  extends IService<Region> {

    //根据关键字获取地区列表
    List<Region> findRegionByKeyword(String keyword);
}