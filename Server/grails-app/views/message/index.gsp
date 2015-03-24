<!DOCTYPE html>
<html>
  <head>
    <meta name="layout" content="main"/>
    <title>Inbox</title>
  </head>
  <body>
    <h1>Teameinladungen</h1>
    <table>
      <thead>
        <tr>
          <td>Absender</td>
          <td>Team</td>
          <td></td>
          <td></td>
        </tr>
      </thead>
      <tbody>
        <g:each in="${teamInvites}" var="teamInvite">
          <tr>
            <td>${teamInvite.getSender().getName()}<td>
            <td>${teamInvite.getSender().getTeam().getName()}</td>
            <td><a href="<g:createLink controller="user" action="joinTeam" id="${teamInvite.getSender().getTeam().getId()}"/>">Beitreten</a></td>
            <td><a href="<g:createLink action="delete" id="${teamInvite.getId()}"/>">loeschen</a></td>
          </tr>
        </g:each>
      </tbody>
    </table>
  </body>
</html>
