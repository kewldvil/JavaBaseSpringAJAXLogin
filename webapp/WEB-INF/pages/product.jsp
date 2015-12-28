<%@page import="com.sopheak.app.entities.User"%>
<%@page import="org.springframework.security.core.context.SecurityContextHolder"%>
<%@page import="org.springframework.security.core.Authentication"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

			<%
				Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
				User user = (User) authentication.getPrincipal();
			%>
			
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<h1>Product List</h1>

	<table cellpadding="0" cellspacing="0" border="1" width="100%">
		<thead>
			<tr>
				<th>#</th>
				<th>Product Name</th>
				<th>Price</th>
				<th>Action</th>
			</tr>
		</thead>
		<tbody>
			<c:set var="count" value="0" scope="page" />
			<c:forEach items="${products}" var="item">
				<tr align="center">
					<c:set var="count" value="${count + 1}" scope="page"/>
					<td><c:out value="${count}" /></td>
					<td><c:out value="${item.productName}" /></td>
					<td><c:out value="${item.unitPrice}" /></td>
					
					 <td>
					 	<% if(user.getRoles().iterator().next().getRoleName().equals("ROLE_ADMIN")){%>
					 		<button onclick="deleteAction(${item.productId})">Delete</button>
					 	<% }else{ %>
					 		<button>View Detail</button>
					 	<%} %>
					 </td> 
					
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
	<script>
		function deleteAction(id){
			$.ajax({
				url:"${pageContext.request.contextPath}/delete",
				method:"POST",
				data:{id:id},
				success:function(data){
					if(data==true){
						window.location.href="${pageContext.request.contextPath}/product";
					}else{
						alert("Oop ! something went wrong ...");
					}
				}
			});
		}
	
	
	</script>
</body>
</html>