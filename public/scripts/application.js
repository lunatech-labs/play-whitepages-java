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

		var url = $(this).find('a').attr('href');

   	// Update the URL's fragment identifier.
   	// The ID attribute is temporarily removed to prevent page scrolling.
   	var id = $(this).attr('id');
   	$(this).removeAttr('id');
   	location.hash = id;
   	$(this).attr('id', id);

		// Load details, decorate buttons and display.
		$section.load(url, function() {
       $section.find('.edit').button({icons:{primary:"ui-icon-pencil"}});
       $section.css('visibility', 'visible');
		});
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

   // Enable the search
   $('.search input').keyup(function() {
      var query = $(this).val();
      var parameter = $(this).attr('name');
      var url = $(this).closest('form').attr('action');
      var selector = url + '?' + parameter + '=' + query + ' .index p';
      $('.index').load(selector, function() {
         $('.index .entry').first().trigger('click');
      });
   });
   $('form.search').submit(function() { return false; });

   // Select the search form or the entry specified by the fragment identifier, e.g. #p23
   if (location.hash == '') {
       $('.search input').focus();
   }
   else {
      $(location.hash).trigger('click');
   }
});
