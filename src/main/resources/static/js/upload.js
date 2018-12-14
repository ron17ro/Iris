/**
 * 
 */
$(document).ready(function(){
	
    $("#dispatch").click(function(){
    	xmlhttp = new XMLHttpRequest();
				$.ajax({
					url:'/uploadPicture',
					type: 'GET',
					
					success: function (data) { 
								$("#profilePicture").val(data[0]);
								
					},
	                error : function(xhr, status, error) {
	                    alert(xhr.responseText);
	                }
					});
				
			});
    });
