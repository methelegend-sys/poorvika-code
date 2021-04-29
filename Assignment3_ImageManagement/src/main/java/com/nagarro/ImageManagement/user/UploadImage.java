package com.nagarro.ImageManagement.user;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.nagarro.ImageManagement.models.UploadModel;

/**
 * Servlet implementation class UploadImage
 */
@SuppressWarnings("serial")
@WebServlet("/UploadImage")
public class UploadImage extends HttpServlet {
	
	
	private String filePath;
       
 

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		filePath = getServletContext().getInitParameter("file-upload");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		UploadModel upload = new UploadModel();
		String imageId = request.getParameter("imageId");
		if(!imageId.isEmpty()) {
			int id = Integer.parseInt(imageId);
			String message = upload.updateFile(request, filePath,id);
			request.setAttribute("message", message);
		}
		else {
			String message = upload.insertFile(request, filePath);
			request.setAttribute("message", message);
		}
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/upload.jsp");
		dispatcher.include(request, response);

	}

}
