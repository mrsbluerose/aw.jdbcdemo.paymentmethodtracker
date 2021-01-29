package aw.jdbcdemo.paymentmethodtracker.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import aw.jdbcdemo.paymentmethodtracker.dao.PaymentMethodNoteDAO;
import aw.jdbcdemo.paymentmethodtracker.dao.PaymentMethodDAO;
import aw.jdbcdemo.paymentmethodtracker.model.PaymentMethodNote;
import aw.jdbcdemo.paymentmethodtracker.model.PaymentMethod;

/**
 * Servlet implementation class PaymentMethodNoteController
 */
@WebServlet("/paymentMethodNoteController")
public class PaymentMethodNoteController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private PaymentMethodNoteDAO paymentMethodNoteDAO = new PaymentMethodNoteDAO();
	private PaymentMethodDAO paymentMethodDAO = new PaymentMethodDAO();
	PaymentMethod paymentMethod;
	private String message = "";       

    public PaymentMethodNoteController() {
        super();
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action= request.getParameter("action");
		if(action.contentEquals("list")) {
			listPaymentMethodNotes(request,response," ");
		} else if(action.contentEquals("create")) {
			createPaymentMethodNote(request,response);
		} else if (action.contentEquals("search")) {
			search(request,response);
		} else if(action.contentEquals("editSelectPaymentMethodNote")) {
			editSelectPaymentMethodNote(request,response);
		} else if (action.contentEquals("edit")) {
			editPaymentMethodNote(request,response);
		} else if(action.contentEquals("deleteSelectPaymentMethodNote")) {
			deleteSelectPaymentMethodNote(request,response);
		} else if (action.contentEquals("delete")) {
			deletePaymentMethodNote(request,response);
		} 
	}
	
	/*
	 * Fetches list of all payment method note records. Accepts message for methods that have confirmation messages
	 */
	private void listPaymentMethodNotes (HttpServletRequest request, HttpServletResponse response, String message) throws ServletException, IOException {
		RequestDispatcher rd=request.getRequestDispatcher("listPaymentMethodNotes.jsp");
		ArrayList<String[]> paymentMethodNoteList = new ArrayList<>();
		paymentMethodNoteList = paymentMethodNoteDAO.listPaymentMethodNotes(Integer.parseInt(request.getParameter("paymentMethodID")));
		paymentMethod = paymentMethodDAO.searchPaymentMethod(Integer.parseInt(request.getParameter("paymentMethodID")));
		request.setAttribute("paymentMethodNoteList",paymentMethodNoteList);
		request.setAttribute("paymentMethodName",paymentMethod.getName()); 
		request.setAttribute("message",message);
		rd.forward(request, response);
	}

	/*
	 * Sends new payment method note information to payment method note DAO
	 */
	private void createPaymentMethodNote(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		int paymentMethodID = Integer.parseInt(request.getParameter("paymentMethodID"));
		String date = request.getParameter("paymentMethodNoteDate");
		String text = request.getParameter("paymentMethodNoteText");
		
		PaymentMethodNote paymentMethodNote = new PaymentMethodNote();
		paymentMethodNote.setPaymentMethodID(paymentMethodID);
		paymentMethodNote.setDate(date);
		paymentMethodNote.setText(text);
		paymentMethodNoteDAO.create(paymentMethodNote);
		
		message = "** Payment Method note created! **";
		listPaymentMethodNotes(request,response,message);
	}
	
	/*
	 * Accepts search type and term and fetches list of relevant payment method records
	 */
	private void search(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		RequestDispatcher rd=request.getRequestDispatcher("searchPaymentMethodNoteResults.jsp");
		String searchType = request.getParameter("searchType");
		String searchTerm = request.getParameter("searchTerm");
		ArrayList<String[]> paymentMethodNoteList = new ArrayList<>();
		int paymentMethodID = Integer.parseInt(request.getParameter("paymentMethodID"));
		paymentMethod = paymentMethodDAO.searchPaymentMethod(paymentMethodID);
		
		if (searchType.contentEquals("paymentMethodNoteID")) {
			paymentMethodNoteList = paymentMethodNoteDAO.searchByID(Integer.parseInt(searchTerm));
		} else if (searchType.contentEquals("paymentMethodNoteDate")) {
			paymentMethodNoteList = paymentMethodNoteDAO.searchByYear(searchTerm,paymentMethodID);
		} else if (searchType.contentEquals("paymentMethodNoteText")) {
			paymentMethodNoteList = paymentMethodNoteDAO.searchByNoteText(searchTerm,paymentMethodID);
		} 
		request.setAttribute("paymentMethodNoteList",paymentMethodNoteList);
		request.setAttribute("paymentMethodName",paymentMethod.getName()); 
		rd.forward(request, response);
	}
	
	/*
	 * Fetches payment method note record to populate editable fields
	 */
	private void editSelectPaymentMethodNote(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		RequestDispatcher rd=request.getRequestDispatcher("editPaymentMethodNote.jsp");
		int paymentMethodNoteID = Integer.parseInt(request.getParameter("paymentMethodNoteID"));
		PaymentMethodNote paymentMethodNote = paymentMethodNoteDAO.searchPaymentMethodNote(paymentMethodNoteID);
		String[] paymentMethodNoteItems = new String[4];
		paymentMethodNoteItems[0]=String.valueOf(paymentMethodNote.getID());
		paymentMethodNoteItems[1]=String.valueOf(paymentMethodNote.getPaymentMethodID());
		paymentMethodNoteItems[2]=paymentMethodNote.getDate();
		paymentMethodNoteItems[3]=paymentMethodNote.getText();
		
		request.setAttribute("paymentMethodNoteItems",paymentMethodNoteItems);
		rd.forward(request, response);
	}
	
	/*
	 * Sends updated fields information to payment method note DAO
	 */
	private void editPaymentMethodNote(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		int paymentMethodNoteID = Integer.parseInt(request.getParameter("paymentMethodNoteID"));
		int paymentMethodID = Integer.parseInt(request.getParameter("paymentMethodID"));
		String date = request.getParameter("paymentMethodNoteDate");
		String text = request.getParameter("paymentMethodNoteText");
		
		PaymentMethodNote paymentMethodNote = new PaymentMethodNote();
		paymentMethodNote.setID(paymentMethodNoteID);
		paymentMethodNote.setPaymentMethodID(paymentMethodID);
		paymentMethodNote.setDate(date);
		paymentMethodNote.setText(text);
		paymentMethodNoteDAO.editPaymentMethodNote(paymentMethodNote);
		
		message = "** Payment Method Note " + paymentMethodNoteID + " edited! **";
		listPaymentMethodNotes(request,response,message);
	}
	
	/*
	 *  Fetches payment method note record to populate payment method note fields for verification
	 */
	private void deleteSelectPaymentMethodNote(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		RequestDispatcher rd=request.getRequestDispatcher("deletePaymentMethodNote.jsp");
		int paymentMethodNoteID = Integer.parseInt(request.getParameter("paymentMethodNoteID"));
		PaymentMethodNote paymentMethodNote = paymentMethodNoteDAO.searchPaymentMethodNote(paymentMethodNoteID);
		String[] paymentMethodNoteItems = new String[4];
		paymentMethodNoteItems[0]=String.valueOf(paymentMethodNote.getID());
		paymentMethodNoteItems[1]=String.valueOf(paymentMethodNote.getPaymentMethodID());
		paymentMethodNoteItems[2]=paymentMethodNote.getDate();
		paymentMethodNoteItems[3]=paymentMethodNote.getText();
		
		request.setAttribute("paymentMethodNoteItems",paymentMethodNoteItems);
		rd.forward(request, response);
	}
	
	/*
	 * Sends payment method note id to payment method note DAO for deletion
	 */
	private void deletePaymentMethodNote(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		int id = Integer.parseInt(request.getParameter("paymentMethodNoteID"));
		paymentMethodNoteDAO.delete(id);
		
		message = "** Payment Method Note " + id + " deleted! **";
		listPaymentMethodNotes(request,response,message);
	}
}
