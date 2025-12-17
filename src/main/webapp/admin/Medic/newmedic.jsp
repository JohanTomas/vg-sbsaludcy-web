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
    <title>Nuevo Medico</title>
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
                            <h4 class="title">Nuevo Medico</h4>
                        </div>
                        <div class="card-content table-responsive">
                            <form class="form-horizontal" method="post" id="addproduct" action="/Contnewm" role="form">

                                <div class="form-group">
                                    <label for="inputEmail1" class="col-lg-2 control-label">Area*</label>
                                    <div class="col-md-6">
                                        <select name="category_id" class="form-control" required>
                                            <option value="">-- SELECCIONAR --</option>
                                            <c:forEach items="${categoryList}" var="category">
                                                <option value="${category.id}">${category.name}</option>
                                            </c:forEach>
                                        </select>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label for="name" class="col-lg-2 control-label">Nombre*</label>
                                    <div class="col-md-6">
                                        <input type="text" name="name" required class="form-control" id="name" placeholder="Nombre" oninput="this.value = this.value.toUpperCase()">
                                        <span id="name-error" class="error-message">El nombre solo debe contener letras</span>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label for="lastname" class="col-lg-2 control-label">Apellido*</label>
                                    <div class="col-md-6">
                                        <input type="text" name="lastname" required class="form-control" id="lastname" placeholder="Apellido" oninput="this.value = this.value.toUpperCase()">
                                        <span id="lastname-error" class="error-message">El apellido solo debe contener letras</span>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-lg-2 control-label">Genero*</label>
                                    <div class="col-md-6" >
                                        <label class="checkbox-inline">
                                            <input type="radio" id="inlineCheckbox1" name="gender" value="H"> Hombre
                                        </label>
                                        <label class="checkbox-inline">
                                            <input type="radio" id="inlineCheckbox2" name="gender" value="M"> Mujer
                                        </label>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label for="document_type" class="col-lg-2 control-label">Tipo de Documento*</label>
                                    <div class="col-md-6">
                                        <select id="document_type" name="document_type" class="form-control" required>
                                            <option value="">-- SELECCIONAR --</option>
                                            <option value="DNI">DNI</option>
                                            <option value="CNI">CNI</option>
                                        </select>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label for="document_dni" class="col-lg-2 control-label">Número de Documento*</label>
                                    <div class="col-md-6">
                                        <input type="text" name="document_dni" required class="form-control" id="document_dni" placeholder="Número de Documento">
                                        <span id="document-error" class="error-message">Número de documento inválido</span>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label for="day_of_birth" class="col-lg-2 control-label">Fecha de Nacimiento*</label>
                                    <div class="col-md-6">
                                        <input type="date" name="day_of_birth" required class="form-control" id="day_of_birth" placeholder="Fecha de Nacimiento">
                                        <span id="dob-error" class="error-message">Debes ser mayor de 18 años</span>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label for="address1" class="col-lg-2 control-label">Dirección*</label>
                                    <div class="col-md-6">
                                        <input type="text" name="address" required class="form-control"  id="address1" placeholder="Direccion">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label for="email" class="col-lg-2 control-label">Email*</label>
                                    <div class="col-md-6">
                                        <input type="text" name="email" required class="form-control" id="email" placeholder="Email">
                                        <span id="email-error" class="error-message">El correo electronico debe ser válido</span>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label for="phone1" class="col-lg-2 control-label">Telefono*</label>
                                    <div class="col-md-6">
                                        <input type="text" name="phone" required class="form-control" id="phone1" placeholder="Telefono">
                                        <span id="phone-error" class="error-message">El telefono debe ser valido</span>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="col-lg-offset-2 col-lg-10">
                                        <button type="submit" class="btn btn-primary">Agregar Medico</button>
                                        <a href="/Contmedic" class="btn btn-primary">Cancelar</a>
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
            const documentTypeField = document.getElementById('document_type');
            const documentNumberField = document.getElementById('document_dni');
            const documentError = document.getElementById('document-error');
            const nameField = document.getElementById('name');
            const lastnameField = document.getElementById('lastname');
            const phoneField = document.getElementById('phone1');
            const emailField = document.getElementById('email');
            const dobField = document.getElementById('day_of_birth');

            const nameError = document.getElementById('name-error');
            const lastnameError = document.getElementById('lastname-error');
            const phoneError = document.getElementById('phone-error');
            const emailError = document.getElementById('email-error');
            const dobError = document.getElementById('dob-error');

            function validateDocumentNumber() {
                const documentType = documentTypeField.value;
                const documentNumber = documentNumberField.value;

                let isValid = false;

                if (documentType === 'DNI' && /^\d{8}$/.test(documentNumber)) {
                    isValid = true;
                } else if (documentType === 'CNI' && /^\d{11,20}$/.test(documentNumber)) {
                    isValid = true;
                }

                if (isValid) {
                    documentNumberField.classList.add('valid');
                    documentNumberField.classList.remove('invalid');
                    documentError.style.display = 'none';
                } else {
                    documentNumberField.classList.add('invalid');
                    documentNumberField.classList.remove('valid');
                    documentError.style.display = 'block';
                }
                return isValid;
            }

            function validateText(field, errorElement) {
                const regex = /^[a-zA-ZáéíóúÁÉÍÓÚñÑ\s]+$/;
                if (regex.test(field.value)) {
                    field.classList.add('valid');
                    field.classList.remove('invalid');
                    errorElement.style.display = 'none';
                } else {
                    field.classList.add('invalid');
                    field.classList.remove('valid');
                    errorElement.style.display = 'block';
                }
            }

            function validateEmail(field, errorElement) {
                const gmailRegex = /^[^\s@]+@gmail\.com$/;
                const vallegrandeRegex = /^[^\s@]+@vallegrande\.edu\.pe$/;
                const hotmailRegex =  /^[^\s@]+@hotmail\.com$/;
                const outlookRegex =  /^[^\s@]+@outlook\.com$/;
                const yahooRegex =  /^[^\s@]+@lyahoo\.com$/;


                if (gmailRegex.test(field.value) || vallegrandeRegex.test(field.value) || hotmailRegex.test(field.value) || outlookRegex.test(field.value) || yahooRegex.test(field.value)) {
                    field.classList.add('valid');
                    field.classList.remove('invalid');
                    errorElement.style.display = 'none';
                } else {
                    field.classList.add('invalid');
                    field.classList.remove('valid');
                    errorElement.style.display = 'block';
                }
            }

            function validatePhone(field, errorElement) {
                const regex = /^9\d{8}$/;
                if (regex.test(field.value)) {
                    field.classList.add('valid');
                    field.classList.remove('invalid');
                    errorElement.style.display = 'none';
                } else {
                    field.classList.add('invalid');
                    field.classList.remove('valid');
                    errorElement.style.display = 'block';
                }
            }

            function validateDOB(field, errorElement) {
                const today = new Date();
                const birthDate = new Date(field.value);
                let age = today.getFullYear() - birthDate.getFullYear();
                const monthDiff = today.getMonth() - birthDate.getMonth();
                if (monthDiff < 0 || (monthDiff === 0 && today.getDate() < birthDate.getDate())) {
                    age--;
                }
                if (age >= 18) {
                    field.classList.add('valid');
                    field.classList.remove('invalid');
                    errorElement.style.display = 'none';
                } else {
                    field.classList.add('invalid');
                    field.classList.remove('valid');
                    errorElement.style.display = 'block';
                }
            }

            function validateForm() {
                validateText(nameField, nameError);
                validateText(lastnameField, lastnameError);
                validateEmail(emailField, emailError);
                validatePhone(phoneField, phoneError);
                validateDOB(dobField, dobError);
                const isDocumentValid = validateDocumentNumber();

                // Check if there are any invalid fields
                const invalidFields = document.querySelectorAll('.invalid');
                return invalidFields.length === 0 && isDocumentValid;
            }

            documentNumberField.addEventListener('input', validateDocumentNumber);
            documentTypeField.addEventListener('change', validateDocumentNumber);
            nameField.addEventListener('input', () => validateText(nameField, nameError));
            lastnameField.addEventListener('input', () => validateText(lastnameField, lastnameError));
            emailField.addEventListener('input', () => validateEmail(emailField, emailError));
            phoneField.addEventListener('input', () => validatePhone(phoneField, phoneError));
            dobField.addEventListener('input', () => validateDOB(dobField, dobError));
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