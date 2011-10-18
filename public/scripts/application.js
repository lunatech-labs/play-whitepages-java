$(document).ready(function() {

    // SECTION element for displaying details.
	var $section = $('<section/>').addClass('box sticky');
	$section.css('visibility', 'hidden');
	$('section.box:last').after($section);

	// Details table links.
	$('.index .entry').live('click', function(event) {
		event.preventDefault();

		// Update list selection.
		$(this).parent().find('.selected').removeClass('selected');
		$(this).addClass('selected');

		// Load and display the details.
		var url = $(this).find('a').attr('href');
		$section.load(url);
    	$section.css('visibility', 'visible');
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

    // Make the details box sticky near the page top.
    var offset = $section.offset();
    var width = $section.width();
    $(window).bind('scroll',function() {
        if(($('body').scrollTop()) + 24 > offset.top) {
            $section.css({ position:'fixed', top:'13px', left: offset.left + 'px', width: width + 'px' });
        }
        else {
            $section.css('position', 'static');
        }
    });
});
