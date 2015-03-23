<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main"/>
		<title>${name}</title>
		<asset:stylesheet src="profile.css"/>
	</head>
	<body class="${type}profile">
	
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
		
		<div class="head">
			<div class="image">
				<img src="<g:createLink controller="profil" action="avatar" id="${id}"/>" alt="image"/>
			</div>
			<hgroup>
				<h1>${name}</h1>
				<g:if test="${type == 'user' && teamname != ''}">
					<h2>${teamName}</h2>
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
         					${activity.getActivity().getDescription()}
							<g:if test ="${type == 'team'}">
								<em>(${activity.member})</em>
							</g:if>          					
         				</li>
       			</g:each>
     		</ul>
		</div>
		
	</body>
</html>
