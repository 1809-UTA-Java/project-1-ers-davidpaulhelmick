package com.revature.ers.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.revature.ers.model.Users;
import com.revature.ers.repository.UserDAO;

@WebServlet("/ViewAllAccountsServlet")
public class ViewAllAccountsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ViewAllAccountsServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		response.getWriter().append("Served at: ").append(request.getContextPath());
		List<Users> list = new ArrayList<Users>();
		UserDAO udao = new UserDAO();
		PrintWriter pw = response.getWriter();
		response.setContentType("text/html");
		list = udao.getUsers();
		
		for (int i = 0; i < list.size(); i++) {
			pw.println( "<!DOCTYPE html>\n" +
					"<html>\n" +
					"<head>\n" +
						"<meta charset=\"UTF-8\">\n" +
						"<title>Employees</title>\n" +
					"</head>\n" + 
					"<body>\n" +
						"<ul><b>User Id: " + list.get(i).getUserID() + "</b>\n" +
							"<li><b>User name: </b>" + list.get(i).getUsername() +"</li>" +
							"<li><b>First name: </b>" + list.get(i).getFirstName() + "</li>" +
							"<li><b>Last name: </b>" + list.get(i).getLastName() + "</li>" +
							"<li><b>Email: </b>" + list.get(i).getEmail() + "</li>" +
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
