package aw.jdbcdemo.paymentmethodtracker.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import aw.jdbcdemo.paymentmethodtracker.model.Account;
import aw.jdbcdemo.paymentmethodtracker.model.PaymentMethod;
import aw.jdbcdemo.paymentmethodtracker.dao.AccountDAO;
import aw.jdbcdemo.paymentmethodtracker.dao.PaymentMethodDAO;

/**
 * Servlet implementation class AccountController
 */
@WebServlet("/accountController")
public class AccountController extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private AccountDAO accountDAO = new AccountDAO();
	private PaymentMethodDAO paymentMethodDAO = new PaymentMethodDAO();
	private String message = "";
       
    public AccountController() {
        super();
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action= request.getParameter("action");
		if(action.contentEquals("list")) {
			listAccounts(request,response," ");
		} else if(action.contentEquals("createNew")) {
				createNewAccount(request,response);
		} else if(action.contentEquals("create")) {
			createAccount(request,response);
		} else if (action.contentEquals("search")) {
			searchAccount(request,response);
		} else if(action.contentEquals("editSelectAccount")) {
			editSelectAccount(request,response);
		} else if (action.contentEquals("edit")) {
			editAccount(request,response);
		} else if(action.contentEquals("deleteSelectAccount")) {
			deleteSelectAccount(request,response);
		} else if (action.contentEquals("delete")) {
			deleteAccount(request,response);
		} else if (action.contentEquals("cancel")) {
			cancelAction(request,response);
		}
	}
	
	/*
	 * Fetches list of all account records. Accepts message for methods that have confirmation messages
	 */
	private void listAccounts (HttpServletRequest request, HttpServletResponse response, String message) throws ServletException, IOException {
		RequestDispatcher rd=request.getRequestDispatcher("listAccounts.jsp");
		ArrayList<String[]> accountList = new ArrayList<>();
		accountList = accountDAO.listAccounts();
		request.setAttribute("accountList",accountList);
		request.setAttribute("message", message);
		rd.forward(request, response);
	}
	
	/*
	 * Propagates create account page with list of payment methods
	 */
	private void createNewAccount(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		RequestDispatcher rd=request.getRequestDispatcher("createAccount.jsp");
		ArrayList<PaymentMethod> paymentMethodList = paymentMethodDAO.paymentMethods();
		request.setAttribute("paymentMethodList", paymentMethodList);
		rd.forward(request, response);
	}
	
	/*
	 * Sends new account information to account DAO
	 */
	private void createAccount(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		String accountName = request.getParameter("accountName");
		int paymentMethodID = Integer.parseInt(request.getParameter("paymentMethod"));
		
		Account account = new Account();
		account.setName(accountName);
		account.setPaymentMethodID(paymentMethodID);
		accountDAO.create(account);
		
		message = "** Account " + account.getID() + " created! **";
		listAccounts(request,response,message);
	}
	
	/*
	 * Accepts search type and term and fetches list of relevant account records
	 */
	private void searchAccount(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		RequestDispatcher rd=request.getRequestDispatcher("searchAccountResults.jsp");
		String searchType = request.getParameter("searchType");
		String searchTerm = request.getParameter("searchTerm");
		ArrayList<String[]> accountList = new ArrayList<>();
		
		if (searchType.contentEquals("accountID")) {
			accountList = accountDAO.searchByID(Integer.parseInt(searchTerm));
		} else if (searchType.contentEquals("accountName")) {
			accountList = accountDAO.searchByName(searchTerm);
		} else if (searchType.contentEquals("paymentMethodID")) {
			accountList = accountDAO.searchByPaymentMethodID(Integer.parseInt(searchTerm));
		}
		request.setAttribute("accountList",accountList);
		rd.forward(request, response);
	}
	
	/*
	 * Fetches account record to populate editable fields
	 */
	private void editSelectAccount(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		RequestDispatcher rd=request.getRequestDispatcher("editAccount.jsp");
		int accountID = Integer.parseInt(request.getParameter("accountID"));
		Account account = accountDAO.searchAccount(accountID);
		String[] accountItems = new String[3];
		accountItems[0]=String.valueOf(account.getID());
		accountItems[1]=account.getName();
		accountItems[2]=String.valueOf(account.getPaymentMethodID());
		
		ArrayList<PaymentMethod> paymentMethodList = paymentMethodDAO.paymentMethods(account.getPaymentMethodID());
		request.setAttribute("paymentMethodList", paymentMethodList);
		request.setAttribute("accountItems",accountItems);
		rd.forward(request, response);
	}
	
	/*
	 * Sends updated fields information to account DAO
	 */
	private void editAccount(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		int accountID = Integer.parseInt(request.getParameter("accountID"));
		String accountName = request.getParameter("accountName");
		int paymentMethodID = Integer.parseInt(request.getParameter("paymentMethod"));
		
		Account account = new Account();
		account.setID(accountID);
		account.setName(accountName);
		account.setPaymentMethodID(paymentMethodID);
		accountDAO.editAccount(account);
		
		message = "** Account " + accountID + " edited! **";
		listAccounts(request,response,message);

	}
	
	/*
	 *  Fetches account record to populate account fields for verification
	 */
	private void deleteSelectAccount(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		RequestDispatcher rd=request.getRequestDispatcher("deleteAccount.jsp");
		int accountID = Integer.parseInt(request.getParameter("accountID"));
		Account account = accountDAO.searchAccount(accountID);
		String[] accountItems = new String[3];
		accountItems[0]=String.valueOf(account.getID());
		accountItems[1]=account.getName();
		accountItems[2]=String.valueOf(account.getPaymentMethodID());
		
		request.setAttribute("accountItems",accountItems);
		rd.forward(request, response);
	}
	
	/*
	 * Sends account id to account DAO for deletion
	 */
	private void deleteAccount(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		int id = Integer.parseInt(request.getParameter("accountID"));
		accountDAO.delete(id);
		
		message = "** Account " + id + " deleted! **";
		listAccounts(request,response,message);
	}
	
	/*
	 * Sends user back to previous page of results.
	 */
	public void cancelAction (HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		String originPage = request.getParameter("originPage");
		if(originPage.contentEquals("listAccounts")) {
			listAccounts(request,response," ");
		} else if(originPage.contentEquals("searchAccountResults")) {
			searchAccount(request,response);
		}
			
	}
}