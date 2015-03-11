<!DOCTYPE html>
<html>
<head>
<g:javascript library='jquery' />
<asset:stylesheet src="jqplot/jquery.jqplot.css" />
<!--[if lt IE 9]><asset:javascript src="jqplot/excanvas.js"/><![endif]-->
<asset:javascript src="jqplot/jquery.min.js" />
<asset:javascript src="jqplot/jquery.jqplot.min.js" />
<asset:javascript src="jqplot/plugins/jqplot.canvasTextRenderer.min.js" />
<asset:javascript
	src="jqplot/plugins/jqplot.canvasAxisLabelRenderer.min.js" />
<asset:javascript src="jqplot/plugins/jqplot.dateAxisRenderer.min.js" />
<asset:javascript src="jqplot/plugins/jqplot.trendline.min.js" />
<g:javascript>
<g:applyCodec encodeAs="none">
$(document).ready(function(){
  var line1=[['2015-06-01 8:00AM',4], ['2015-06-02 8:00AM',6.5], ['2015-06-02 8:00PM',5.7], ['2015-06-03 8:00AM',9], ['2015-06-05 8:00AM',8.2]];
  var line2=[['2015-06-01 8:00PM',6], ['2015-06-02 8:00PM',12], ['2015-06-02 8:00PM',5], ['2015-06-03 8:00PM',13], ['2015-06-04 8:00AM',0]];
   //$.jqplot.config.enablePlugins = true;
  var plot1 = $.jqplot('chart1', [line1], {
      seriesDefaults: {
        trendline: {show: true,label:"Trend",lineWidth : 1,color: '#00FF00'}
    },
      axes:{
        xaxis:{
          renderer:$.jqplot.DateAxisRenderer, 
          tickOptions:{formatString:'%d.%m'},
          tickInterval:'1 day'
        },
        yaxis:{
            min:0,
        	pad:2
        } 
      },
      series:[{lineWidth:4, markerOptions:{style:'circle'},label:"Besucher"},
             {lineWidth:1, markerOptions:{style:'diamond'},label:"Abgeschlossene Aktivitaeten"},
            ],
      legend: {
        show: true,
        location: 'nw',     
        xoffset: 12,        
        yoffset: 12      
    },
    
    
  });
});
</g:applyCodec>
</g:javascript>
</head>
<body>
	<div id="chart1" style="height: 300px; width: 375px;"></div>
</body>
</html>
