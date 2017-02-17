<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<head>
    <title><spring:message code="page.multipleactions.title"/></title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
</head>
<body>
	<h1><spring:message code="page.multipleactions.result.header"/></h1>
    <p>${content}</p>
    <p><a href="<spring:url value="multipleActions"/>"><spring:message code="page.multipleactions.new.message"/></a></p>
    <p><a href="<spring:url value="/"/>"><spring:message code="general.back.home"/></a></p>
</body>
</html>