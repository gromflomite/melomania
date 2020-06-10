// Datatables plugin JS
// Run the function when the DOM element (page) it charged and ready
$(document).ready(function() {
	// Seleccion por id => #example y ejecuta el plugin .DataTable();
	$('#table').DataTable();
});

// Function to ask the user for delete confirmation
function confirmDelete(title) {

	// The confirmDelete() method returns true if the user clicked "OK", and false otherwise.
	if (confirm('Do you really want to delete the album "' + title + '"?')) {

	} else {
		event.preventDefault();
	}
}
