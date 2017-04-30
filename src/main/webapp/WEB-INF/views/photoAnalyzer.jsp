<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8" name="viewport"
	content="width=device-width, initial-scale=1">
<title>UAN</title>

<link rel="stylesheet" type="text/css"
	href='<c:url value="/web-resources/libs/bootstrap-3.1.1/css/bootstrap.min.css"/>'>
<link rel="stylesheet" type="text/css"
	href='<c:url value="/web-resources/libs/bootstrap-dialog/css/bootstrap-dialog.min.css"/>'>
<link rel="stylesheet" type="text/css"
	href='<c:url value="/web-resources/css/style.css"/>'>

</head>
<body>
<%! static int photoID=4; %>
<%
	if (photoID == 10)
	{photoID=4;}
%>




<div class="container">
		<div class="panel panel-default">
			<div class="panel-heading text-center">
				<h3>Universe Alert notification</h3>
				<h4>(Cataclysm Learning Process)</h4>
				<h4>Based on: Spring MVC + JPA/Hibernate</h4>
			</div>

			<div class="jpg-dz" <%--style="text-align: center"--%> >

				How it works?
				<hr>
				<img src="/web-resources/pictures/Mn.jpg" align="right" style="width: 420px">
			</div>

			<div class="panel-body">
				<div>

					<hr>
					<div class="row">

						<div class="col-xs-2">
							<img src="/web-resources/pictures/<%=photoID%>d.jpg" style="width: 520px">

						</div>

						<div class="col-xs-10">


							<%--<h4 class="media-heading" align="right">What do you think about this photo?</h4>
							<button id="parse-button1" class="btn btn-primary btn-lg pull-right">
								<h4 align="middle">Variant 1</h4>
								<span class="glyphicon glyphicon-picture"></span> Good
							</button>
							<button id="parse-button2" class="btn btn-primary btn-lg pull-right">
								<h4 align="middle">Variant 2</h4>
								<span class="glyphicon glyphicon-picture"></span> Bad
							</button>
							<button id="parse-button3" class="btn btn-primary btn-lg pull-right">
								<h4 align="middle">Variant 3</h4>
								<span class="glyphicon glyphicon-picture"></span> Worse
							</button>--%>

							<form action = "/goPhotoAnalyzer" method="GET" >
								<h4 align="right" >
									<p><b>What do you think about this photo?</b><br/>
								<p><input type="radio" name="associate" value="fire"/> It's a Fire?</p>
								<p><input type="radio" name="associate" value="tornado"/> It's a Tornado? </p>
								<p><input type="radio" name="associate" value="tsunami"/> It's a Tsunami? </p>
								<p><input type="radio" name="associate" value="storm"/> It's a Storm? </p>
								<p><input type="radio" name="associate" value="flood"/> It's a Flood? </p>
								<p><input type="radio" name="associate" value="earthquake"/> It's a Earthquake? </p>
								<p><input type="radio" name="associate" value="blizzard"/> It's a Blizzard? </p>
								<p><input type="radio" name="associate" value="dontknow"/> I don't know </p>
								<input hidden="hidden" name="phID" value="<%=photoID %>">
									<%--<c:set var="photoID" value="${photoID + 1}" scope="session" />
									<c:out value="${photoID}"/>--%>
									<%photoID ++;%>
									<input type="submit"/>
								</h4>

							</form>





						</div>
					</div>
					<div>
				<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
			</div>
		</div>
	</div>


	<script type="text/javascript"
		src='<c:url value="/web-resources/libs/jquery/jquery-2.1.1.js"/>'></script>
	<script type="text/javascript"
		src='<c:url value="/web-resources/libs/bootstrap-3.1.1/js/bootstrap.js"/>'></script>
	<script type="text/javascript"
		src='<c:url value="/web-resources/libs/bootstrap-dialog/js/bootstrap-dialog.min.js"/>'></script>
	<script type="text/javascript"
		src='<c:url value="/web-resources/libs/dropzone.js"/>'></script>
	<script type="text/javascript"
		src='<c:url value="/web-resources/js/app.js"/>'></script>
	<script type="text/javascript"
		src='<c:url value="/web-resources/js/parse.js"/>'></script>
</body>
</html>