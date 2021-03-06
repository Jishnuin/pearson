package com.pearson.store_backend.dto;

import java.beans.JavaBean;
import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
public class Store implements Serializable {
	@Id
	String store_id;
	String postCode;
	String city;
	String address;
	Date openedDate;

	public Store(String store_id, String postCode, String city, String address, Date date) {
		super();
		this.store_id = store_id;
		this.postCode = postCode;
		this.city = city;
		this.address = address;
		this.openedDate = date;
		
	}

}
