package com.test.demo.Entities;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "holdings")
@AttributeOverrides({  
    @AttributeOverride(name="id", column=@Column(name="holding_id")),  
    //@AttributeOverride(name="name", column=@Column(name="name"))  
})  
public class Holdings extends Node{

	@Column(name = "holding_value",nullable=false)
	private Double holdingValue=0.0;

	@OneToMany(cascade = CascadeType.ALL,mappedBy = "holding")
	private Set<FundsHoldings> fundsHoldings = new HashSet<FundsHoldings>();

	public Double getHoldingValue() {
		return holdingValue;
	}

	public void setHoldingValue(Double holdingValue) {
		this.holdingValue = holdingValue;
	}

	public Set<FundsHoldings> getFundsHoldings() {
		return fundsHoldings;
	}

	public void setFundsHoldings(Set<FundsHoldings> fundsHoldings) {
		this.fundsHoldings = fundsHoldings;
	}

}
