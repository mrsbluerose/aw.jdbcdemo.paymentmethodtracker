package aw.jdbcdemo.paymentmethodtracker.controller;

import java.io.IOException;
import java.io.PrintWriter;

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
       
    public AccountController() {
        super();
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String action= request.getParameter("action");
		if(action.contentEquals("create")) {
			createAccount(request,response);
		} else if (action.contentEquals("search")) {
			searchAccount(request,response);
		} else if (action.contentEquals("edit")) {
			editAccount(request,response);
		} else if (action.contentEquals("delete")) {
			deleteAccount(request,response);
		} else if (action.contentEquals("cancel")) {
			cancelAccount(request,response);
		}

	}
	
	private void createAccount(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String name = request.getParameter("name");
		int paymentMethod = Integer.parseInt(request.getParameter("paymentMethod"));
		
		Account account = new Account();
		account.setName(name);
		account.setPaymentMethodID(paymentMethod);
		dao.save(account);
		
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.print("<b>Account Created!!</b>");
		out.print("<br/><a href='listAccounts.jsp'>Home</a>");
	}
	
	private void searchAccount(HttpServletRequest request, HttpServletResponse response) throws IOException {
		
	}
	
	private void editAccount(HttpServletRequest request, HttpServletResponse response) throws IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		String name = request.getParameter("name");
		int paymentMethod = Integer.parseInt(request.getParameter("paymentMethod"));
		
		Account account = new Account();
		account.setID(id);
		account.setName(name);
		account.setPaymentMethodID(paymentMethod);
		dao.edit(account);
		
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.print("<b>Account Updated!!</b>");
		out.print("<br/><a href='listAccounts.jsp'>Back to Accounts</a>");
	}
	
	private void deleteAccount(HttpServletRequest request, HttpServletResponse response) throws IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		dao.delete(id);
		
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.print("<b>Account Deleted!!</b>");
		out.print("<br/><a href='listAccounts.jsp'>Back to Accounts</a>");
	}
	
	private void cancelAccount(HttpServletRequest request, HttpServletResponse response) throws IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.print("<b>Action cancelled</b>");
		out.print("<br/><a href='listAccounts.jsp'>Back to Accounts</a>");
	}
	
}