<!DOCTYPE html>
<html>
  <head>
    <meta name="layout" content="main"/>
    <title>EnergyChallenge</title>
    <asset:stylesheet src="table.css"/>

  </head>
  <body>
    <h1>Admin Bereich</h1>
      <table class="list">
          <thead>
          <tr>
              <td>Email addresse</td>
              <td>Vollname</td>
              <td>Team</td>
              <td>Institute</td>
              <td>Punkte</td>
              <td></td>
              <td></td>
          </tr>
          </thead>
          <tbody>
          <g:each in="${users}" var="user">
              <%-- TODO Link to Users --%>
              <tr>
                  <td>
                      ${user.getEmail()}
                  </td>
                  <td>
                      ${user.getTitle()}
                      ${user.getFirstName()}
                      ${user.getLastName()}
                  </td>
                  <td>
                      <g:if test="${user.getTeam()}">
                        ${user.getTeam().getName()}
                      </g:if>
                  </td>
                  <td>
                      ${user.getInstitute().getName()}
                  </td>
      
                  <td>
                      ${user.getPoints()}
                  </td>
                  <td>
                     <g:if test="${user.blocked}">
                       <a href="<g:createLink action="unblockUser" id="${user.id}"/>">aktivieren</a>
                     </g:if><g:else>
                       <a href="<g:createLink action="blockUser" id="${user.id}"/>">blocken</a>
                     </g:else>
                  </td>
                  <td>
                     <a href="<g:createLink action="deleteUser" id="${user.id}"/>">loeschen</a>
                  </td>
              </tr>
          </g:each>
          </tbody>
      </table>    
  </body>
</html>
