package com.example.demo.persistence;

public class Country {
	private Long id;

	private String countrycode;

	private String countryname;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCountrycode() {
		return countrycode;
	}

	public void setCountrycode(String countrycode) {
		this.countrycode = countrycode;
	}

	public String getCountryname() {
		return countryname;
	}

	public void setCountryname(String countryname) {
		this.countryname = countryname;
	}

	@Override
	public String toString() {
		return "Country [id=" + id + ", countrycode=" + countrycode + ", countryname=" + countryname + "]";
	}

}