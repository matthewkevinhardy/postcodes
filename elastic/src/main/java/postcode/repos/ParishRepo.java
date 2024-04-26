package postcode.repos;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.annotations.Query;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import postcode.documents.Parish;

public interface ParishRepo extends ElasticsearchRepository<Parish, String> {

	@Query("{\"match\": {\"PARNCP21CD\": {\"query\":\"?0\", \"fuzziness\": \"0\"} }}")
	public Optional<Parish> findByPARNCP21CD(String pARNCP21CD);

	public Page<Parish> findByPARNCP21NM(String PARNCP21NM, Pageable pageable);
}
