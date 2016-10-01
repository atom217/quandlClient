package com.atom217.mapping;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;



@Entity
@Table(name = "stockhighlow")
public class StockHighLow {
	
	 
	@Id
	@Column(name="stockid")
	private String stockId;
	
	@Column(name="low")
	private Double low;

	@Column(name="high")
	private Double high;

	@Column(name="lowP")
	private Double lowP;

	@Column(name="highP")
	private Double highP;

	@Column(name="timestamp")
	private String timeStamp;
	
	@Column(name= "now")
	private Double nowValue;
	
	public StockHighLow() {
	}


	

	public StockHighLow(String stockId) {
		super();
		this.stockId = stockId;
	}




	public StockHighLow(String stockId, Double low, Double high, String timeStamp) {
		super();
		this.stockId = stockId;
		this.low = low;
		this.high = high;
		this.timeStamp = timeStamp;
	}


	
	public String getStockId() {
		return stockId;
	}


	@OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "symbol")
	public void setStockId(String stockId) {
		this.stockId = stockId;
	}


	public Double getLow() {
		return low;
	}


	public Double getNowValue() {
		return nowValue;
	}




	public void setNowValue(Double nowValue) {
		this.nowValue = nowValue;
	}




	public void setLow(Double low) {
		this.low = low;
	}


	public Double getHigh() {
		return high;
	}


	public void setHigh(Double high) {
		this.high = high;
	}


	public String getTimeStamp() {
		return timeStamp;
	}


	public void setTimeStamp(String timeStamp) {
		this.timeStamp = timeStamp;
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
