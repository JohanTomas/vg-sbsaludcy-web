<%--
  Created by IntelliJ IDEA.
  User: USUARIO
  Date: 2/07/2024
  Time: 13:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Receta Medicas | Sistema</title>
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

    <!-- Incluyendo SweetAlert -->
    <script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>

    <style>
        .row {
            margin-right: 0px;
            margin-left: 0px;
        }
        .sidebar .nav li > a,
        .off-canvas-sidebar .nav li > a {
            margin: 10px 15px;
            border-radius: 3px;
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
                        <h4 class="title">Nueva Receta</h4>
                    </div>
                    <div class="card-content table-responsive">

                        <form class="form-horizontal" method="post" id="addproduct" action="/Contnewpr" role="form">
                            <div class="form-group">
                                <label for="inputEmail1" class="col-lg-2 control-label">Nombre Cita</label>
                                <div class="col-md-6">
                                    <select name="reservation_id" class="form-control" required>
                                        <option value="">-- SELECCIONAR --</option>
                                        <c:forEach items="${quotesList}" var="quotes">
                                            <option value="${quotes.id}">${quotes.reservation_title}</option>
                                        </c:forEach>
                                    </select>
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="inputEmail1" class="col-lg-2 control-label">Doctor</label>
                                <div class="col-md-6">
                                    <input type="text" id="medicName" name="medicName" class="form-control" readonly>
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="inputEmail1" class="col-lg-2 control-label">Paciente</label>
                                <div class="col-md-6">
                                    <input type="text" id="pacientName" name="pacientName" class="form-control" readonly>
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="inputEmail1" class="col-lg-2 control-label">Enfermedad</label>
                                <div class="col-md-6">
                                    <textarea class="form-control" name="sickness" required placeholder="Enfermedad"></textarea>
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="inputEmail1" class="col-lg-2 control-label">Medicamentos</label>
                                <div class="col-md-6">
                                    <textarea class="form-control" name="medicaments" required placeholder="Medicamentos"></textarea>
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="inputEmail1" class="col-lg-2 control-label">Costo</label>
                                <div class="col-md-6">
                                    <div class="input-group">
                                        <span class="input-group-addon"><i class="fa fa-usd"></i></span>
                                        <input type="text" class="form-control" name="price" required placeholder="Costo">
                                    </div>
                                </div>
                            </div>
                            <div class="form-group">
                                <div class="col-lg-offset-2 col-lg-10">
                                    <button type="submit" class="btn btn-primary">Agregar Cita</button>
                                    <a href="/Contprescription" class="btn btn-primary">Cancelar</a>
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
<!-- Llamar a nombre de medico y paciente por el titulo de la cita! -->
<script>
    // Función para actualizar los campos del paciente y del doctor
    function update() {
        var quotesList = ${quotesList}; // Lista de citas con detalles
        var quetesSelect = document.getElementById("reservation_id");
        var selectedquotesId = quetesSelect.options[quetesSelect.selectedIndex].value;

        var medicNameInput = document.getElementById("medicName");
        var pacientNameInput = document.getElementById("pacientName");

        // Buscar la cita seleccionada y actualizar los campos
        quotesList.forEach(function(quotes) {
            if (quotes.id == selectedquotesId) {
                medicNameInput.value = quotes.medicName;
                pacientNameInput.value = quotes.pacientName;
            }
        });
    }
</script>
</body>
</html>
