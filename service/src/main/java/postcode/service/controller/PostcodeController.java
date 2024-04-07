package postcode.service.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import postcode.documents.Postcode;
import postcode.documents.Ward;
import postcode.repos.PostcodeRepo;
import postcode.repos.WardRepo;
import postcode.service.model.PostcodeWard;

@RestController
public class PostcodeController {

	@Autowired
	private PostcodeRepo postcodeRepo;

	@Autowired
	private WardRepo wardRepo;

	@GetMapping(path = "/postcode/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Postcode> byId(@PathVariable String id) {
		Optional<Postcode> postcode = postcodeRepo.findById(id);
		return ResponseEntity.of(postcode);
	}

	@GetMapping(path = "/postcode/pcd/{pcd}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Postcode> byPcd(@PathVariable String pcd) {
		return ResponseEntity.of(postcodeRepo.findByPcd(pcd));
	}

	@GetMapping(path = "/postcode/ward/{wd22cd}", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Postcode> postcodeByWard(@PathVariable String wd22cd, Pageable pageable) {
		return postcodeRepo.findByOsward(wd22cd, pageable);
	}

	@GetMapping(path = "/ward/{wd22cd}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Ward> ward(@PathVariable String wd22cd) {
		return ResponseEntity.of(wardRepo.findByWd22cd(wd22cd));
	}

	@GetMapping(path = "/postcodeward/{pcd}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<PostcodeWard> postcodeWard(@PathVariable String pcd) {
		ResponseEntity<PostcodeWard> response;

		Optional<Postcode> pc = postcodeRepo.findByPcd(pcd);
		if (pc.isPresent()) {
			Optional<Ward> w = wardRepo.findByWd22cd(pc.get().getOsward());
			response = ResponseEntity.ok(new PostcodeWard(pc.get(), w.get()));
		} else {
			response = new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

		return response;
	}
}
