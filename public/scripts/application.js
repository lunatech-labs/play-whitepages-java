$(document).ready(function() {

    // SECTION element for displaying details.
	var section = $('<section/>').addClass('box');
	section.hide();
	$('section.box:last').after(section);

	// Details table links.
	$('.index .entry').live('click', function(event) {
		event.preventDefault();

		// Update list selection.
		$(this).parent().find('.selected').removeClass('selected');
		$(this).addClass('selected');

		// Load and display the details.
		var url = $(this).find('a').attr('href');
		section.load(url);
		section.show();
	});

    // Paging (infinite scroll)
    $('.index').infinitescroll({
        debug: false,
        navSelector: '.next',
        nextSelector: ".next a:first",
        itemSelector: ".index p",
		loading: {
            img: "/assets/scripts/infinite-scroll/ajax-loader.gif",
            msgText: "",
            finishedMsg: "All entries loaded"
        }
    });

});
