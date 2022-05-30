/**
 * 
 */
 
/*window.addEventListener('DOMContentLoaded', function() { // $( document ).ready(function() {  // Handler for .ready() called.});

});*/

$(document).ready( function () {
    $('#table_id').DataTable({
		processing: true,
        ajax: '/api/file/',
        columns: [
            { data: 'name' },
            { data: 'hr.position' },
            { data: 'contact.0' },
            { data: 'contact.1' },
            { data: 'hr.start_date' },
            { data: 'hr.salary' },
        ],
	});
} );