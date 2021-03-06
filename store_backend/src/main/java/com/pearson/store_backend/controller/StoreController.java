package com.pearson.store_backend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pearson.store_backend.dto.Store;
import com.pearson.store_backend.dto.StoreResponse;
import com.pearson.store_backend.service.StoreService;

@RestController
@RequestMapping("/api/v1/stores")
public class StoreController {
	@Autowired
	private StoreService service;

	@GetMapping("/{id}")
	public ResponseEntity<StoreResponse> fetch(@PathVariable("id") String id) {

		Store store = service.fetch(id);
		if (store != null)
			return new ResponseEntity<StoreResponse>(new StoreResponse("Data found ", false, store, null), HttpStatus.OK);
		else
			return new ResponseEntity<StoreResponse>(new StoreResponse("Data not found ", true, store, null),HttpStatus.NOT_FOUND);

	}

	@GetMapping("/sort/{sort}")
	public ResponseEntity<StoreResponse> fetchAll(@PathVariable String sort) {
		List<Store> store = service.fetchAll(sort);
		if (store != null) {
			return new ResponseEntity<StoreResponse>(new StoreResponse("Data found ", false, null, store), HttpStatus.OK);
		} else {
			return new ResponseEntity<StoreResponse>(new StoreResponse("Data not found ", true, null, store),HttpStatus.NOT_FOUND);
		}
	}

}
