<g:applyLayout name="base">
	<head>
		<asset:stylesheet src="main.css" />
		<asset:stylesheet src="navigation.css" />		
		<g:layoutHead />
	</head>
	<content tag="topRight"> 
		<h2>Loged in as: ${org.apache.shiro.SecurityUtils.getSubject().getPrincipal() }</h2>
		<g:form name="logoutFrom" url="[action:'signOut',controller:'auth']">
			<input type="submit" value="Logout" />
		</g:form> 
	</content>
	<body>
		<div class="mainBody">
			<nav>
				<ul>
					<li><a href="${createLink(controller:'Landing')}" >EnergyChallenge</a></li>
					<li><a href="${createLink(controller:'Activity')}" >Aktivitäten</a></li>
					<li><a href="${createLink(controller:'Ranking')}" >Rangliste</a></li>
					<li><a href="${createLink(controller:'Proposal')}" >Energiesparvorschläge</a></li>
					<li><a href="${createLink(controller:'Statistics')}" >Statistiken</a></li>
				</ul>
			</nav>
			<div id="content">
				<g:layoutBody />
			</div>
		</div>
	</body>
</g:applyLayout>
