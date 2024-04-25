package postcode.repos;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.annotations.Query;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import postcode.documents.Postcode;

public interface PostcodeRepo extends ElasticsearchRepository<Postcode, String> {

	@Query("{\"match\": {\"pcd\": {\"query\":\"?0\", \"fuzziness\": \"0\"} }}")
	public Optional<Postcode> findByPcd(String pcd);

	@Query("{\"match\": {\"osward\": {\"query\":\"?0\", \"fuzziness\": \"0\"} }}")
	public Page<Postcode> findByOsward(String osward, Pageable pageable);
}
