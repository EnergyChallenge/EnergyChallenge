
$.fn.countdown = function() {
	
	this.each(function() {
        
        var content = $.trim($(this).text());
        
        if (content.search(":") != -1) {
        	        	
        	var element = $(this);
        	var seconds = parseInt(content.slice(6, 8));
        	seconds += (60 * parseInt(content.slice(3, 5)));
        	seconds += (3600 * parseInt(content.slice(0, 2)));
        	
        	element.data("seconds", seconds);
        	
        	setInterval(function () {
        		
        		seconds = element.data("seconds");
        		
        		if (seconds > 0) {	
	        		element.text(secondsTimeSpanToHMS(seconds));
	        		element.data("seconds", (seconds - 1));
	        	} else {
	        		element.parent().html('<a class="reload" href="#"><i class="fa fa-refresh"></i> Neuladen</a>').click(function() {
	        			location.reload();
	        		});
	        	}
        		
        	}, 1000);
        }
        
    });
	
};

function secondsTimeSpanToHMS(s) {
    var h = Math.floor(s/3600); //Get whole hours
    s -= h*3600;
    var m = Math.floor(s/60); //Get remaining minutes
    s -= m*60;
    return (h < 10 ? '0'+h : h) +
    		":" + (m < 10 ? '0'+m : m) +
    		":" + (s < 10 ? '0'+s : s);
}

$(document).ready(function() {
	$(".countdown").countdown();
	$('a.reload').click(function() {
		location.reload();
		return false;
	});
});