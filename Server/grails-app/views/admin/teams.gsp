<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="main" />
    <title>EnergyChallenge</title>
    <asset:stylesheet src="table.css"/>
</head>
<body>

<h1>Teamverwaltung</h1>

<table class="list">
    <thead>
    <tr>
        <td>Name</td>
        <td>Members</td>
        <td>Punkte</td>
        <td></td>
        <td></td>
    </tr>
    </thead>
    <tbody>
    <g:each in="${teams}" var="team">
        <%-- TODO Link to Teams --%>
        <tr>
            <td>
                ${team.getName()}
            </td>
            <td>
                <ul>
                  <g:each in="${team.getMembers()}" var="member">
                    <li>${member.getName()}</li>
                  </g:each>
                </ul>
            </td>
            <td>
                ${team.getPoints()}
            </td>
            <td>
               <g:if test="${team.blocked}">
                 <a href="<g:createLink action="unblockTeam" id="${team.id}"/>">aktivieren</a>
               </g:if><g:else>
                 <a href="<g:createLink action="blockTeam" id="${team.id}"/>">blocken</a>
               </g:else>
            </td>
            <td>
               <a href="<g:createLink action="deleteTeam" id="${team.id}"/>">loeschen</a>
            </td>

        </tr>
    </g:each>
    </tbody>
</table>
</body>
</html>
