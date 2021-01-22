package aw.jdbcdemo.paymentmethodtracker.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import aw.jdbcdemo.paymentmethodtracker.model.AccountNote;
import aw.jdbcdemo.paymentmethodtracker.util.ConnectionUtil;

public class AccountNoteDAO {

	public ArrayList<String[]> listAccountNotes(int id) {
		ArrayList<String[]> accountNoteList = new ArrayList<>();
		String[] accountNoteInfo;
		Connection connection;
		try {
			connection = ConnectionUtil.getConnection();
			PreparedStatement statement = connection.prepareStatement("SELECT an.account_note_id, an.account_note_date, an.account_note_text "
					+ "FROM account_note AS an " 
					+ "INNER JOIN account AS a ON an.account_id=a.account_id "
					+ "WHERE an.account_id=?;");
			statement.setInt(1,id);
			ResultSet resultSet = statement.executeQuery();
			while(resultSet.next()) {
				accountNoteInfo = new String[3];
				accountNoteInfo[0]=String.valueOf(resultSet.getInt(1));
				accountNoteInfo[1]=resultSet.getString(2);
				accountNoteInfo[2]=resultSet.getString(3);
				accountNoteList.add(accountNoteInfo);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		return accountNoteList;
	}

	public void create(AccountNote accountNote) {
		Connection connection;
		try {
			connection = ConnectionUtil.getConnection();
			PreparedStatement statement = connection.prepareStatement("INSERT INTO account_note (account_id,account_note_date,account_note_text) VALUES(?,?,?)");
			statement.setInt(1, accountNote.getAccountID());
			statement.setString(2,accountNote.getDate());
			statement.setString(3,accountNote.getNote());
			statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
	}

	public AccountNote searchAccountNote(int id) {
		AccountNote accountNote = new AccountNote();
		Connection connection;
		try {
			connection = ConnectionUtil.getConnection();
			PreparedStatement statement = connection.prepareStatement("SELECT * FROM account_note WHERE account_note_id=?");
			statement.setInt(1, id);
			ResultSet resultSet = statement.executeQuery();
			while(resultSet.next()) {
				accountNote.setID(resultSet.getInt(1));
				accountNote.setAccountID(resultSet.getInt(2));
				accountNote.setDate(resultSet.getString(3));
				accountNote.setNote(resultSet.getString(3));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		return accountNote;
	}
	
	public ArrayList<String[]> searchAccountNote(String condition) {
		ArrayList<String[]> accountNoteList = new ArrayList<>();
		String[] accountNoteInfo;
		Connection connection;
		try {
			connection = ConnectionUtil.getConnection();
			PreparedStatement statement = connection.prepareStatement("SELECT an.account_note_id, a.account_name, an.account_note_date, an.account_note_text "
					+ "FROM account_note AS an " 
					+ "INNER JOIN account AS a ON an.account_id=a.account_id "
					+ condition);
			ResultSet resultSet = statement.executeQuery();
			while(resultSet.next()) {
				accountNoteInfo = new String[3];
				accountNoteInfo[0]=String.valueOf(resultSet.getInt(1));
				accountNoteInfo[1]=resultSet.getString(2);
				accountNoteInfo[2]=resultSet.getString(3);
				accountNoteList.add(accountNoteInfo);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		return accountNoteList;
	}
	
	public ArrayList<String[]> searchByID(int id) {
		String condition = "WHERE account_note_id=" + id + ";";
		ArrayList<String[]> accountNoteList = searchAccountNote(condition);		
		return accountNoteList;
	}

	public ArrayList<String[]> searchByAccountID(int id) {
		String condition = "WHERE account_id=" + id + ";";
		ArrayList<String[]> accountNoteList = searchAccountNote(condition);		
		return accountNoteList;
	}

	public ArrayList<String[]> searchByYear(String year) {
		String condition = "WHERE account_name LIKE '%" + year + "%';";
		ArrayList<String[]> accountNoteList = searchAccountNote(condition);		
		return accountNoteList;
	}

	

	public void editAccountNote(AccountNote accountNote) {
		Connection connection;
		try {
			connection = ConnectionUtil.getConnection();
			PreparedStatement statement = connection.prepareStatement("UPDATE account_note SET account_note_date=?, account_note_text=? WHERE account_note_id=?");
			statement.setString(1, accountNote.getDate());
			statement.setString(2, accountNote.getNote());
			statement.setInt(3, accountNote.getID());
			statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
	}

	public void delete(int id) {
		Connection connection;
		try {
			connection = ConnectionUtil.getConnection();
			PreparedStatement statement = connection.prepareStatement("DELETE FROM account_note WHERE account_note_id=?");
			statement.setInt(1, id);
			statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
	}

}
