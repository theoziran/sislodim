<?xml version='1.0' encoding='ISO-8859-1' ?>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd" >
<html xmlns="http://www.w3.org/1999/xhtml"
   xmlns:ui="http://java.sun.com/jsf/facelets"
   xmlns:f="http://java.sun.com/jsf/core"
   xmlns:h="http://java.sun.com/jsf/html"
   xmlns:a4j="http://richfaces.org/a4j"
   xmlns:rich="http://richfaces.org/rich">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />
<title>Insert title here</title>
</head>
<body>
<h:outputText value="Autenticação"></h:outputText>
<a4j:form>

	<rich:panel header="RichFaces Greeter" style="width: 315px">

		<h:outputText value="Your name: " />

		<h:inputText value="#{param.name}">

			<f:validateLength minimum="1" maximum="30" />

		</h:inputText>

	</rich:panel>

</a4j:form>

</body>
</html>
