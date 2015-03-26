<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="main" />
    <title>EnergyChallenge</title>
    <asset:stylesheet src="table.css"/>
</head>
<body>

<h1>Team Erstellen</h1>

<g:form action="newTeam">
    <table>
        <tr>
            <td>Teamname:</td>
            <td><input type="text" name="name" value="${name}" /></td>
        </tr>
        <tr>
            <td />
            <td><input type="submit" value="Create" /></td>
        </tr>
    </table>
</g:form>
</body>
</html>
