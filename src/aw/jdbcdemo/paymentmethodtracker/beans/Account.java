package aw.jdbcdemo.paymentmethodtracker.beans;

public class Account {
	private int id;
	private String name;
	private int paymentMethod;

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

	public int getPaymentMethod() {
		return paymentMethod;
	}

	public void setPaymentMethod(int paymentMethod) {
		this.paymentMethod = paymentMethod;
	}

	@Override
	public String toString() {
		return "Account [id=" + id + ", name=" + name + ", paymentMethod=" + paymentMethod + "]";
	}

}
