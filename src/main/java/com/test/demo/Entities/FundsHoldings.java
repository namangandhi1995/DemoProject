package com.test.demo.Entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "Funds_Holdings")
public class FundsHoldings {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private int id;
	
	@ManyToOne
	@JoinColumn(name = "fund_id")
	private Funds fund;

	@ManyToOne
	@JoinColumn(name = "holding_id")
	private Holdings holding;

	@Column
	private int quantity=0;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Funds getFund() {
		return fund;
	}

	public void setFund(Funds fund) {
		this.fund = fund;
	}

	public Holdings getHolding() {
		return holding;
	}

	public void setHolding(Holdings holding) {
		this.holding = holding;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

}
