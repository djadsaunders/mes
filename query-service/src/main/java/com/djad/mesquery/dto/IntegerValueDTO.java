package com.djad.mesquery.dto;

public class IntegerValueDTO {
	private int value;

	public IntegerValueDTO(int value) {
		this.value = value;
	}

	public void setValue(int value) {
		this.value = value;
	}

	public int getValue() {
		return this.value;
	}
}