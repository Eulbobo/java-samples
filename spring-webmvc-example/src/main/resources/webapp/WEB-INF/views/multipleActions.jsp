<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<head>
	<title><spring:message code="page.multipleactions.title"/></title>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
</head>
<body>
	<h1><spring:message code="page.multipleactions.header"/></h1>
	<form:form method="post" action="multipleActions">
		<p>
			<input type="submit" name="bouton_validation" value="Validation"  /><br/> 
			<input type="submit" name="bouton_annulation" value="Annulation" /><br/>
			<input type="submit" name="bouton" value="Action1" /><br/>
			<input type="submit" name="bouton" value="Action2" /><br/>
			<input type="submit" name="bouton" value="Action3" /><br/>
			<input type="submit" name="bouton" value="Action4" />
		</p>
	</form:form>
</body>
</html>