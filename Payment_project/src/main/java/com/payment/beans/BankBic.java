package com.payment.beans;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name="bank_bic")
public class BankBic {
	@Id
	String bic;
	String institution_name;
	public String getBic() {
		return bic;
	}
	public void setBic(String bic) {
		this.bic = bic;
	}
	public String getInstitution_name() {
		return institution_name;
	}
	public void setInstitution_name(String institution_name) {
		this.institution_name = institution_name;
	}
	@Override
	public String toString() {
		return "BankBic [bic=" + bic + ", institution_name=" + institution_name + "]";
	}
	
}
