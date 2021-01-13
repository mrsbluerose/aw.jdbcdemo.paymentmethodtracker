package aw.jdbcdemo.paymentmethodtracker.controller;

import java.io.IOException;
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
		}

	}
	
	private void createAccount(HttpServletRequest request, HttpServletResponse response) throws IOException {
		
	}
	
	private void searchAccount(HttpServletRequest request, HttpServletResponse response) throws IOException {
		
	}
	
	private void editAccount(HttpServletRequest request, HttpServletResponse response) throws IOException {
		
	}
	
	private void deleteAccount(HttpServletRequest request, HttpServletResponse response) throws IOException {
		
	}
}