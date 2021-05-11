package test.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

@Entity
public class Country {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(unique = true)
	private String name;
	
	@ManyToMany
	@JoinTable(
			name = "Contry_codes",
			joinColumns = @JoinColumn(name = "country_id"),
			inverseJoinColumns = @JoinColumn(name = "countryCode_id"))
	private List<CurrencyCodeDb> codes = new ArrayList<>();

	public Country(String name) {
		this.name = name;
	}

	public Country() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<CurrencyCodeDb> getCodes() {
		return codes;
	}

	public void setCodes(List<CurrencyCodeDb> codes) {
		this.codes = codes;
	}
	
	public void addCode(CurrencyCodeDb code) {
		System.out.println(code);
		codes.add(code);
		code.getCountries().add(this);
	}
	
	
}
