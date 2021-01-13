package aw.jdbcdemo.paymentmethodtracker.model;

public class PaymentMethodNote {
	private int id;
	private int paymentMethodID;
	private String date;
	private String note;

	public int getId() {
		return id;
	}

	public void setId(int id) {
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

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	@Override
	public String toString() {
		return "PaymentMethodNote [id=" + id + ", paymentMethodID=" + paymentMethodID + ", date=" + date + ", note="
				+ note + "]";
	}

}
