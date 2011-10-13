$(document).ready(function() {

	$('table.index').dataTable();

	// Details table links.
	$('table.index tr').click(function() {
		console.log('click');
		var url = $(this).find('a').attr('href');
		window.location = url;
	});

});
