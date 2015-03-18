<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="main" />
    <title>EnergyChallenge</title>
    <asset:stylesheet src="table.css"/>
</head>
<body>

<h1>Team Löschen</h1>

<g:if test="${flash.message}">
    <div class="message">${flash.message}</div>
</g:if>
<g:form action="deleteTeam">
    <input type="hidden" name="targetUri" value="${targetUri}" />
    <table>
        <tbody>
        <tr>
            <td>Team:</td>
            <td>
                <select name="teamId" >
                    <g:each in="${Team.findAll()}" var="teamId">
                        <option value="${team.getId()}">${team.getName()}</option>
                    </g:each>
                </select>
            </td>
        </tr>
        <tr>
            <td />
            <td><input type="submit" value="Löschen" /></td>
        </tr>
        </tbody>
    </table>
</g:form>
</body>
</html>