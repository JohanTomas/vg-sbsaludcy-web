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
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <meta http-equiv="X-UA-Compatible" content="ie=edge">
  <title>Servicio Basico de Salud</title>
  <!-- CARPETA RESOURCE -->
  <link rel="icon" type="img" href="../resource/sbs.png">

  <!-- CARPETA ASSETS - CSS-->
  <link rel="stylesheet" href="../assets/css/bootstrap.min.css">
  <link rel="stylesheet" href="../assets/css/material-dashboard.css">
  <link rel="stylesheet" href="../assets/css/demo.css">
  <link rel="stylesheet" href="../assets/css/stylel.css">
  <link rel="stylesheet" href="../assets/css/card.css">

  <!-- CARPETA ASEETS - FONT-AWESOME -->
  <link rel="stylesheet" href="../assets/font-awesome/css/font_awesome.css">
  <link rel="stylesheet" href="../assets/font-awesome/css/font-awesome.min.css">
  <link href="https://fonts.googleapis.com/css2?family=Material+Icons" rel="stylesheet">

  <!-- CARPETA ASSETS - JS -->
  <script src="../assets/js/jquery.min.js"></script>

  <!--CARPETA ASSETS - FULLCALENDAR -->
  <link rel="stylesheet" href="../assets/fullcalendar/fullcalendar.min.css">
  <link rel="stylesheet" href="../assets/fullcalendar/fullcalendar.print.css" media="print">

  <!-- SCRIPT ASSETS - FULLCALENDAR -->
  <script src="../assets/fullcalendar/moment.min.js"></script>
  <script src="../assets/fullcalendar/fullcalendar.min.js"></script>

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
    .badge {
      display: inline-block;
      min-width: 10px;
      padding: 3px 7px;
      font-size: 12px;
      font-weight: 700;
      line-height: 1;
      color: #fff;
      text-align: center;
      white-space: nowrap;
      vertical-align: middle;
      background-color: #2cb123;
      border-radius: 10px;
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

      <!-- SCRIPT DE CALENDARIO -->
      <script>
        $(document).ready(function() {
          $('#calendar').fullCalendar({
            header: {
              left: 'prev,next today',
              center: 'title',
              right: 'month,agendaWeek,agendaDay'
            },
            defaultDate: 'Y-m-d',
            eventLimit: true,
            events: true
          });
        });
      </script>

      <br><br><br><br>
      <div class="row">
        <div class="col-md-12">
          <div class="card">
            <div class="card-header" data-background-color="blue">
              <h4 class="title">Bienvenido a la Plataforma</h4>
            </div>
            <div class="card-content table-responsive">
              <div class="main-content">
                <div class="container">
                  <div class="row">
                    <!-- Estudiantes -->
                    <div class="col-md-3">
                      <div class="card-counter primary">
                        <i class="material-icons">sentiment_very_satisfied</i>
                        <span class="count-numbers">${totalReservaciones}</span>
                        <span class="count-name">Citas</span>
                      </div>
                    </div>

                    <!-- Docentes -->
                    <div class="col-md-3">
                      <div class="card-counter danger">
                        <i class="material-icons">psychology</i>
                        <span class="count-numbers">${totalPacientes}</span>
                        <span class="count-name">Pacientes</span>
                      </div>
                    </div>

                    <!-- Padres -->
                    <div class="col-md-3">
                      <div class="card-counter success">
                        <i class="material-icons">supervisor_account</i>
                        <span class="count-numbers">${totalMedicos}</span>
                        <span class="count-name">Medicos</span>
                      </div>
                    </div>

                    <!-- Administradores -->
                    <div class="col-md-3">
                      <div class="card-counter info">
                        <i class="material-icons">person_outline</i>
                        <span class="count-numbers">${totalAdmins}</span>
                        <span class="count-name">Administradores</span>
                      </div>
                    </div>
                  </div>

                  <br>

                  <div class="row">
                    <!-- Reservación de Citas Recientes -->
                    <div class="col-sm-6">
                      <div class="card">
                        <div class="card-body">
                          <h5 class="card-title">Citas recientes
                            <a href="/Contquotes" class="btn btn-success btn-sm">Ver todos</a>
                          </h5>

                          <table class="table table-striped table-hover">
                            <thead class="thead-dark">
                            <tr>
                              <th>Apellidos/Nombres</th>
                              <th>Estado</th>
                            </tr>
                            </thead>
                            <tbody>
                            <c:forEach items="${quotesList}" var="quotes">
                                <tr>
                                    <td>${quotes.pacient_name} ${quotes.pacient_lastname}</td>
                                    <td>
                                        <span class="badge badge-success">Activo</span>
                                    </td>
                                </tr>
                            </c:forEach>
                            </tbody>
                          </table>
                        </div>
                      </div>
                    </div>

                    <!-- Pacientes Recientes -->
                    <div class="col-sm-6">
                      <div class="card">
                        <div class="card-body">
                          <h5 class="card-title">Pacientes recientes
                            <a href="/Contpacient" class="btn btn-success btn-sm">Ver todos</a>
                          </h5>

                          <table class="table table-striped table-hover">
                            <thead class="thead-dark">
                            <tr>
                              <th>Apellidos/Nombres</th>
                              <th>Estado</th>
                            </tr>
                            </thead>
                            <tbody>
                            <c:forEach items="${pacientList}" var="pacient">
                                <tr>
                                    <td>${pacient.name} ${pacient.lastname}</td>
                                    <td>
                                        <span class="badge badge-success">Activo</span>
                                    </td>
                                </tr>
                            </c:forEach>
                            </tbody>
                          </table>
                        </div>
                      </div>
                    </div>

                    <!-- Medicos Recientes -->
                    <div class="col-sm-6">
                      <div class="card">
                        <div class="card-body">
                          <h5 class="card-title">Medicos recientes
                            <a href="/Contmedic" class="btn btn-success btn-sm">Ver todos</a>
                          </h5>

                          <table class="table table-striped table-hover">
                            <thead class="thead-dark">
                            <tr>
                              <th>Apellidos/Nombres</th>
                              <th>Estado</th>
                            </tr>
                            </thead>
                            <tbody>
                            <c:forEach items="${medicList}" var="medic">
                                <tr>
                                    <td>${medic.name} ${medic.lastname}</td>
                                    <td>
                                        <span class="badge badge-success">Activo</span>
                                    </td>
                                </tr>
                            </c:forEach>
                            </tbody>
                          </table>
                        </div>
                      </div>
                    </div>
                  </div>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>

      <div class="row">
        <div class="col-md-12">
          <div class="card">
            <div class="card-header" data-background-color="blue">
              <h4 class="title">Calendario de Citas</h4>
            </div>
            <div class="card-content table-responsive">
              <div id="calendar"></div>
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
  <script src="../assets/js/bootstrap.min.js" type="text/javascript"></script>
  <script src="../assets/js/material.min.js" type="text/javascript"></script>

  <!--  Charts Plugin -->
  <script src="../assets/js/chartist.min.js"></script>

  <!--  Notifications Plugin    -->
  <script src="../assets/js/bootstrap-notify.js"></script>

  <!--  Google Maps Plugin    -->
  <script type="text/javascript" src="https://maps.googleapis.com/maps/api/js"></script>

  <!-- Material Dashboard javascript methods -->
  <script src="../assets/js/material-dashboard.js"></script>

  <!-- Material Dashboard DEMO methods, don't include it in your project! -->
  <script src="../assets/js/demo.js"></script>

  <script type="text/javascript">
    $(document).ready(function() {

      // Javascript method's body can be found in assets/js/demos.js
      demo.initDashboardPageCharts();

    });
  </script>
</body>
</html>