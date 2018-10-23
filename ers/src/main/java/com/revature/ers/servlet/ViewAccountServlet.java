package com.revature.ers.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.revature.ers.model.Users;

@WebServlet("/ViewAccountServlet")
public class ViewAccountServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ViewAccountServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Users user = (Users) request.getSession().getAttribute("user");
		response.setContentType("text/html");
		PrintWriter pw = response.getWriter();
		pw.println( "<!DOCTYPE html>\n" +
				"<html>\n" +
				"<head>\n" +
					"<meta charset=\"UTF-8\">\n" +
					"<title>Employees</title>\n" +
				"</head>\n" + 
				"<body>\n" +
					"<ul><b>User Id: " + String.valueOf(user.getUserID()) + "</b>\n" +
						"<li><b>User name: </b>" + user.getUsername() +"</li>" +
						"<li><b>First name: </b>" + user.getFirstName() + "</li>" +
						"<li><b>Last name: </b>" + user.getLastName() + "</li>" +
						"<li><b>Email: </b>" + user.getEmail() + "</li>" +
					"</ul>\n" +
				"</body>\n" +
				"</html>\n"
		);
		pw.close();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
