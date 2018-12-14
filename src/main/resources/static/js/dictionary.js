$(document).ready(function(){
	
    $("#searchDictionary").click(function(e){
    	
				$.ajax({
					url:"/search-dictionary",
					type: "GET",
					data:{'search': document.getElementsByName("search")[0].value },
					contentType: "application/json; charset=utf-8",
				    dataType: "json",
					success: function (data) { 
							    var definitionsHTML="";
							    $.each(data, function (key,value) {	 	
							    
							    	 $.each(value, function (k1,v1) {	
							    		 $.each(v1.lexicalEntries, function (k2,v2) {	
							    			 definitionsHTML += "<h4 style='font-style: italic; color: red'>Lexical category: " + this.lexicalCategory+"</h4><hr>" ;
							    		$.each(v2.entries, function (k3,v3) {	
							    			if(typeof this.etymologies !== 'undefined') {
							    			definitionsHTML += "<span style='font-style: italic;font-weight: bold;'>Etymologies: </span><span>" + this.etymologies + "</span><br>";
							    			// definitionsHTML += this.etymologies;
							    			}
							    		$.each(v3.senses, function (k4,v4) {
							    			
							    			if(typeof this.domains !== 'undefined') 
							    			 definitionsHTML += "<span style='font-style: italic;font-weight: bold;'>Domain: </span><span>" + this.domains +"</span><br>" ;
							    			if(typeof this.definitions !== 'undefined') 
							    			 definitionsHTML += "<span  style='font-style: italic;font-weight: bold;'>Definition: </span><span>" + this.definitions +"</span><br>" ;
							    			
							    			if(v4.examples !== null && typeof v4.examples !== 'undefined'){
							    				definitionsHTML += "<p style='font-style: italic;font-weight: bold;'>Examples: </p>";
							    			 $.each(v4.examples, function (k5,v5) {	
							    				 if(typeof this.registers !== 'undefined'  && this.registers !==null) 		
							    					 definitionsHTML += "<span  style='font-style: italic;font-weight: bold;'>Registers : </span><span>" + this.registers +"</span><br>" ;
							    			     definitionsHTML += "<span>" + this.text +"</span>" ;
							    			 });
							    			 definitionsHTML += "<hr>";
							    			}
							    			
							    		 });
								    	 });
							    		 });
							    	 });
//							    
							    	console.log(value);
							    });							
							    $("#definitions").html( definitionsHTML );
							
					},
	                error : function(xhr, status, error) {
	                   
	                }
					});
				
			});
    });