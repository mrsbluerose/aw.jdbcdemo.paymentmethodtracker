package aw.jdbcdemo.paymentmethodtracker.model;

public class PaymentMethod {
	private int id;
	private String name;
	private String description;
	private String expDate;

	public int getID() {
		return id;
	}

	public void setID(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getExpDate() {
		return expDate;
	}

	public void setExpDate(String expDate) {
		this.expDate = expDate;
	}

	@Override
	public String toString() {
		return "PaymentMethod [id=" + id + ", name=" + name + ", description=" + description + ", expDate=" + expDate
				+ "]";
	}

}
