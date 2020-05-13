package com.djad.mesquery.dto;

public class StringValueDTO {
	private String value;

	public StringValueDTO(String value) {
		this.value = value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getValue() {
		return this.value;
	}
}