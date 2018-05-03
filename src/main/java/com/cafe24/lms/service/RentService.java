package com.cafe24.lms.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cafe24.lms.domain.Item;
import com.cafe24.lms.domain.Rent;
import com.cafe24.lms.repository.RentRepository;

@Service
@Transactional
public class RentService {
	
	@Autowired
	RentRepository rentRepository;
	
	static final Integer ITEM_LIMIT = 4;
	static final Integer PAGE_LIMIT = 5;
	
	public Map<String, Object> findAllRents(Optional<Integer> pageNum){
		PageRequest pageRequest = null;
		Integer recentPageValue = null;

		if (pageNum.isPresent()) {
			recentPageValue = pageNum.get();
		} else {
			recentPageValue = 1;
		}

		pageRequest = new PageRequest(recentPageValue - 1, ITEM_LIMIT);
		Page<Rent> page = rentRepository.findAll(pageRequest);

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("page", page);
		map.put("startPage", (recentPageValue - 1) / PAGE_LIMIT * PAGE_LIMIT + 1);
		map.put("endPage", (recentPageValue - 1) / PAGE_LIMIT * PAGE_LIMIT + PAGE_LIMIT);
		map.put("itemLimit", ITEM_LIMIT);
		map.put("pageLimit", PAGE_LIMIT);

		return map;
	}
	
	public Map<String, Object> findAllReservation(Optional<Integer> pageNum){
		PageRequest pageRequest = null;
		Integer recentPageValue = null;

		if (pageNum.isPresent()) {
			recentPageValue = pageNum.get();
		} else {
			recentPageValue = 1;
		}

		pageRequest = new PageRequest(recentPageValue - 1, ITEM_LIMIT);
		Page<Rent> page = rentRepository.findAllReservation(pageRequest);

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("page", page);
		map.put("startPage", (recentPageValue - 1) / PAGE_LIMIT * PAGE_LIMIT + 1);
		map.put("endPage", (recentPageValue - 1) / PAGE_LIMIT * PAGE_LIMIT + PAGE_LIMIT);
		map.put("itemLimit", ITEM_LIMIT);
		map.put("pageLimit", PAGE_LIMIT);

		return map;
	}

}
