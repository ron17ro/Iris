<%@ include file="common/header.jspf"%>
<%@ include file="common/navigation.jspf"%>


<div class="container" style="overflow-x: auto;">
<div class="row content">
		<div class="col-xs-12 well">
			<a class="btn btn-success" href="/add-user">New User</a>
			

			<form:form class="search" name="searchForm"
				action="/search-users" method="GET" commandName="search">
				<label>Search by: </label>
				<label class="checkbox-inline"><input type="checkbox" value="byUsername">Username</label>
				<label class="checkbox-inline"><input type="checkbox" value="byFName">First name</label>
				<label class="checkbox-inline"><input type="checkbox" value="byEmail">Email</label>
				<label class="checkbox-inline"><input type="checkbox" value="byAll">All</label>
				
				<input type="text" name="search" id="search" />
				<button type="submit" class="btn btn-success">Search</button>
			</form:form>

			<table class="table table-striped table-condensed">
				<caption>All users</caption>
				<thead>

					<tr>
						<th>#</th>
						<th>First Name</th>
						<th>Last Name</th>
						<th>Username</th>
						<th>Roles</th>
						<th>Department</th>
						<th>Email</th>
						<th>Phone</th>

					</tr>
				</thead>
				<tbody>
					<c:forEach items="${users}" var="user">
						<tr>
							<td>${user.user_id}</td>
							<td>${user.first_name}</td>
							<td>${user.last_name}</td>
							<td>${user.username}</td>
							<td><c:forEach items="${user.getRoles()}" var="role">
								${role.role_name}
						</c:forEach></td>
							<td>${user.getDepartment().department_name}</td>
							<td>${user.email}</td>
							<td>${user.phone}</td>
							<td><a type="button" class="btn btn-info"
								href="/view-user?id=${user.user_id}">View</a></td>
							<td><a type="button" class="btn btn-success"
								href="/update-user?id=${user.user_id}">Update</a></td>
							<td><a type="button" class="btn btn-warning"
								href="/delete-user?id=${user.user_id}">Delete</a></td>

						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
</div>
</div>
<%@ include file="common/footer.jspf"%>