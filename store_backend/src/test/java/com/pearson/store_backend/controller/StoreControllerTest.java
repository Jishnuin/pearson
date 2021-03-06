package com.pearson.store_backend.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pearson.store_backend.dto.Store;
import com.pearson.store_backend.dto.StoreResponse;
import com.pearson.store_backend.service.StoreService;

@SpringBootTest
class StoreControllerTest {

	@Mock
	private StoreService service;
	@InjectMocks
	private StoreController storeController;

	private MockMvc mockmvc;

//	@Autowired
//	private WebApplicationContext context;

	ObjectMapper mapper;

//	@BeforeEach
//	void setUp() throws Exception {
//		mockmvc = MockMvcBuilders.webAppContextSetup(context).build();
//	}
	@BeforeEach
	public void setup() {
		this.mockmvc = MockMvcBuilders.standaloneSetup(storeController).build();
		this.mapper = new ObjectMapper();
	}

	@Test
	void getStoreByIdTest() throws Exception {

		Store store = new Store();
		store.setStore_id("12");
		store.setPostCode("563322");
		store.setCity("bangalore");
		store.setAddress("krt");
		store.setOpenedDate(new Date());

		Mockito.when(service.fetch(Mockito.anyString())).thenReturn(store);

		String contentAsString = mockmvc
				.perform(get("/api/v1/stores/12" + store.getStore_id()).contentType(MediaType.APPLICATION_JSON_VALUE)
						.content(mapper.writeValueAsString(store)))
				.andExpect(MockMvcResultMatchers.status().isOk()).andReturn().getResponse().getContentAsString();

		StoreResponse readValue = mapper.readValue(contentAsString, StoreResponse.class);

		assertEquals("Data found ", readValue.getMsg());
	}

	@Test
	void getStoresByCityTest() throws UnsupportedEncodingException, Exception {

		List<Store> list = new ArrayList();
		Store store = new Store();
		store.setStore_id("12");
		store.setPostCode("563322");
		store.setCity("bangalore");
		store.setAddress("krt");
		store.setOpenedDate(new Date());
		list.add(store);

		when(service.fetchAll("city")).thenReturn(list);

		String contentAsString = mockmvc
				.perform(get("/api/v1/stores/sort/city").content(mapper.writeValueAsString(list)))
				.andExpect(status().isOk()).andReturn().getResponse().getContentAsString();
				StoreResponse readValue = mapper.readValue(contentAsString,StoreResponse.class);
		assertEquals("Data found ",readValue.getMsg());
	}

}
