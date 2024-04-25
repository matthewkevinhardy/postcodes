package postcode.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import postcode.documents.Parish;
import postcode.documents.Postcode;
import postcode.documents.Ward;
import postcode.repos.ParishRepo;
import postcode.repos.PostcodeRepo;
import postcode.repos.WardRepo;
import postcode.service.model.PostcodeDTO;
import postcode.service.model.PostcodeWardDTO;

@Service
public class PostcodeService {

	@Autowired
	private PostcodeRepo postcodeRepo;

	@Autowired
	private ParishRepo parishRepo;

	@Autowired
	private WardRepo wardRepo;

	public Optional<Postcode> getPostcodeByPcd(String pcd) {
		return postcodeRepo.findByPcd(pcd);
	}

	public Page<Postcode> getPostcodeByWard(String wd22cd, Pageable pageable) {
		return postcodeRepo.findByOsward(wd22cd, pageable);
	}

	public PostcodeWardDTO postcodeWard(String pcd) {
		PostcodeWardDTO response = null;

		Optional<Postcode> pc = getPostcodeByPcd(pcd);
		if (pc.isPresent()) {
			Optional<Ward> w = wardRepo.findByWd22cd(pc.get().getOsward());
			response = new PostcodeWardDTO.PostcodeWardBuilder(pc.get(), w.get()).build();
		}

		return response;
	}

	public PostcodeDTO getPostcodeDTO(String pcd) {
		PostcodeDTO response = null;

		Optional<Postcode> pc = getPostcodeByPcd(pcd);
		if (pc.isPresent()) {
			Optional<Ward> w = wardRepo.findByWd22cd(pc.get().getOsward());
			Optional<Parish> p = parishRepo.findByPARNCP21CD(pc.get().getParish());
			response = PostcodeDTO.from(pc.get(), w.get(), p.get());
		}

		return response;
	}
}
