$(document).ready(function() {

    var clickCounter = false;

    $( "#search" ).click(function() {
        if(clickCounter == false){
            var windowWidth = $(window).width();
            if(windowWidth < 600){

                $( "#search" ).css({
                    zIndex: "1",
                })
                $( "#searchglass" ).css({
                    top: "8px"
                }).blur()

                $( "#search" ).animate({
                    width: "218px"
                }, 500, function() {
                    // Animation complete.
                });

                clickCounter = true;
                return false

            }
        }
    });
});