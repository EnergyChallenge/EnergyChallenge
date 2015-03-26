<!DOCTYPE html>
<html>
  <head>
    <meta name="layout" content="main"/>
    <title>Benachrichtigung</title>
    <asset:stylesheet src="table.css"/>
  </head>
  <body>
    <g:if test="${activityNotifications || teamInvites}">
      <g:if test="${teamInvites}">
        <h1>Teameinladungen</h1>
        <table class="list">
          <thead>
            <tr>
              <th>Absender</th>
              <th>Team</th>
              <th></th>
              <th></th>
            </tr>
          </thead>
          <tbody>
            <g:each in="${teamInvites}" var="teamInvite">
              <tr>
                <td>${teamInvite.getSender().getName()}</td>
                <td>${teamInvite.getSender().getTeam().getName()}</td>
                <td><a href="<g:createLink controller="user" action="joinTeam" id="${teamInvite.getSender().getTeam().getId()}"/>">Beitreten</a></td>
                <td><a href="<g:createLink action="delete" id="${teamInvite.getId()}"/>">loeschen</a></td>
              </tr>
            </g:each>
          </tbody>
        </table>
      </g:if>
      <br>
      <br>
      <g:if test="${activityNotifications}">
        <table class="list">
          <tbody>
            <g:each in="${activityNotifications}" var="activityNotification">
              <tr>
                <td>Sie haben schon lange keine Aktivität ausgeführt</td>
                <td><a href="<g:createLink action="delete" id="${activityNotification.getId()}"/>">löschen</a></td>
              </tr>
            </g:each>
          </tbody>
        </table>
      </g:if>
    </g:if>
    <g:else>
      <h1>Keine Nachrichten</h1>
    </g:else>
  </body>
</html>
