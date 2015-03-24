<!DOCTYPE html>
<html>
  <head>
  	<meta name="layout" content="main"/>
  	<title>EnergyChallenge</title>
    <asset:stylesheet src="table.css"/>

  </head>
  <body>
    <h1>Aktivitätsverwaltung</h1>
<table class="list">
    <thead>
    <tr>
        <td>Activitäten Beschreibung</td>
        <td>Punkte</td>
        <td>Dauer</td>
        <td></td>
        <td></td>
    </tr>
    </thead>
    <tbody>
    <g:each in="${activities}" var="activity">
        <%-- TODO Link to Activities --%>
        <tr>
            <td>
                ${activity.getDescription()}
            </td>
            <td>
                ${activity.getPoints()}
            </td>
            <td>
                ${activity.getDuration()}
            </td>
            <td>
               <a href="<g:createLink action="editActivity" params="[activityId: "${activity.id}", description: "${activity.getDescription()}", points: "${activity.getPoints()}"]"/>">bearbeiten</a> 
            </td>
            <td>
               <a href="<g:createLink action="deleteActivity" params="[activityId: "${activity.id}"]"/>">loeschen</a> 
            </td>

        </tr>
    </g:each>
    </tbody>
</table>    
  </body>
</html>
