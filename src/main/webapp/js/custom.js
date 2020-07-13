// Password hash cipher function
function cipherPassword() {
		
	// Retrieve password from form
	var password = document.getElementById('password').value;

	// Use the library to get the password hash
	var passwordHash = sha256(password);

	// Save the hash and send back
	document.getElementById('password').value = passwordHash;

	// USER EDIT SCENARIO - Check if the user wants to change the password
	var newPassword = document.getElementById('passwordChangeConfirm');
	
	if (newPassword){
	 
	 var newPasswordHash = md5(newPassword.value);	 
	 
	 document.getElementById('passwordChangeConfirm').value = newPasswordHash;	 
	} 
	
	// Send the form
	return true; // If not true, the form will not be sended
}

// Function to ask the user for delete confirmation
function confirmDelete(title) {

	// The confirmDelete() method returns true if the user clicked "OK", and false otherwise.
	if (confirm('Do you really want to delete the album "' + title + '"?')) {

	} else {
		event.preventDefault();
	}
}

// Datatables plugin JS
// Run the function when the DOM element (page) it charged and ready
$(document).ready(function() {
	// Seleccion por id => #example y ejecuta el plugin .DataTable();
	$('#table').DataTable();
});