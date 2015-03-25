<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="main" />
    <title>EnergyChallenge</title>
    <asset:stylesheet src="table.css"/>
</head>
<body>

<h1>Team Bearbeiten</h1>

<g:if test="${flash.message}">
    <div class="message">${flash.message}</div>
</g:if>
<g:form action="editTeam">
    <input type="hidden" name="targetUri" value="${targetUri}" />
    <table>
        <tbody>
        <tr>
            <td>Name:</td>
            <td><input type="text" name="name" value="${name}" /></td>
        </tr>
        <tr>
            <td />
            <td><input type="submit" value="bearbeiten" /></td>
        </tr>
        </tbody>
    </table>
</g:form>
</body>
</html>