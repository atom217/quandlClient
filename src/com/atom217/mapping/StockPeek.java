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
@Table(name = "stockpeek")
public class StockPeek {
	
	@Id
	@Column(name="stockid")
	private String stockId;
	
	@Column(name="lowp")
	private Double lowP;

	@Column(name="highp")
	private Double highP;
	
	@Column(name="timestamp")
	private String timeStamp;
	
	
	public StockPeek() {
	}




	public StockPeek(String stockId, Double lowP, Double highP, String timeStamp) {
		super();
		this.stockId = stockId;
		this.lowP = lowP;
		this.highP = highP;
		this.timeStamp = timeStamp;
	}




	public void setLowP(Double lowP) {
		this.lowP = lowP;
	}




	public void setHighP(Double highP) {
		this.highP = highP;
	}




	
	@OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "symbol")
	public String getStockId() {
		return stockId;
	}


	public void setStockId(String stockId) {
		this.stockId = stockId;
	}



	public String getTimeStamp() {
		return timeStamp;
	}


	public void setTimeStamp(String timeStamp) {
		this.timeStamp = timeStamp;
	}
	
	

}
