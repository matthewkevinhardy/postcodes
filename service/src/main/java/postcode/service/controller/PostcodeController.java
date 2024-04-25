package postcode.service.controller;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import postcode.documents.Postcode;
import postcode.service.PostcodeService;
import postcode.service.model.PostcodeDTO;
import postcode.service.model.PostcodeWardDTO;

@RestController
@RequestMapping("/v1/postcode")
public class PostcodeController {

	private static final Logger LOG = LogManager.getLogger(PostcodeController.class);

	@Autowired
	private PostcodeService postcodeService;

	@GetMapping(path = "/{pcd}", produces = APPLICATION_JSON_VALUE)
	public ResponseEntity<Postcode> byPcd(@PathVariable String pcd) {
		return ResponseEntity.of(postcodeService.getPostcodeByPcd(pcd));
	}

	@GetMapping(produces = APPLICATION_JSON_VALUE)
	public Page<Postcode> postcodeByWard(@RequestParam(name = "ward") String wd22cd, Pageable pageable) {
		return postcodeService.getPostcodeByWard(wd22cd, pageable);
	}

	@GetMapping(path = "/postcodeward/{pcd}", produces = APPLICATION_JSON_VALUE)
	public ResponseEntity<PostcodeWardDTO> postcodeWard(@PathVariable String pcd) {
		return ResponseEntity.ofNullable(postcodeService.postcodeWard(pcd));
	}

	@GetMapping(path = "/dto/{pcd}", produces = APPLICATION_JSON_VALUE)
	public ResponseEntity<PostcodeDTO> postcodeDTO(@PathVariable String pcd) {
		PostcodeDTO postcodeWard = postcodeService.getPostcodeDTO(pcd.toLowerCase().replaceAll(" ", ""));
		return ResponseEntity.ofNullable(postcodeWard);
	}
}
