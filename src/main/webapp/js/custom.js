/**
* Check if entered username exists in the DB (check is done at every key press). 
* 
*/
function searchUserByName(event) {

	// Get the value of the userName form field
	let formValueEntered = event.target.value;

	// REST service call endpoint
	const endpoint = ('http://playzone.cyou/melomania/api/user?name=' + formValueEntered);

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
	let elementSignupButton = document.getElementById('signUpButton'); // HTML sign up button
	let xhttp = new XMLHttpRequest();

	xhttp.open("GET", endpoint);
	xhttp.send();

	xhttp.onreadystatechange = function() {

		// Check the response of the REST service
		if (this.readyState == 4 && this.status == 204) { // Value NOT found in the DB

			console.debug('API response 204 -> Name does not exist');

			/**
			* Check if userName form field is empty:			
			*
			* If empty -> Remove every element, showing nothing to user
			* If not empty -> Show the <p> nameCheck element
			*/
			if (formValueEntered.length > 3) {

				console.debug('userName field 1+');

				elementSignupButton.removeAttribute('disabled'); // Enable sign up button

				elementNameCheck.style.display = 'block';

				elementNameCheck.innerHTML = '<span style="color: #228B22;"> <i class="fas fa-check"></i> </span> Nick available';
				elementNameCheck.classList.add('text-success');
				elementNameCheck.classList.remove('text-danger', 'font-weight-bold');

				elementUserName.classList.add('text-success');
				elementUserName.classList.remove('text-danger', 'font-weight-bold');

			} else { // Empty

				console.debug('userName field 0');

				elementSignupButton.setAttribute('disabled', 'disabled'); // Disable sign up button

				elementNameCheck.innerHTML = '';
				elementNameCheck.style.display = 'none';

			} // End if-else empty field

		} // End if REST service


		// Check the response of the REST service
		if (this.readyState == 4 && this.status == 200) { // Value found in the DB

			console.debug('API response 200 -> Name exists');

			/**
			* Check if userName form field is empty:			
			*
			* If empty -> Remove every element, showing nothing to user
			* If not empty -> Show the <p> nameCheck element
			*/
			if (formValueEntered.length > 3) {

				console.debug('userName field 1+');

				elementNameCheck.style.display = 'block';

				elementNameCheck.innerHTML = '<span style="color: #FF0000;"> <i class="fas fa-times"></i> </span> Nick already registered';

				elementNameCheck.classList.add('text-danger');
				elementNameCheck.classList.remove('text-success');

				elementSignupButton.setAttribute('disabled', 'disabled'); // Disable sign up button

				elementUserName.classList.add('text-danger', 'font-weight-bold');
				elementUserName.classList.remove('text-success');

			} else { // Empty

				console.debug('userName field 0');

				elementSignupButton.setAttribute('disabled', 'disabled'); // Disable sign up button

				elementNameCheck.innerHTML = '';
				elementNameCheck.style.display = 'none';

			} // End if-else empty field

		} // End if REST service


	} // End AJAX function

} // End name check


// Password hash cipher function
function cipherPassword() {

	// Retrieve password from -> Get hash -> Put the value into the HTML element	
	let password = document.getElementById('password').value;
	let passwordHash = sha256(password);
	document.getElementById('password').value = passwordHash;

	// USER REGISTER SCENARIO - Check if passwordConfirm is on the DOM (only exists on user register form)
	let passwordConfirmExist = document.getElementById('passwordConfirm');

	if (passwordConfirmExist) {		
		let passwordConfirmHash = sha256(passwordConfirmExist.value);
		document.getElementById('passwordConfirm').value = passwordConfirmHash;
	}

	// USER EDIT SCENARIO - Check if the user wants to change the password
	let newPassword = document.getElementById('passwordChangeConfirm');

	if (newPassword) {
		let newPasswordHash = sha256(newPassword.value);
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

// Function to ask the admin for album approve confirmation
function confirmApprove(title) {

	// The confirmDelete() method returns true if the user clicked "OK", and false otherwise.
	if (confirm('Do you really want to approve the album "' + title + '"?')) {

	} else {
		event.preventDefault();
	}
}

// Function to ask the admin for album disapprove confirmation
function confirmDisapprove(title) {

	// The confirmDelete() method returns true if the user clicked "OK", and false otherwise.
	if (confirm('Do you really want to disapprove the album "' + title + '"?')) {

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