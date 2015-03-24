<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main"/>
		<title>${name}</title>
		<asset:stylesheet src="profile.css"/>
	</head>
	<body class="${type}profile">
	
		<div class="head">
		
			<g:if test="${isCurrent == true && type == 'user'}">
				<a class="editprofile button" href="${createLink(controller:'User', action: 'edit')}">
					Bearbeiten
				</a>
			</g:if>
			<g:elseif test="${isCurrent == true && type == 'team'}">
				<a class="editprofile button" href="${createLink(controller:'Team', action: 'edit')}">
					Bearbeiten
				</a>
			</g:elseif>
		
			<div class="image">
				<img src="<g:createLink controller="profile" action="avatar" id="${id}"/>" alt="image"/>
			</div>
			<hgroup>
				<h1>${name}</h1>
				<g:if test="${type == 'user' && teamname != ''}">
					<h2><a href="<g:createLink controller="profile" action="team" id="${user.getTeam().getId()}"/>">${teamName}</a></h2>
				</g:if>
			</hgroup>
			<div class="clear"></div>
		</div>
		
		<div class="maincards">
			<g:if test="${type == 'user'}">
				<div class="card institute">
					<div class="value">${institute}</div>
					<div class="label">Institut</div>
				</div>
			</g:if>
			<div class="card points">
				<div class="value big">${collectedPoints}</div>
				<div class="label">Gesammelte Punkte</div>
			</div>
			<div class="card position">
				<div class="value big">${rankingPosition}.</div>
				<div class="label">Platz in der Rangliste</div>
			</div>
			<div class="clear"></div>
		</div>
		
		<div class="left">
			<g:if test="${type == 'team'}">
				<h2>Teammitglieder</h2>
				<ul>
					<g:each in="${members}" var="member">
						<li>
							<!-- TODO Not Tag in Tag -->
							<a href="<g:createLink action="user" id="${member.id}" />">
								${member.name}
							</a>
						</li>
					</g:each>
				</ul>
			</g:if>
		</div>
		
    	<div class="right">
      		<h2>Zuletzt abgeschlossene Aktivitäten</h2>
     		<ul>
       			<g:each in="${lastActivities}" var="activity">
         				<li>
						<g:if test ="${type == 'user'}">
         					${activity.getActivity().getDescription()}
						</g:if>
						<g:else>
         						${activity.completedActivity.getActivity().getDescription()}
							<em>
								(
								<a href="<g:createLink controller="profile" action="user" id="${activity.member.getId()}"/>">
								${activity.member.getName()}
								</a>
								)
							</em>
						</g:else>          					
         				</li>
       			</g:each>
     		</ul>
	</div>
	<g:if test="${type == 'team' && user.getTeam() == null}">
		<a href="<g:createLink controller="user" action="joinTeam" id="${id}"/>" > Team beitreten </a>		
	</g:if>
	<g:if test="${type == 'user' && user.getTeam() == null && de.unikiel.klik.model.User.findByEmail(org.apache.shiro.SecurityUtils.getSubject().getPrincipal()).getTeam() != null}">
                <a href="<g:createLink controller="message" action="inviteUserToTeam" id="${id}"/>" >zu meinem Team einladen</a>		
	</g:if>
	</body>
</html>
