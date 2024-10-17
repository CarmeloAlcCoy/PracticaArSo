<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
pageEncoding="ISO-8859-1"%> <%@ taglib
uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%> <%@ taglib
uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>

<fmt:setLocale value="${sessionScope.lang}" />

<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Error 404 Not Found</title>
<link rel="stylesheet" href="css/style.css" />
</head>
<div id="header">
	<h1>BlaBlaCar</h1>

	<a href="error.jsp/idioma.ctrl?language=en_GB"><fmt:message key="head_english" /></a>
	<a href="error.jsp/idioma.ctrl?language=es_ES"><fmt:message key="head_spanish" /></a>
</div>
<body>

	<div id="section">
		<h1>
			<span class="">404</span> <fmt:message key="uy" />
		</h1>
		<h2><fmt:message key="estadoCerca" />.</h2>
		<a href="inicio.ctrl"><fmt:message key="inicio" /></a>
	</div>
</body>
<div id="footer">
	<b>Aplicaciones Distribuidas 18/19</b>
</div>

</html>

