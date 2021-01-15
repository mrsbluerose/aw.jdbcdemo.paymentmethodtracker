package aw.jdbcdemo.paymentmethodtracker.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import aw.jdbcdemo.paymentmethodtracker.model.PaymentMethod;
import aw.jdbcdemo.paymentmethodtracker.dao.PaymentMethodDAO;

/**
 * Servlet implementation class AccountController
 */
@WebServlet("/paymentMethodController")
public class PaymentMethodController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private PaymentMethodDAO dao = new PaymentMethodDAO();
       
    public PaymentMethodController() {
        super();
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String action= request.getParameter("action");
		if(action.contentEquals("create")) {
			createPaymentMethod(request,response);
		} else if (action.contentEquals("search")) {
			searchPaymentMethod(request,response);
		} else if (action.contentEquals("edit")) {
			editPaymentMethod(request,response);
		} else if (action.contentEquals("delete")) {
			deletePaymentMethod(request,response);
		}
	}
	
	private void createPaymentMethod(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String name = request.getParameter("name");
		String description = request.getParameter("description");
		String expDate = request.getParameter("expDate");
		
		PaymentMethod paymentMethod = new PaymentMethod();
		paymentMethod.setName(name);
		paymentMethod.setDescription(description);
		paymentMethod.setExpDate(expDate);
		dao.create(paymentMethod);
		
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.print("<b>Payment Method Created!!</b><br/>");
		out.print("<a href='listPaymentMethods.jsp'>Back to Payment Methods</a>");	}
	
	private void searchPaymentMethod(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String searchType = request.getParameter("searchType");
		String searchTerm = request.getParameter("searchTerm");
		ArrayList<PaymentMethod> paymentMethodList = new ArrayList<>();
		
		if (searchType.contentEquals("id")) {
			paymentMethodList = dao.searchByID(Integer.parseInt(searchTerm));
		} else if (searchType.contentEquals("name")) {
			paymentMethodList = dao.searchByName(searchTerm);
		} else if (searchType.contentEquals("expYear")) {
			paymentMethodList = dao.searchByExpirationYear(searchTerm);
		}
		
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.print("<a href='listPaymentMethods.jsp'>Back to Payment Methods</a><br/>");
		out.print("<b>Payment Methods Found:</b><br/>");
		for(PaymentMethod a:paymentMethodList) {
			out.print(a + "<br/>");
		}
	}
	
	private void editPaymentMethod(HttpServletRequest request, HttpServletResponse response) throws IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		String name = request.getParameter("name");
		String description = request.getParameter("description");
		String expDate = request.getParameter("expDate");
		
		PaymentMethod paymentMethod = new PaymentMethod();
		paymentMethod.setID(id);
		paymentMethod.setName(name);
		paymentMethod.setDescription(description);
		paymentMethod.setExpDate(expDate);
		dao.edit(paymentMethod);
		
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.print("<b>Payment Method Updated!!</b><br/>");
		out.print("<a href='listPaymentMethods.jsp'>Back to Payment Methods</a>");
	}
	
	private void deletePaymentMethod(HttpServletRequest request, HttpServletResponse response) throws IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		dao.delete(id);
		
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.print("<b>Payment Method Deleted!!</b><br/>");
		out.print("<a href='listPaymentMethods.jsp'>Back to Payment Methods</a>");
	}
	
}