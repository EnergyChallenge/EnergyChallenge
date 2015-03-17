<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main"/>
		<title>Name hier</title>
	</head>
	<body>
		
		<div class="head">
			<div class="image">
				<!-- Image here instead of div -->
				<div style="width: 100px; height: 100px; background-color: lime;"></div>
				<h1>Name hier</h1>
				<h2>Team evtl. hier</h2>
			</div>
		</div>
		<div class="left">
			<ul>
				<li>Institut eventuell hier</li><!-- Falls User: -->
				<li>Gesammelte Punkte: 1024</li>
				<li>Position: 78</li><!-- Eventuell Link hier -->
			</ul>
			<!-- Falls Team: -->
			<ul>
				<li>Teammitglied 1</li><!-- Link hier -->
				<li>Teammitglied 2</li><!-- Link hier -->
				<li>Teammitglied 3</li><!-- Link hier -->
			</ul>
		</div>
		<div class="right">
			<ul>
				<li>Letzte Aktivität 1</li><!-- Eventuell User, falls Team -->
				<li>Letzte Aktivität 2</li><!-- Eventuell User, falls Team -->
				<li>Letzte Aktivität 3</li><!-- Eventuell User, falls Team -->
			</ul>
		</div>
		
		
			<p>
				${org.apache.shiro.SecurityUtils.getSubject().getProperties() }
				${org.apache.shiro.SecurityUtils.getSubject().getSession().getId() }
			</p>
	</body>
</html>
