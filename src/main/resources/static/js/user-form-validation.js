/**
 * 
 */
// Wait for the DOM to be ready
$(document).ready(function() {
  // Initialize form validation on the registration form.
  // It has the name attribute "registration"
	//$('#registrationForm').trigger("reset");
  $("form[name='registrationForm']").validate({
    // Specify validation rules
    rules: {
      // The key name on the left side is the name attribute
      // of an input field. Validation rules are defined
      // on the right side
      first_name: {
    	  required: true,
      },
      last_name: {
    	  required: true,
      },
      email: {
        required: true,
        // Specify that email should be validated
        // by the built-in "email" rule
        email: true
      },
      password: {
        required: true,
        minlength: 5
      },
      roles:{
    	  required:true,
      }
    },
    errorElement: "span",
    // Specify validation error messages

    messages: {
      first_name: "Please enter a value for first name.",
      last_name: "Please enter a value for last name.",
      password: {
        required: "Please provide a password",
        minlength: "Your password must be at least 5 characters long"
      },
      email: "Please enter a valid email address.",
      roles:"Please select at least one role."
    },
    // Make sure the form is submitted to the destination defined
    // in the "action" attribute of the form when valid
    submitHandler: function(form) {
      form.submit();
    }
  });
});
