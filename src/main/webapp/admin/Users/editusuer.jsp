<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%
    if (session.getAttribute("authenticatedUser") == null) {
        response.sendRedirect("/index.jsp");
    }
%>

<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title> Editar Usuario</title>
    <!-- CARPETA RESOURCE -->
    <link rel="icon" type="img" href="../../resource/sbs.png">

    <!-- CARPETA ASSETS - CSS-->
    <link rel="stylesheet" href="../../assets/css/bootstrap.min.css">
    <link rel="stylesheet" href="../../assets/css/material-dashboard.css">
    <link rel="stylesheet" href="../../assets/css/demo.css">
    <link rel="stylesheet" href="../../assets/css/stylel.css">

    <!-- CARPETA ASEETS - FONT-AWESOME -->
    <link rel="stylesheet" href="../../assets/font-awesome/css/font_awesome.css">
    <link rel="stylesheet" href="../../assets/font-awesome/css/font-awesome.min.css">

    <!-- CARPETA ASSETS - JS -->
    <script src="../../assets/js/jquery.min.js" type="text/javascript"></script>

    <style>
        .row {
            margin-right: 0px;
            margin-left: 0px;
        }
        .sidebar .nav li > a,
        .off-canvas-sidebar .nav li > a {
            color: white;
        }
        .sidebar .nav i, .off-canvas-sidebar .nav i {
            color: white;
        }
        .sidebar .nav p, .off-canvas-sidebar .nav p {
            color: white;
        }
        .sidebar .logo .simple-text, .off-canvas-sidebar .logo .simple-text {
            color: white;
        }
        .card [data-background-color="blue"] {
            background: #2e2e2e;
            box-shadow: 0 12px 20px -10px rgba(0, 188, 212, 0.28), 0 4px 20px 0px rgba(0, 0, 0, 0.12), 0 7px 8px -5px rgba(0, 188, 212, 0.2);
        }
    </style>
</head>
<body>
    <div class="wrapper">

        <div class="sidebar" data-color="blue">
            <div class="logo">
                <a href="/platform-initial" class="simple-text">
                    SBS-CY
                </a>
            </div>

            <div class="sidebar-wrapper">
                <ul class="nav">
                    <li class="">
                        <a href="/platform-initial">
                            <i class="fa fa-home"></i>
                            <p>INICIO</p>
                        </a>
                    </li>
                    <li>
                        <a href="/Contquotes">
                            <i class="fa fa-calendar"></i>
                            <p>CITAS</p>
                        </a>
                    </li>
                    <li>
                        <a href="/Contpacient">
                            <i class="fa fa-male"></i>
                            <p>PACIENTES</p>
                        </a>
                    </li>
                    <li>
                        <a href="/Contmedic">
                            <i class="fa fa-support"></i>
                            <p>MEDICOS</p>
                        </a>
                    </li>
                    <li>
                        <a href="/Contcategory">
                            <i class="fa fa-th-list"></i>
                            <p>CATEGORIAS</p>
                        </a>
                    </li>
                    <!--li>
                        <a href="admin/Report/reports.jsp">
                            <i class="fa fa-area-chart"></i>
                            <p>REPORTE DE CITAS</p>
                        </a>
                    </li -->
                    <li>
                        <a href="admin/Prescription/prescription.jsp">
                            <i class="fa fa-area-chart"></i>
                            <p>RECETAS</p>
                        </a>
                    </li>
                    <li>
                        <a href="/Contuser">
                            <i class="fa fa-users"></i>
                            <p>USUARIOS</p>
                        </a>
                    </li>
                </ul>
            </div>
        </div>

        <div class="main-panel">
            <nav class="navbar navbar-transparent navbar-absolute">
                <div class="container-fluid">
                    <div class="navbar-header">
                        <a class="navbar-brand" href="/platform-initial"><b>Sistema de Citas Medicas</b></a>
                    </div>
                    <div class="collapse navbar-collapse">
                        <ul class="nav navbar-nav navbar-right">
                            <li class="dropdown">
                                <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                                    <i class="fa fa-user"></i>
                                </a>
                                <ul class="dropdown-menu">
                                    <li><a href="#">Perfil</a></li>
                                    <li><a href="#">Configuración</a></li>
                                    <li><a href="/Logout">Cerra Sesión</a></li>
                                </ul>
                            </li>
                        </ul>
                    </div>
                </div>
            </nav>

            <br><br><br><br>
            <div class="row">
                <div class="col-md-12">
                    <div class="card">
                        <div class="card-header" data-background-color="blue">
                            <h4 class="title">Editar Usuario</h4>
                        </div>
                        <div class="card-content table-responsive">

                            <form class="form-horizontal" method="post" id="addproduct" action="/Contactu" role="form">
                                <input type="hidden" name="user_id" value="${user.id}">

                                <div class="form-group">
                                    <label for="inputEmail1" class="col-lg-2 control-label">Nombre*</label>
                                    <div class="col-md-6">
                                        <input type="text" name="name" required class="form-control" id="name" placeholder="Nombre" value="${user.name}">
                                        <span id="name-error" class="error-message">El nombre solo debe contener letras</span>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label for="inputEmail1" class="col-lg-2 control-label">Apellido*</label>
                                    <div class="col-md-6">
                                        <input type="text" name="lastname" required class="form-control" id="lastname" placeholder="Apellido" value="${user.lastname}">
                                        <span id="lastname-error" class="error-message">El apellido solo debe contener letras</span>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label for="inputEmail1" class="col-lg-2 control-label">Nombre de usuario*</label>
                                    <div class="col-md-6">
                                        <input type="text" name="username" required class="form-control" id="username" placeholder="Nombre de usuario" value="${user.username}">
                                        <span id="username-error" class="error-message">El nombre de usuario solo debe contener letras</span>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label for="inputEmail1" class="col-lg-2 control-label">Email*</label>
                                    <div class="col-md-6">
                                        <input type="text" name="email" required class="form-control" id="email" placeholder="Email" value="${user.email}">
                                        <span id="email-error" class="error-message">El correo electronico debe ser válido</span>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <label for="inputEmail1" class="col-lg-2 control-label">Contraseña</label>
                                    <div class="col-md-6">
                                        <input type="password" name="password" required class="form-control" id="inputEmail1" placeholder="Contraseña" value="${user.password}">
                                        <p class="help-block">La contraseña solo se modificara si escribes algo, en caso contrario no se modifica.</p>
                                    </div>
                                </div>

                                <input type="hidden" name="is_active" value="${user.is_active}" checked readonly>

                                <div class="form-group">
                                    <label for="inputEmail1" class="col-lg-2 control-label">Labor o Rol*</label>
                                    <div class="col-md-6">
                                        <select name="is_admin" class="form-control" required>
                                            <option value="">-- SELECCIONAR --</option>
                                            <option value="ADMINISTRADOR" <c:if test="${user.is_admin == 'ADMINISTRADOR'}">selected</c:if>>ADMINISTRADOR</option>
                                            <option value="TRABAJADOR" <c:if test="${user.is_admin == 'TRABAJADOR'}">selected</c:if>>TRABAJOR</option>
                                        </select>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <div class="col-lg-offset-2 col-lg-10">
                                        <button type="submit" class="btn btn-primary">Actualizar Usuario</button>
                                        <a href="/Contuser" class="btn btn-primary">Cancelar</a>
                                    </div>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
            </div>

            <footer class="footer">
                <div class="container-fluid">
                    <nav class="pull-left">
                        <ul>
                            <li>
                                <a href="#">
                                    Log de cambios
                                </a>
                            </li>
                            <li>
                                <a target="_blank">
                                    Evilnapsis
                                </a>
                            </li>
                        </ul>
                    </nav>
                    <p class="copyright pull-right">
                        <a target="_blank">SBS-CY</a> &copy; 2024
                    </p>
                </div>
            </footer>
        </div>
    </div>

    <script>
        document.addEventListener('DOMContentLoaded', function() {
            const nameField = document.getElementById('name');
            const lastnameField = document.getElementById('lastname');
            const usernameField = document.getElementById('username');
            const emailField = document.getElementById('email');

            const nameError = document.getElementById('name-error');
            const lastnameError = document.getElementById('lastname-error');
            const usernameError = document.getElementById('username-error');
            const emailError = document.getElementById('email-error');

            function validateText(field, errorElement) {
                const regex = /^[a-zA-ZáéíóúÁÉÍÓÚñÑ\s]+$/;
                if (regex.test(field.value)) {
                    field.classList.add('valid');
                    field.classList.remove('invalid');
                    errorElement.classList.remove('active');
                } else {
                    field.classList.add('invalid');
                    field.classList.remove('valid');
                    errorElement.classList.add('active');
                }
            }

            function validateTextUser(field, errorElement) {
                const regex = /^[a-zA-Z\s]+$/;
                if (regex.test(field.value)) {
                    field.classList.add('valid');
                    field.classList.remove('invalid');
                    errorElement.classList.remove('active');
                } else {
                    field.classList.add('invalid');
                    field.classList.remove('valid');
                    errorElement.classList.add('active');
                }
            }

            function validateEmail(field, errorElement) {
                const regex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
                if (regex.test(field.value)) {
                    field.classList.add('valid');
                    field.classList.remove('invalid');
                    errorElement.classList.remove('active');
                } else {
                    field.classList.add('invalid');
                    field.classList.remove('valid');
                    errorElement.classList.add('active');
                }
            }

            nameField.addEventListener('input', () => validateText(nameField, nameError));
            lastnameField.addEventListener('input', () => validateText(lastnameField, lastnameError));
            usernameField.addEventListener('input', () => validateTextUser(usernameField, usernameError));
            emailField.addEventListener('input', () => validateEmail(emailField, emailError));
        });
    </script>

    <!--   Core JS Files   -->
    <script src="../../assets/js/bootstrap.min.js" type="text/javascript"></script>
    <script src="../../assets/js/material.min.js" type="text/javascript"></script>

    <!--  Charts Plugin -->
    <script src="../../assets/js/chartist.min.js"></script>

    <!--  Notifications Plugin    -->
    <script src="../../assets/js/bootstrap-notify.js"></script>

    <!--  Google Maps Plugin    -->
    <script type="text/javascript" src="https://maps.googleapis.com/maps/api/js"></script>

    <!-- Material Dashboard javascript methods -->
    <script src="../../assets/js/material-dashboard.js"></script>

    <!-- Material Dashboard DEMO methods, don't include it in your project! -->
    <script src="../../assets/js/demo.js"></script>

    <script type="text/javascript">
        $(document).ready(function() {

            // Javascript method's body can be found in assets/js/demos.js
            demo.initDashboardPageCharts();

        });
    </script>
</body>
</html>