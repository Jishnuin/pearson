package com.pearson.store_backend.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "statusCode", "msg", "error", "store" })
@AllArgsConstructor
@NoArgsConstructor
public class StoreResponse {
	@JsonProperty
	private String msg;
	@JsonProperty
	private boolean error;
	@JsonProperty
	private Store store;
	@JsonProperty
	private List<Store> stores;
}
