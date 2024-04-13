package postcode.repos;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import postcode.documents.Postcode;

public interface PostcodeRepo extends ElasticsearchRepository<Postcode, String> {
	public Optional<Postcode> findByPcd(String pcd);

	public Page<Postcode> findByOsward(String osward, Pageable pageable);
}
