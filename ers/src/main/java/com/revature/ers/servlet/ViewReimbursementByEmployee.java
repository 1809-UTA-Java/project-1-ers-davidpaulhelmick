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
import com.revature.ers.repository.ReimbursementDAO;
import com.revature.ers.repository.UserDAO;

@WebServlet("/ViewReimbursementByEmployee")
public class ViewReimbursementByEmployee extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ViewReimbursementByEmployee() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ReimbursementDAO rdao = new ReimbursementDAO();
		PrintWriter pw = response.getWriter();
		
		String author = request.getParameter("author");
		UserDAO edao = new UserDAO();
		Users user = edao.getUserByName(author);
		List<Reimbursement> reim = (List<Reimbursement>) rdao.getReimbursementsByAuthor(user);
				
		response.setContentType("text/html");
		
		if(reim.size() == 0) {
			pw.println( "<!DOCTYPE html>\n" +
					"<html>\n" +
					"<head>\n" +
						"<meta charset=\"UTF-8\">\n" +
						"<title>Reimbursements</title>\n" +
					"</head>\n" + 
					"<body>\n" +
					"None Found" +
					"</body>\n" +
					"</html>\n"
			);
		}
		
		for (int i = 0; i < reim.size(); i++) {
			pw.println( "<!DOCTYPE html>\n" +
					"<html>\n" +
					"<head>\n" +
						"<meta charset=\"UTF-8\">\n" +
						"<title>Reimbursements</title>\n" +
					"</head>\n" + 
					"<body>\n" +
						"<ul><b>Transaction number " + (i+1) + "</b>\n" +
							"<li><b>ID: </b>" + reim.get(i).getrID() + "</li>\n" +
							"<li><b>Description: </b>" + reim.get(i).getDescription() +"</li>" +
							"<li><b>Amount: </b>" + reim.get(i).getAmount() + "</li>" +
							"<li><b>Timestamp: </b>" + reim.get(i).getSubmitted() + "</li>" +
							"<li><b>Status: </b>" + reim.get(i).getStatus().getrStatus() + "</li>" +
							"<li><b>Image: </b>" + reim.get(i).getReceipt() + "</li>" +
						"</ul>\n" +
					"</body>\n" +
					"</html>\n"
			);
		}
		pw.println( "<!DOCTYPE html>\n" +
				"<html>\n" +
				"<head>\n" +
					"<meta charset=\"UTF-8\">\n" +
					"<title>Reimbursements</title>\n" +
				"</head>\n" + 
				"<body>\n" +
					"<div><a href='manager-homepage.html'>Back</a></div>" +
				"</body>\n" +
				"</html>\n"
			);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
