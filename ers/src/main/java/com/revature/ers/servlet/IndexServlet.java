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

@WebServlet("/login")
public class IndexServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public IndexServlet() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse resp = (HttpServletResponse) response;
		PrintWriter pw = response.getWriter();
    	String username = request.getParameter("name");
		String password = request.getParameter("password");
		
		Users user;
		UserDAO edao = new UserDAO();
		user = edao.getUserByName(username);
		
		if (user == null) {
			response.setContentType("text/html");
			
			request.getRequestDispatcher("login.html").include(request, response);
			pw.println("<font color='red'><b>That user does not exist.</b></font>");
		}
		else
		{
			if (!username.equals(user.getUsername()) || !password.equals(user.getPassword())) {
				response.setContentType("text/html");
				
				request.getRequestDispatcher("login.html").include(request, response);
				pw.println("<font color='red'><b>The credentials don't match.</b></font>");
			}
			else {
				req.getSession().setAttribute("user", user);
				if (user.getUserRole().getRoles().equals("employee")){
					request.getRequestDispatcher("employee-homepage.html").include(request, response);
				}
				else
					request.getRequestDispatcher("manager-homepage.html").include(request, response);	
			}
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
