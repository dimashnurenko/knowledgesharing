package examples.onetoone.problem;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import static javax.persistence.FetchType.LAZY;

@Entity
@Table(name = "address")
public class Address {

	@Id
	@GeneratedValue
	@Column(name = "address_id")
	private Long id;

	@Column
	private String zip;

	@OneToOne(fetch = LAZY)
	@JoinColumn(name = "user_id")
	private User user;

	@OneToOne(fetch = LAZY, mappedBy = "address")
	private AddressDetails details;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getZip() {
		return zip;
	}

	public void setZip(String zip) {
		this.zip = zip;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public AddressDetails getDetails() {
		return details;
	}

	public void setDetails(AddressDetails details) {
		this.details = details;
	}
}
