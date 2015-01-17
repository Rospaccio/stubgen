<!DOCTYPE html>

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<html>
<body>
	<h1 style="text-align: center;">Instance generator</h1>
	<p style="display: none;">JSESSIONID=${jsessionid}</p>
	<form:form method="post" enctype="multipart/form-data"
		modelAttribute="uploadedFile" action="submitFile">
		<div style="text-align: center;">

			<input type="file" name="uploadedFile" />
			<!--  -->
			<input type="submit" value="Upload JAR file" />

		</div>
	</form:form>

	<c:if test="${ ! empty stubClasses }">
		<div id="classReportDiv" style="width: 100%;">

			<div id="stubClassesReport" style="width: 40%; float: left;">
				<form:form method="post" action="instantiate"
					commandName="stubClasses">
					<h2>Available classes:</h2>

					<c:forEach items="${stubClasses}" var="clazz" varStatus="status">

						<div>
							<h4>${clazz.name}</h4>
							<div style="width: 50%; float: left;">Loadable:</div>
							<div style="width: 50%; float: left;">${clazz.loadable}</div>
							<div style="width: 50%; float: left;">Instantiable:</div>
							<div>${clazz.instantiable}</div>
							<c:if test="${clazz.instantiable}">
								<div>
									<form:button id="instantiateButton" name="className"
										value="${clazz.name}">
										Get an instance
									</form:button>
								</div>
							</c:if>
						</div>

					</c:forEach>
				</form:form>
			</div>

			<div id="plainTextOutputDiv" style="width: 60%; float: left;">
				<h4>JSON serialization</h4>
				<textarea id="plainTextArea" style="width: 99%; height: 450px;">
					${plainText}
				</textarea>
			</div>
		</div>
	</c:if>



</body>
</html>