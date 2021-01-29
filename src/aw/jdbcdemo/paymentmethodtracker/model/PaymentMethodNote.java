package aw.jdbcdemo.paymentmethodtracker.model;

public class PaymentMethodNote {
	private int id;
	private int paymentMethodID;
	private String date;
	private String text;

	public int getID() {
		return id;
	}

	public void setID(int id) {
		this.id = id;
	}

	public int getPaymentMethodID() {
		return paymentMethodID;
	}

	public void setPaymentMethodID(int paymentMethodID) {
		this.paymentMethodID = paymentMethodID;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	@Override
	public String toString() {
		return "PaymentMethodNote [id=" + id + ", paymentMethodID=" + paymentMethodID + ", date=" + date + ", text="
				+ text + "]";
	}

}
