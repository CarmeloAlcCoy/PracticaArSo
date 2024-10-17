<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<f:view>
	<f:loadBundle basename="resources.messages" var="msg" />
	<title><h:outputText value="#{msg.aplicacion}" /></title>
</f:view>
<link rel="stylesheet" href="css/style.css">
</head>
<f:view>
	<body>
		<div id="header">
			<%@ include file="divHeader.jsp"%>
		</div>
		<div id="nav">
			<%@ include file="divNav.jsp"%>
		</div>
		<div id="section">

			<f:loadBundle basename="resources.messages" var="msg" />
			<div style="text-align: center;">
				<h3>
					<h:outputText value="#{msg.falloLoginTitulo}" />
				</h3>
			</div>
			<hr>
			<h:form id="LoginForm">
				<b><h:outputText value="#{msg.falloLogin}" /></b>
				<br>
				<br>
			</h:form>

		</div>
</f:view>
<div id="footer">
	<%@ include file="divFooter.jsp"%>
</div>
</body>
</html>