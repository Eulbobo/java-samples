<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<head>
	<title><spring:message code="page.greeting.title"/></title>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<style>
	.error {
		color: red;
		font-weight: bold;
	}
	</style>
</head>
<body>
	<h1><spring:message code="page.greeting.header"/></h1>
	<form:form method="post" action="greeting" modelAttribute="greetingForm">
		<p><spring:message code="page.greeting.id"/><form:input path="id" /> <form:errors path="id" cssClass="error"/>
		</p>
		<p><spring:message code="page.greeting.content"/><form:input path="content" /> <form:errors path="content" cssClass="error"/>
		</p>
		<p>
			<input type="submit" value="<spring:message code="button.submit"/>" /> <input type="reset" value="<spring:message code="button.reset"/>" />
		</p>
	</form:form>
	<p><a href="<spring:url value="/"/>"><spring:message code="general.back.home"/></a></p>
</body>
</html>