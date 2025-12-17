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
    <title>Cita Medicas | Sistema</title>
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
                    <div class="btn-group pull-right">
                    </div>

                    <div class="card">
                        <div class="card-header" data-background-color="blue">
                            <h4 class="title">Citas</h4>
                        </div>
                        <div class="card-content table-responsive">
                            <a href="/Contnewpm" class="btn btn-primary"><i class="fa fa-calendar"></i> Nueva Cita</a>
                            <a href="/q_restore" class="btn btn-primary">Citas Anteriores</a>
                            <br><br>
                            <form action="" class="form-horizontal" role="form">
                                <input type="hidden" name="view" value="reservations">

                                <div class="form-group">
                                    <div class="col-lg-2">
                                        <div class="input-group">
                                            <span class="input-group-addon"><i class="fa fa-search"></i></span>
                                            <input type="text" name="q" value="Palabra clave" class="form-control" placeholder="Palabra clave">
                                        </div>
                                    </div>
                                    <div class="col-lg-2">
                                        <div class="input-group">
                                            <span class="input-group-addon"><i class="fa fa-male"></i></span>
                                            <select name="pacient_id" class="form-control">
                                                <option value="">PACIENTE</option>
                                                <c:forEach items="${pacientList}" var="pacient">
                                                    <option value="${pacient.id}">${pacient.name} ${pacient.lastname}</option>
                                                </c:forEach>
                                            </select>
                                        </div>
                                    </div>
                                    <div class="col-lg-2">
                                        <div class="input-group">
                                            <span class="input-group-addon"><i class="fa fa-support"></i></span>
                                            <select name="medic_id" class="form-control">
                                                <option value="">MEDICO</option>
                                                <c:forEach items="${medicList}" var="medic">
                                                    <option value="${medic.id}">${medic.name} ${medic.lastname}</option>
                                                </c:forEach>
                                            </select>
                                        </div>
                                    </div>
                                    <div class="col-lg-4">
                                        <div class="input-group">
                                            <span class="input-group-addon"><i class="fa fa-calendar"></i></span>
                                            <input type="date" name="date_at" value="Palabra clave" class="form-control" placeholder="Palabra clave">
                                        </div>
                                    </div>

                                    <div class="col-lg-2">
                                        <button class="btn btn-primary btn-block">Buscar</button>
                                    </div>
                                </div>
                            </form>

                            <table class="table table-bordered table-hover">
                                <thead>
                                    <th style="text-align: center; vertical-align: middle;">ID</th>
                                    <th style="text-align: center; vertical-align: middle;">Asunto</th>
                                    <th style="text-align: center; vertical-align: middle;">Paciente</th>
                                    <th style="text-align: center; vertical-align: middle;">Medico</th>
                                    <th style="text-align: center; vertical-align: middle;">Fecha</th>
                                    <th style="text-align: center; vertical-align: middle;">Hora</th>
                                    <th style="text-align: center; vertical-align: middle;">Acciones</th>
                                </thead>
                                <c:forEach items="${quotesList}" var="quotes">
                                    <tr>
                                        <td style="text-align: center; vertical-align: middle;">${quotes.id}</td>
                                        <td style="text-align: center; vertical-align: middle;">${quotes.title}</td>
                                        <td style="text-align: center; vertical-align: middle;">${quotes.pacient_name} ${quotes.pacient_lastname}</td>
                                        <td style="text-align: center; vertical-align: middle;">${quotes.medic_name} ${quotes.medic_lastname}</td>
                                        <td style="text-align: center; vertical-align: middle;">${quotes.date_at}</td>
                                        <td style="text-align: center; vertical-align: middle;">${quotes.time_at}</td>
                                        <td style="width:180px; text-align: center; vertical-align: middle;">
                                            <a href="/Conteditq?id=${quotes.id}" class="btn btn-warning btn-xs">Editar</a>
                                            <a href="/Contdeleteq?id=${quotes.id}" class="btn btn-danger btn-xs delete-btn">Eliminar</a>
                                        </td>
                                    </tr>
                                </c:forEach>
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

    <!--  ALERTA DE AGREGAR CON CHET -->
    <% String mensaje = request.getParameter("mensaje");
        if (mensaje != null && !mensaje.isEmpty()) { %>
    <script>
        swal({
            title: "<%= mensaje %>",
            icon: "success",
            button: "Aceptar",
        });
    </script>
    <% } %>

    <!-- ALERTA DE CANCELAR O ELIMINAR -->
    <script type="text/javascript">
        $(document).ready(function() {
            $('.delete-btn').on('click', function(e) {
                e.preventDefault();
                const deleteUrl = $(this).attr('href');

                swal({
                    title: "¿Deseas eliminar la Cita?",
                    icon: "warning",
                    buttons: true,
                    dangerMode: true,
                })
                    .then((willDelete) => {
                        if (willDelete) {
                            window.location.href = deleteUrl;
                        } else {
                            swal("Eliminación cancelada!");
                        }
                    });
            });

            // Mostrar mensaje de eliminación si está presente
            const mensajedelete = '<%= request.getParameter("mensajedelete") %>';
            if (mensajedelete && mensajedelete !== 'null' && mensajedelete !== '') {
                swal({
                    title: mensajedelete,
                    icon: "success",
                    button: "Aceptar",
                });
            }
        });
    </script>
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