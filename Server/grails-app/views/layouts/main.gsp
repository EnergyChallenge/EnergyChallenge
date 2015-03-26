<g:applyLayout name="base">

	<head>
		<title>EnergyChallenge</title>
		<asset:stylesheet src="main.css" />    
		<asset:javascript src="jqplot/jquery.min.js" />	 
		<%-- <asset:javascript src="fixedNav.js" /> --%>
		<g:layoutHead />
	</head>
	
	<content tag="logo">
	<div class="topLeft">
				<a href="https://www.klik.uni-kiel.de/de"><asset:image src="klik_Logo.png" alt="klik_Logo" align="left" width="106px" heigth="auto" /></a>
				<h1>EnergyChallenge</h1>
			</div>
	
	<content tag="userInfo">
		<% def user = de.unikiel.klik.persistence.User.findByEmail(org.apache.shiro.SecurityUtils.getSubject().getPrincipal())%>
		<g:if test="${user}" >
			<div id="userinfo">
				<div class="profile">
					<a href="${createLink(controller:'user', action:'edit')}" class="avatar">
						<img src="${createLink(controller:'profile', action:'avatar', id: user.getId())}" alt="${user.getName()}" />
					</a>
					<div class="name">
						<a href="${createLink(controller:'profile')}">
							${user.getName()}
						</a>
					</div>				
				</div>
				<div class="actions">
					<div class="stats">
						<a href="${createLink(controller:'message', action:'index')}" class="inbox">
							<span class="value">
								<g:if test="${user.getMessages().size() != 0}">
									<i class="fa fa-envelope"></i>
									${user.getMessages().size()}
								</g:if>
								<g:else>
									<i class="fa fa-envelope-o"></i>
								</g:else>
							</span>
						</a><!--  No whitespace
						--><a href="${createLink(controller:'profile')}" class="points">
							<i class="fa fa-diamond"></i>
							<span class="value">${user.getPoints()}</span>
						</a>
					</div>
					<g:form name="logoutFrom" url="[action:'signOut',controller:'auth']">
						<input type="submit" value="Abmelden" />
					</g:form>	
				</div>
			</div>
		</g:if>
	</content>
	
	<body class="${pageProperty(name: 'body.class')}">

			<nav>
				<div id="search">
					<g:form action="searchForm" url="[action:'displaySearchResults',controller:'search']">
	        			<input type="search" name="query" value="${query}" placeholder="Suchen" />
	        			<button class="fa fa-search"></button>
					</g:form>
				</div>
				<ul>
					<li>
						<a href="${createLink(controller:'Landing')}" >
							<i class="fa fa-home fa-fw"></i>
							EnergyChallenge
						</a>
					</li>
					<li>
						<a href="${createLink(controller:'Activity')}" >
							<i class="fa fa-check-square-o fa-fw"></i>
							Aktivitäten
						</a>
					</li>
					<li>
						<a href="${createLink(controller:'Ranking')}" >
							<i class="fa fa-trophy fa-fw"></i>
							Rangliste
						</a>
					</li>
					<li>
						<a href="${createLink(controller:'Proposal')}" >
							<i class="fa fa-plus fa-fw"></i>
							Energiesparvorschläge
						</a>
					</li>
					<li>
						<a href="${createLink(controller:'Statistics')}" >
							<i class="fa fa-area-chart fa-fw"></i>
							Statistiken
						</a>
					</li>
				</ul>
				<!-- Just show if User is Admin -->
                <g:if test="${org.apache.shiro.SecurityUtils.getSubject().hasRole('admin')}">
					<h3>Verwaltung</h3>
					<ul>
						<li>
							<a href="${createLink(controller:'Admin', action: 'users')}" >
							<i class="fa fa-user fa-fw"></i>
							Benutzer verwalten</a>
						</li>
						<li>
							<a href="${createLink(controller:'Admin', action: 'teams')}" >
							<i class="fa fa-users fa-fw"></i>
							Teams verwalten
							</a>
						</li>
						<li>
							<a href="${createLink(controller:'Admin', action: 'activities')}" >
							<i class="fa fa-scissors fa-fw"></i>
							Aktivitäten verwalten
							</a>
						</li>
						<li>
							<a href="${createLink(controller:'Admin', action: 'proposals')}" >
							<i class="fa fa-paperclip fa-fw"></i>
							Vorschläge verwalten
							</a>
						</li>
						<li>
							<a href="${createLink(controller:'Admin', action: 'message')}" >
							<i class="fa fa-envelope fa-fw"></i>
							E-Mail senden
							</a>
						</li>
					</ul>
				</g:if>
			</nav>
			<div id="content">
				<div class="floater">
					<g:if test="${flash.message}">
						<div class="flashmessage">${flash.message}</div>
					</g:if>
					<g:elseif test="${flash.error}">
						<div class="flasherror">${flash.error}</div>
					</g:elseif>
					<g:javascript>setTimeout(function() {$(".flashmessage,.flasherror").fadeOut('slow');}, 2000);</g:javascript>
					<g:layoutBody />
				</div>
				<div class="clear"></div>
			</div>
	</body>
	
</g:applyLayout>
