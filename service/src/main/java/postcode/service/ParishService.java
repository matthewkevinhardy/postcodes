package postcode.service;

import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import postcode.documents.Parish;
import postcode.repos.ParishRepo;
import postcode.service.util.IncludeSortableFields;

@Service
public class ParishService {

	@Value("${postcodes.service.parish.sortableFields}")
	private Set<String> sortableFields;

	@Autowired
	private ParishRepo parishRepo;

	public Optional<Parish> getParish(String pARNCP21CD) {
		return parishRepo.findByParncp21cd(pARNCP21CD);
	}

	public Page<Parish> getParishByName(String PARNCP21NM, Pageable pageable) {
		Pageable updatePageable = IncludeSortableFields.updatePageable(pageable, sortableFields);

		return parishRepo.findByParncp21nm(PARNCP21NM, updatePageable);
	}
}
