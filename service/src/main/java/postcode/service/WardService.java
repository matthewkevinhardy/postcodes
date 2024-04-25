package postcode.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import postcode.documents.Ward;
import postcode.repos.WardRepo;

@Service
public class WardService {

	@Autowired
	private WardRepo wardRepo;

	public Optional<Ward> getWard(String wd22cd) {
		return wardRepo.findByWd22cd(wd22cd);
	}

	public Page<Ward> getWardByName(String wd22nm, Pageable pageable) {
		return wardRepo.findByWd22nm(wd22nm, pageable);
	}

}
