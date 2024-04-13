package postcode.repos;

import java.util.Optional;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import postcode.documents.Parish;

public interface ParishRepo extends ElasticsearchRepository<Parish, String> {
	public Optional<Parish> findByPARNCP21CD(String pcd);
}
