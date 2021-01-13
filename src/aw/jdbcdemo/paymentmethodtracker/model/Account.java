package aw.jdbcdemo.paymentmethodtracker.model;

public class Account {
	private int id;
	private String name;
	private int paymentMethodID;

	public int getID() {
		return id;
	}

	public void setID(int iD) {
		id = iD;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getPaymentMethodID() {
		return paymentMethodID;
	}

	public void setPaymentMethodID(int paymentMethod) {
		this.paymentMethodID = paymentMethod;
	}

	@Override
	public String toString() {
		return "Account [id=" + id + ", name=" + name + ", paymentMethodID=" + paymentMethodID + "]";
	}
}
