package com.cafe24.lms.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.cafe24.lms.domain.Item;

public interface ItemRepository extends JpaRepository<Item, Long> {

	List<Item> findByNo(Long no);

	Page<Item> findAll(Pageable pageable);

}
