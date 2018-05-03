package com.cafe24.lms.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Reservation {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "reservation_no")
	private Long no;
	private Long rentOrder;

	@OneToOne(mappedBy = "reservation")
	Rent rent;

	public Long getNo() {
		return no;
	}

	public void setNo(Long no) {
		this.no = no;
	}

	public Long getRentOrder() {
		return rentOrder;
	}

	public void setRentOrder(Long rentOrder) {
		this.rentOrder = rentOrder;
	}

	public Rent getRent() {
		return rent;
	}

	public void setRent(Rent rent) {
		this.rent = rent;
	}

	@Override
	public String toString() {
		return "Reservation [no=" + no + ", rentOrder=" + rentOrder + "]";
	}

}
