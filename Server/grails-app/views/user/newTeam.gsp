<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="main" />
    <title>EnergyChallenge</title>
    <asset:stylesheet src="table.css"/>
</head>
<body>

<h1>Create Team</h1>

<g:if test="${flash.message}">
    <div class="message">${flash.message}</div>
</g:if>
<g:form action="newTeam">
<input type="hidden" name="targetUri" value="${targetUri}" />
    <table>
        <tr>
            <td>Team Name:</td>
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