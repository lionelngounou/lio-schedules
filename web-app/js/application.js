if (typeof jQuery !== 'undefined') {
	(function($) {
		$('#spinner').ajaxStart(function() {
			$(this).fadeIn();
		}).ajaxStop(function() {
			$(this).fadeOut();
		});
		initDatePicker();
	})(jQuery);
}

function initDatePicker(){
	$('.date-picker').datepicker();
}
