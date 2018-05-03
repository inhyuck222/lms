package com.cafe24.lms.service;

import java.util.Calendar;
import java.util.Date;
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
import com.cafe24.lms.domain.Reservation;
import com.cafe24.lms.domain.User;
import com.cafe24.lms.repository.ItemRepository;
import com.cafe24.lms.repository.RentRepository;
import com.cafe24.lms.repository.ReservationRepository;

@Service
@Transactional
public class ItemService {

	@Autowired
	ItemRepository itemRepository;

	@Autowired
	RentRepository rentRepository;

	@Autowired
	ReservationRepository reservationRepository;

	private static Integer RENT_DATE = 7;

	public boolean rentItem(Long itemNo, User authUser) {

		Long maxOrder = 0L;

		Date leaseDate = null;
		Date returnDate = null;

		List<Rent> rents = rentRepository.findByItemNo(itemNo);
		if (rents.size() > 0) {
			for (Rent rent : rents) {
				// 한 아이디로 대여(예약) 중인 물품을 두번 대여(예약) 못하는 로직
				// 차후에 반납 일자와 현재 날짜를 비교해서 날짜가 지난 물품이면 대여(예약)이 가능하도록 변경
				if (rent.getUser().getNo() == authUser.getNo()) {
					return false;
				}
				Reservation reservation = rent.getReservation();
				if (reservation != null && maxOrder < reservation.getRentOrder()) {
					maxOrder = reservation.getRentOrder();
				}
				leaseDate = rent.getReturnDate();
			}
			maxOrder++;
		} else {
			leaseDate = new Date();
		}

		Calendar calendar = Calendar.getInstance();
		calendar.setTime(leaseDate);
		calendar.add(Calendar.DATE, RENT_DATE);
		returnDate = calendar.getTime();

		List<Item> items = itemRepository.findByNo(itemNo);
		if (items.size() < 1) {
			return false;
		}
		Item itemRented = items.get(0);
		itemRented.setRented(true);

		Rent newRent = new Rent();
		Reservation newReservation = new Reservation();

		newRent.setItem(itemRented);
		newRent.setUser(authUser);
		newRent.setLeaseDate(leaseDate);
		newRent.setReturnDate(returnDate);
		newRent.setReservation(newReservation);

		newReservation.setRentOrder(maxOrder);
		newReservation = reservationRepository.save(newReservation);
		newReservation.setRent(newRent);

		newRent = rentRepository.save(newRent);

		return true;
	}

	static final Integer ITEM_LIMIT = 4;
	static final Integer PAGE_LIMIT = 5;

	public Map<String, Object> getAllItems(Optional<Integer> pageNum) {
		PageRequest pageRequest = null;
		Integer recentPageValue = null;

		if (pageNum.isPresent()) {
			recentPageValue = pageNum.get();
		} else {
			recentPageValue = 1;
		}

		pageRequest = new PageRequest(recentPageValue - 1, ITEM_LIMIT);
		Page<Item> page = itemRepository.findAll(pageRequest);

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("page", page);
		map.put("startPage", (recentPageValue - 1) / PAGE_LIMIT * PAGE_LIMIT + 1);
		map.put("endPage", (recentPageValue - 1) / PAGE_LIMIT * PAGE_LIMIT + PAGE_LIMIT);
		map.put("itemLimit", ITEM_LIMIT);
		map.put("pageLimit", PAGE_LIMIT);

		return map;
	}

}
