<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main"/>
		<title>EnergyChallenge</title>
	</head>
	<body>
			<h1>Hello from... <em>Profil</em>!</h1>
			<p>
				${org.apache.shiro.SecurityUtils.getSubject().getProperties() }
				${org.apache.shiro.SecurityUtils.getSubject().getSession().getId() }
			</p>
	</body>
</html>
