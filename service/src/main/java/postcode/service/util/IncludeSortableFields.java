package postcode.service.util;

import java.util.List;
import java.util.Set;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;

public class IncludeSortableFields {
	public static PageRequest updatePageable(Pageable pageable, Set<String> sortableFields) {
		// Remove incompatible sort fields
		List<Order> sortableOrder = pageable.getSort().filter(s -> sortableFields.contains(s.getProperty())).toList();
		return PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), Sort.by(sortableOrder));
	}
}
