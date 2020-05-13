package com.djad.mesquery.dto;

public class ResourceDTO {
	private String name;
	private String tag;
	private int productionState;
	private int availability;
	private String currentShift;
	private String currentCrew;
	private String currentProductionRun;
	private String currentProduct;

	public ResourceDTO(String tag, String name, int productionState, int availability, 
		String currentShift, String currentCrew, String currentProductionRun, String currentProduct) {
		this.tag = tag;
		this.name = name;
		this.productionState = productionState;
		this.availability = availability;
		this.currentShift = currentShift;
		this.currentCrew = currentCrew;
		this.currentProductionRun = currentProductionRun;
		this.currentProduct = currentProduct;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}

	public int getProductionState() {
		return productionState;
	}

	public void setProductionState(int productionState) {
		this.productionState = productionState;
	}

	public int getAvailability() {
		return availability;
	}

	public void setAvailability(int availability) {
		this.availability = availability;
	}

	public String getCurrentShift() {
		return currentShift;
	}

	public void setCurrentShift(String currentShift) {
		this.currentShift = currentShift;
	}

	public String getCurrentCrew() {
		return currentCrew;
	}

	public void setCurrentCrew(String currentCrew) {
		this.currentCrew = currentCrew;
	}

	public String getCurrentProductionRun() {
		return currentProductionRun;
	}

	public void setCurrentProductionRun(String currentProductionRun) {
		this.currentProductionRun = currentProductionRun;
	}

	public String getCurrentProduct() {
		return currentProduct;
	}

	public void setCurrentProduct(String currentProduct) {
		this.currentProduct = currentProduct;
	}
}