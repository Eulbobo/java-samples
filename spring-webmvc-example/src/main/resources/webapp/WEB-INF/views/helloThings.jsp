<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<head>
    <title><spring:message code="page.hellothings.title"/></title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
</head>
<body>
	<h1><spring:message code="page.hellothings.header"/></h1>
	<form:form action="thingsAndStuff" method="post" modelAttribute="thingParam">
		<p><spring:message code="page.hellothings.value"/> : ${thingParam.name}</p>
    	<form:select path="name">
    		<form:option value=""/>
	    	<form:options items="${things}"/>
    	</form:select>
    	<input type="submit">
    </form:form>
</body>
</html>