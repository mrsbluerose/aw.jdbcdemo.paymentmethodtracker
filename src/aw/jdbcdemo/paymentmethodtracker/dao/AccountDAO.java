package aw.jdbcdemo.paymentmethodtracker.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import aw.jdbcdemo.paymentmethodtracker.model.Account;
import aw.jdbcdemo.paymentmethodtracker.util.ConnectionUtil;

public class AccountDAO {

	/*
	 * Returns a list of String arrays with account information (account id, account name, payment method name)
	 */
	public ArrayList<String[]> listAccounts(){
		ArrayList<String[]> accountList = new ArrayList<>();
		String[] accountInfo;
		Connection connection;
		try {
			connection = ConnectionUtil.getConnection();
			PreparedStatement statement = connection.prepareStatement("SELECT a.account_id, "
					+ "a.account_name, pm.payment_method_name "
					+ "FROM account AS a " 
					+ "INNER JOIN payment_method AS pm ON a.payment_method_id=pm.payment_method_id;");
			ResultSet resultSet = statement.executeQuery();
			
			//This array consists of account id, account name, payment method name
			while(resultSet.next()) {
				accountInfo = new String[3];
				accountInfo[0]=String.valueOf(resultSet.getInt(1));
				accountInfo[1]=resultSet.getString(2);
				accountInfo[2]=resultSet.getString(3);
				accountList.add(accountInfo);
			}
			ConnectionUtil.closeQuietly(connection);
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		return accountList;
	}
	
	/*
	 * Creates a new account record in account table
	 */
	public void create(Account account) {
		Connection connection;
		try {
			connection = ConnectionUtil.getConnection();
			PreparedStatement statement = connection.prepareStatement("INSERT INTO account "
					+ "(account_name,payment_method_id) VALUES(?,?)");
			statement.setString(1, account.getName());
			statement.setInt(2, account.getPaymentMethodID());
			statement.executeUpdate();
			ConnectionUtil.closeQuietly(connection);
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	/*
	 * Returns an account that matches the given account id
	 */
	public Account searchAccount(int id) {
		Account account = new Account();
		Connection connection;
		try {
			connection = ConnectionUtil.getConnection();
			PreparedStatement statement = connection.prepareStatement("SELECT * FROM account WHERE account_id=?");
			statement.setInt(1, id);
			ResultSet resultSet = statement.executeQuery();
			
			while(resultSet.next()) {
				account.setID(resultSet.getInt(1));
				account.setName(resultSet.getString(2));
				account.setPaymentMethodID(resultSet.getInt(3));
			}
			ConnectionUtil.closeQuietly(connection);

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		return account;
	}
	
	/*
	 * Returns a list of string arrays with account information based on the search condition 
	 * specified in following methods
	 */
	private ArrayList<String[]> searchAccount(String condition) {
		ArrayList<String[]> accountList = new ArrayList<>();
		String[] accountInfo;
		Connection connection;
		try {
			connection = ConnectionUtil.getConnection();
			PreparedStatement statement = connection.prepareStatement("SELECT a.account_id, a.account_name, "
					+ "pm.payment_method_name "
					+ "FROM account AS a " 
					+ "INNER JOIN payment_method AS pm ON a.payment_method_id=pm.payment_method_id "
					+ condition);
			ResultSet resultSet = statement.executeQuery();
			
			//This array consists of account id, account name, payment method name
			while(resultSet.next()) {
				accountInfo = new String[3];
				accountInfo[0]=String.valueOf(resultSet.getInt(1));
				accountInfo[1]=resultSet.getString(2);
				accountInfo[2]=resultSet.getString(3);
				accountList.add(accountInfo);
			}
			ConnectionUtil.closeQuietly(connection);

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		return accountList;
	}
	
	/////////////////////Delete in favor of id only search\\\\\\\\\\\\\\\\\
	/*
	 * Returns a list of string arrays with account information based on account id 
	 */
	public ArrayList<String[]> searchByID(int id) {
		String condition = "WHERE account_id=" + id + ";";
		ArrayList<String[]> accountList = searchAccount(condition);		
		return accountList;
	}
	
	/*
	 * Returns a list of string arrays with account information based on character sequence contained in account name 
	 */
	public ArrayList<String[]> searchByName(String name) {
		String condition = "WHERE account_name LIKE '%" + name + "%';";
		ArrayList<String[]> accountList = searchAccount(condition);		
		return accountList;
	}
	
	/*
	 * Returns a list of string arrays with account information based on the given payment method id
	 */
	public ArrayList<String[]> searchByPaymentMethodID(int paymentMethodID) {
		String condition = "WHERE a.payment_method_id=" + paymentMethodID + ";";
		ArrayList<String[]> accountList = searchAccount(condition);		
		return accountList;
	}
	
	/*
	 * Edits the specified account in account table based on the given account object
	 */
	public void editAccount(Account account) {
		Connection connection;
		try {
			connection = ConnectionUtil.getConnection();
			PreparedStatement statement = connection.prepareStatement("UPDATE account SET account_name=?, payment_method_id=? WHERE account_id=?");
			statement.setString(1, account.getName());
			statement.setInt(2, account.getPaymentMethodID());
			statement.setInt(3, account.getID());
			statement.executeUpdate();
			ConnectionUtil.closeQuietly(connection);
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	/*
	 * Deletes the specified account in account table based on the given account id
	 */
	public void delete(int id) {
		Connection connection;
		try {
			connection = ConnectionUtil.getConnection();
			PreparedStatement statement = connection.prepareStatement("DELETE FROM account WHERE account_id=?");
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
