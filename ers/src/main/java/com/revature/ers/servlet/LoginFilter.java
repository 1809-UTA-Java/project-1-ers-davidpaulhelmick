package com.revature.ers.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.revature.ers.model.Users;
import com.revature.ers.repository.UserDAO;;

@WebFilter("/login")
public class LoginFilter implements Filter {

	public void init(FilterConfig filterConfig) throws ServletException {
		
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
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
				chain.doFilter(req, resp);
			}
		}
	}

	public void destroy() {
		
	}

}
