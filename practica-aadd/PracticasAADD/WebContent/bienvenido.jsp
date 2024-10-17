<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>

<fmt:setLocale value="${sessionScope.lang}" />

<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title><fmt:message key="bienvenido_title" /></title>
<link rel="stylesheet" href="css/style.css" />
</head>
<div id="header">
	<h1>BlaBlaCar</h1>

		<a href="idioma.ctrl?language=en_GB"><fmt:message key="head_english" /></a>
		<a href="idioma.ctrl?language=es_ES"><fmt:message key="head_spanish" /></a>
		
</div>
<body>

	<div id="section">
		<hr>
		<p>
			<b><fmt:message key="bienvenido_tag1" /></b>
		</p>
		<p>
			<b><fmt:message key="bienvenido_tag2" /></b>
		</p>
		<p>
			<b><fmt:message key="bienvenido_tag3" /></b>
		</p>
		<p>
			<b><fmt:message key="bienvenido_tag4" /></b>
		</p>
		<hr>
		<a href="/PracticasAADD/faces/login.jsp"><fmt:message key="login" /></a>
		<a href="/PracticasAADD/faceletsRegistro.xhtml"><fmt:message
				key="registro" /></a><br>
	</div>
</body>

<div id="footer">
	<b>Aplicaciones Distribuidas 18/19</b>
	<div>
	<a title="Buzón de sugerencias" href="buzon.xhtml"><img src="img/buzon-sugerencias.png" alt="Buzón de sugerencias" style="width: 37.6px; height: 26.1px; "/></a>
	</div>
</div>

</html>