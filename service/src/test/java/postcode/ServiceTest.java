package postcode;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Optional;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.elasticsearch.client.elc.ElasticsearchTemplate;
import org.testcontainers.elasticsearch.ElasticsearchContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import postcode.documents.Ward;
import postcode.repos.WardRepo;

@SpringBootTest
@Testcontainers
public class ServiceTest {

	@Autowired
	private ElasticsearchTemplate elasticsearchTemplate;

	@Autowired
	private WardRepo wardRepo;

	@Container
	public static ElasticsearchContainer elasticsearchContainer = new PostcodeElasticsearchContainer();

	@BeforeAll
	static void setUp() {
		elasticsearchContainer.start();
	}

	@BeforeEach
	void testIsContainerRunning() {
		assertTrue(elasticsearchContainer.isRunning());

		Ward ward = new Ward();
		ward.setWd22cd("WardId");
		ward.setWd22nm("WardName");
		wardRepo.save(ward);
	}

	@AfterAll
	static void destroy() {
		elasticsearchContainer.stop();
	}

	@Test
	void testCRUD() {
		Optional<Ward> ward = wardRepo.findById("WardId");
		assertTrue(ward.isPresent());
	}
}
