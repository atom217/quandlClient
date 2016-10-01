package com.atom217.utility;

public class Stock {

	private String stockName; 
	
	private Double highValue; 

	private Double lowValue;
	
	private Double nowValue;
	
	private Double highP; 
	
	private Double lowP;
	
	public Stock() {
		// TODO Auto-generated constructor stub
	}

	
	public Stock(String stockName) {
		super();
		this.stockName = stockName;
	}

	

	public Stock(String stockName, Double highValue, Double lowValue, Double nowValue) {
		super();
		this.stockName = stockName;
		this.highValue = highValue;
		this.lowValue = lowValue;
		this.nowValue = nowValue;
	}


	public Stock(String stockName, Double nowValue) {
		super();
		this.stockName = stockName;
		this.nowValue = nowValue;
	}


	public String getStockName() {
		return stockName;
	}

	public void setStockName(String stockName) {
		this.stockName = stockName;
	}

	public Double getHighValue() {
		return highValue;
	}

	public void setHighValue(Double highValue) {
		this.highValue = highValue;
	}

	public Double getLowValue() {
		return lowValue;
	}

	public void setLowValue(Double lowValue) {
		this.lowValue = lowValue;
	}

	public Double getNowValue() {
		return nowValue;
	}

	public void setNowValue(Double nowValue) {
		this.nowValue = nowValue;
	}


	public Double getLowP() {
		return lowP;
	}


	public void setLowP(Double lowP) {
		this.lowP = lowP;
	}


	public Double getHighP() {
		return highP;
	}


	public void setHighP(Double highP) {
		this.highP = highP;
	}
	
	

}
