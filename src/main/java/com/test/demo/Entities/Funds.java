package com.test.demo.Entities;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "funds")
@AttributeOverrides({  
    @AttributeOverride(name="id", column=@Column(name="fund_id")),  
    //@AttributeOverride(name="name", column=@Column(name="name"))  
})
public class Funds extends Node{

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "fund")
	private Set<FundsHoldings> fundsHoldings = new HashSet<FundsHoldings>();

	@ManyToMany(mappedBy = "funds")
	private Set<Investors> investors;

	public Set<FundsHoldings> getFundsHoldings() {
		return fundsHoldings;
	}

	public void setFundsHoldings(Set<FundsHoldings> fundsHoldings) {
		this.fundsHoldings = fundsHoldings;
	}

	public Set<Investors> getInvestors() {
		return investors;
	}

	public void setInvestors(Set<Investors> investors) {
		this.investors = investors;
	}


}
