<g:applyLayout name="base">

	<head>
		<title>Energy | Challenge</title>
		<asset:stylesheet src="main.css" />    
		<asset:javascript src="jqplot/jquery.min.js" />	 
		<asset:javascript src="fixedNav.js" />
		<asset:javascript src="mobileSearch.js" />
		<asset:javascript src="countdown.js" />
		<g:layoutHead />
	</head>
	
	<content tag="logo">
		<div class="headertitle">
			<a href="/" class="logo">
				<asset:image src="klik_logo.png" alt="klik logo" />
			</a>
			<div class="headerbackground"><asset:image src="header_image.png"/></div>
			<h1 class="logofont">Energy | Challenge</h1>
			<content tag="userInfo">
				<% def user = de.unikiel.klik.persistence.User.findByEmail(org.apache.shiro.SecurityUtils.getSubject().getPrincipal())%>
				<g:if test="${user}" >
					<div id="userinfo">
						<div class="profile">
							<a href="${createLink(controller:'profile')}" class="avatar">
								<img src="${createLink(controller:'profile', action:'avatar', id: user.getId())}" alt="${user.getName()}" />
							</a>
						</div>
						<div class="actions">
							<div class="stats">
								<a href="${createLink(controller:'message', action:'index')}" class="inbox">
									<i class="fa fa-envelope"></i>
									<div class="value-circle">
										${user.getMessages().size()}
									</div>
								</a>
								<a href="${createLink(controller:'profile')}" class="points">
									<i class="fa fa-leaf"></i>
									<div class="value-circle">
										${user.getPoints()}
									</div>
								</a>
							</div>
						</div>
						<g:form name="logoutFrom" url="[action:'signOut',controller:'auth']">
							<input type="submit" value="Abmelden" />
						</g:form>
					</div>
				</g:if>
			</content>
		</div>


	</content>
	
	<body class="${pageProperty(name: 'body.class')}">

			<nav>
				<div id="search">
					<g:form action="searchForm" url="[action:'displaySearchResults',controller:'search']">
	        			<input type="search" name="query" value="${query}" placeholder="Suchen" />
	        			<button id="searchglass" class="fa fa-search"></button>
					</g:form>
				</div>
				<ul>
					<li>
						<a href="${createLink(controller:'Landing')}" >
							<i class="fa fa-home fa-fw"></i>
							<span class="title">EnergyChallenge</span>
						</a>
					</li>
					<li>
						<a href="${createLink(controller:'Activity')}" >
							<i class="fa fa-check-square-o fa-fw"></i>
							<span class="title">Aktivitäten</span>
						</a>
					</li>
					<li>
						<a href="${createLink(controller:'Ranking')}" >
							<i class="fa fa-trophy fa-fw"></i>
							<span class="title">Rangliste</span>
						</a>
					</li>
					<li>
						<a href="${createLink(controller:'Proposal')}" >
							<i class="fa fa-plus fa-fw"></i>
							<span class="title">Energiesparvorschläge</span>
						</a>
					</li>
					<li>
						<a href="${createLink(controller:'Statistics')}" >
							<i class="fa fa-area-chart fa-fw"></i>
							<span class="title">Statistiken</span>
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
								<span class="title">Benutzer verwalten</span>
							</a>
						</li>
						<li>
							<a href="${createLink(controller:'Admin', action: 'teams')}" >
							<i class="fa fa-users fa-fw"></i>
							<span class="title">Teams verwalten</span>
							</a>
						</li>
						<li>
							<a href="${createLink(controller:'Admin', action: 'activities')}" >
							<i class="fa fa-scissors fa-fw"></i>
							<span class="title">Aktivitäten verwalten</span>
							</a>
						</li>
						<li>
							<a href="${createLink(controller:'Admin', action: 'proposals')}" >
							<i class="fa fa-paperclip fa-fw"></i>
							<span class="title">Vorschläge verwalten</span>
							</a>
						</li>
						<li>
							<a href="${createLink(controller:'Admin', action: 'message')}" >
							<i class="fa fa-envelope fa-fw"></i>
							<span class="title">E-Mail senden</span>
							</a>
						</li>
					</ul>
				</g:if>
			</nav>
			<div id="main">
				<div id="content">
					<div class="floater">
						<g:if test="${flash.message}">
							<div class="flashmessage">${flash.message}</div>
							<g:javascript>
								$(".flashmessage").hide();
								$(".flashmessage").slideDown('slow');
								setTimeout(function() {$(".flashmessage").fadeOut('slow');}, 2000);
							</g:javascript>
						</g:if>
						<g:elseif test="${flash.error}">
							<div class="flasherror">${flash.error}</div>
							<g:javascript>
								$(".flasherror").hide();
								$(".flasherror").slideDown('slow');
								setTimeout(function() {$(".flasherror").fadeOut('slow');}, 2000);
							</g:javascript>
						</g:elseif>
						<g:javascript>setTimeout(function() {$(".flashmessage,.flasherror").fadeOut('slow');}, 2000);</g:javascript>
						<g:layoutBody />
						<div class="clear"></div>
					</div>
					<div class="clear"></div>
				</div>
				<footer class="clear">
					<a href="http://www.uni-kiel.de/suchen/impressum.shtml">Impressum</a>
					 &bull;
					<a href="http://www.uni-kiel.de/suchen/kontakt.shtml">Kontakt</a>
					 &bull;
					<a href="http://www.uni-kiel.de/suchen/impressum.shtml#datenschutz">Datenschutz</a>
				</footer>
			</div>
	</body>
	
</g:applyLayout>
