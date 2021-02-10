package aw.jdbcdemo.paymentmethodtracker.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import aw.jdbcdemo.paymentmethodtracker.model.PaymentMethodNote;
import aw.jdbcdemo.paymentmethodtracker.util.ConnectionUtil;

public class PaymentMethodNoteDAO {

	/*
	 * Returns a list of String arrays with paymentMethod note information 
	 * (paymentMethod note id, paymentMethod note date, paymentMethod note text)
	 */
	public ArrayList<String[]> listPaymentMethodNotes(int id) {
		ArrayList<String[]> paymentMethodNoteList = new ArrayList<>();
		String[] paymentMethodNoteInfo;
		Connection connection;
		try {
			connection = ConnectionUtil.getConnection();
			PreparedStatement statement = connection.prepareStatement("SELECT pmn.payment_method_note_id, "
					+ "pmn.payment_method_note_date, pmn.payment_method_note_text "
					+ "FROM payment_method_note AS pmn " 
					+ "INNER JOIN payment_method AS pm ON pmn.payment_method_id=pm.payment_method_id "
					+ "WHERE pm.payment_method_id=?;");
			statement.setInt(1,id);
			ResultSet resultSet = statement.executeQuery();
			
			//This array consists of paymentMethod note id, paymentMethod note date, paymentMethod note text
			while(resultSet.next()) {
				paymentMethodNoteInfo = new String[3];
				paymentMethodNoteInfo[0]=String.valueOf(resultSet.getInt(1));
				paymentMethodNoteInfo[1]=resultSet.getString(2);
				paymentMethodNoteInfo[2]=resultSet.getString(3);
				paymentMethodNoteList.add(paymentMethodNoteInfo);
			}
			ConnectionUtil.closeQuietly(connection);
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		return paymentMethodNoteList;
	}

	/*
	 * Creates a new paymentMethod note record in paymentMethod note table
	 */
	public void create(PaymentMethodNote paymentMethodNote) {
		Connection connection;
		try {
			connection = ConnectionUtil.getConnection();
			PreparedStatement statement = connection.prepareStatement("INSERT INTO payment_method_note "
					+ "(payment_method_id,payment_method_note_date,payment_method_note_text) VALUES(?,?,?)");
			statement.setInt(1, paymentMethodNote.getPaymentMethodID());
			statement.setString(2,paymentMethodNote.getDate());
			statement.setString(3,paymentMethodNote.getText());
			statement.executeUpdate();
			ConnectionUtil.closeQuietly(connection);
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
	}
	
	/*
	 * Returns an payment method that matches the given payment method note id
	 */
	public PaymentMethodNote searchPaymentMethodNote(int id) {
		PaymentMethodNote paymentMethodNote = new PaymentMethodNote();
		Connection connection;
		try {
			connection = ConnectionUtil.getConnection();
			PreparedStatement statement = connection.prepareStatement("SELECT * FROM "
					+ "payment_method_note WHERE payment_method_note_id=?");
			statement.setInt(1, id);
			ResultSet resultSet = statement.executeQuery();
			while(resultSet.next()) {
				paymentMethodNote.setID(resultSet.getInt(1));
				paymentMethodNote.setPaymentMethodID(resultSet.getInt(2));
				paymentMethodNote.setDate(resultSet.getString(3));
				paymentMethodNote.setText(resultSet.getString(4));
			}
			ConnectionUtil.closeQuietly(connection);
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		return paymentMethodNote;
	}
	
	/*
	 * Returns a list of string arrays with paymentMethod information based on the search condition 
	 * specified in following methods
	 */
	public ArrayList<String[]> searchPaymentMethodNote(String condition) {
		ArrayList<String[]> paymentMethodNoteList = new ArrayList<>();
		String[] paymentMethodNoteInfo;
		Connection connection;
		try {
			connection = ConnectionUtil.getConnection();
			PreparedStatement statement = connection.prepareStatement("SELECT payment_method_note_id, "
					+ "payment_method_note_date, payment_method_note_text "
					+ "FROM payment_method_note " 
					+ condition);
			ResultSet resultSet = statement.executeQuery();
			
			//This array consists of paymentMethod note id, paymentMethod note date, paymentMethod note text
			while(resultSet.next()) {
				paymentMethodNoteInfo = new String[3];
				paymentMethodNoteInfo[0]=String.valueOf(resultSet.getInt(1));
				paymentMethodNoteInfo[1]=resultSet.getString(2);
				paymentMethodNoteInfo[2]=resultSet.getString(3);
				paymentMethodNoteList.add(paymentMethodNoteInfo);
			}
			ConnectionUtil.closeQuietly(connection);
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		return paymentMethodNoteList;
	}
	
	
	/////////////////////Delete in favor of id only search\\\\\\\\\\\\\\\\\
	/*
	 * Returns a list of string arrays with paymentMethod note information based on paymentMethod note id 
	 */
	public ArrayList<String[]> searchByID(int id) {
		String condition = "WHERE payment_method_note_id=" + id + ";";
		ArrayList<String[]> paymentMethodNoteList = searchPaymentMethodNote(condition);		
		return paymentMethodNoteList;
	}
	
	/*
	 * Returns a list of string arrays with paymentMethod note information based on the given year character sequence
	 */
	public ArrayList<String[]> searchByYear(String year, int paymentMethodID) {
		String condition = "WHERE payment_method_note_date LIKE '%" + year + "%' "
				+ "AND payment_method_id=" + paymentMethodID + ";";
		ArrayList<String[]> paymentMethodNoteList = searchPaymentMethodNote(condition);		
		return paymentMethodNoteList;
	}
	
	/*
	 * Returns a list of string arrays with paymentMethod note information based on the given text character sequence
	 */
	public ArrayList<String[]> searchByNoteText(String text,int paymentMethodID) {
		String condition = "WHERE payment_method_note_text LIKE '%" + text + "%'"
				+ "AND payment_method_id=" + paymentMethodID + ";";
		ArrayList<String[]> paymentMethodNoteList = searchPaymentMethodNote(condition);		
		return paymentMethodNoteList;
	}

	/*
	 * Edits the specified paymentMethod in paymentMethod note table based on the given paymentMethod note object
	 */
	public void editPaymentMethodNote(PaymentMethodNote paymentMethodNote) {
		Connection connection;
		try {
			connection = ConnectionUtil.getConnection();
			PreparedStatement statement = connection.prepareStatement("UPDATE payment_method_note "
					+ "SET payment_method_note_date=?, payment_method_note_text=? WHERE payment_method_note_id=?");
			statement.setString(1, paymentMethodNote.getDate());
			statement.setString(2, paymentMethodNote.getText());
			statement.setInt(3, paymentMethodNote.getID());
			statement.executeUpdate();
			ConnectionUtil.closeQuietly(connection);
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
	}

	/*
	 * Edits the specified paymentMethod in paymentMethod note table based on the given paymentMethod note id
	 */
	public void delete(int id) {
		Connection connection;
		try {
			connection = ConnectionUtil.getConnection();
			PreparedStatement statement = connection.prepareStatement("DELETE FROM payment_method_note "
					+ "WHERE payment_method_note_id=?");
			statement.setInt(1, id);
			statement.executeUpdate();
			ConnectionUtil.closeQuietly(connection);
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
	}

}
