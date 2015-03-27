<!DOCTYPE html>
<html>
  <head>
    <meta name="layout" content="main"/>
    <title>EnergyChallenge</title>
    <asset:stylesheet src="landing.css"/>
  </head>
  <body>
	<h1>EnergyChallenge</h1>
    <div id="top">
    	<div class="card">
    	<h3>Meine Favoriten</h3>
    	<table class="list">
          </tbody>
	    <g:if test="${favoriteActivities.size() > 0}">
	      <g:each in="${favoriteActivities}" var="favorite">
              <tr>
                <td>
                    <a href="${createLink(action:'removeFromFavorites',controller:'activity', params:[id: favorite.activity.getId(), origin:'landing'])}" class="tooltipLeft">
                      <i class="fa fa-star"></i>
                      <span>aus Favoriten entfernen</span>
                    </a>
                </td>
                <td>
                	${favorite.activity.getDescription()}
                	<em>
    	            	<g:if test="${favorite.activity.getPoints() == 1}">
    	            		(${favorite.activity.getPoints()}&nbsp;Punkt)
    	            	</g:if>
    	            	<g:else>
    	            		(${favorite.activity.getPoints()}&nbsp;Punkte)
    	            	</g:else>
                	</em>
                </td>
                <td style="width: 85px; text-align: right;">
                  <g:if test="${favorite.executable}" >
                     <a class="button" href="${createLink(controller:'activity', action:'completeActivity', params:[id: favorite.activity.getId(), origin:'landing'])}">
                       erledigen
                     </a>
                  </g:if>
                  <g:else><i class="fa fa-lock"></i>${favorite.countdown}</g:else>
                </td>
              </tr>
            </g:each>
            </g:if>
            <g:else>
	      <tr>
		<td style="font-style:italic">Sie haben momentan keine favorisieren Aktivitäten.</td>
	      </tr>
            </g:else>
          </tbody>
        </table>
        <div class="content rightalign">
	        <a href="<g:createLink controller="activity" action="index"/>">
	        	<em>Weitere Aktivitäten</em>
	        </a>
        </div>
      </div>
    </div>
    <div id="leftside">
    <div class="card">
      <h3>Punktestand</h3>
      <table class="list">
        <tr>
          <td>Meine Punkte:</td>
          <td>${user.getPoints()}</td>
        </tr>
        <g:if test="${team}">
          <tr>
            <td>Team Punkte:</td>
            <td>${team.getPoints()}</td>
          </tr>
        </g:if>
      </table>
     </div>
     </div>
     <div id="rightside">
     <div class="card">
      <g:if test="${team}">
        <h3>Team <em>${team.getName()}</em></h3>
        <table class="list">
          <g:each in="${team.getMembers()}" var="member">
            <tr>
              <td>
              	<a href="<g:createLink controller="profile" action="user" id="${member.getId()}"/>">
              		${member.getName()}
              	</a>
              </td>
              <td>${member.getPoints()}</td>
            </tr>
          </g:each>
        </table>
      </g:if>
      <g:else>
		<h3>Sie haben noch kein Team!</h3>
		<g:if test="${teamProposals.size() > 0}">
			<div class="content"><strong>Hier einige Vorschläge:</strong></div>
			<ul>
				<g:each in="${teamProposals}" var="team">
					<li>
						<a href="${createLink(controller:'profile', action: 'team', id:team.id)}">${team.getName()}</a>
					</li>
				</g:each>
			</ul>
		</g:if>
      </g:else>
      </div>
      </div>
  </body>
</html>
