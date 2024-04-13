package postcode.service.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import postcode.documents.Postcode;
import postcode.documents.Ward;
import postcode.service.PostcodeService;
import postcode.service.model.PostcodeWard;

@RestController
public class PostcodeController {

	@Autowired
	private PostcodeService postcodeService;

	@GetMapping(path = "/postcode/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Postcode> byId(@PathVariable String id) {
		Optional<Postcode> postcode = postcodeService.getPostcode(id);
		return ResponseEntity.of(postcode);
	}

	@GetMapping(path = "/postcode/pcd/{pcd}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Postcode> byPcd(@PathVariable String pcd) {
		return ResponseEntity.of(postcodeService.getPostcodeByPcd(pcd));
	}

	@GetMapping(path = "/postcode/ward/{wd22cd}", produces = MediaType.APPLICATION_JSON_VALUE)
	public Page<Postcode> postcodeByWard(@PathVariable String wd22cd, Pageable pageable) {
		return postcodeService.getPostcodeByWard(wd22cd, pageable);
	}

	@GetMapping(path = "/ward/{wd22cd}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Ward> ward(@PathVariable String wd22cd) {
		return ResponseEntity.of(postcodeService.getWard(wd22cd));
	}

	@GetMapping(path = "/postcodeward/{pcd}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<PostcodeWard> postcodeWard(@PathVariable String pcd) {
		PostcodeWard postcodeWard = postcodeService.postcodeWard(pcd);
		return ResponseEntity.ofNullable(postcodeWard);
	}
}
