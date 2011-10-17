$(document).ready(function() {

	var section = $('<section/>').addClass('box');
	section.hide();
	$('section.box:last').after(section);

	// Details table links.
	$('table.index tr').click(function(event) {
		event.preventDefault();

		// Update list selection.
		$(this).parent().find('.selected').removeClass('selected');
		$(this).addClass('selected');

		// Load and display the details.
		var url = $(this).find('a').attr('href');
		section.load(url);
		section.show();
	});

});
