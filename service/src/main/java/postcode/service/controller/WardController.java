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

import postcode.documents.Ward;
import postcode.service.PostcodeService;

@RestController
@RequestMapping("/v1/ward")
public class WardController {

	private static final Logger LOG = LogManager.getLogger(WardController.class);

	@Autowired
	private PostcodeService postcodeService;

	@GetMapping(path = "/{wd22cd}", produces = APPLICATION_JSON_VALUE)
	public ResponseEntity<Ward> ward(@PathVariable String wd22cd) {
		return ResponseEntity.of(postcodeService.getWard(wd22cd));
	}
}
