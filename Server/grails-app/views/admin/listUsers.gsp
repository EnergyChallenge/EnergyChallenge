<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="main" />
    <title>EnergyChallenge</title>
    <asset:stylesheet src="table.css"/>
</head>
<body>

<h1>Benutzer</h1>

<table class="list">
    <thead>
    <tr>
        <td>Email addresse</td>
        <td>Vollname</td>
        <td>Team</td>
        <td>Institute</td>
        <td>Punkte</td>
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
                ${user.getTeam()}
            </td>
            <td>
                ${user.getInstitute()}
            </td>

            <td>
                ${user.getPoints()}
            </td>
        </tr>
    </g:each>
    </tbody>
</table>
</body>
</html>