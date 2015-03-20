<!DOCTYPE html>
<html>
  <head>
    <meta name="layout" content="main"/>
    <title>${name}</title>
  </head>
  <body>
    <g:if test="${isCurrent == true}">
      <div>Bearbeiten</div>
    </g:if>
    <div class="head">
      <div class="image">
        <!-- Image here instead of div -->
        <div style="width: 100px; height: 100px;">
          <g:if test="${type == 'user'}">
            <img src="<g:createLink controller="user" action="avatar" id="${id}"/>" alt="image"/>
          </g:if><g:else>
            <img src="<g:createLink controller="team" action="avatar" id="${id}"/>" alt="image"/>
          </g:else>
        </div>
        <h1>${name}</h1>
        <g:if test="${type == 'user' && teamname != ''}">
          <h2>${teamName}</h2>
        </g:if>
      </div>
    </div>
    <div class="left">
      <ul>
        <g:if test="${type == 'user'}">
          <li>Institut: ${institute}</li>
        </g:if>
        <li>Gesammelte Punkte: ${collectedPoints}</li>
        <li>Position: ${rankingPosition}</li><!-- Eventuell Link hier -->
      </ul>
      <g:if test="${type == 'team'}">
        <h2>Team Mitglieder</h2>
        <ul>
          <g:each in="${members}" var="member">
            <li><a href="<g:createLink action="user" id="${member.id}" />">${member.name}</a></li><!-- Link hier -->
          </g:each>
        </ul>
      </g:if>
    </div>
    <div class="right">
      <h2>Zuletzt Abgeschlossene Aktivitaeten</h2>
      <g:if test ="${type == 'user'}">
      <ul>
        <g:each in="${recentActivities}" var="completedActivity">
          <li>${completedActivity.getActivity().getDescription()}</li>
        </g:each>
      </ul>
      </g:if>
      <g:else>
        <table>
          <tbody>
            <g:each in="${lastActivities}" var="activity">
              <tr>
                <td>${activity.member}</td>
                <td>${activity.recentActivity.getActivity().getDescription()}</td>
              </tr>
            </g:each>
          </tbody>
        </table>
      </g:else>
    </div>
  </body>
</html>
