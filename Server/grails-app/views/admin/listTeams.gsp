<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="main" />
    <title>EnergyChallenge</title>
    <asset:stylesheet src="table.css"/>
</head>
<body>

<h1>Teams</h1>

<table class="list">
    <thead>
    <tr>
        <td>Name</td>
        <td>Members</td>
        <td>Punkte</td>
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
                ${team.getMembers()}
            </td>
            <td>
                ${team.getPoints()}
            </td>
        </tr>
    </g:each>
    </tbody>
</table>
</body>
</html>