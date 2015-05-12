
$.fn.countdownStart = function() {
	
	this.each(function() {
        
       	var element = $(this);

        setInterval(function () {
        	
        	seconds = element.data("seconds");
        
        	if (seconds > 0) {

        		var remSeconds = seconds;
        		
        		var days = Math.floor(remSeconds/86400);
        		remSeconds -= days*86400;
        		element.find(".days .value").text(days < 10 ? '0'+days : days);
        		
        		var hours = Math.floor(remSeconds/3600);
        		remSeconds -= hours*3600;
        		element.find(".hours .value").text(hours < 10 ? '0'+hours : hours);
        		
        		var minutes = Math.floor(remSeconds/60);
        		remSeconds -= minutes*60;
        		element.find(".minutes .value").text(minutes < 10 ? '0'+minutes : minutes);
        		
        		element.find(".seconds .value").text(remSeconds < 10 ? '0'+remSeconds : remSeconds);
	       		
	       		element.data("seconds", (seconds - 1));
	       	} else {
	       		location.reload();
	       	}
        		
        }, 1000);
        
    });
	
};

$(document).ready(function() {
	$("#countdown").countdownStart();
});