package postcode.service;

import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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
import postcode.service.util.IncludeSortableFields;

@Service
public class PostcodeService {

	@Value("${postcodes.service.postcode.sortableFields}")
	private Set<String> sortableFields;

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

		Pageable updatePageable = IncludeSortableFields.updatePageable(pageable, sortableFields);

		return postcodeRepo.findByOsward(wd22cd, updatePageable);
	}

	public Page<Postcode> getPostcodeByParish(String pARNCP21CD, Pageable pageable) {

		Pageable updatePageable = IncludeSortableFields.updatePageable(pageable, sortableFields);

		return postcodeRepo.findByParish(pARNCP21CD, updatePageable);
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
			Optional<Parish> p = parishRepo.findByParncp21cd(pc.get().getParish());
			response = PostcodeDTO.from(pc.get(), w.get(), p.get());
		}

		return response;
	}
}
