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
            <g:each in="${favoriteActivities}" var="favorite">
              <tr>
                <td>
                    <a href="${createLink(action:'removeFromFavorites',controller:'activity',id: favorite.activity.getId())}" class="tooltipLeft">
                      <i class="fa fa-star"></i>
                      <span>aus Favoriten entfernen</span>
                    </a>
                </td>
                <td>
                	${favorite.activity.getDescription()}
                	<em>
    	            	(${favorite.activity.getPoints()}
    	            	<g:if test="${favorite.activity.getPoints() == 1}">
    	            		Punkt)
    	            	</g:if>
    	            	<g:else>
    	            		Punkte)
    	            	</g:else>
                	</em>
                </td>
                <td>
                  <g:if test="${favorite.executable}" >
                     <a class="button" href="${createLink(controller:'activity', action:'completeActivity', id: favorite.activity.getId())}">
                       erledigen
                     </a>
                  </g:if>
                  <g:else><i class="fa fa-lock"></i>${favorite.countdown}</g:else>
                </td>
              </tr>
            </g:each>
          </tbody>
        </table>
        <a href="<g:createLink controller="activity" action="index"/>" class="button">Weitere Aktivitäten</a>
      </div>
    </div>
    <div id="leftside">
    <div class="card">
      <h3>Punktestand</h3>
      <table>
        <tr>
          <td>Meine Punkte:<td>
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
        <h3>Mein Team</h3>
        <table>
          <g:each in="${team.getMembers()}" var="member">
            <tr>
              <td><a href="<g:createLink controller="profile" action="user" id="${member.getId()}"/>">${member.getName()}</a></td>
              <td>${member.getPoints()}</td>
            </tr>
          </g:each>
        </table>
      </g:if>
      <g:else>
		<h3>Du hast noch kein Team. Hier einige Vorschläge:</h3>
        <table>
          <g:each in="${teamProposals}" var="team">
            <tr>
              <td>${team.getName()}</td>
            </tr>
          </g:each>
        </table>
      </g:else>
      </div>
      </div>
  </body>
</html>
