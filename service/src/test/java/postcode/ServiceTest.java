package postcode;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Optional;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.testcontainers.elasticsearch.ElasticsearchContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import postcode.documents.Ward;
import postcode.repos.WardRepo;

@SpringBootTest(webEnvironment = WebEnvironment.DEFINED_PORT)
@Testcontainers
public class ServiceTest {

	// @Autowired
	// private ElasticsearchTemplate elasticsearchTemplate;

	@LocalServerPort
	private int port;

	@Autowired
	private WardRepo wardRepo;

	@Autowired
	private TestRestTemplate restTemplate;

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

	@Test
	void wardEndpointTest() throws Exception {
		Ward testWard = this.restTemplate.getForObject("http://localhost:" + port + "/ward/WardId", Ward.class);
		assertThat(testWard.getWd22cd(), equalTo("WardId"));
		assertThat(testWard.getWd22nm(), equalTo("WardName"));
	}
}
