<%@ include file="common/header.jspf"%>
<%@ include file="common/navigation.jspf"%>
<div class="container">
	<form:form name="createTicket" action="add-ticket" method="POST" commandName="addTicket" class="form-horizontal">
		<div id="divDispatch">
		<input type="button" name="dispatch" id="dispatch" value="Dispatch" class="btn btn-primary btn-sm" />
		</div>
		
			<h4 class="headerText">Ticket Form</h4>
			<hr>
				<form:hidden path="ticket_id" />
						
				<div class="form-group">
					<form:label path="customer" class="col-sm-2">Customer</form:label>			
					<div class="col-sm-5">		
					
					<form:select path="customer" items="${customers}"
						itemValue="customer_id" itemLabel="customer_name"
						class="form-control input-sm"/>
					<form:errors path="customer" cssClass="text-warning" />
					</div>
				</div>
			
				<div class="form-group">
					<form:label path="owner" class="col-sm-2">Owner</form:label>
					<div class="col-sm-5">	
					<input type="text" id="ownerName" name="ownerName" value="${defaultUser.getName()}" readonly
						 class="form-control  input-sm"	/>		
					<input type="hidden" id="ownerID" name="ownerID" value="${defaultUser.user_id}" />
					</div>			
				</div>
			
				<div class="form-group">
					<form:label path="priority"  class="col-sm-2">Priority</form:label>
					<div class="col-sm-5">
					<form:select path="priority" items="${priorities}"
						itemValue="priority_id" itemLabel="priority_name"
						class="form-control input-sm" required="required">
					</form:select>
					</div>
				</div>
			
				<div class="form-group">
					<form:label path="status" class="col-sm-2">Status</form:label>
					<div class="col-sm-5">
					<form:select path="status" items="${statuses}"
						itemValue="status_id" itemLabel="status_name"
						class="form-control input-sm" required="required">
					</form:select>
					<form:errors path="status" cssClass="text-warning" />
					</div>
				</div>
			
			<div class="form-group">
				<label for="subject" class="col-sm-2">Subject</label>
				<div class="col-sm-5">
					<form:input path="subject" type="text" class="form-control input-sm" required="required" />
					<form:errors path="subject" cssClass="text-warning" />
				</div>
			</div>
			
			<div class="form-group">
					<form:label path="description" class="col-sm-2">Description</form:label>
					<br>
					<div class="col-sm-5">
					<form:textarea path="description" class="form-control input-sm" />
					<form:errors path="description" cssClass="text-warning" />
					</div>
				</div>
		
			
			<div class="row" id="divButtons">
				<div class="form-actions floatRight">
					<c:choose>
						<c:when test="${edit}">
							<input type="submit" value="Update"
								class="btn btn-primary btn-sm" />
							<input type="button" name="close" id="close" value="Close ticket"
							    class="btn btn-primary btn-sm" /> or 
						</c:when>
						<c:otherwise>
							<input type="submit" value="Create"
								class="btn btn-primary btn-sm" /> or
						</c:otherwise>
					</c:choose>
					 <a	href="<c:url value='/all-tickets' />"
								class="btn btn-danger btn-sm">Cancel</a>
				</div>
			</div>
	
	</form:form>
</div>

<script src="js/ticket.js"></script>
<%@ include file="common/footer.jspf"%>