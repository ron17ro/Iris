/**
 * 
 */
// Wait for the DOM to be ready
$(document).ready(function() {
  // Initialize form validation on the registration form.
  // It has the name attribute "registration"
	//$('#registrationForm').trigger("reset");
  $("form[name='registerCustomer']").validate({
    // Specify validation rules
    rules: {
      // The key name on the left side is the name attribute
      // of an input field. Validation rules are defined
      // on the right side
    	customer_name: {
    		required: true,
      },
      contact_person: {
    	  required: true,
      },
      email: {
        required: true,
        // Specify that email should be validated
        // by the built-in "email" rule
        email: true
      },
      phone: {
        required: true,
        minlength: 10
      },
      address:{
    	  required:true,
      }
    },
    errorElement: "span",
    // Specify validation error messages

    messages: {
    	customer_name: "Please enter a customer name.",
    	contact_person: "Please enter a contact name.",
    	phone: {
	        required: "Please provide a phone number",
	        minlength: "The phone number must contain 10 digits"
      },
      email: "Please enter a valid email address.",
      address:"Please enter a valid  address."
    },
    // Make sure the form is submitted to the destination defined
    // in the "action" attribute of the form when valid
    submitHandler: function(form) {
      form.submit();
    }
  });
});
