<!DOCTYPE html>

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<html>

	<head>
		<script type="text/javascript" src="jquery-1.10.2.js"></script>
	</head>
	
	<body>
		<div id="messageReceiverDiv">
			<textarea id="messageArea"></textarea>
		</div>
		<div id="messageSenderDiv">
			<form:form method="post" action="send-all" commandName="send-all">
				<input type="submit"></input>
			</form:form>
		</div>
	</body>
	<script type="text/javascript">
		// <!--

		// -->
	</script>
</html>