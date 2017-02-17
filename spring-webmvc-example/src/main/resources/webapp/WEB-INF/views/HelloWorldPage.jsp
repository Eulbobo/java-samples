<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<html>
<head>
<title><spring:message code="page.home.title"/></title>
</head>
<body>
	<h2>${message}</h2>
	
	<h2><spring:message code="page.home.functions"/></h2>
	<ul>
		<li><a href="<spring:url value="greeting"/>"><spring:message code="page.home.function.greetings"/></a></li>
		<li><a href="<spring:url value="multipleActions"/>"><spring:message code="page.home.function.multipleactions"/></a></li>
	</ul>
</body>
</html>