package postcode;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.testcontainers.junit.jupiter.Testcontainers;

import postcode.documents.Ward;
import postcode.repos.ParishRepo;
import postcode.repos.PostcodeRepo;
import postcode.repos.WardRepo;

@SpringBootTest(webEnvironment = WebEnvironment.DEFINED_PORT)
@Testcontainers
public class ServiceTest {

	@LocalServerPort
	private int port;

	@Autowired
	private TestRestTemplate restTemplate;

	@MockBean
	WardRepo wardRepo;

	@MockBean
	PostcodeRepo postcodeRepo;

	@MockBean
	ParishRepo parishRepo;

	@BeforeEach
	void testIsContainerRunning() {
		Ward ward = new Ward();
		ward.setWd22cd("WardId");
		ward.setWd22nm("WardName");

		Mockito.when(wardRepo.findById("WardId")).thenReturn(Optional.of(ward));
		Mockito.when(wardRepo.findByWd22cd("WardId")).thenReturn(Optional.of(ward));
	}

	@Test
	void testCRUD() {
		Optional<Ward> ward = wardRepo.findById("WardId");
		assertTrue(ward.isPresent());
	}

	@Test
	void wardEndpointTest() {
		Ward testWard = this.restTemplate.getForObject("http://localhost:" + port + "/ward/WardId", Ward.class);
		assertEquals(testWard.getWd22cd(), "WardId");
		assertEquals(testWard.getWd22nm(), "WardName");
	}
}
