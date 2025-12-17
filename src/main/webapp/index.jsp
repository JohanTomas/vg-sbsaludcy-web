<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%
	if (session.getAttribute("authenticatedUser") != null) {
		response.sendRedirect("layout.jsp");
	}
%>

<!doctype html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<meta name="viewport"
		  content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
	<meta http-equiv="X-UA-Compatible" content="ie=edge">
	<title>Red Salud | Sistema</title>
	<!-- CARPETA RESOURCES (IMAGENES) -->
	<link rel="icon" type="img" href="resource/sbs.png">

	<!-- CARPETA ASSETS - CSS-->
	<link rel="stylesheet" href="assets/css/bootstrap.min.css">
	<link rel="stylesheet" href="assets/css/demo.css">
	<link rel="stylesheet" href="assets/css/material-dashboard.css">

	<!-- CARPETA ASEETS - FONT-AWESOME -->
	<link rel="stylesheet" href="../../assets/font-awesome/css/font_awesome.css">
	<link rel="stylesheet" href="../../assets/font-awesome/css/font-awesome.min.css">

	<!-- Incluyendo SweetAlert -->
	<script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>

	<style>
		body{
			background-repeat: no-repeat;
			background-attachment: fixed;
			background-size: cover;
			background-position: top;
			background-image:url(http://bit.ly/2gPLxZ4);
			width: 100%;
			height: 100%;
			font-family: Arial, Helvetica;
			letter-spacing: 0.02em;
			font-weight: 400;
			-webkit-font-smoothing: antialiased;
		}
		.input-group-addon {
			padding: 6px 12px;
			font-size: 14px;
			font-weight: 400;
			line-height: 1;
			color: black;
			text-align: center;
			background-color: #eee;
			border: 1px solid #ccc;
			border-radius: 4px;
		}
		.input-group .form-control:first-child, .input-group-addon:first-child, .input-group-btn:first-child>.btn, .input-group-btn:first-child>.btn-group>.btn, .input-group-btn:first-child>.dropdown-toggle, .input-group-btn:last-child>.btn-group:not(:last-child)>.btn, .input-group-btn:last-child>.btn:not(:last-child):not(.dropdown-toggle) {
			border-top-right-radius: 0;
			border-bottom-right-radius: 0;
		}
		.input-group-addon:first-child {
			border-right: 0;
			background: #cdcaca;
		}
		.form-control {
			display: block;
			width: 100%;
			height: 34px;
			padding: 6px 12px;
			font-size: 14px;
			line-height: 1.42857143;
			color: #555;
			background-color: #fff;
			background-image: none;
			border: 1px solid #ccc;
			border-radius: 4px;
			-webkit-box-shadow: inset 0 1px 1px rgba(0, 0, 0, .075);
			box-shadow: inset 0 1px 1px rgba(0, 0, 0, .075);
			-webkit-transition: border-color ease-in-out .15s, -webkit-box-shadow ease-in-out .15s;
			-o-transition: border-color ease-in-out .15s, box-shadow ease-in-out .15s;
			transition: border-color ease-in-out .15s, box-shadow ease-in-out .15s;
		}
		.login-userinput {
			margin-bottom: 10px;
		}
		.input-group {
			position: relative;
			display: table;
			border-collapse: separate;
		}
		.card .cards-header {
			box-shadow: 0 10px 30px -12px rgba(0, 0, 0, 0.42), 0 4px 25px 0px rgba(0, 0, 0, 0.12), 0 8px 10px -5px rgba(0, 0, 0, 0.2);
			border-radius: 3px;
			padding: 15px;
			background-color: #999999;
		}
		.checkboxs {
			position: relative;
			display: block;
			margin-top: 10px;
			margin-bottom: 10px;
		}
	</style>
</head>
<body>
	<br><br><br><br><br><br>
	<div class="container">
		<div class="row">
			<div class="col-md-4 col-md-offset-4">
				<div class="card">
					<div class="cards-header" data-background-color="blue">
						<h4 class="title">Servicio Basico de Salud <br> Cañete - Yauyos</h4>
					</div>
					<br>
					<div class="card-content table-responsive">
						<form accept-charset="UTF-8" role="form" method="post" action="/Login">
							<!--<fieldset>
								<div class="form-group">
									<input class="form-control" placeholder="Usuario" name="username" type="text">
								</div>
								<div class="form-group">
									<input class="form-control" placeholder="Contraseña" name="password" type="password" value="">
								</div>
								<input class="btn btn-primary btn-block" type="submit" value="Iniciar Sesion">
							</fieldset>-->

							<div class="input-group login-userinput">
								<span class="input-group-addon"><i class="fa fa-user"></i></span>
								<input id="txtUser" type="text" class="form-control" name="username" placeholder="Username">
							</div>
							<div class="input-group">
								<span class="input-group-addon"><i class="fa fa-key"></i></span>
								<input  id="txtPassword" type="password" class="form-control" name="password" placeholder="Password">
							</div>
							<div class="checkboxs login-options">
								<label><input type="checkbox"> Recordar</label>
							</div>
							<button class="btn btn-primary btn-block login-button" type="submit"><i class="fa fa-sign-in"></i> Iniciar Sesión</button>
						</form>
					</div>
				</div>
			</div>
		</div>
	</div>

	<!--  ALERTA DE AGREGAR CON CHET -->
	<% String mensaje = (String) request.getAttribute("mensaje");
		if (mensaje != null && !mensaje.isEmpty()) { %>
		<script>
			swal({
				text: "<%= mensaje %>",
				icon: "error",
				button: "Aceptar",
			});
		</script>
	<% } %>
</body>
</html>
