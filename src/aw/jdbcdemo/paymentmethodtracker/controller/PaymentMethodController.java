package aw.jdbcdemo.paymentmethodtracker.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import aw.jdbcdemo.paymentmethodtracker.model.Account;
import aw.jdbcdemo.paymentmethodtracker.model.PaymentMethod;
import aw.jdbcdemo.paymentmethodtracker.dao.PaymentMethodDAO;

/**
 * Servlet implementation class AccountController
 */
@WebServlet("/paymentMethodController")
public class PaymentMethodController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private PaymentMethodDAO paymentMethodDAO = new PaymentMethodDAO();
	private String message = "";
       
    public PaymentMethodController() {
        super();
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String action= request.getParameter("action");
		if(action.contentEquals("list")) {
			listPaymentMethods(request, response, " ");
		} else if(action.contentEquals("create")) {
			createPaymentMethod(request,response);
		} else if (action.contentEquals("search")) {
			searchPaymentMethod(request,response);
		} else if (action.contentEquals("editSelectPaymentMethod")) {
			editSelectPaymentMethod(request,response);
		} else if (action.contentEquals("edit")) {
			editPaymentMethod(request,response);
		} else if (action.contentEquals("deleteSelectPaymentMethod")) {
			deleteSelectPaymentMethod(request,response);
		} else if (action.contentEquals("delete")) {
			deletePaymentMethod(request,response);
		}
	}
	
	/*
	 * Fetches list of all payment method records. Accepts message for methods that have confirmation messages
	 */
	private void listPaymentMethods (HttpServletRequest request, HttpServletResponse response, String message) throws ServletException, IOException {
		RequestDispatcher rd=request.getRequestDispatcher("listPaymentMethods.jsp");
		ArrayList<String[]> paymentMethodList = new ArrayList<>();
		paymentMethodList = paymentMethodDAO.listPaymentMethods();
		request.setAttribute("paymentMethodList",paymentMethodList);
		request.setAttribute("message", message);
		rd.forward(request, response);
	}
	
	/*
	 * Sends new payment method information to payment method DAO
	 */
	private void createPaymentMethod(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		String name = request.getParameter("paymentMethodName");
		String description = request.getParameter("paymentMethodDescription");
		String expDate = request.getParameter("paymentMethodExpDate");
		
		PaymentMethod paymentMethod = new PaymentMethod();
		paymentMethod.setName(name);
		paymentMethod.setDescription(description);
		paymentMethod.setExpDate(expDate);
		paymentMethodDAO.create(paymentMethod);
		
		message = "** Payment Method " + paymentMethod.getID() + " created! **";
		listPaymentMethods(request,response,message);
	}
	
	/*
	 * Accepts search type and term and fetches list of relevant payment method records
	 */
	private void searchPaymentMethod(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		RequestDispatcher rd=request.getRequestDispatcher("searchPaymentMethodResults.jsp");
		String searchType = request.getParameter("searchType");
		String searchTerm = request.getParameter("searchTerm");
		ArrayList<String[]> paymentMethodList = new ArrayList<>();
		
		if (searchType.contentEquals("paymentMethodID")) {
			paymentMethodList = paymentMethodDAO.searchByID(Integer.parseInt(searchTerm));
		} else if (searchType.contentEquals("paymentMethodName")) {
			paymentMethodList = paymentMethodDAO.searchByName(searchTerm);
		} else if (searchType.contentEquals("paymentMethodDescription")) {
			paymentMethodList = paymentMethodDAO.searchByDescription(searchTerm);
		} else if (searchType.contentEquals("paymentMethodExpYear")) {
			paymentMethodList = paymentMethodDAO.searchByExpirationYear(searchTerm);
		}
		
		request.setAttribute("paymentMethodList",paymentMethodList);
		rd.forward(request, response);
	}
	
	/*
	 * Fetches payment method record to populate editable fields
	 */
	private void editSelectPaymentMethod(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		RequestDispatcher rd=request.getRequestDispatcher("editPaymentMethod.jsp");
		int paymentMethodID = Integer.parseInt(request.getParameter("paymentMethodID"));
		PaymentMethod paymentMethod = paymentMethodDAO.searchPaymentMethod(paymentMethodID);
		String[] paymentMethodItems = new String[4];
		paymentMethodItems[0]=String.valueOf(paymentMethod.getID());
		paymentMethodItems[1]=paymentMethod.getName();
		paymentMethodItems[2]=paymentMethod.getDescription();
		paymentMethodItems[3]=paymentMethod.getExpDate();
		
		request.setAttribute("paymentMethodItems",paymentMethodItems);
		rd.forward(request, response);
	}
	
	/*
	 * Sends updated fields information to payment method DAO
	 */
	private void editPaymentMethod(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		int paymentMethodID = Integer.parseInt(request.getParameter("paymentMethodID"));
		String name = request.getParameter("paymentMethodName");
		String description = request.getParameter("paymentMethodDescription");
		String expDate = request.getParameter("paymentMethodExpDate");
		
		PaymentMethod paymentMethod = new PaymentMethod();
		paymentMethod.setID(paymentMethodID);
		paymentMethod.setName(name);
		paymentMethod.setDescription(description);
		paymentMethod.setExpDate(expDate);
		paymentMethodDAO.edit(paymentMethod);
		
		message = "** Payment Method " + paymentMethodID + " edited! **";
		listPaymentMethods(request,response,message);
	}
	
	/*
	 *  Fetches payment method record to populate payment method fields for verification
	 */
	private void deleteSelectPaymentMethod(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		RequestDispatcher rd=request.getRequestDispatcher("deletePaymentMethod.jsp");
		int paymentMethodID = Integer.parseInt(request.getParameter("paymentMethodID"));
		PaymentMethod paymentMethod = paymentMethodDAO.searchPaymentMethod(paymentMethodID);
		String[] paymentMethodItems = new String[4];
		paymentMethodItems[0]=String.valueOf(paymentMethod.getID());
		paymentMethodItems[1]=paymentMethod.getName();
		paymentMethodItems[2]=paymentMethod.getDescription();
		paymentMethodItems[3]=paymentMethod.getExpDate();
		
		request.setAttribute("paymentMethodItems",paymentMethodItems);
		rd.forward(request, response);
	}
	
	/*
	 * Sends payment method id to payment method DAO for deletion
	 */
	private void deletePaymentMethod(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		int paymentMethodID = Integer.parseInt(request.getParameter("paymentMethodID"));
		paymentMethodDAO.delete(paymentMethodID);
		
		message = "** Payment method " + paymentMethodID + " deleted! **";
		listPaymentMethods(request,response,message);
	}
	
}