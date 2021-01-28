package aw.jdbcdemo.paymentmethodtracker.model;

public class AccountNote {
	private int id;
	private int accountID;
	private String date;
	private String text;

	public int getID() {
		return id;
	}

	public void setID(int id) {
		this.id = id;
	}

	public int getAccountID() {
		return accountID;
	}

	public void setAccountID(int accountID) {
		this.accountID = accountID;
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
		return "AccountNote [id=" + id + ", accountID=" + accountID + ", date=" + date + ", text=" + text + "]";
	}

}
