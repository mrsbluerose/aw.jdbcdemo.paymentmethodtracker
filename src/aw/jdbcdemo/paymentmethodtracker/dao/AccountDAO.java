package aw.jdbcdemo.paymentmethodtracker.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import aw.jdbcdemo.paymentmethodtracker.model.Account;
import aw.jdbcdemo.paymentmethodtracker.util.ConnectionUtil;

public class AccountDAO {

	public void save(Account account) {
		Connection connection;
		try {
			connection = ConnectionUtil.getConnection();
			PreparedStatement statement = connection.prepareStatement("INSERT INTO account (name,payment_method_id) VALUES(?,?)");
			statement.setString(1, account.getName());
			statement.setInt(2, account.getPaymentMethodID());
			statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public void edit(Account account) {
		Connection connection;
		try {
			connection = ConnectionUtil.getConnection();
			PreparedStatement statement = connection.prepareStatement("UPDATE account SET name=?, payment_method_id=? WHERE id=?");
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
	
	public Account searchByID(int id) {
		Account account = new Account();
		Connection connection;
		try {
			connection = ConnectionUtil.getConnection();
			PreparedStatement statement = connection.prepareStatement("SELECT * FROM account WHERE id=?");
			statement.setInt(1, id);
			ResultSet resultSet = statement.executeQuery();
			while(resultSet.next()) {
				account.setID(resultSet.getInt(1));
				account.setName(resultSet.getString(2));
				account.setPaymentMethodID(resultSet.getInt(3));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		return account;
	}
	
	public Account searchByName(String name) {
		Account account = new Account();
		Connection connection;
		try {
			connection = ConnectionUtil.getConnection();
			PreparedStatement statement = connection.prepareStatement("SELECT * FROM account WHERE name LIKE ?");
			statement.setString(1, "%"+name+"%");
			ResultSet resultSet = statement.executeQuery();
			while(resultSet.next()) {
				account.setID(resultSet.getInt(1));
				account.setName(resultSet.getString(2));
				account.setPaymentMethodID(resultSet.getInt(3));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		return account;
	}
	
	public Account searchByPaymentMethodID(int paymentMethodID) {
		Account account = new Account();
		Connection connection;
		try {
			connection = ConnectionUtil.getConnection();
			PreparedStatement statement = connection.prepareStatement("SELECT * FROM account WHERE payment_method_id=?");
			statement.setInt(1, paymentMethodID);
			ResultSet resultSet = statement.executeQuery();
			while(resultSet.next()) {
				account.setID(resultSet.getInt(1));
				account.setName(resultSet.getString(2));
				account.setPaymentMethodID(resultSet.getInt(3));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		return account;
	}
}
