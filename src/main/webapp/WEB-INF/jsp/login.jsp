<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://www.springframework.org/security/tags" prefix="sec"%>
<!-- credits for the login page
https://bootsnipp.com/snippets/56A0W
 -->
<html>
<head>
<title>Login</title>
<link href="webjars/bootstrap/3.3.7-1/css/bootstrap.min.css"
	rel="stylesheet">
<link rel="stylesheet" href="css/login.css">
<script type="application/javascript" src="webjars/jquery/2.2.4/jquery.js"></script>
<script type="application/javascript" src="js/login.js"></script>


</head>
<body>
<div class="container">

	<div id="login-box">
	<div class="text-center">
		 <a href="/">
		      <span class="glyphicon glyphicon-home"></span> Home</a>
		</div>
		<div class="logo">
			<h1 class="logo-caption"><span class="tweak">H</span>elpdesk <span class="tweak">L</span>ogin</h1>	
		</div><!-- /.logo -->
		
			<div>
			<c:if test="${invalidCredentials!=null}">
				<div class="error-message">
					<c:out value="${invalidCredentials}"></c:out>
				</div>
			</c:if>
			</div>
		
		<form:form name="loginForm"  action="login" method="POST" autocomplete="off" class="form-horizontal">
		<div class="controls">
			<c:if test="${not empty SPRING_SECURITY_LAST_EXCEPTION}">
		      <font color="red">
		        <c:out value="${SPRING_SECURITY_LAST_EXCEPTION.message}"/>.
		      </font>
			</c:if>
			<input type="text" name="username" id="username" placeholder="Username" class="form-control" />
			<input type="password" name="password" id="password" placeholder="Password" class="form-control" /><br>
			<input class="btn btn-success btn-block" type="submit" value="Login"/>
		<br><br>
		<div class="logo">
                 <h4 class="logo-caption"> Forgot password?    <a href="/forgot">Reset password</a></h4>
          </div>
		</div><!-- /.controls -->
		
		</form:form>
	</div><!-- /#login-box -->
</div><!-- /.container -->
<div id="particles-js"></div>

<!--<script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/particles.js/2.0.0/particles.min.js"></script>-->
</body>
</html>