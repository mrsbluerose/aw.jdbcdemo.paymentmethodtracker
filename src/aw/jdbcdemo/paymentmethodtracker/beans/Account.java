package aw.jdbcdemo.paymentmethodtracker.beans;

public class Account {
	private int ID;
	private String name;
	private int paymentMethod;
	private String recurring;
	private boolean active;

	public int getID() {
		return ID;
	}

	public void setID(int iD) {
		ID = iD;
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

	public String getRecurring() {
		return recurring;
	}

	public void setRecurring(String recurring) {
		this.recurring = recurring;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	@Override
	public String toString() {
		return "AccountArchiveLog [ID=" + ID + ", name=" + name + ", paymentMethod=" + paymentMethod + ", recurring="
				+ recurring + ", active=" + active + "]";
	}

}
