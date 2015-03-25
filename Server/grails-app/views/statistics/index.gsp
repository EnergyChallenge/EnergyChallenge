<!DOCTYPE html>
<html>
<head>

<g:if test="${authenticatedUser}">
     <meta name="layout" content="main" />
</g:if>
<g:else>
     <meta name="layout" content="public" />
</g:else>

<title>EnergyChallenge</title>


<asset:stylesheet src="jqplot/jquery.jqplot.css" />
<g:javascript library='jquery' />
<!--[if lt IE 9]><asset:javascript src="jqplot/excanvas.js"/><![endif]-->
<asset:javascript src="jqplot/jquery.jqplot.min.js" />
<asset:javascript src="jqplot/plugins/jqplot.canvasTextRenderer.min.js" />
<asset:javascript
	src="jqplot/plugins/jqplot.canvasAxisLabelRenderer.min.js" />
<asset:javascript src="jqplot/plugins/jqplot.dateAxisRenderer.min.js" />
<asset:javascript src="jqplot/plugins/jqplot.pieRenderer.min.js" />

<g:javascript>
<g:applyCodec encodeAs="none">
$(document).ready(function(){
  var data1 = ${pageVisitsIndex};
  var data11 = ${pageVisitsSignIn};
  var plot1 = $.jqplot('chart1',[data1, data11],{
    axes:{
      xaxis:{renderer:$.jqplot.DateAxisRenderer,
        tickOptions:{formatString:'%d.%m'},
        tickInterval:'1 day'},
        yaxis:{min:0,pad:2}},
    series:[{lineWidth:4, markerOptions:{style:'circle'}, label:"Index"},
            {lineWidth:4, markerOptions:{style:'circle'}, label:"Logins"}],
    legend: {
      show: true,location: 'nw', xoffset: 12,yoffset: 12
    }
  });
  var data2 = ${mostPopularActivitys };
  var plot2 = jQuery.jqplot ('chart2', [data2], 
    { 
      seriesDefaults: {
        renderer: jQuery.jqplot.PieRenderer, 
        rendererOptions: {
          showDataLabels: true
        }
      },
      legend: { show:true, location: 's'}
    }
  );
  var data3 = ${pointsOverDays};
  var plot3 = $.jqplot('chart3',[data3],{
    axes:{
      xaxis:{renderer:$.jqplot.DateAxisRenderer,
        tickOptions:{formatString:'%d.%m'},
        tickInterval:'1 day'},
        yaxis:{min:0,pad:2}},
    series:[{lineWidth:4, markerOptions:{style:'circle'}, label:"Punkte"}],
    legend: {
      show: true,location: 'nw', xoffset: 12,yoffset: 12
    }
  });
});
</g:applyCodec>
</g:javascript>
</head>
<body>
	<h1>
		Statistiken
	</h1>
	<div style="position: relative; height: 800px">
		<div style="position: absolute; top: 0px; left: -30px;">
			<h1>Besucher</h1>
			<div id="chart1" style="height: 300px; width: 350px;"></div>
		</div>
		<div style="position: absolute; top: 400px; left: -30px;">
			<h1>Energiespar Punkte</h1>
			<div id="chart3" style="height: 300px; width: 350px;"></div>
		</div>
		<div style="position: absolute; top: 0px; right: -30px;">
			<h1>Beliebteste Aktivitaeten</h1>
			<div id="chart2" style="height: 750px; width: 350px;"></div>
		</div>
		<div style="position: absolute; bottom: 0px; right: 0px;">
			<g:link action="exportCsv"> Download <i class="fa fa-download"></i> </g:link>
		</div>

	</div>
	       
</body>
</html>
