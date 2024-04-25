package postcode.repos;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.annotations.Query;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import postcode.documents.Ward;

public interface WardRepo extends ElasticsearchRepository<Ward, String> {

	@Query("{\"match\": {\"WD22CD\": {\"query\":\"?0\", \"fuzziness\": \"0\"} }}")
	public Optional<Ward> findByWd22cd(String wd22cd);

	public Page<Ward> findByWd22nm(String wd22nm, Pageable pageable);
}
