package aw.jdbcdemo.paymentmethodtracker.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import aw.jdbcdemo.paymentmethodtracker.model.AccountNote;
import aw.jdbcdemo.paymentmethodtracker.util.ConnectionUtil;

public class AccountNoteDAO {

	/*
	 * Returns a list of String arrays with account note information 
	 * (account note id, account note date, account note text)
	 */
	public ArrayList<String[]> listAccountNotes(int id) {
		ArrayList<String[]> accountNoteList = new ArrayList<>();
		String[] accountNoteInfo;
		Connection connection;
		try {
			connection = ConnectionUtil.getConnection();
			PreparedStatement statement = connection.prepareStatement("SELECT an.account_note_id, "
					+ "an.account_note_date, an.account_note_text "
					+ "FROM account_note AS an " 
					+ "INNER JOIN account AS a ON an.account_id=a.account_id "
					+ "WHERE a.account_id=?;");
			statement.setInt(1,id);
			ResultSet resultSet = statement.executeQuery();
			
			//This array consists of account note id, account note date, account note text
			while(resultSet.next()) {
				accountNoteInfo = new String[3];
				accountNoteInfo[0]=String.valueOf(resultSet.getInt(1));
				accountNoteInfo[1]=resultSet.getString(2);
				accountNoteInfo[2]=resultSet.getString(3);
				accountNoteList.add(accountNoteInfo);
			}
			ConnectionUtil.closeQuietly(connection);
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		return accountNoteList;
	}

	/*
	 * Creates a new account note record in account note table
	 */
	public void create(AccountNote accountNote) {
		Connection connection;
		try {
			connection = ConnectionUtil.getConnection();
			PreparedStatement statement = connection.prepareStatement("INSERT INTO account_note "
					+ "(account_id,account_note_date,account_note_text) VALUES(?,?,?)");
			statement.setInt(1, accountNote.getAccountID());
			statement.setString(2,accountNote.getDate());
			statement.setString(3,accountNote.getText());
			statement.executeUpdate();
			ConnectionUtil.closeQuietly(connection);
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
	}
	
	/*
	 * Returns an account that matches the given account note id
	 */
	public AccountNote searchAccountNote(int id) {
		AccountNote accountNote = new AccountNote();
		Connection connection;
		try {
			connection = ConnectionUtil.getConnection();
			PreparedStatement statement = connection.prepareStatement("SELECT * FROM "
					+ "account_note WHERE account_note_id=?");
			statement.setInt(1, id);
			ResultSet resultSet = statement.executeQuery();
			while(resultSet.next()) {
				accountNote.setID(resultSet.getInt(1));
				accountNote.setAccountID(resultSet.getInt(2));
				accountNote.setDate(resultSet.getString(3));
				accountNote.setText(resultSet.getString(4));
			}
			ConnectionUtil.closeQuietly(connection);
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		return accountNote;
	}
	
	/*
	 * Returns a list of string arrays with account information based on the search condition 
	 * specified in following methods
	 */
	public ArrayList<String[]> searchAccountNote(String condition) {
		ArrayList<String[]> accountNoteList = new ArrayList<>();
		String[] accountNoteInfo;
		Connection connection;
		try {
			connection = ConnectionUtil.getConnection();
			PreparedStatement statement = connection.prepareStatement("SELECT account_note_id, "
					+ "account_note_date, account_note_text "
					+ "FROM account_note " 
					+ condition);
			ResultSet resultSet = statement.executeQuery();
			
			//This array consists of account note id, account note date, account note text
			while(resultSet.next()) {
				accountNoteInfo = new String[3];
				accountNoteInfo[0]=String.valueOf(resultSet.getInt(1));
				accountNoteInfo[1]=resultSet.getString(2);
				accountNoteInfo[2]=resultSet.getString(3);
				accountNoteList.add(accountNoteInfo);
			}
			ConnectionUtil.closeQuietly(connection);
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		return accountNoteList;
	}
	
	
	/////////////////////Delete in favor of id only search\\\\\\\\\\\\\\\\\
	/*
	 * Returns a list of string arrays with account note information based on account note id 
	 */
	public ArrayList<String[]> searchByID(int id) {
		String condition = "WHERE account_note_id=" + id + ";";
		ArrayList<String[]> accountNoteList = searchAccountNote(condition);		
		return accountNoteList;
	}
	
	/*
	 * Returns a list of string arrays with account note information based on the given year character sequence
	 */
	public ArrayList<String[]> searchByYear(String year,int accountID) {
		String condition = "WHERE account_note_date LIKE '%" + year + "%' "
				+ "AND account_id=" + accountID + ";";
		ArrayList<String[]> accountNoteList = searchAccountNote(condition);		
		return accountNoteList;
	}
	
	/*
	 * Returns a list of string arrays with account note information based on the given text character sequence
	 */
	public ArrayList<String[]> searchByNoteText(String text, int accountID) {
		String condition = "WHERE account_note_text LIKE '%" + text + "%' "
				+ "AND account_id=" + accountID + ";";
		ArrayList<String[]> accountNoteList = searchAccountNote(condition);		
		return accountNoteList;
	}

	/*
	 * Edits the specified account in account note table based on the given account note object
	 */
	public void editAccountNote(AccountNote accountNote) {
		Connection connection;
		try {
			connection = ConnectionUtil.getConnection();
			PreparedStatement statement = connection.prepareStatement("UPDATE account_note SET account_note_date=?, account_note_text=? WHERE account_note_id=?");
			statement.setString(1, accountNote.getDate());
			statement.setString(2, accountNote.getText());
			statement.setInt(3, accountNote.getID());
			statement.executeUpdate();
			ConnectionUtil.closeQuietly(connection);
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
	}

	/*
	 * Edits the specified account in account note table based on the given account note id
	 */
	public void delete(int id) {
		Connection connection;
		try {
			connection = ConnectionUtil.getConnection();
			PreparedStatement statement = connection.prepareStatement("DELETE FROM account_note WHERE account_note_id=?");
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
