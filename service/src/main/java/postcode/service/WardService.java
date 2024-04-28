package postcode.service;

import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import postcode.documents.Ward;
import postcode.repos.WardRepo;
import postcode.service.util.IncludeSortableFields;

@Service
public class WardService {

	@Value("${postcodes.service.ward.sortableFields}")
	private Set<String> sortableFields;

	@Autowired
	private WardRepo wardRepo;

	public Optional<Ward> getWard(String wd22cd) {
		return wardRepo.findByWd22cd(wd22cd);
	}

	public Page<Ward> getWardByName(String wd22nm, Pageable pageable) {

		Pageable updatePageable = IncludeSortableFields.updatePageable(pageable, sortableFields);

		return wardRepo.findByWd22nm(wd22nm, updatePageable);
	}

}
