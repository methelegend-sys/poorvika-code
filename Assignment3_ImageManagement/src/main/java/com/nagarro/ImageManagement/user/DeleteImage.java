package com.nagarro.ImageManagement.user;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.nagarro.ImageManagement.entities.Images;
import com.nagarro.ImageManagement.models.ImagesModel;

/**
 * Servlet implementation class DeleteImage
 */
@WebServlet("/DeleteImage")
public class DeleteImage extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteImage() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Integer imageId = Integer.parseInt(request.getParameter("id"));
		ImagesModel imageModel = new ImagesModel();
		Images image  = imageModel.getImage(imageId);
		imageModel.deleteImage(image);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/upload.jsp");
		dispatcher.include(request, response);	
	}

}
