<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="ISO-8859-1">
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- Bootstrap CSS -->
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3"
	crossorigin="anonymous">


<title>Curso JSP</title>

<style type="text/css">
form {
	position: absolute;
	top: 40%;
	left: 25%;
	right: 25%
}

h5 {
	position: absolute;
	top: 30%;
	left: 25%;
}

.msg {
	position: absolute;
	top: 70%;
	left: 25%;
	font-size: 15px;
	color: red;
}
</style>

</head>
<body>
	<h5>Bem vindo ao curso de JSP</h5>

	<form action="ServletLogin" method="post"
		class="row g-3 needs-validation" novalidate>
		<input type="hidden" value="<%=request.getParameter("url")%>"
			name="url">

		<div class="col-md-6">
			<label class="form-label">Login</label> <input class="form-control"
				name="login" type="text" required="required">
			<div class="valid-feedback">
      			Ok!
    		</div>
    		<div class="invalid-feedback">
      			Campo Obrigatório!
    		</div>
		</div>

		<div class="col-md-6">
			<label class="form-label">Senha</label> <input class="form-control"
				name="senha" type="password" required="required">
			<div class="valid-feedback">
      			Ok!
    		</div>
    		<div class="invalid-feedback">
      			Campo Obrigatório!
    		</div>
		</div>

		<input type="submit" class="btn btn-primary" value="Acessar">

	</form>

	<h5 class="msg">${msg}</h5>

	<!-- Option 1: Bootstrap Bundle with Popper -->
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"
		integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p"
		crossorigin="anonymous"></script>

	<script type="text/javascript">
		// Example starter JavaScript for disabling form submissions if there are invalid fields
		(function() {
			'use strict'

			// Fetch all the forms we want to apply custom Bootstrap validation styles to
			var forms = document.querySelectorAll('.needs-validation')

			// Loop over them and prevent submission
			Array.prototype.slice.call(forms).forEach(function(form) {
				form.addEventListener('submit', function(event) {
					if (!form.checkValidity()) {
						event.preventDefault()
						event.stopPropagation()
					}

					form.classList.add('was-validated')
				}, false)
			})
		})()
	</script>
</body>
</html>

