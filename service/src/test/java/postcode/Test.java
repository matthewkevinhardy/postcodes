package postcode;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.elasticsearch.client.elc.ElasticsearchTemplate;
import org.testcontainers.elasticsearch.ElasticsearchContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

@SpringBootTest
@Testcontainers
public class Test {

	@Autowired
	private ElasticsearchTemplate elasticsearchTemplate;

	@Container
	public static ElasticsearchContainer elasticsearchContainer = new PostcodeElasticsearchContainer();

	@BeforeAll
	static void setUp() {
		elasticsearchContainer.start();
	}

	@BeforeEach
	void testIsContainerRunning() {
		assertTrue(elasticsearchContainer.isRunning());
		// recreateIndex();
	}

	@AfterAll
	static void destroy() {
		elasticsearchContainer.stop();
	}

	@org.junit.jupiter.api.Test
	void test() {

	}
}
