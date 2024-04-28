package postcode.service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;
import org.springframework.stereotype.Service;

import postcode.documents.Parish;
import postcode.repos.ParishRepo;

@Service
public class ParishService {

	@Value("${postcodes.service.parish.sortableFields}")
	private Set<String> sortableFields;

	@Autowired
	private ParishRepo parishRepo;

	public Optional<Parish> getParish(String pARNCP21CD) {
		return parishRepo.findByPARNCP21CD(pARNCP21CD);
	}

	public Page<Parish> getParishByName(String PARNCP21NM, Pageable pageable) {
		// Remove incompatible sort fields
		List<Order> sortableOrder = pageable.getSort().filter(s -> sortableFields.contains(s.getProperty())).toList();
		Pageable adjustedPageable = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(),
				Sort.by(sortableOrder));

		return parishRepo.findByPARNCP21NM(PARNCP21NM, adjustedPageable);
	}
}
