package com.pearson.store_backend.service;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.stereotype.Service;

import com.pearson.store_backend.dto.Store;
import com.pearson.store_backend.exception.StoreException;

import net.bytebuddy.implementation.bytecode.Throw;

@Service
public class StoreServiceImpl implements StoreService {
	String line = "";
	long cal;

	@Override
	public Store fetch(String id) {

		try {

			BufferedReader br = new BufferedReader(new FileReader("src/main/resources/Stores.csv"));

			while ((line = br.readLine()) != null) {
				String[] attributes = line.split(",");

				if (attributes[0].equals(id)) {
					Store store = new Store();
					store.setStore_id(id);
					store.setPostCode(attributes[1]);
					store.setCity(attributes[2]);
					store.setAddress(attributes[3] + "," + attributes[4]);
					String date = attributes[5];
					store.setOpenedDate(new SimpleDateFormat("dd/MM/yyyy").parse(date));
					
					return store;

				}
			}
		} catch (Exception e) {
			e.printStackTrace();

		}

		throw new StoreException("Id is not found...!");

	}

	@SuppressWarnings("deprecation")
	@Override
	public List<Store> fetchAll(String sort) {

		try {
			FileReader fileReader = new FileReader("src/main/resources/stores.csv");
			CSVParser parser = new CSVParser(fileReader,
					CSVFormat.DEFAULT.withFirstRecordAsHeader().withIgnoreHeaderCase().withTrim());

			List<Store> list = new ArrayList<Store>();
			Iterable<CSVRecord> csvRecord = parser.getRecords();
			for (@SuppressWarnings("unused")
			CSVRecord csvRecordTwo : csvRecord) {
				Store store = new Store();
				store.setStore_id(csvRecordTwo.get("Store Id"));
				store.setPostCode(csvRecordTwo.get("Post Code"));
				store.setCity(csvRecordTwo.get("City"));
				store.setAddress(csvRecordTwo.get("Address"));
				String date = csvRecordTwo.get("opened Date");
				store.setOpenedDate(new SimpleDateFormat("dd/MM/yyyy").parse(date));

				cal = store.getOpenedDate().getDate() - new Date().getDate();
				list.add(store);
			}

			if (sort.equalsIgnoreCase("city")) {
				return list.stream().sorted(Comparator.comparing(Store::getCity)).collect(Collectors.toList());
			} else if (sort.equalsIgnoreCase("date")) {
				return list.stream().sorted(Comparator.comparing(Store::getOpenedDate)).collect(Collectors.toList());
			} else {
				throw new StoreException("give an appropriate parameter to sort...Either City or Date...!");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		throw new StoreException("give an appropriate parameter to sort...Either City or Date...!");

	}

}
