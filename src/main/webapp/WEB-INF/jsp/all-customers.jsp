<%@ include file="common/header.jspf"%>
<%@ include file="common/navigation.jspf"%>

<div class="container" style="overflow-x: auto;">
	<div class="row content">
		<div class="col-xs-12 well">
			<sec:authorize access="hasAnyRole('MANAGER','ADMIN')">
			<a class="btn btn-success" href="/add-customer">New customer</a>
			</sec:authorize>
			<form:form class="search" name="searchForm"
				action="/search-customers" method="GET" commandName="search">
				<label>Search: </label>
				<input type="text" name="search" id="search" />
				<button type="submit" class="btn btn-success">Search</button>
			</form:form>

			<table class="table table-striped table-condensed">
				<caption>All customers</caption>
				<thead>

					<tr>
						<th>#</th>
						<th>Customer name</th>
						<th>Contact Person</th>
						<th>Email</th>
						<th>Phone</th>
						<th>Address</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${customers}" var="customer">
						<tr>
							<td>${customer.customer_id}</td>
							<td>${customer.customer_name}</td>
							<td>${customer.contact_person}</td>
							<td>${customer.email}</td>
							<td>${customer.phone}</td>
							<td>${customer.address}</td>
							<td><a type="button" class="btn btn-info"
								href="/view-customer?id=${customer.customer_id}">View</a></td>
							<td><sec:authorize access="hasAnyRole('MANAGER','ADMIN')">
							<a type="button" class="btn btn-success"
								href="/update-customer?id=${customer.customer_id}">Update</a></sec:authorize></td>
							<td><sec:authorize access="hasAnyRole('MANAGER','ADMIN')">
							<a type="button" class="btn btn-warning"
								href="/delete-customer?id=${customer.customer_id}">Delete</a></sec:authorize></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
	</div>
</div>
<div class="container">

</div>
<%@ include file="common/footer.jspf"%>