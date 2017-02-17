<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<head>
    <title><spring:message code="page.result.title"/></title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
</head>
<body>
	<h1><spring:message code="page.result.header"/></h1>
    <p>${greetingForm.id}</p>
    <p>${greetingForm.content}</p>
    <p><a href="<spring:url value="/greeting"/>"><spring:message code="page.result.new.message"/></a></p>
    <p><a href="<spring:url value="/"/>"><spring:message code="general.back.home"/></a></p>
</body>
</html>