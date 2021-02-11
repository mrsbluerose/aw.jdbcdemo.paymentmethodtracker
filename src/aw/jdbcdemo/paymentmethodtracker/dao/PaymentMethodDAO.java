package aw.jdbcdemo.paymentmethodtracker.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import aw.jdbcdemo.paymentmethodtracker.model.Account;
import aw.jdbcdemo.paymentmethodtracker.model.PaymentMethod;
import aw.jdbcdemo.paymentmethodtracker.util.ConnectionUtil;


public class PaymentMethodDAO {
	
	/*
	 * Returns a list of String arrays with payment method (pm) information 
	 * (pm id, pm name, pm description, pm expDate)
	 */
	public ArrayList<String[]> listPaymentMethods(){
		ArrayList<String[]> paymentMethodList = new ArrayList<>();
		String[] paymentMethodInfo;
		Connection connection;
		try {
			connection = ConnectionUtil.getConnection();
			PreparedStatement statement = connection.prepareStatement("SELECT * "
					+ "FROM payment_method;");
			ResultSet resultSet = statement.executeQuery();
			
			//This array consists of pm id, pm name, pm description, pm expDate
			while(resultSet.next()) {
				paymentMethodInfo = new String[4];
				paymentMethodInfo[0]=String.valueOf(resultSet.getInt(1));
				paymentMethodInfo[1]=resultSet.getString(2);
				paymentMethodInfo[2]=resultSet.getString(3);
				paymentMethodInfo[3]=resultSet.getString(4);
				paymentMethodList.add(paymentMethodInfo);
			}
			ConnectionUtil.closeQuietly(connection);
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		return paymentMethodList;
	}
	
	public ArrayList<PaymentMethod> listPaymentMethodNames(){
		ArrayList<PaymentMethod> paymentMethodList = new ArrayList<>();
		PaymentMethod paymentMethod;
		Connection connection;
		try {
			connection = ConnectionUtil.getConnection();
			PreparedStatement statement = connection.prepareStatement("SELECT * "
					+ "FROM payment_method ORDER BY payment_method_name ASC;");
			ResultSet resultSet = statement.executeQuery();
			
			//This array list all payment methods
			while(resultSet.next()) {
				paymentMethod = new PaymentMethod();
				paymentMethod.setID(resultSet.getInt(1));
				paymentMethod.setName(resultSet.getString(2));
				paymentMethod.setDescription(resultSet.getString(3));
				paymentMethod.setExpDate(resultSet.getString(4));
				paymentMethodList.add(paymentMethod);
			}
			ConnectionUtil.closeQuietly(connection);
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return paymentMethodList;
	}
	
	public ArrayList<PaymentMethod> paymentMethods(int paymentMethodID){
		ArrayList<PaymentMethod> paymentMethodList = new ArrayList<>();
		PaymentMethod paymentMethod = searchPaymentMethod(paymentMethodID);
		paymentMethodList.add(paymentMethod);
		Connection connection;
		try {
			connection = ConnectionUtil.getConnection();
			PreparedStatement statement = connection.prepareStatement("SELECT * "
					+ "FROM payment_method ORDER BY payment_method_name ASC;");
			ResultSet resultSet = statement.executeQuery();
			
			//This array list all payment methods
			while(resultSet.next()) {
				paymentMethod = new PaymentMethod();
				paymentMethod.setID(resultSet.getInt(1));
				paymentMethod.setName(resultSet.getString(2));
				paymentMethod.setDescription(resultSet.getString(3));
				paymentMethod.setExpDate(resultSet.getString(4));
				paymentMethodList.add(paymentMethod);
			}
			ConnectionUtil.closeQuietly(connection);
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return paymentMethodList;
	}

	/*
	 * Creates a new payment method record in payment method table
	 */
	public void create(PaymentMethod paymentMethod) {
		Connection connection;
		try {
			connection = ConnectionUtil.getConnection();
			PreparedStatement statement = connection.prepareStatement("INSERT INTO payment_method "
					+ "(payment_method_name,payment_method_description,payment_method_exp_date) VALUES(?,?,?)");
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
	
	/*
	 * Returns a payment method record that matches the given payment method id
	 */
	public PaymentMethod searchPaymentMethod(int id) {
		PaymentMethod paymentMethod = new PaymentMethod();
		Connection connection;
		try {
			connection = ConnectionUtil.getConnection();
			PreparedStatement statement = connection.prepareStatement("SELECT * FROM payment_method "
					+ "WHERE payment_method_id=?");
			statement.setInt(1, id);
			ResultSet resultSet = statement.executeQuery();
			
			while(resultSet.next()) {
				paymentMethod.setID(resultSet.getInt(1));
				paymentMethod.setName(resultSet.getString(2));
				paymentMethod.setDescription(resultSet.getString(3));
				paymentMethod.setExpDate(resultSet.getString(4));
			}
			ConnectionUtil.closeQuietly(connection);

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		return paymentMethod;
	}
	
	/*
	 * Returns a list of string arrays with payment method information based on the search condition 
	 * specified in following methods
	 */
	public ArrayList<String[]> searchPaymentMethod(String condition) {
		ArrayList<String[]> paymentMethodList = new ArrayList<>();
		String[] paymentMethodInfo;
		Connection connection;
		try {
			connection = ConnectionUtil.getConnection();
			PreparedStatement statement = connection.prepareStatement("SELECT * FROM payment_method "
					+ condition);
			ResultSet resultSet = statement.executeQuery();
			while(resultSet.next()) {
				paymentMethodInfo = new String[4];
				paymentMethodInfo[0]=String.valueOf(resultSet.getInt(1));
				paymentMethodInfo[1]=resultSet.getString(2);
				paymentMethodInfo[2]=resultSet.getString(3);
				paymentMethodInfo[3]=resultSet.getString(4);
				paymentMethodList.add(paymentMethodInfo);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return paymentMethodList;
	}
	
	/*
	 * Returns a list of string arrays with payment method information based on payment method id 
	 */
	public ArrayList<String[]> searchByID(int id) {
		String condition = "WHERE payment_method_id=" + id + ";";
		ArrayList<String[]> paymentMethodList = searchPaymentMethod(condition);		
		return paymentMethodList;
	}
	
	/*
	 * Returns a list of string arrays with payment method information based on character sequence 
	 * contained in payment method name
	 */
	public ArrayList<String[]> searchByName(String name) {
		String condition = "WHERE payment_method_name LIKE '%" + name + "%';";
		ArrayList<String[]> paymentMethodList = searchPaymentMethod(condition);		
		return paymentMethodList;
	}
	
	/*
	 * Returns a list of string arrays with payment method information based on character sequence 
	 * contained in payment method description
	 */
	public ArrayList<String[]> searchByDescription(String description) {
		String condition = "WHERE payment_method_description LIKE '%" + description + "%';";
		ArrayList<String[]> paymentMethodList = searchPaymentMethod(condition);		
		return paymentMethodList;
	}
	
	/*
	 * Returns a list of string arrays with payment method information based on character sequence 
	 * contained in payment method expiration date
	 */
	public ArrayList<String[]> searchByExpirationYear(String expYear) {
		String condition = "WHERE payment_method_exp_date "
				+ "LIKE '%" + expYear + "%';";
		ArrayList<String[]> paymentMethodList = searchPaymentMethod(condition);		
		return paymentMethodList;
	}

	/*
	 * Edits the specified payment method in payment method table based on the given payment method object
	 */
	public void edit(PaymentMethod paymentMethod) {
		Connection connection;
		try {
			connection = ConnectionUtil.getConnection();
			PreparedStatement statement = connection.prepareStatement("UPDATE payment_method SET "
					+ "payment_method_name=?, payment_method_description=?, payment_method_exp_date=? "
					+ "WHERE payment_method_id=?");
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
	
	/*
	 * Deletes the specified payment method in payment method table based on the given payment method id
	 */
	public void delete(int id) {
		Connection connection;
		try {
			connection = ConnectionUtil.getConnection();
			PreparedStatement statement = connection.prepareStatement("DELETE FROM payment_method "
					+ "WHERE payment_method_id=?");
			statement.setInt(1, id);
			statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
}
