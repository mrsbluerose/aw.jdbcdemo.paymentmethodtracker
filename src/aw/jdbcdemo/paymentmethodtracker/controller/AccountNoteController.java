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
import aw.jdbcdemo.paymentmethodtracker.model.AccountNote;

/**
 * Servlet implementation class AccountNoteController
 */
@WebServlet("/accountNoteController")
public class AccountNoteController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private AccountNoteDAO dao = new AccountNoteDAO();
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
	
	private void listAccountNotes (HttpServletRequest request, HttpServletResponse response, String message) throws ServletException, IOException {
		RequestDispatcher rd=request.getRequestDispatcher("listAccountNotes.jsp");
		ArrayList<String[]> accountNoteList = new ArrayList<>();
		accountNoteList = dao.listAccountNotes(Integer.parseInt(request.getParameter("id")));
		request.setAttribute("accountNoteList",accountNoteList);
		request.setAttribute("message", message);
		rd.forward(request, response);
	}
	
	private void createAccountNote(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		int accountID = Integer.parseInt(request.getParameter("accountID"));
		String date = request.getParameter("date");
		String note = request.getParameter("note");
		
		AccountNote accountNote = new AccountNote();
		accountNote.setAccountID(accountID);
		accountNote.setDate(date);
		accountNote.setNote(note);
		dao.create(accountNote);
		
		message = "** Account Note " + accountNote.getID() + " created! **";
		listAccountNotes(request,response,message);
	}
	
	private void search(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		RequestDispatcher rd=request.getRequestDispatcher("accountNoteSearchResults.jsp");
		String searchType = request.getParameter("searchType");
		String searchTerm = request.getParameter("searchTerm");
		ArrayList<String[]> accountNoteList = new ArrayList<>();
		
		if (searchType.contentEquals("id")) {
			accountNoteList = dao.searchByID(Integer.parseInt(searchTerm));
		} else if (searchType.contentEquals("accountID")) {
			accountNoteList = dao.searchByAccountID(Integer.parseInt(searchTerm));
		} else if (searchType.contentEquals("date")) {
			accountNoteList = dao.searchByYear(searchTerm);
		} 

		request.setAttribute("accountNoteList",accountNoteList);
		rd.forward(request, response);
		
	}
	
	private void editSelectAccountNote(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		RequestDispatcher rd=request.getRequestDispatcher("editAccountNote.jsp");
		int id = Integer.parseInt(request.getParameter("id"));
		AccountNote accountNote = dao.searchAccountNote(id);
		String[] accountNoteItems = new String[3];
		accountNoteItems[0]=String.valueOf(accountNote.getAccountID());
		accountNoteItems[1]=accountNote.getDate();
		accountNoteItems[2]=accountNote.getNote();
		
		request.setAttribute("accountNoteItems",accountNoteItems);
		rd.forward(request, response);
	}
	
	private void editAccountNote(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		int id = Integer.parseInt(request.getParameter("id"));
		int accountID = Integer.parseInt(request.getParameter("accountID"));
		String date = request.getParameter("date");
		String note = request.getParameter("note");
		
		AccountNote accountNote = new AccountNote();
		accountNote.setID(accountID);
		accountNote.setAccountID(accountID);
		accountNote.setDate(date);
		accountNote.setNote(note);
		dao.editAccountNote(accountNote);
		
		message = "** Account Note " + id + " edited! **";
		listAccountNotes(request,response,message);

	}
	
	private void deleteSelectAccountNote(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		RequestDispatcher rd=request.getRequestDispatcher("deleteAccount.jsp");
		int id = Integer.parseInt(request.getParameter("id"));
		AccountNote accountNote = dao.searchAccountNote(id);
		String[] accountNoteItems = new String[3];
		accountNoteItems[0]=String.valueOf(accountNote.getAccountID());
		accountNoteItems[1]=accountNote.getDate();
		accountNoteItems[2]=accountNote.getNote();
		
		request.setAttribute("accountNoteItems",accountNoteItems);
		rd.forward(request, response);
	}
	
	private void deleteAccountNote(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		int id = Integer.parseInt(request.getParameter("id"));
		dao.delete(id);
		
		message = "** Account Note " + id + " deleted! **";
		listAccountNotes(request,response,message);
	}
	
}