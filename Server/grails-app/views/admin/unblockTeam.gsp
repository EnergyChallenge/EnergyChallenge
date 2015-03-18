<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="main" />
    <title>EnergyChallenge</title>
    <asset:stylesheet src="table.css"/>
</head>
<body>

<h1>Team Entsperren</h1>

<g:if test="${flash.message}">
    <div class="message">${flash.message}</div>
</g:if>
<g:form action="unblockTeam">
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
            <td><input type="submit" value="Entsperren" /></td>
        </tr>
        </tbody>
    </table>
</g:form>
</body>
</html>