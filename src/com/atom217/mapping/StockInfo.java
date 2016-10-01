package com.atom217.mapping;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;



@Entity
@Table(name = "stockinfo")
public class StockInfo {

	@Column(name="name")
	private String name;
	
	@Id
	@Column(name="symbol")
	private String symbol;
	
	public StockInfo() {
	}

	
	public StockInfo(String name, String symbol) {
		super();
		this.name = name;
		this.symbol = symbol;
	}


	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSymbol() {
		return symbol;
	}
	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}

	
}
