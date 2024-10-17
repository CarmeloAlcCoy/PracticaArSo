<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<!DOCTYPE>

<html>
<head>
<meta charset="utf-8">
<f:view locale="#{beanLocale.locale}">
	<f:loadBundle basename="resources.messages" var="msg" />
	<title><h:outputText value="#{msg.login}" /></title>
</f:view>
<link rel="stylesheet" href="css/style.css">
</head>
<body>
<f:view>
	<div id="header">
		<%@ include file="divHeader.jsp"%>
	</div>
	<div id="nav">
		<%@ include file="divNav.jsp"%>
	</div>
	<div id="section">

	<div style="text-align: center;">
		<h3>
			<h:outputText value="#{msg.login}" />
		</h3>
	</div>
	<hr>
	<h:form id="LoginForm">
		<h:panelGrid columns="2">
		<h:outputLabel value="#{msg.usuario}: " for="usuario" />
		<h:inputText id="usuario" value="#{beanLogin.usuario}" required="true">
			<f:validateLength maximum="10" />
		</h:inputText>
		<h:message for="usuario" />
		<p>
			<h:outputText value="#{msg.clave}: " />
			<h:inputSecret id="clave" value="#{beanLogin.clave}" required="true" />
			<h:message for="clave" />
		</p>
		<h:commandButton id="submit" action="#{beanLogin.validacion}"
			value="#{msg.login}" />
		<br>
		<br>
		<h:outputLink value="https://www.um.es/informatica">
			<h:graphicImage
				value="https://www.um.es/informatica/images/fium-logo-negativo-web.png"
				title="Index" height="30" />
		</h:outputLink>
		</h:panelGrid>
	</h:form>

<hr>
	</div>
	</f:view>
	<div id="footer">
		<%@ include file="divFooter.jsp"%>
	</div>
</body>
</html>