package com.revature.ers.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.revature.ers.model.Reimbursement;
import com.revature.ers.model.Users;
import com.revature.ers.repository.ReimbursementDAO;

@WebServlet("/DenyReimbursement")
public class DenyReimbursementServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public DenyReimbursementServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String reimId = request.getParameter("reimId");
		PrintWriter pw = response.getWriter();
		ReimbursementDAO rdao = new ReimbursementDAO();
		Users resolver = (Users) request.getSession().getAttribute("user");
		
		Reimbursement reim = rdao.getRimbursementById(reimId);
		reim.setResolver(resolver);
		reim.setStatus(rdao.status(2));
		rdao.setReimbursement(reim);
		
		request.getRequestDispatcher("manager-homepage.html").include(request, response);
		pw.println("<h4>The request was denied.</h4>");
	}
}
