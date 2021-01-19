package aw.jdbcdemo.paymentmethodtracker.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import aw.jdbcdemo.paymentmethodtracker.model.Account;
import aw.jdbcdemo.paymentmethodtracker.model.PaymentMethod;
import aw.jdbcdemo.paymentmethodtracker.util.ConnectionUtil;

public class AccountDAO {

	public ArrayList<String[]> listAccounts(){
		ArrayList<String[]> accountList = new ArrayList<>();
		String[] accountInfo;
		Connection connection;
		try {
			connection = ConnectionUtil.getConnection();
			PreparedStatement statement = connection.prepareStatement("SELECT a.account_id, a.account_name, pm.payment_method_name "
					+ "FROM account AS a " 
					+ "INNER JOIN payment_method AS pm ON a.payment_method_id=pm.payment_method_id;");
			ResultSet resultSet = statement.executeQuery();
			while(resultSet.next()) {
				accountInfo = new String[3];
				accountInfo[0]=String.valueOf(resultSet.getInt(1));
				accountInfo[1]=resultSet.getString(2);
				accountInfo[2]=resultSet.getString(3);
				accountList.add(accountInfo);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		return accountList;
	}
	
	public void create(Account account) {
		Connection connection;
		try {
			connection = ConnectionUtil.getConnection();
			PreparedStatement statement = connection.prepareStatement("INSERT INTO account (account_name,payment_method_id) VALUES(?,?)");
			statement.setString(1, account.getName());
			statement.setInt(2, account.getPaymentMethodID());
			statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public ArrayList<Account> searchByID(int id) {
		ArrayList<Account> accountList = new ArrayList<>();
		Account account = new Account();
		Connection connection;
		try {
			connection = ConnectionUtil.getConnection();
			PreparedStatement statement = connection.prepareStatement("SELECT * FROM account WHERE account_id=?");
			statement.setInt(1, id);
			ResultSet resultSet = statement.executeQuery();
			while(resultSet.next()) {
				account = new Account();
				account.setID(resultSet.getInt(1));
				account.setName(resultSet.getString(2));
				account.setPaymentMethodID(resultSet.getInt(3));
				accountList.add(account);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		return accountList;
	}
	
	public ArrayList<Account> searchByName(String name) {
		ArrayList<Account> accountList = new ArrayList<>();
		Account account;
		Connection connection;
		try {
			connection = ConnectionUtil.getConnection();
			PreparedStatement statement = connection.prepareStatement("SELECT * FROM account WHERE account_name LIKE ?");
			statement.setString(1, "%"+name+"%");
			ResultSet resultSet = statement.executeQuery();
			while(resultSet.next()) {
				account = new Account();
				account.setID(resultSet.getInt(1));
				account.setName(resultSet.getString(2));
				account.setPaymentMethodID(resultSet.getInt(3));
				accountList.add(account);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		return accountList;
	}
	
	public ArrayList<Account> searchByPaymentMethodID(int paymentMethodID) {
		ArrayList<Account> accountList = new ArrayList<>();
		Account account;
		Connection connection;
		try {
			connection = ConnectionUtil.getConnection();
			PreparedStatement statement = connection.prepareStatement("SELECT * FROM account WHERE payment_method_id=?");
			statement.setInt(1, paymentMethodID);
			ResultSet resultSet = statement.executeQuery();
			while(resultSet.next()) {
				account = new Account();
				account.setID(resultSet.getInt(1));
				account.setName(resultSet.getString(2));
				account.setPaymentMethodID(resultSet.getInt(3));
				accountList.add(account);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		return accountList;
	}
	
	public void edit(Account account) {
		Connection connection;
		try {
			connection = ConnectionUtil.getConnection();
			PreparedStatement statement = connection.prepareStatement("UPDATE account SET account_name=?, payment_method_id=? WHERE account_id=?");
			statement.setString(1, account.getName());
			statement.setInt(2, account.getPaymentMethodID());
			statement.setInt(3, account.getID());
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
			PreparedStatement statement = connection.prepareStatement("DELETE FROM account WHERE account_id=?");
			statement.setInt(1, id);
			statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
}
