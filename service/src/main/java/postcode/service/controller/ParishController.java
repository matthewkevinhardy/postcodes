package postcode.service.controller;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import postcode.documents.Parish;
import postcode.service.PostcodeService;

@RestController
@RequestMapping("/v1/parish")
public class ParishController {

	private static final Logger LOG = LogManager.getLogger(ParishController.class);

	@Autowired
	private PostcodeService postcodeService;

	@GetMapping(path = "/{pARNCP21CD}", produces = APPLICATION_JSON_VALUE)
	public ResponseEntity<Parish> parish(@PathVariable String pARNCP21CD) {
		return ResponseEntity.of(postcodeService.getParish(pARNCP21CD));
	}
}
