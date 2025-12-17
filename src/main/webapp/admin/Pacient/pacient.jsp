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
    <title>Pacientes | Sistema</title>
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
    <script src="../../assets/js/jquery.min.js"></script>

    <!-- Incluyendo SweetAlert -->
    <script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>

    <!-- Incluyendo jsPDF y autoTable -->
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jspdf/2.5.1/jspdf.umd.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jspdf-autotable/3.5.23/jspdf.plugin.autotable.min.js"></script>

    <!-- Incluyendo SheetJS -->
    <script src="https://cdnjs.cloudflare.com/ajax/libs/xlsx/0.17.3/xlsx.full.min.js"></script>

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
                                    <li><a href="/Logout">Cerrar Sesión</a></li>
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
                        <button class="btn btn-facebook" onclick="exportToCSV()">CSV</button>
                        <button class="btn btn-success" onclick="exportToXLS()">EXCEL</button>
                        <button class="btn btn-danger" onclick="exportTableToPDF()">PDF</button>
                    </div>
                    <div class="card">
                        <div class="card-header" data-background-color="blue">
                            <h4 class="title">Pacientes</h4>
                        </div>
                        <div class="card-content table-responsive">
                            <a href="admin/Pacient/newpacient.jsp" class="btn btn-primary"><i class='fa fa-male'></i> Nuevo Paciente</a>
                            <a href="/Contp_restore" class="btn btn-primary">Restaurar Paciente</a>
                            <!-- Formulario de búsqueda -->
                            <div class="form-group">
                                <input type="text" id="searchInput" class="form-control" placeholder="Buscar por Nombre y Apellido, DNI o Dirección...">
                            </div>
                            <table id="patientsTable" class="table table-bordered table-hover">
                                <thead>
                                    <th style="text-align: center; vertical-align: middle;">ID</th>
                                    <th style="text-align: center; vertical-align: middle;">Nombre completo</th>
                                    <th style="text-align: center; vertical-align: middle;">DNI</th>
                                    <th style="text-align: center; vertical-align: middle;">Dirección</th>
                                    <th style="text-align: center; vertical-align: middle;">Email</th>
                                    <th style="text-align: center; vertical-align: middle;">Telefono</th>
                                    <th style="text-align: center; vertical-align: middle;">Acciones</th>
                                </thead>
                                <c:forEach items="${pacientList}" var="pacient">
                                    <tr>
                                        <td style="text-align: center; vertical-align: middle;">${pacient.id}</td>
                                        <td>${pacient.name} ${pacient.lastname}</td>
                                        <td style="text-align: center; vertical-align: middle;">${pacient.document_dni}</td>
                                        <td style="text-align: center; vertical-align: middle;">${pacient.address}</td>
                                        <td style="text-align: center; vertical-align: middle;">${pacient.email}</td>
                                        <td style="text-align: center; vertical-align: middle;">${pacient.phone}</td>
                                        <td style="width:280px; text-align: center; vertical-align: middle;">
                                            <a href="/Contp_history?id=${pacient.id}" class="btn btn-primary btn-xs">Historial</a>
                                            <a href="/Conteditp?id=${pacient.id}" class="btn btn-warning btn-xs">Editar</a>
                                            <a href="/Contdeletep?id=${pacient.id}" class="btn btn-danger btn-xs delete-btn">Eliminar</a>
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
            // Añadiendo evento de clic a los botones de eliminar
            $('.delete-btn').on('click', function(e) {
                e.preventDefault();  // Prevenir la redirección inmediata
                const deleteUrl = $(this).attr('href');

                swal({
                    title: "¿Deseas eliminar el Paciente?",
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

            // Filtro de búsqueda
            $('#searchInput').on('keyup', function() {
                var value = $(this).val().toLowerCase();
                $('#patientsTable tbody tr').filter(function() {
                    $(this).toggle($(this).text().toLowerCase().indexOf(value) > -1);
                });
            });
        });
    </script>

    <!--   Core JS Files   -->
    <script src="../../assets/js/bootstrap.min.js"></script>
    <script src="../../assets/js/material.min.js"></script>

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

        function exportToCSV() {
            const rows = document.querySelectorAll("#patientsTable tbody tr");
            const csvData = [['Nombre completo', 'DNI', 'Dirección', 'Email', 'Teléfono']];
            rows.forEach(row => {
                const cols = row.querySelectorAll('td');
                const rowData = [];
                cols.forEach((col, index) => {
                    if (index < 5) {
                        rowData.push(col.innerText.replace(/,/g, ''));
                    }
                });
                csvData.push(rowData);
            });
            const csvContent = "data:text/csv;charset=utf-8," + csvData.map(e => e.join(",")).join("\n");
            const encodedUri = encodeURI(csvContent);
            const link = document.createElement("a");
            link.setAttribute("href", encodedUri);
            link.setAttribute("download", "Pacientes.csv");
            document.body.appendChild(link);
            link.click();
            document.body.removeChild(link);
        }

        function exportToXLS() {
            const wb = XLSX.utils.book_new();
            const rows = document.querySelectorAll("#patientsTable tbody tr");
            const data = [['Nombre completo', 'DNI', 'Dirección', 'Email', 'Teléfono']];
            rows.forEach(row => {
                const cols = row.querySelectorAll('td');
                const rowData = [];
                cols.forEach((col, index) => {
                    if (index < 5) {
                        rowData.push(col.innerText);
                    }
                });
                data.push(rowData);
            });
            const ws = XLSX.utils.aoa_to_sheet(data);
            XLSX.utils.book_append_sheet(wb, ws, 'Pacientes');
            XLSX.writeFile(wb, 'Pacientes.xlsx');
        }

        async function exportTableToPDF() {
            const { jsPDF } = window.jspdf;
            const doc = new jsPDF();

            const tableData = [];
            const rows = document.querySelectorAll("#patientsTable tbody tr");
            rows.forEach(row => {
                const rowData = [];
                const cells = row.querySelectorAll("td:not(:last-child)");
                cells.forEach(cell => {
                    rowData.push(cell.textContent.trim());
                });
                tableData.push(rowData);
            });

            doc.autoTable({
                head: [['Nombre completo', 'DNI', 'Dirección', 'Email', 'Teléfono']],
                body: tableData,
                startY: 10,
                headStyles: { fillColor: [22, 160, 133] },
                styles: { fontSize: 10, cellPadding: 2, halign: 'center' }
            });

            doc.save('Pacientes.pdf');
        }
    </script>

</body>
</html>