package com.pearson.store_backend.service;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Date;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import com.pearson.store_backend.dto.Store;
@ExtendWith(MockitoExtension.class)
class StoreServiceImplTest {
	@InjectMocks
	private StoreServiceImpl service;

	@Test
	void testFetch() {
		
		Store store = service.fetch("1525eec4-7bed-4597-bf5a-e06fcede5f4f");
		assertEquals("1525eec4-7bed-4597-bf5a-e06fcede5f4f", store.getStore_id());
	}

	@Test
	void testFetchAll() {
		
		assertEquals("1525eec4-7bed-4597-bf5a-e06fcede5f4f",service.fetchAll("city").get(0).getStore_id());
		
	}
	

}
