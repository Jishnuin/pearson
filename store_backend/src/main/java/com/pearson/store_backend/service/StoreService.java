package com.pearson.store_backend.service;

import java.util.List;

import com.pearson.store_backend.dto.Store;

public interface StoreService {

	public Store fetch(String id) ;
	
	public List<Store> fetchAll(String store);
}
