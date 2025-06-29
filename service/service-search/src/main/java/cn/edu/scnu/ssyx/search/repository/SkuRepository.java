package cn.edu.scnu.ssyx.search.repository;
import cn.edu.scnu.ssyx.model.search.SkuEs;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
public interface SkuRepository  extends ElasticsearchRepository<SkuEs,Long> {

}