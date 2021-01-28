package aw.jdbcdemo.paymentmethodtracker.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import aw.jdbcdemo.paymentmethodtracker.dao.AccountNoteDAO;
import aw.jdbcdemo.paymentmethodtracker.dao.AccountDAO;
import aw.jdbcdemo.paymentmethodtracker.model.Account;
import aw.jdbcdemo.paymentmethodtracker.model.AccountNote;

/**
 * Servlet implementation class AccountNoteController
 */
@WebServlet("/accountNoteController")
public class AccountNoteController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private AccountNoteDAO accountNoteDAO = new AccountNoteDAO();
	private AccountDAO accountDAO = new AccountDAO();
	Account account;
	private String message = "";
	
    public AccountNoteController() {
        super();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action= request.getParameter("action");
		if(action.contentEquals("list")) {
			listAccountNotes(request,response," ");
		} else if(action.contentEquals("create")) {
			createAccountNote(request,response);
		} else if (action.contentEquals("search")) {
			search(request,response);
		} else if(action.contentEquals("editSelectAccountNote")) {
			editSelectAccountNote(request,response);
		} else if (action.contentEquals("edit")) {
			editAccountNote(request,response);
		} else if(action.contentEquals("deleteSelectAccountNote")) {
			deleteSelectAccountNote(request,response);
		} else if (action.contentEquals("delete")) {
			deleteAccountNote(request,response);
		} 
	}
	
    /*
	 * Fetches list of all account note records. Accepts message for methods that have confirmation messages
	 */
	private void listAccountNotes (HttpServletRequest request, HttpServletResponse response, String message) throws ServletException, IOException {
		RequestDispatcher rd=request.getRequestDispatcher("listAccountNotes.jsp");
		ArrayList<String[]> accountNoteList = new ArrayList<>();
		accountNoteList = accountNoteDAO.listAccountNotes(Integer.parseInt(request.getParameter("accountID")));
		account = accountDAO.searchAccount(Integer.parseInt(request.getParameter("accountID")));
		request.setAttribute("accountNoteList",accountNoteList);
		request.setAttribute("accountName",account.getName()); 
		request.setAttribute("message",message);
		rd.forward(request, response);
	}
	
	/*
	 * Sends new account note information to account note DAO
	 */
	private void createAccountNote(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		int accountID = Integer.parseInt(request.getParameter("accountID"));
		String date = request.getParameter("accountNoteDate");
		String text = request.getParameter("accountNoteText");
		
		AccountNote accountNote = new AccountNote();
		accountNote.setAccountID(accountID);
		accountNote.setDate(date);
		accountNote.setText(text);
		accountNoteDAO.create(accountNote);
		
		message = "** Account Note created! **";
		listAccountNotes(request,response,message);
	}
	
	/*
	 * Accepts search type and term and fetches list of relevant account records
	 */
	private void search(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		RequestDispatcher rd=request.getRequestDispatcher("searchAccountNoteResults.jsp");
		String searchType = request.getParameter("searchType");
		String searchTerm = request.getParameter("searchTerm");
		ArrayList<String[]> accountNoteList = new ArrayList<>();
		account = accountDAO.searchAccount(Integer.parseInt(request.getParameter("accountID")));
		
		if (searchType.contentEquals("accountNoteID")) {
			accountNoteList = accountNoteDAO.searchByID(Integer.parseInt(searchTerm));
		} else if (searchType.contentEquals("date")) {
			accountNoteList = accountNoteDAO.searchByYear(searchTerm);
		} else if (searchType.contentEquals("noteText")) {
			accountNoteList = accountNoteDAO.searchByNoteText(searchTerm);
		} 
		request.setAttribute("accountNoteList",accountNoteList);
		request.setAttribute("accountName",account.getName()); 
		rd.forward(request, response);
		
	}
	
	/*
	 * Fetches account note record to populate editable fields
	 */
	private void editSelectAccountNote(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		RequestDispatcher rd=request.getRequestDispatcher("editAccountNote.jsp");
		int accountNoteID = Integer.parseInt(request.getParameter("accountNoteID"));
		AccountNote accountNote = accountNoteDAO.searchAccountNote(accountNoteID);
		String[] accountNoteItems = new String[4];
		accountNoteItems[0]=String.valueOf(accountNote.getID());
		accountNoteItems[1]=String.valueOf(accountNote.getAccountID());
		accountNoteItems[2]=accountNote.getDate();
		accountNoteItems[3]=accountNote.getText();
		
		request.setAttribute("accountNoteItems",accountNoteItems);
		rd.forward(request, response);
	}
	
	/*
	 * Sends updated fields information to account note DAO
	 */
	private void editAccountNote(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		int accountNoteID = Integer.parseInt(request.getParameter("accountNoteID"));
		int accountID = Integer.parseInt(request.getParameter("accountID"));
		String date = request.getParameter("accountNoteDate");
		String text = request.getParameter("accountNoteText");
		
		AccountNote accountNote = new AccountNote();
		accountNote.setID(accountNoteID);
		accountNote.setAccountID(accountID);
		accountNote.setDate(date);
		accountNote.setText(text);
		accountNoteDAO.editAccountNote(accountNote);
		
		message = "** Account Note " + accountNoteID + " edited! **";
		listAccountNotes(request,response,message);
	}
	
	/*
	 *  Fetches account note record to populate account note fields for verification
	 */
	private void deleteSelectAccountNote(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		RequestDispatcher rd=request.getRequestDispatcher("deleteAccountNote.jsp");
		int accountNoteID = Integer.parseInt(request.getParameter("accountNoteID"));
		AccountNote accountNote = accountNoteDAO.searchAccountNote(accountNoteID);
		String[] accountNoteItems = new String[4];
		accountNoteItems[0]=String.valueOf(accountNote.getID());
		accountNoteItems[1]=String.valueOf(accountNote.getAccountID());
		accountNoteItems[2]=accountNote.getDate();
		accountNoteItems[3]=accountNote.getText();
		
		request.setAttribute("accountNoteItems",accountNoteItems);
		rd.forward(request, response);
	}
	
	/*
	 * Sends account note id to account note DAO for deletion
	 */
	private void deleteAccountNote(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		int id = Integer.parseInt(request.getParameter("accountNoteID"));
		accountNoteDAO.delete(id);
		
		message = "** Account Note " + id + " deleted! **";
		listAccountNotes(request,response,message);
	}
	
}