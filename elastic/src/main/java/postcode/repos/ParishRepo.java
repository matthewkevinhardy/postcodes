package postcode.repos;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.annotations.Query;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import postcode.documents.Parish;

public interface ParishRepo extends ElasticsearchRepository<Parish, String> {

	@Query("{\"match\": {\"PARNCP21CD\": {\"query\":\"?0\", \"fuzziness\": \"0\"} }}")
	public Optional<Parish> findByParncp21cd(String parncp21cd);

	public Page<Parish> findByParncp21nm(String parncp21nm, Pageable pageable);
}
