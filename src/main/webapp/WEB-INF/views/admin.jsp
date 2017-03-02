<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@include file="/WEB-INF/views/template/header.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<div class="container-wrapper">
	<div class="container">
		<div class="page-header">
			<h1>Administrator Page</h1>
			<p class="lead">Checkout all the awesome products available nav!</p>
		</div>

		<h3>
			<a href="<c:url value ="/admin/productInventory" />">Inventory</a>
		</h3>
		<p>Here you can view, check and modify the product inventory!</p>


		<%@ include file="/WEB-INF/views/template/footer.jsp"%>
	</div>
</div>
</html>


