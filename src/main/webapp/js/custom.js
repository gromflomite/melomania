/**
* Check if entered username exists in the DB (check is done at every key press). 
* 
*/
function searchUserByName(event) {

	// Get the value of the userName form field
	const formValueEntered = event.target.value;

	// REST service call endpoint
	const endpoint = ('http://localhost:8080/melomania/api/user?name=' + formValueEntered);

	console.debug('Name entered by user in login form:' + formValueEntered);

	/**
	* ---- AJAX ---- 
	*
	* elementUserName -> user name form field
	* elementNameCheck -> HTML <p> to show if value entered is found (by default, has no content)
	* 
	* If form field is empty, elementNameCheck HTML content is removed
	*
	*/
	let elementUserName = document.getElementById('userName'); // userName HTML form field
	let elementNameCheck = document.getElementById('nameCheck'); // HTML <p> element
	let elementLoginButton = document.getElementById('loginButton'); // HTML login button
	let xhttp = new XMLHttpRequest();

	xhttp.open("GET", endpoint);
	xhttp.send();

	xhttp.onreadystatechange = function() {

		// Check the response of the REST service
		if (this.readyState == 4 && this.status == 200) { // Value found in the DB

			console.debug('API response 200 -> Name exists');

			/**
			* Check if userName form field is empty:			
			*
			* If empty -> Remove every element, showing nothing to user
			* If not empty -> Show the <p> nameCheck element
			*/
			if (formValueEntered) { // Not empty

				console.debug('userName field 1+');
				
				elementNameCheck.style.display = 'block';

				elementNameCheck.innerHTML = '<span style="color: #228B22;"> <i class="fas fa-check"></i> </span> User found';
				elementNameCheck.classList.add('text-success');
				elementNameCheck.classList.remove('text-danger');
				
				elementLoginButton.removeAttribute('disabled'); // Enable login button

				elementUserName.classList.add('text-success', 'font-weight-bold');
				elementUserName.classList.remove('text-danger');				

			} else { // Empty

				console.debug('userName field 0');
				
				elementLoginButton.setAttribute('disabled', 'disabled'); // Disable login button

				elementNameCheck.innerHTML = '';
				elementNameCheck.style.display = 'none';
				
			} // End if-else empty field

		} // End if REST service

		// Check the response of the REST service
		if (this.readyState == 4 && this.status == 204) { // Value NOT found in the DB

			console.debug('API response 204 -> Name does not exist');

			/**
			* Check if userName form field is empty:			
			*
			* If empty -> Remove every element, showing nothing to user
			* If not empty -> Show the <p> nameCheck element
			*/
			if (formValueEntered) { // NOT empty

				console.debug('userName field 1+');
				
				elementLoginButton.setAttribute('disabled', 'disabled'); // Disable login button
				
				elementNameCheck.style.display = 'block';

				elementNameCheck.innerHTML = '<span style="color: #FF0000;"> <i class="fas fa-times"></i> </span> User not found';
				elementNameCheck.classList.add('text-danger');
				elementNameCheck.classList.remove('text-success', 'font-weight-bold');

				elementUserName.classList.add('text-danger');
				elementUserName.classList.remove('text-success', 'font-weight-bold');

			} else { // Empty

				console.debug('userName field 0');
				
				elementLoginButton.setAttribute('disabled', 'disabled'); // Disable login button

				elementNameCheck.innerHTML = '';
				elementNameCheck.style.display = 'none';

			} // End if-else empty field

		} // End if REST service

	} // End AJAX function

} // End name check


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

	if (newPassword) {

		var newPasswordHash = sha256(newPassword.value);

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