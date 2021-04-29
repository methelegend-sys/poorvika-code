package com.nagarro.ImageManagement.user;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.nagarro.ImageManagement.entities.User;
import com.nagarro.ImageManagement.models.UserModel;

/**
 * Servlet implementation class Validate
 */
@WebServlet("/Validate")
public class Validate extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Validate() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username = (String) request.getParameter("username");
		String password = (String) request.getParameter("password");
		UserModel userM = new UserModel();
		
		User user = userM.getUser(username);
		if(user!=null) {
			if(user.getPassword().equals(password)) {
				HttpSession session  = request.getSession();
				session.setAttribute("username",user.getUserName());
				session.setAttribute("firstName",user.getFirstName());
				session.setAttribute("lastName",user.getLastName());
				session.setAttribute("userId",user.getId());
				
				RequestDispatcher rd = request.getRequestDispatcher("/upload.jsp");
				rd.include(request, response);
			}
			else {
				request.setAttribute("message", "Invalid Password");
				RequestDispatcher rd = request.getRequestDispatcher("/index.jsp");
				rd.forward(request, response); 
			}
		}
		else {
			request.setAttribute("message", "Invalid Username");
			RequestDispatcher rd = request.getRequestDispatcher("/index.jsp");
			rd.include(request, response); 
		}
	
	}

}

