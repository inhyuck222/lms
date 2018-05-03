package com.cafe24.lms.domain;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrimaryKeyJoinColumn;

import test.DVDCategory;

@Entity
@DiscriminatorValue("DVD")
@PrimaryKeyJoinColumn(name = "dvd_no")
public class DVD extends Item {

	private String DistributionCompany;

	public String getDistributionCompany() {
		return DistributionCompany;
	}

	public void setDistributionCompany(String distributionCompany) {
		DistributionCompany = distributionCompany;
	}

	@Override
	public String toString() {
		return "DVD [DistributionCompany=" + DistributionCompany + "]";
	}
}
