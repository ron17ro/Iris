<%@ include file="common/header.jspf"%>
<%@ include file="common/navigation.jspf"%>

<div class="container" style="overflow-x: auto;">
	<div class="row content">
		<div class="col-xs-12 well">

			<a  class="btn btn-success" href="/add-ticket">New ticket</a>

			<form:form class="search" name="searchForm"
				action="/search-tickets" method="GET" commandName="search">
						<label>Search by: </label>
				<label class="checkbox-inline"><input type="checkbox" value="myTickets">My tickets</label>		
				<label class="checkbox-inline"><input type="checkbox" value="byOwner">Owner</label>
				<label class="checkbox-inline"><input type="checkbox" value="byCustomer">Customer</label>
				<label class="checkbox-inline"><input type="checkbox" value="bySubject">Subject</label>
				<label class="checkbox-inline"><input type="checkbox" value="byAll">All</label>
				<label>Search: </label>
				<input type="text" name="search" id="search" />
				<button type="submit" class="btn btn-success">Search</button>
			</form:form>
			<table class="table table-striped table-condensed">
				<caption>All tickets</caption>
				<thead>

					<tr>
						<th>#</th>
						<th>Customer</th>
						<th>Prio</th>
						<th>Owner</th>
						<th>Subject</th>
						<th>Status</th>
						<th></th>
						<th>Category</th>
						<th>Created</th>

					</tr>
				</thead>
				<tbody>
					<c:forEach items="${tickets}" var="ticket">
						<tr>
							<td>${ticket.ticket_id}</td>
							<td>${ticket.customer.getCustomer_name()}</td>
							<td>${ticket.priority.getPriority_name()}</td>
							<td>${ticket.owner.getName()}</td>
							<td>${ticket.subject}</td>
							<td>${ticket.status.getStatus_name()}</td>
							<td></td>
							<td>${ticket.category}</td>

							<td><fmt:formatDate value="${ticket.creation_date}"
									pattern="dd/MM/yyyy" /></td>

							<td><a type="button" class="btn btn-success"
								href="/update-ticket?id=${ticket.ticket_id}">Update</a></td>
							<sec:authorize access="hasAnyRole('ADMIN')">
								<td><a type="button" class="btn btn-warning"
									href="/delete-ticket?id=${ticket.ticket_id}">Delete</a></td>
							</sec:authorize>
						</tr>
					</c:forEach>

				</tbody>
			</table>
		</div>
	</div>
</div>

<%@ include file="common/footer.jspf"%>