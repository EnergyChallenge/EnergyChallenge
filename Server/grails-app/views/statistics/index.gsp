<!DOCTYPE html>
<html>
<head>
<meta name="layout" content="main" />
<title>EnergyChallenge</title>

<asset:stylesheet src="jqplot/jquery.jqplot.css" />
<g:javascript library='jquery' />
<!--[if lt IE 9]><asset:javascript src="jqplot/excanvas.js"/><![endif]-->
<asset:javascript src="jqplot/jquery.min.js" />
<asset:javascript src="jqplot/jquery.jqplot.min.js" />
<asset:javascript src="jqplot/plugins/jqplot.canvasTextRenderer.min.js" />
<asset:javascript
	src="jqplot/plugins/jqplot.canvasAxisLabelRenderer.min.js" />
<asset:javascript src="jqplot/plugins/jqplot.pieRenderer.min.js" />

<g:javascript>
<g:applyCodec encodeAs="none">
$(document).ready(function(){
var data1 = ${pageVisitsLast10Days};
var plot1 = $.jqplot ('chart1', [data1], {
      // Give the plot a title.
      title: 'Plot With Options',
      // You can specify options for all axes on the plot at once with
      // the axesDefaults object.  Here, we're using a canvas renderer
      // to draw the axis label which allows rotated text.
      axesDefaults: {
        labelRenderer: $.jqplot.CanvasAxisLabelRenderer
      },
      // Likewise, seriesDefaults specifies default options for all
      // series in a plot.  Options specified in seriesDefaults or
      // axesDefaults can be overridden by individual series or
      // axes options.
      // Here we turn on smoothing for the line.
      seriesDefaults: {
          rendererOptions: {
              smooth: true
          }
      },
      // An axes object holds options for all axes.
      // Allowable axes are xaxis, x2axis, yaxis, y2axis, y3axis, ...
      // Up to 9 y axes are supported.
      axes: {
        // options for each axis are specified in seperate option objects.
        xaxis: {
          label: "X Axis",
          // Turn off "padding".  This will allow data point to lie on the
          // edges of the grid.  Default padding is 1.2 and will keep all
          // points inside the bounds of the grid.
          pad: 0
        },
        yaxis: {
          label: "Y Axis"
        }
      }
    });
});
$(document).ready(function(){
  var data2 = ${mostPopularActivitys };
  var plot2 = jQuery.jqplot ('chart2', [data2], 
    { 
      seriesDefaults: {
        // Make this a pie chart.
        renderer: jQuery.jqplot.PieRenderer, 
        rendererOptions: {
          // Put data labels on the pie slices.
          // By default, labels show the percentage of the slice.
          showDataLabels: true
        }
      },
      legend: { show:true, location: 's' }
    }
  );
});
</g:applyCodec>
</g:javascript>
</head>
<body>
	<h1>
		Statistics
	</h1>
	<div style="position: relative; height: 700px">
		<div style="position: absolute; top: 0px; left: 0px;">
			<h1>Besucher</h1>
			<div id="chart1" style="height: 300px; width: 375px;"></div>
		</div>
		<div style="position: absolute; top: 0px; right: 0px;">
			<h1>Beliebteste Aktivitaeten</h1>
			<div id="chart2" style="height: 500px; width: 375px;"></div>
		</div>
	</div>
</body>
</html>
