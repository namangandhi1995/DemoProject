package com.test.demo.Entities;

import java.util.Set;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "investors")
@AttributeOverrides({  
    @AttributeOverride(name="id", column=@Column(name="investor_id")),  
    //@AttributeOverride(name="name", column=@Column(name="name"))  
})  
public class Investors extends Node {


	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "Investors_Funds", joinColumns = {
			@JoinColumn(referencedColumnName = "investor_id") }, inverseJoinColumns = {
					@JoinColumn(referencedColumnName = "fund_id") })
	private Set<Funds> funds;


	public Set<Funds> getFunds() {
		return funds;
	}

	public void setFunds(Set<Funds> funds) {
		this.funds = funds;
	}

}
