package aw.jdbcdemo.paymentmethodtracker.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import aw.jdbcdemo.paymentmethodtracker.model.PaymentMethod;
import aw.jdbcdemo.paymentmethodtracker.util.ConnectionUtil;


public class PaymentMethodDAO {

	public void create(PaymentMethod paymentMethod) {
		Connection connection;
		try {
			connection = ConnectionUtil.getConnection();
			PreparedStatement statement = connection.prepareStatement("INSERT INTO payment_method (name,description,expiration_date) VALUES(?,?,?)");
			statement.setString(1, paymentMethod.getName());
			statement.setString(2, paymentMethod.getDescription());
			statement.setString(3, paymentMethod.getExpDate());
			statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
		
	public ArrayList<PaymentMethod> searchByID(int id) {
		ArrayList<PaymentMethod> paymentMethodList = new ArrayList<>();
		PaymentMethod paymentMethod = new PaymentMethod();
		Connection connection;
		try {
			connection = ConnectionUtil.getConnection();
			PreparedStatement statement = connection.prepareStatement("SELECT * FROM payment_method WHERE id=?");
			statement.setInt(1, id);
			ResultSet resultSet = statement.executeQuery();
			while(resultSet.next()) {
				paymentMethod = new PaymentMethod();
				paymentMethod.setID(resultSet.getInt(1));
				paymentMethod.setName(resultSet.getString(2));
				paymentMethod.setDescription(resultSet.getString(3));
				paymentMethod.setExpDate(resultSet.getString(4));
				paymentMethodList.add(paymentMethod);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		return paymentMethodList;
	}
	
	public ArrayList<PaymentMethod> searchByName(String name) {
		ArrayList<PaymentMethod> paymentMethodList = new ArrayList<>();
		PaymentMethod paymentMethod = new PaymentMethod();
		Connection connection;
		try {
			connection = ConnectionUtil.getConnection();
			PreparedStatement statement = connection.prepareStatement("SELECT * FROM payment_method WHERE name LIKE ?");
			statement.setString(1, "%"+name+"%");
			ResultSet resultSet = statement.executeQuery();
			while(resultSet.next()) {
				paymentMethod = new PaymentMethod();
				paymentMethod.setID(resultSet.getInt(1));
				paymentMethod.setName(resultSet.getString(2));
				paymentMethod.setDescription(resultSet.getString(3));
				paymentMethod.setExpDate(resultSet.getString(4));
				paymentMethodList.add(paymentMethod);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		return paymentMethodList;
	}
	
	public ArrayList<PaymentMethod> searchByExpirationYear(String expYear) {
		ArrayList<PaymentMethod> paymentMethodList = new ArrayList<>();
		PaymentMethod paymentMethod = new PaymentMethod();
		Connection connection;
		try {
			connection = ConnectionUtil.getConnection();
			PreparedStatement statement = connection.prepareStatement("SELECT * FROM payment_method WHERE expiration_date LIKE ?");
			statement.setString(1, "%"+expYear+"%");
			ResultSet resultSet = statement.executeQuery();
			while(resultSet.next()) {
				paymentMethod = new PaymentMethod();
				paymentMethod.setID(resultSet.getInt(1));
				paymentMethod.setName(resultSet.getString(2));
				paymentMethod.setDescription(resultSet.getString(3));
				paymentMethod.setExpDate(resultSet.getString(4));
				paymentMethodList.add(paymentMethod);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		return paymentMethodList;
	}

	public void edit(PaymentMethod paymentMethod) {
		Connection connection;
		try {
			connection = ConnectionUtil.getConnection();
			PreparedStatement statement = connection.prepareStatement("UPDATE payment_method SET name=?, description=?, expiration_date=? WHERE id=?");
			statement.setString(1, paymentMethod.getName());
			statement.setString(2, paymentMethod.getDescription());
			statement.setString(3, paymentMethod.getExpDate());
			statement.setInt(4, paymentMethod.getID());
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
			PreparedStatement statement = connection.prepareStatement("DELETE FROM payment_method WHERE id=?");
			statement.setInt(1, id);
			statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
}
