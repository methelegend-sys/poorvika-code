package com.nagarro.ImageManagement.models;

import java.io.File;
import java.nio.file.Paths;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import org.apache.tomcat.util.http.fileupload.disk.DiskFileItemFactory;
import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;

import com.nagarro.ImageManagement.entities.Images;

@MultipartConfig
public class UploadModel {
	private boolean isMultipart;
	private int maxFileSize = 1024 * 1024;
	private File file;
	private static final double MAX_TOTAL_SIZE = 10;

	private String getFileExtension(String fileName) {

		if (fileName.lastIndexOf(".") != -1 && fileName.lastIndexOf(".") != 0)
			return fileName.substring(fileName.lastIndexOf(".") + 1);
		else
			return "";
	}

	public String insertFile(HttpServletRequest request, String filePath) throws ServletException, java.io.IOException {

		isMultipart = ServletFileUpload.isMultipartContent(request);

		if (!isMultipart) {

			return "No file Uploaded";

		}

		DiskFileItemFactory factory = new DiskFileItemFactory();

		ServletFileUpload upload = new ServletFileUpload(factory);
		upload.setSizeMax(maxFileSize);

		try {
			Part filePart = request.getPart("file");
			String uniqueID = UUID.randomUUID().toString();
			HttpSession session = request.getSession(false);
			double size = filePart.getSize();
			String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();
			file = new File(
					filePath + session.getAttribute("username") + uniqueID + "." + this.getFileExtension(fileName));
			ImagesModel image = new ImagesModel();
			if (size / (1024 * 1024) > 1) {
				return "Maximum File Limit reached. Image should be les than 1 MB";
			}
			if ((image.getTotalSize((Integer) session.getAttribute("userId")) + size)
					/ (1024 * 1024) > MAX_TOTAL_SIZE) {

				return "Total Size limit exceeded. Total Images size " + "should be less than 10 MB";
			} else {
				if (image.addImages(session.getAttribute("username") + uniqueID + "." + this.getFileExtension(fileName),
						size, (Integer) session.getAttribute("userId"), request.getParameter("name"))) {
					filePart.write(file.toString());

					return "Upload Successful!!";

				} else {
					return "Error Occured";
				}

			}

		} catch (Exception ex) {
			System.out.println(ex);
			return "Exception Occured";
		}

	}

	public String updateFile(HttpServletRequest request, String filePath, int imageId)
			throws ServletException, java.io.IOException {

		isMultipart = ServletFileUpload.isMultipartContent(request);

		String name = request.getParameter("name");
		ImagesModel imageModel = new ImagesModel();
		Images image = imageModel.getImage(imageId);
		image.setName(name);
		@SuppressWarnings("unused")
		String inputFile = request.getParameter("file");

		DiskFileItemFactory factory = new DiskFileItemFactory();

		ServletFileUpload upload = new ServletFileUpload(factory);
		upload.setSizeMax(maxFileSize);

		try {
			Part filePart = request.getPart("file");
			String uniqueID = UUID.randomUUID().toString();
			HttpSession session = request.getSession(false);
			double size = filePart.getSize();
			if (size != 0) {
				File f = new File(image.getImagePath());
				f.delete();
				String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();
				file = new File(
						filePath + session.getAttribute("username") + uniqueID + "." + this.getFileExtension(fileName));

				if (size / (1024 * 1024) > 1) {
					return "Maximum File Limit reached. Image should be les than 1 MB";
				}
				if ((imageModel.getTotalSize((Integer) session.getAttribute("userId")) + size)
						/ (1024 * 1024) > MAX_TOTAL_SIZE) {

					return "Total Size limit exceeded. Total Images size " + "should be less than 10 MB";
				} else {
					image.setImagePath(
							session.getAttribute("username") + uniqueID + "." + this.getFileExtension(fileName));
					image.setSize(size);
					filePart.write(file.toString());
				}

			}

		} catch (Exception ex) {
			System.out.println(ex);
			return "Exception Occured";
		}

		if (imageModel.updateImages(image)) {
			return "Edit Successful !!!";
		} else {
			return "Error Occured";
		}

	}

}
