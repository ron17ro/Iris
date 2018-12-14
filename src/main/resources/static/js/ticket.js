/**
 * 
 */
//for ticket dispatch
$(document).ready(function(){
	
    $("#dispatch").click(function(){
    	xmlhttp = new XMLHttpRequest();
				$.ajax({
					url:'/dispatch',
					type: 'GET',
					
					success: function (data) { 
								$("#ownerID").val(data[0]);
								$("#ownerName").val(data[1]);
								 
					},
	                error : function(xhr, status, error) {
	                    alert(xhr.responseText);
	                }
					});
				
			});
    });
$(document).ready(function(){
	
    $("#close").click(function(){
    	$("#divDispatch").hide();
		$("#divButtons").hide();
		
		$("#status option[value=5]").attr("selected","selected");
		$.ajax({
		    type : "POST",
		    url : "/update-ticket",
		
		}); 
		$("#customer").attr("disabled","disabled");
		$("#priority").attr("disabled","disabled");
		$("#status").attr("disabled","disabled");
		$("#subject").attr("disabled","disabled");
		$("#description").attr("disabled","disabled");
		$("h4.headerText").text("Ticket closed");
		
		
			});
    });


// Wait for the DOM to be ready
$(document).ready(function() {

  $("form[name='createTicket']").validate({
    rules: {
    	customer:{
    		 required:true,
    	},
    	
    	priority:{
    		 required:true,
    	},
	      subject:{
	    	  required:true,
	      },
	      description:{
	    	  required:true,
	      }
    },
    errorElement: "span",
    // Specify validation error messages

    messages: {
      customer: "Please select a customer.",
      priority:"Please select ticket priority.",
      subject:"Please enter the issue subject.",
      description: "Ticket description is mandatory",
    },
 
    submitHandler: function(form) {
      form.submit();
    }
  });
});