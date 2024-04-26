package postcode.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import postcode.documents.Parish;
import postcode.repos.ParishRepo;

@Service
public class ParishService {

	@Autowired
	private ParishRepo parishRepo;

	public Optional<Parish> getParish(String pARNCP21CD) {
		return parishRepo.findByPARNCP21CD(pARNCP21CD);
	}

	public Page<Parish> getParishByName(String PARNCP21NM, Pageable pageable) {
		return parishRepo.findByPARNCP21NM(PARNCP21NM, pageable);
	}
}
