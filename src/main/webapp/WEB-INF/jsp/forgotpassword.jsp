<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://www.springframework.org/security/tags"
	prefix="sec"%>
<!-- credits for the login page
https://bootsnipp.com/snippets/56A0W
 -->
<html>
<head>
<title>Login</title>
<link href="webjars/bootstrap/3.3.7-1/css/bootstrap.min.css"
	rel="stylesheet">
<link rel="stylesheet" href="css/login.css">
<script type="application/javascript"
	src="webjars/jquery/2.2.4/jquery.js"></script>
<script type="application/javascript" src="js/login.js"></script>
</head>
<body>
	<div class="container">
		<div id="login-box">
			<div class="logo">
			<div class="text-center">
				 <a href="/">
				      <span class="glyphicon glyphicon-home"></span> Home</a>
				</div>
				<h2 class="logo-caption">
					<span class="tweak">F</span>orgot <span class="tweak">P</span>assword?
				</h2>
				<h5 class="logo-caption">
					Enter your e-mail address and we'll send you a link to reset your
					password.
					</h5>
			</div>
			<!-- /.logo -->
			
			
			<c:if test="${successMessage!=null}">
				<div class="success-message">
					<c:out value="${successMessage}"></c:out>
				</div>
			</c:if>
			<c:if test="${errorMessage!=null}">
				<div class="error-message">
					<c:out value="${errorMessage}"></c:out>
				</div>
			</c:if>
			
			<form:form name="forgotPassword" action="/forgot" method="POST"
				autocomplete="off" class="form-horizontal">
				<div class="controls">
						<input type="text" name="email" id="email" placeholder="email"
							class="form-control" /><br>
					
					<button class="btn btn-success btn-block" type="submit">Reset Password</button>
				</div>
				<br>
				<div class="logo">
                  <h4 class="logo-caption">Return to <a href="/login">Login</a></h4>
         		</div>
		
		<!-- /.controls -->
		</form:form>
	</div>
	<!-- /#login-box -->
	</div>
	<!-- /.container -->
	<div id="particles-js"></div>

	<!--<script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/particles.js/2.0.0/particles.min.js"></script>-->
</body>
</html>