package postcode.repos;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.annotations.Query;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import postcode.documents.LaUaName;

public interface LaUaRepo extends ElasticsearchRepository<LaUaName, String> {

	@Query("{\"match\": {\"LAD23CD\": {\"query\":\"?0\", \"fuzziness\": \"0\"} }}")
	public Optional<LaUaName> findByLAD23CD(String LAD23CD);

	public Page<LaUaName> findByLAD23NM(String LAD23NM, Pageable pageable);
}
