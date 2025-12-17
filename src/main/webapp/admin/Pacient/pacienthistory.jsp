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
    <title>Historial de Citas del Paciente</title>
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
        thead {
            background: #2e2e2e;
            color: white;
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
                        <button type="button" class="navbar-toggle" data-toggle="collapse">
                            <span class="sr-only">Toggle navigation</span>
                            <span class="icon-bar"></span>
                            <span class="icon-bar"></span>
                            <span class="icon-bar"></span>
                        </button>
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
                            <h4 class="title">Historial de Citas del Paciente</h4>
                            <p>Paciente: ${pacient.name} ${pacient.lastname}</p>
                        </div>
                        <br><br>
                        <div class="card-content table-responsive">
                            <table class="table table-bordered table-hover">
                                <thead>
                                <th style="text-align: center; vertical-align: middle;">Nombre</th>
                                <th style="text-align: center; vertical-align: middle;">Apellido</th>
                                <th style="text-align: center; vertical-align: middle;">Genero</th>
                                <th style="text-align: center; vertical-align: middle;">DNI</th>
                                <th style="text-align: center; vertical-align: middle;">Fecha de Nacimiento</th>
                                </thead>
                                <tr>
                                    <td style="text-align: center; vertical-align: middle;">${pacient.name}</td>
                                    <td style="text-align: center; vertical-align: middle;">${pacient.lastname}</td>
                                    <td style="text-align: center; vertical-align: middle;">${pacient.gender}</td>
                                    <td style="text-align: center; vertical-align: middle;">${pacient.document_dni}</td>
                                    <td style="text-align: center; vertical-align: middle;">${pacient.day_of_birth}</td>
                                </tr>
                            </table>
                            <br><br><br>
                            <table class="table table-bordered table-hover">
                                <thead>
                                <th style="text-align: center; vertical-align: middle;">Dirección</th>
                                <th style="text-align: center; vertical-align: middle;">Telefono</th>
                                <th style="text-align: center; vertical-align: middle;">Correo</th>
                                <th style="text-align: center; vertical-align: middle;">Enfermedad Preexistente</th>
                                <th style="text-align: center; vertical-align: middle;">Alergia</th>
                                </thead>
                                <tr>
                                    <td style="text-align: center; vertical-align: middle;">${pacient.address}</td>
                                    <td style="text-align: center; vertical-align: middle;">${pacient.phone}</td>
                                    <td style="text-align: center; vertical-align: middle;">${pacient.email}</td>
                                    <td style="text-align: center; vertical-align: middle;">${pacient.sickness}</td>
                                    <td style="text-align: center; vertical-align: middle;">${pacient.alergy}</td>
                                </tr>
                            </table>
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
</body>
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
</html>