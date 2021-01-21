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
import aw.jdbcdemo.paymentmethodtracker.dao.AccountDAO;

/**
 * Servlet implementation class AccountController
 */
@WebServlet("/accountController")
public class AccountController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private AccountDAO dao = new AccountDAO();
	private String message = "";
       
    public AccountController() {
        super();
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action= request.getParameter("action");
		if(action.contentEquals("list")) {
			listAccounts(request,response," ");
		} else if(action.contentEquals("create")) {
			createAccount(request,response);
		} else if (action.contentEquals("search")) {
			search(request,response);
		} else if(action.contentEquals("editSelectAccount")) {
			editSelectAccount(request,response);
		} else if (action.contentEquals("edit")) {
			editAccount(request,response);
		} else if(action.contentEquals("deleteSelectAccount")) {
				deleteSelectAccount(request,response);
		} else if (action.contentEquals("delete")) {
			deleteAccount(request,response);
		} 
	}
	
	private void listAccounts (HttpServletRequest request, HttpServletResponse response, String message) throws ServletException, IOException {
		RequestDispatcher rd=request.getRequestDispatcher("listAccounts.jsp");
		ArrayList<String[]> accountList = new ArrayList<>();
		accountList = dao.listAccounts();
		request.setAttribute("accountList",accountList);
		request.setAttribute("message", message);
		rd.forward(request, response);
	}
	
	private void createAccount(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		String name = request.getParameter("name");
		int paymentMethod = Integer.parseInt(request.getParameter("paymentMethod"));
		
		Account account = new Account();
		account.setName(name);
		account.setPaymentMethodID(paymentMethod);
		dao.create(account);
		
		message = "** Account " + account.getID() + " created! **";
		listAccounts(request,response,message);
	}
	
	private void search(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		RequestDispatcher rd=request.getRequestDispatcher("accountSearchResults.jsp");
		String searchType = request.getParameter("searchType");
		String searchTerm = request.getParameter("searchTerm");
		ArrayList<String[]> accountList = new ArrayList<>();
		
		if (searchType.contentEquals("id")) {
			accountList = dao.searchByID(Integer.parseInt(searchTerm));
		} else if (searchType.contentEquals("name")) {
			accountList = dao.searchByName(searchTerm);
		} else if (searchType.contentEquals("paymentMethodID")) {
			accountList = dao.searchByPaymentMethodID(Integer.parseInt(searchTerm));
		}
		
		request.setAttribute("accountList",accountList);
		rd.forward(request, response);
		
	}
	
	private void editSelectAccount(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		RequestDispatcher rd=request.getRequestDispatcher("editAccount.jsp");
		int id = Integer.parseInt(request.getParameter("id"));
		Account account = dao.searchAccount(id);
		String[] accountItems = new String[3];
		accountItems[0]=String.valueOf(account.getID());
		accountItems[1]=account.getName();
		accountItems[2]=String.valueOf(account.getPaymentMethodID());
		
		request.setAttribute("accountItems",accountItems);
		rd.forward(request, response);
	}
	
	private void editAccount(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		int id = Integer.parseInt(request.getParameter("id"));
		String name = request.getParameter("name");
		int paymentMethod = Integer.parseInt(request.getParameter("paymentMethod"));
		
		Account account = new Account();
		account.setID(id);
		account.setName(name);
		account.setPaymentMethodID(paymentMethod);
		dao.editAccount(account);
		
		message = "** Account " + id + " edited! **";
		listAccounts(request,response,message);

	}
	
	private void deleteSelectAccount(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		RequestDispatcher rd=request.getRequestDispatcher("deleteAccount.jsp");
		int id = Integer.parseInt(request.getParameter("id"));
		Account account = dao.searchAccount(id);
		String[] accountItems = new String[3];
		accountItems[0]=String.valueOf(account.getID());
		accountItems[1]=account.getName();
		accountItems[2]=String.valueOf(account.getPaymentMethodID());
		
		request.setAttribute("accountItems",accountItems);
		rd.forward(request, response);
	}
	
	private void deleteAccount(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		int id = Integer.parseInt(request.getParameter("id"));
		dao.delete(id);
		
		message = "** Account " + id + " deleted! **";
		listAccounts(request,response,message);
	}
	
}