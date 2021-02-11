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

    /*
	 * Determines appropriate action. 
	 * "JSP" means it is directing to the appropriate JSP and populating any necessary data.
	 * "DAO" means it is calling the DAO to access data.
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String action= request.getParameter("action");
		if(action.contentEquals("list")) {
			listPaymentMethods(request, response, " ");
		} else if(action.contentEquals("createPaymentMethodJSP")) {
			createPaymentMethodJSP(request,response);
		} else if(action.contentEquals("createPaymentMethodDAO")) {
			createPaymentMethodDAO(request,response);
		} else if (action.contentEquals("searchPaymentMethodJSP")) {
			searchPaymentMethodJSP(request,response);
		} else if (action.contentEquals("searchPaymentMethodDAO")) {
			searchPaymentMethodDAO(request,response);
		} else if(action.contentEquals("editPaymentMethodJSP")) {
			editPaymentMethodJSP(request,response);
		} else if (action.contentEquals("editPaymentMethodDAO")) {
			editPaymentMethodDAO(request,response);
		} else if(action.contentEquals("deletePaymentMethodJSP")) {
			deletePaymentMethodJSP(request,response);
		} else if (action.contentEquals("deletePaymentMethodDAO")) {
			deletePaymentMethodDAO(request,response);
		} else if (action.contentEquals("cancel")) {
			cancelAction(request,response);
		}
	}
	
	/*
	 * Fetches list of all payment method records. Accepts message for methods that have confirmation messages
	 */
	private void listPaymentMethods (HttpServletRequest request, HttpServletResponse response, String message) throws ServletException, IOException {
		RequestDispatcher rd=request.getRequestDispatcher("listPaymentMethods.jsp");
		ArrayList<String[]> paymentMethodList = new ArrayList<>();
		paymentMethodList = paymentMethodDAO.listPaymentMethodsByID();
		request.setAttribute("paymentMethodList",paymentMethodList);
		request.setAttribute("message", message);
		rd.forward(request, response);
	}
	
	/*
	 * Forwards request to create payment methods page
	 */
	private void createPaymentMethodJSP(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		RequestDispatcher rd=request.getRequestDispatcher("createPaymentMethod.jsp");
		rd.forward(request, response);
	}
	
	/*
	 * Sends new payment method information to payment method DAO
	 */
	private void createPaymentMethodDAO(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
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
	 * Sends user to search page with origin page info
	 */
	private void searchPaymentMethodJSP(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		RequestDispatcher rd=request.getRequestDispatcher("searchPaymentMethod.jsp");
		rd.forward(request, response);
	}
	
	/*
	 * Accepts search type and term and fetches list of relevant payment method records
	 */
	private void searchPaymentMethodDAO(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
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
	private void editPaymentMethodJSP(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
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
	private void editPaymentMethodDAO(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
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
	private void deletePaymentMethodJSP(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
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
	private void deletePaymentMethodDAO(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		int paymentMethodID = Integer.parseInt(request.getParameter("paymentMethodID"));
		paymentMethodDAO.delete(paymentMethodID);
		
		message = "** Payment method " + paymentMethodID + " deleted! **";
		listPaymentMethods(request,response,message);
	}
	
	/*
	 * Sends user back to previous page of results.
	 */
	public void cancelAction(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		String originPage = request.getParameter("originPage");
		if(originPage.contentEquals("listPaymentMethods")) {
			listPaymentMethods(request,response," ");
		} else if(originPage.contentEquals("searchPaymentMethodResults")) {
			searchPaymentMethodDAO(request,response);
		}
			
	}
	
}