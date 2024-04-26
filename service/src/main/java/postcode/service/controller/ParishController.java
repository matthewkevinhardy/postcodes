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

import postcode.documents.Parish;
import postcode.service.ParishService;

@RestController
@RequestMapping("/v1/parish")
public class ParishController {

	private static final Logger LOG = LogManager.getLogger(ParishController.class);

	@Autowired
	private ParishService parishService;

	@GetMapping(path = "/{pARNCP21CD}", produces = APPLICATION_JSON_VALUE)
	public ResponseEntity<Parish> parish(@PathVariable String pARNCP21CD) {
		return ResponseEntity.of(parishService.getParish(pARNCP21CD));
	}

	@GetMapping(produces = APPLICATION_JSON_VALUE)
	public Page<Parish> byName(@RequestParam(name = "name") String PARNCP21NM, Pageable pageable) {
		return parishService.getParishByName(PARNCP21NM, pageable);
	}
}
