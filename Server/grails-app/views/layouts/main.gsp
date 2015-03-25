<g:applyLayout name="base">

	<head>
		<asset:stylesheet src="main.css" />
		<asset:javascript src="jqplot/jquery.min.js" />
		<asset:javascript src="fixedNav.js" />    
		<g:layoutHead />
	</head>
	<content tag="logo">
	<div class="topLeft">
				<%-- <a href="http://grails.org"><asset:image src="grails_logo.png" alt="Grails"/></a>  --%>
				<figure><asset:image src="klik_Logo.png" alt="klik_Logo" align="left" width="106px" heigth="auto" /></figure>
				<h1>EnergyChallenge</h1>
			</div>
	
	<content tag="userInfo">
		<% def user = de.unikiel.klik.model.User.findByEmail(org.apache.shiro.SecurityUtils.getSubject().getPrincipal())%>
		<g:if test="${user}" >
			<div id="userinfo">
				<div class="profile">
					<a href="${createLink(controller:'user', action:'edit')}" class="avatar">
						<img src="${createLink(controller:'profile', action:'avatar', id: user.getId())}" alt="${user.getName()}" />
					</a>
					<div class="name">
						<!-- TODO Change link to profile / not profile -->
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
							</span>
						</a><!--  No whitespace
						--><a href="${createLink(controller:'profile')}" class="points">
							<i class="fa fa-trophy"></i>
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
					<li><a href="${createLink(controller:'Landing')}" >EnergyChallenge</a></li>
					<li><a href="${createLink(controller:'Activity')}" >Aktivitäten</a></li>
					<li><a href="${createLink(controller:'Ranking')}" >Rangliste</a></li>
					<li><a href="${createLink(controller:'Proposal')}" >Energiesparvorschläge</a></li>
					<li><a href="${createLink(controller:'Statistics')}" >Statistiken</a></li>
				</ul>
				<!-- TODO Just show if User is Admin -->
				<g:if test="true">
					<h3>Verwaltung</h3>
					<ul>
						<li><a href="${createLink(controller:'Admin', action: 'users')}" >Benutzer verwalten</a></li>
						<li><a href="${createLink(controller:'Admin', action: 'teams')}" >Teams verwalten</a></li>
						<li><a href="${createLink(controller:'Admin', action: 'activities')}" >Aktivitäten verwalten</a></li>
						<li><a href="${createLink(controller:'Admin', action: 'proposals')}" >Vorschläge verwalten</a></li>
						<li><a href="${createLink(controller:'Admin', action: 'message')}" >E-Mail senden</a></li>
					</ul>
				</g:if>
			</nav>
			<div id="content">
				<div class="floater">
					<g:if test="${flash.message}">
						<div class="flashmessage">${flash.message}</div>
					</g:if>
					<g:javascript>setTimeout(function() {$(".flashmessage").fadeOut('slow');}, 2000);</g:javascript>
					<g:layoutBody />
				</div>
				<div class="clear"></div>
			</div>
	</body>
	
</g:applyLayout>
