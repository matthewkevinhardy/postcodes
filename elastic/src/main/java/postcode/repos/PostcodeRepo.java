package postcode.repos;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import postcode.documents.Postcode;

public interface PostcodeRepo extends ElasticsearchRepository<Postcode, String> {
	public Optional<Postcode> findByPcd(String pcd);

	public List<Postcode> findByOsward(String osward, Pageable pageable);
}
