<!DOCTYPE html>
<html>
  <head>
    <meta name="layout" content="main"/>
    <title>EnergyChallenge</title>
  </head>
  <body>
    <h1>EnergyChallenge</h1>
    <div style="float:left">
      <h2>Meine Favoriten</h2>
      <table>
      	<style>
		#td {margin-left: 30px; margin-right: 20px;}
		</style>
        <g:each in="${favoriteActivitys}" var="activity">
          <tr>
            <td> ${activity.getDescription()} </td>
            <td> ${activity.getPoints()} Punkt(e) </td>
            <td>
              <g:if test="Aktivität erledigbar" >
                <g:form name="completeActivityForm" url="[action:'completeActivity',controller:'activity',id: activity.getId()]">
                  <g:actionSubmitImage value="Aktivität erledigen" action="completeActivity"
                     src="${resource(dir: 'images', file: 'complete.png')}"/>	<!-- actionsubmitImage creates an imagebutton for the method completeActivity -->
                </g:form>
              </g:if>
              <g:else>
              <!-- TODO Aktivitätencounter anzeigen -->
              </g:else>
            </td>
            <td>
              <g:form name="addToFavoritesForm" url="[action:'removeFromFavorites',controller:'activity',id: activity.getId()]">
              <g:actionSubmitImage value="defavorisieren" action="removeFromFavorites"
                     src="${resource(dir: 'images', file: 'favorite1.png')}"/>  
              </g:form>
            </td>
          </tr>
        </g:each>
      </table>
      <a href="<g:createLink controller="activity" action="index"/>">Weitere Aktivitäten</a>
    </div>
    <div style="float:right">
      <h2>Punkte Stand</h2>
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
      <g:if test="${team}">
        <h2>Mein Team</h2>
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
  </body>
</html>
