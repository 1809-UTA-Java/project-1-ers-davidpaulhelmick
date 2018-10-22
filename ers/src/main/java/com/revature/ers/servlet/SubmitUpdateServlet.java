package com.revature.ers.servlet;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.revature.ers.model.Users;
import com.revature.ers.repository.UserDAO;

@WebServlet("/submit-update")
public class SubmitUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public SubmitUpdateServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Users user = (Users) request.getSession().getAttribute("user");
		System.out.println("Submit " + user.getEmail());
		UserDAO udao = new UserDAO();
		response.setContentType("text/html");
		PrintWriter pw = response.getWriter();
		
		String username = request.getParameter("name");
		String password = request.getParameter("password");
		String firstName = request.getParameter("first-name");
		String lastName = request.getParameter("last-name");
		String email = request.getParameter("email");
		
		
		if (!username.equals("")) {
			user.setUsername(username);
		} 
		if (!password.equals("")) {
			user.setPassword(password);
		}
		if (!firstName.equals("")) {
			user.setFirstName(firstName);
		}
		if (!lastName.equals("")) {
			user.setLastName(lastName);
		}
		if (!email.equals("")) {
			user.setEmail(email);
		}
		
		udao.setUser(user);
		
		request.getRequestDispatcher("employee-homepage.html").include(request, response);
		pw.println("<h4>Account updated.</h4>");
	}
}