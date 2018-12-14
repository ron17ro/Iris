<%@ include file="common/header.jspf"%>
<%@ include file="common/navigation.jspf"%>
<div class="container">
	<div class="row content" style="height: 100%">
		<div class="col-xs-12 well">
		Error!!
			Failed URL: ${url}
   			Exception:  ${exception.message}
        <c:forEach items="${exception.stackTrace}" var="ste">    ${ste} 
    </c:forEach>
		</div>
	</div>
</div>
<%@ include file="common/footer.jspf"%>