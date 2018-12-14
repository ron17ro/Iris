<%@ include file="common/header.jspf" %>
<link href="webjars/bootstrap/3.3.7-1/css/bootstrap.min.css"
	rel="stylesheet">
<link rel="stylesheet" href="css/homepage.css">
<link rel="stylesheet" href="css/login.css">


<div class="container-fluid background">
			  <div class=" col-xs-6 centerbox">
			    <div class="row">
			     <div class="text-center ">
			     <div id="logo">
					<h3 class="logo-caption"><span class="tweak">W</span>elcome to our  
					<span class="tweak">H</span>elpdesk application!</h3><br>
				</div><!-- /.logo -->
					<h5> Please <a href="/login">Login</a> or search a word!</h5>
					
				</div>
				</div>
				<div class="row">
				 <div class="text-center">
					
						<h5>Oxford dictionary</h5>
						<div>
						<c:if test="${invalidSearch!=null}">
							<div class="error-message">
								<c:out value="${invalidSearch}"></c:out>
							</div>
						</c:if>
						</div>
						<br> <label>Search: </label> <input type="text" name="search" id="search" />
						<input type="button" id="searchDictionary"
							class="btn btn-success" value="Search word in dictionary">
							<div id="definitions"></div>
				
					</div>
				</div>
			</div>
		</div>
<div id="particles-js"></div>
<script src="js/login.js"></script>
<script src="js/dictionary.js"></script>
<%@ include file="common/footer.jspf" %>