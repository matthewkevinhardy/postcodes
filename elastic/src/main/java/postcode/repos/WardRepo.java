package postcode.repos;

import java.util.Optional;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import postcode.documents.Ward;

public interface WardRepo extends ElasticsearchRepository<Ward, String> {
	public Optional<Ward> findByWd22cd(String wd22cd);
}
