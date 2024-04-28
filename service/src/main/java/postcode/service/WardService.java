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

import postcode.documents.Ward;
import postcode.repos.WardRepo;

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

		// Remove incompatible sort fields
		List<Order> sortableOrder = pageable.getSort().filter(s -> sortableFields.contains(s.getProperty())).toList();
		Pageable adjustedPageable = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(),
				Sort.by(sortableOrder));

		return wardRepo.findByWd22nm(wd22nm, adjustedPageable);
	}

}
