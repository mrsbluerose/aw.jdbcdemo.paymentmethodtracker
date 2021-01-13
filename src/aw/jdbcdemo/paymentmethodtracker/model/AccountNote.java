package aw.jdbcdemo.paymentmethodtracker.model;

public class AccountNote {
	private int id;
	private int accountID;
	private String date;
	private String note;

	public int getId() {
		return id;
	}

	public void setId(int id) {
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

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	@Override
	public String toString() {
		return "AccountNote [id=" + id + ", accountID=" + accountID + ", date=" + date + ", note=" + note + "]";
	}

}
