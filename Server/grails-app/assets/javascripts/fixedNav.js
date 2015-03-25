$(document).ready(function() {
	$(window).scroll(function() {
		if ($('#container nav').height() + $('footer').outerHeight() <= $(window).height()) {
			if ($(this).scrollTop() >= 100) {
				$('#container nav').addClass('fixed');
			} else {
				$('#container nav').removeClass('fixed');
			}
		} else {
			$('#container nav').removeClass('fixed');
		}
	});
});