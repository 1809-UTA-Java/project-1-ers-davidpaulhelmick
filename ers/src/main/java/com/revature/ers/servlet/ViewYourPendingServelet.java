package com.revature.ers.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.revature.ers.model.Reimbursement;
import com.revature.ers.model.Users;
import com.revature.ers.repository.UserDAO;
import com.revature.ers.repository.ReimbursementDAO;

@WebServlet("/ViewYourPending")
public class ViewYourPendingServelet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ViewYourPendingServelet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ReimbursementDAO rdao = new ReimbursementDAO();
		PrintWriter pw = response.getWriter();
		
		String author = request.getParameter("author");
		UserDAO edao = new UserDAO();
		Users user = edao.getUserByName(author);
		List<Reimbursement> reim = (List<Reimbursement>) rdao.getPendingReimbursementsByAuthor(user);
		
		
		response.setContentType("text/html");
		
		for (int i = 0; i < reim.size(); i++) {
			pw.println( "<!DOCTYPE html>\n" +
					"<html>\n" +
					"<head>\n" +
						"<meta charset=\"UTF-8\">\n" +
						"<title>Reimbursements</title>\n" +
					"</head>\n" + 
					"<body>\n" +
						"<ul>\n" +
							"<li><b>Description: </b>" + reim.get(i).getDescription() +"</li>" +
							"<li><b>Amount: </b>" + reim.get(i).getAmount() + "</li>" +
							"<li><b>Timestamp: </b>" + reim.get(i).getSubmitted() + "</li>" +
							"<li><b>Status: </b>" + reim.get(i).getStatus() + "</li>" +
						"</ul>\n" +
					"</body>\n" +
					"</html>\n"
			);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
