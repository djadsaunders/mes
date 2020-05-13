package com.djad.mesquery.dto;

public class ProductionRunDTO {
	private String runName;
	private Double inCount;
	private Double outCount;

	public String getRunName() {
		return runName;
	}

	public void setRunName(String runName) {
		this.runName = runName;
	}

	public Double getInCount() {
		return inCount;
	}

	public void setInCount(Double inCount) {
		this.inCount = inCount;
	}

	public Double getOutCount() {
		return outCount;
	}

	public void setOutCount(Double outCount) {
		this.outCount = outCount;
	}

	public ProductionRunDTO(String runName, Double inCount, Double outCount) {
		this.runName = runName;
		this.inCount = inCount;
		this.outCount = outCount;
	}
}