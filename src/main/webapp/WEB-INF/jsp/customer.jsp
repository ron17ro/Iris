<%@ include file="common/header.jspf"%>
<%@ include file="common/navigation.jspf"%>
<div class="container">
	<form:form name="registerCustomer" action="add-customer" method="POST" commandName="addCustomer" class="form-horizontal">
		<form:hidden path="customer_id" />
	
		<h4 style="visibility:${hiddenFlag}">Customer form</h4>
		<hr>
		<div class="form-group">
			<label for="customer_name" class="col-sm-2"><i
				class="glyphicon glyphicon-user"></i> Customer name</label>
			<div class="col-sm-5">
				<form:input path="customer_name" type="text" class="form-control"
					readonly="${flag}" required="required" />
				<form:errors path="customer_name" cssClass="text-warning" />
			</div>
		</div>
		<div class="form-group">
			<label for="contact_person" class="col-sm-2"><i
				class="glyphicon glyphicon-user"></i> Contact person</label>
			<div class="col-sm-5">
				<form:input path="contact_person" type="text" class="form-control"
					readonly="${flag}" required="required" />
				<form:errors path="contact_person" cssClass="text-warning" />
			</div>
		</div>
		<div class="form-group">
			<label for="email" class="col-sm-2"><i
				class="glyphicon glyphicon-envelope"></i> Email</label>
			<div class="col-sm-5">
				<form:input path="email" type="text" class="form-control"
					readonly="${flag}" required="required" />
				<form:errors path="email" cssClass="text-warning" />
			</div>
		</div>
		<div class="form-group">
			<label for="phone" class="col-sm-2"><i
				class="glyphicon glyphicon-phone-altPhone"></i> Phone</label>
			<div class="col-sm-5">
				<form:input path="phone" type="text" class="form-control"
					readonly="${flag}" required="required" />
				<form:errors path="phone" cssClass="text-warning" />
			</div>
		</div>
		<div class="form-group">
			<label for="address" class="col-sm-2"><i
				class="glyphicon glyphicon-home"></i>Address</label>
			<div class="col-sm-5">
				<form:input path="address" type="text" class="form-control"
					readonly="${flag}" required="required" />
				<form:errors path="address" cssClass="text-warning" />
			</div>
		</div>
		<div class="form-group">
			<label for="contract_number" class="col-sm-2">Contract number</label>
			<div class="col-sm-5">
				<form:input path="contract_number" type="text" class="form-control"
					readonly="${flag}" required="required" />
				<form:errors path="contract_number" cssClass="text-warning" />
			</div>
		</div>
		
		<div class="row">
				<div class="form-actions floatRight">
				<c:if test="${!flag}">
					<c:choose>
						<c:when test="${edit}">						
							<input type="submit" value="Update"
								class="btn btn-primary btn-sm" /> or
						</c:when>
						<c:otherwise>
							<input type="submit" value="Create"
								class="btn btn-primary btn-sm" /> or 
						</c:otherwise>
					</c:choose>
					</c:if>
					<a href="<c:url value='/all-customers' />"
								class="btn btn-danger btn-sm">Cancel</a>
				</div>
			</div>
	
	</form:form>
</div>
<script src="js/customer-form-validation.js"></script>
<%@ include file="common/footer.jspf"%>