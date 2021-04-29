<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jstl/core" prefix="c"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.nagarro.ImageManagement.entities.Images"%>
<%@page import="com.nagarro.ImageManagement.models.ImagesModel"%>
<%@page import="java.util.*"%>
<%@page import=" javax.servlet.http.HttpSession"%>
<% 
	String username  = (String)session.getAttribute("username");
	if(username==null ){
		response.sendRedirect(request.getContextPath());
	}

%>
<!DOCTYPE html>
<html>
<head>
<style>
table {
	width: 100%;
	border: 2px solid black;
	border-collapse: collapse;
}

th, td {
	padding: 15px;
	border: 2px solid black;
	text-align: center;
}

hr {
	width: 100%;
	height: 2px;
	background: black;
}
</style>
<meta charset="ISO-8859-1">
<title>Upload file</title>
</head>
<body>
	<div style="width: 80%; float: left;">
	<h1 style="text-align: center;">IMAGE MANAGEMENT UTILITY</h1>
	</div>
	<div style="width: 20%; float: left; align-content:center;">
		<a href="<%=request.getContextPath() %>/Logout"><button >Logout</button></a>
	</div>
	
	<hr>
	
	Please select a file to upload:
	<br />
	<form action="uploadImage" method="post" enctype="multipart/form-data">
		<input 
			name = "imageId"
			type="hidden" 
			value = <c:if test="${not empty imageId}">
						${imageId}
					</c:if>
		>
		<div style="width: 30%; float: left;">

			Name : 
			<input 
				name="name" 
				type="text" 
				placeholder="Name of the image"
				required 
				value = <c:if test="${not empty imageName}">
							${imageName}
						</c:if>
			>
		</div>
		<div style="width: 30%; float: left">
			<input 
				type="file" 
				name="file" 
				size="1"  
				accept="image/*" 
				<c:if test="${empty imageId}">
				required 
				</c:if>/>
		</div>
		<button type="submit" value="Submit">Submit</button>
  <button type="reset" value="Reset">Cancel</button>
	</form>

	<br>

	
	<%
      
      ImagesModel imageModel = new ImagesModel();
    	List<Images> imageList = imageModel.listImages((Integer)session.getAttribute("userId"));
      
      if(!imageList.isEmpty()){
      %>
      <h2>Uploaded Images</h2>
	<table>
		<tr>
			<th>S.No</th>
			<th>Name</th>
			<th>Size</th>
			<th>Preview</th>
			<th>Actions</th>
		</tr>

		<%
  		    
      	
      	Iterator<Images> it  = imageList.iterator();
      	int i=0;
      	while(it.hasNext()){
      	i++;
      Images image = it.next();
      %>

		<tr>
			<td><%= i %></td>
			<td><%= image.getName()%></td>
			<td><%= imageModel.getReadableSize(image.getSize()) %>
			<td><img
				src="<%= request.getContextPath()%>/Images?filename=<%= image.getImagePath()%>"
				style="max-width: 100; max-height: 100"></td>

			<td>
				<a 
					href="<%= request.getContextPath()%>
						/EditImage?id=<%= image.getId() %>
						&name=<%= image.getName() %>">
					Edit
				</a>
				&nbsp; &nbsp; 
				<a 
					href="<%= request.getContextPath()%>
						/DeleteImage?id=<%= image.getId() %>">
					Delete
				</a>
		</tr>
		<% } %>
	</table>
	<% } else { %>
	<h2>No Uploaded Images</h2>
	<% } %>
</body>
</html>