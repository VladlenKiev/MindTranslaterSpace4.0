<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8" name="viewport"
	content="width=device-width, initial-scale=1">
<title>Spring MVC + Dropzone.js Example</title>

<link rel="stylesheet" type="text/css"
	href='<c:url value="/web-resources/libs/bootstrap-3.1.1/css/bootstrap.min.css"/>'>
<link rel="stylesheet" type="text/css"
	href='<c:url value="/web-resources/libs/bootstrap-dialog/css/bootstrap-dialog.min.css"/>'>
<link rel="stylesheet" type="text/css"
	href='<c:url value="/web-resources/css/style.css"/>'>

</head>
<body>
	<div class="container">
		<div class="panel panel-default">
			<div class="panel-heading text-center">
				<h3>Universe Alert notification</h3>
				<h4>Based on: Spring MVC + JPA/Hibernate + Dropzone.js</h4>
			</div>
			<div class="panel-body">
				<div>
					<form id="dropzone-form" class="dropzone"
						enctype="multipart/form-data">

						<div
							class="dz-default dz-message file-dropzone text-center well col-sm-12">

							<span class="glyphicon glyphicon-paperclip"></span> <span>
								To attach files, drag and drop here</span><br> <span>OR</span><br>
							<span>Just Click</span>
						</div>

						<!-- this is were the previews should be shown. -->
						<div class="dropzone-previews"></div>
					</form>
					<hr>
					<button id="upload-button123" class="btn btn-primary">
						<span class="glyphicon glyphicon-upload"></span> Upload
					</button>

					<a class="btn btn-primary pull-right" href="list">
						<span class="glyphicon glyphicon-eye-open"></span> View All Uploads
					</a>
				</div>
				<hr>
				<button id="parse-button" class="btn btn-info btn-lg">
					<span class="glyphicon glyphicon-check"></span> Parse
				</button>
				<a class="btn btn-success pull-right btn-lg disabled" href="analyze">
					<span class="glyphicon glyphicon-print"></span> View Analyzed
				</a>
				<hr>
				<button type="button" class="btn btn-block btn-lg">INFO</button>


			</div>

			<div class="btn-group">
				<button class="btn btn-large btn-danger">Action</button>
				<button class="btn btn-mini dropdown-toggle" data-toggle = "dropdown">
					<span class="caret"></span>
				</button>
				<uL class="dropdown-menu" role="menu">
					<li><a href="#"> first</a></li>
					<li><a href="#"> second</a></li>
					<li><a href="#"> third</a></li>
				</uL>
			</div>
		</div>
	<div class="alert alert-success fade in" id="my-alert">
		<strong> Succes! </strong> Files successful uploaded!
		<button type="button" class="close" data-dismiss="alert">&times;</button>
	</div>
		<a class="alert-link" href="parse" id="myLink">
			Add To Cart
		</a>
	</div>

	@Html.ActionLink("Add To Cart", "AddToCart", null, new { id="myLink"})


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
</body>
</html>