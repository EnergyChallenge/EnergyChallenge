<!DOCTYPE html>
<html>
  <head>
    <meta name="layout" content="main"/>
    <title>EnergyChallenge</title>
    <asset:stylesheet src="landing.css"/>
  </head>
  <body>
	<h1>EnergyChallenge</h1>
    <div id="leftside">
    	<div class="card">
    	<h3>Meine Favoriten</h3>
    	<table>
    	<g:each in="${favoriteActivities}" var="favorite">
    	<tr>
    		<td>
    			<g:form name="addToFavoritesForm" url="[action:'removeFromFavorites',controller:'activity',id: favorite.activity.getId()]">
        	      	<g:actionSubmitImage value="defavorisieren" action="removeFromFavorites"
        	      						src="${resource(dir: 'images', file: 'favorite1.png')}"/>  
				</g:form>
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
                <g:form name="completeActivityForm" url="[action:'completeActivity',controller:'activity',id: favorite.activity.getId()]">
                  <g:actionSubmitImage value="Aktivität erledigen" action="completeActivity"
                     src="${resource(dir: 'images', file: 'complete.png')}"/>	<!-- actionsubmitImage creates an imagebutton for the method completeActivity -->
                </g:form>
              </g:if>
              <g:else>${favorite.countdown}</g:else>
            </td>
          </tr>
        </g:each>
      </table>
      <a href="<g:createLink controller="activity" action="index"/>">Weitere Aktivitäten</a>
      </div>
    </div>
    <div id="rightside">
    <div class="card">
      <h3>Punkte Stand</h3>
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
     <div class="card">
      <g:if test="${team}">
        <h3>Mein Team</h3>
        <table>
          <g:each in="${team.getMembers()}" var="member">
            <tr>
              <td>${member.getTitle()} ${member.getFirstName()} ${member.getLastName()}</td>
              <td>${member.getPoints()}</td>
            </tr>
          </g:each>
        </table>
      </g:if>
      </div>
    </div>
  </body>
</html>
