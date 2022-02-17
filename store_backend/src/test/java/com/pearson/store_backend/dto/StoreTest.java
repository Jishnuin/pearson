package com.pearson.store_backend.dto;

import static org.junit.jupiter.api.Assertions.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;

import org.junit.jupiter.api.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

class StoreTest {
	String json = "{\"store_id\":\"12\",\"postCode\":\"563322\",\"city\":\"bangalore\",\"address\":\"krt\",\"openedDate\":1644991123837}";
	ObjectMapper mapper = new ObjectMapper();

	@Test
	void test() throws JsonProcessingException, ParseException {
		Store store = new Store();
		store.setAddress("krt");
		store.setCity("bangalore");
		store.setOpenedDate(new Date());
		store.setPostCode("563322");
		store.setStore_id("12");

//		System.out.println(mapper.writeValueAsString(store));

		Store readValue = mapper.readValue(json, Store.class);

		assertEquals(json, mapper.writeValueAsString(readValue));
	}



}






































