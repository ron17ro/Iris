<%@ include file="common/header.jspf"%>
<%@ include file="common/navigation.jspf"%>
<div class="container">
	<div class="col-sm-10">
		<form:form name="registrationForm" method="POST"
			modelAttribute="addUser" autocomplete="off" class="form-horizontal"
			enctype="multipart/form-data">
			<form:hidden path="user_id" />

			<h4 style="visibility:${hiddenFlag}">User form</h4>
			<hr>
			<div class="form-group">
				<label for="profilePicture" class="col-sm-2"><i
					class="glyphicon glyphicon-user"></i>Profile picture<em>*</em></label>
				<div class="col-sm-5">
					<input id="profilePicture" name="profilePicture" type="file"
						class="form-control" readonly="${flag}" />
					<input type="hidden" id="profilePicture" name="profilePicture" value="${profilePicture}" />
				</div>
				<!-- 	<a type="button" class="btn btn-info" href="/uploadPicture" onclick="upload">Upload picture</a> -->
			</div>

			<div class="form-group">
				<label for="first_name" class="col-sm-2"><i
					class="glyphicon glyphicon-user"></i> First name<em>*</em></label>
				<div class="col-sm-5">
					<form:input path="first_name" type="text" class="form-control"
						readonly="${flag}" required="required" />
					<form:errors path="first_name" class="text-warning" element="span" />
				</div>

			</div>
			<div class="form-group">
				<label for="last_name" class="col-sm-2"><i
					class="glyphicon glyphicon-user"></i> Last name<em>*</em></label>
				<div class="col-sm-5">
					<form:input path="last_name" type="text" class="form-control"
						readonly="${flag}" required="required" />
					<form:errors path="last_name" class="text-warning" />
				</div>
			</div>
			<div class="form-group">
				<label for="username" class="col-sm-2">Username<em>*</em></label>
				<div class="col-sm-5">
					<form:input path="username" type="text" class="form-control"
						readonly="${flag}" required="required" placeholder="username" />
					<form:errors path="username" class="text-warning" />
				</div>
			</div>
			<div class="form-group">
				<label for="password" class="col-sm-2">Password<em>*</em></label>
				<div class="col-sm-5">
					<form:input path="password" type="password" class="form-control"
						readonly="${flag}" required="required" placeholder="password" />
					<form:errors path="password" class="text-warning" />
				</div>
			</div>
			<div class="form-group">
				<label for="department" class="col-sm-2">Department</label>
				<div class="col-sm-5">
					<form:select path="department" items="${departments}"
						multiple="true" itemValue="department_id"
						itemLabel="department_name" class="form-control input-sm" />
					<form:errors path="department" class="text-warning" />
				</div>
			</div>
			<div class="form-group">
				<label for="email" class="col-sm-2"><i
					class="glyphicon glyphicon-envelope"></i> Email<em>*</em></label>
				<div class="col-sm-5">
					<form:input path="email" type="text" class="form-control"
						readonly="${flag}" required="required" />
					<form:errors path="email" class="text-warning" />
				</div>
			</div>
			<div class="form-group">
				<label for="phone" class="col-sm-2"><i
					class="glyphicon glyphicon-phone-altPhone"></i>Phone</label>
				<div class="col-sm-5">
					<form:input path="phone" type="text" class="form-control"
						readonly="${flag}" required="required" />
					<form:errors path="phone" class="text-warning" />
				</div>
			</div>
			<div class="form-group">
				<label for="roles" class="col-sm-2">Roles<em>*</em></label>
				<div class="col-sm-5">
					<form:select path="roles" items="${roles}" multiple="true"
						itemValue="role_id" itemLabel="role_name"
						class="form-control input-sm" required="required" />
					<form:errors path="roles" class="text-warning" />
				</div>
			</div>
			<div class="row">
				<div class="form-actions floatRight">
				<sec:authorize access="hasAnyRole('MANAGER','ADMIN')">
					<c:choose>
						<c:when test="${edit}">
							<input type="submit" value="Update"
								class="btn btn-primary btn-sm" /> or <a
								href="<c:url value='/all-users' />"
								class="btn btn-danger btn-sm">Cancel</a>
						</c:when>
						<c:otherwise>
							<input type="submit" value="Register"
								class="btn btn-primary btn-sm" /> or <a
								href="<c:url value='/all-users' />"
								class="btn btn-danger btn-sm">Cancel</a>
						</c:otherwise>
					</c:choose>
					</sec:authorize>
					<sec:authorize access="hasAnyRole('USER')">
					<input type="submit" value="Update"
								class="btn btn-primary btn-sm" /> or 
					<a href="<c:url value='/home' />"
								class="btn btn-danger btn-sm">Cancel</a>
								</sec:authorize>
				</div>
			</div>
		</form:form>
	</div>
	
	<div class="col-sm-2 hidden-xs">
	
	<c:if test="${profilePictureDisplay != null}">
	<img id="profilePictureDisplay"
			src="${profilePictureDisplay}"
			class="img-responsive img-thumbnail ">
	
	</c:if>
	</div>

</div>

<script src="js/user-form-validation.js"></script>

<%@ include file="common/footer.jspf"%>