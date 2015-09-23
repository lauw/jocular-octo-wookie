package demo;

import javax.persistence.Id;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;

@Entity
class Reservation {

	@Id
	@GeneratedValue
	private Long id;

	private String name;
	
	Reservation() { // why JPA why??
	}

	public Reservation(String name) {
		this.name = name;
	}

	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}
}