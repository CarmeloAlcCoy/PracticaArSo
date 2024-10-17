<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>

<h1>BlaBlaCar</h1>

<div id="languages">

	<div class="inline-block">
	
	<h:form>
		<h:commandButton value="#{msg.head_english}" action="#{beanLocale.setLocale('en')}"/>
		<h:commandButton value="#{msg.head_spanish}" action="#{beanLocale.setLocale('es')}"/>
	</h:form>
		<!-- <img
			src="https://upload.wikimedia.org/wikipedia/commons/thumb/9/9a/Flag_of_Spain.svg/1200px-Flag_of_Spain.svg.png"
			style="width: 25px; height: 17px"> <img
			src="https://upload.wikimedia.org/wikipedia/en/thumb/a/ae/Flag_of_the_United_Kingdom.svg/1280px-Flag_of_the_United_Kingdom.svg.png"
			style="width: 25px; height: 17px"> -->
	</div>

</div>

