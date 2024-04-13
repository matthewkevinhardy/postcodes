package postcode.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import postcode.documents.Postcode;
import postcode.documents.Ward;
import postcode.repos.PostcodeRepo;
import postcode.repos.WardRepo;
import postcode.service.model.PostcodeWard;

@Service
public class PostcodeService {

	@Autowired
	private PostcodeRepo postcodeRepo;

	@Autowired
	private WardRepo wardRepo;

	public Optional<Postcode> getPostcodeByPcd(String pcd) {
		return postcodeRepo.findByPcd(pcd);
	}

	public Optional<Postcode> getPostcode(String id) {
		return postcodeRepo.findById(id);
	}

	public Page<Postcode> getPostcodeByWard(String wd22cd, Pageable pageable) {
		return postcodeRepo.findByOsward(wd22cd, pageable);
	}

	public Optional<Ward> getWard(String wd22cd) {
		return wardRepo.findByWd22cd(wd22cd);
	}

	public PostcodeWard postcodeWard(String pcd) {
		PostcodeWard response = null;

		Optional<Postcode> pc = getPostcodeByPcd(pcd);
		if (pc.isPresent()) {
			Optional<Ward> w = getWard(pc.get().getOsward());
			response = new PostcodeWard.PostcodeWardBuilder(pc.get(), w.get()).build();
		}

		return response;
	}
}
