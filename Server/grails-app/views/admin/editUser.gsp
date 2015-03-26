<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="main" />
    <title>EnergyChallenge</title>
    <asset:stylesheet src="table.css"/>
</head>
<body>

<h1>Benutzer Bearbeiten</h1>

<g:if test="${flash.message}">
    <div class="message">${flash.message}</div>
</g:if>
<g:form action="editUser">
    <input type="hidden" name="targetUri" value="${targetUri}" />
    <table>
        <tbody>
        <tr>
            <td>Email:</td>
            <td><input type="text" name="email" value="${email}" /></td>
        </tr>
        <tr>
            <td>Titel:</td>
            <td>
                <select name="title" >
                    <option value="">-</option>
                    <option value="Prof">Prof.</option>
                    <option value="Doktor">Dr.</option>
                </select>
            </td>
        </tr>
        <tr>
            <td>Vorname:</td>
            <td><input type="text" name="firstName" value="${firstName}" /></td>
        </tr>
        <tr>
            <td>Nachname:</td>
            <td><input type="text" name="lastName" value="${lastName}" /></td>
        </tr>
        <tr>
            <td>Team:</td>
            <td>
                <select name="team" >
                    <g:each in="${Team.findAll()}" var="team">
                        <option value="${team.getId()}">${team.getName()}</option>
                    </g:each>
                </select>
            </td>
        </tr>
        <tr>
            <td>Fakultät:</td>
            <td>
                <select name="institude" >
                    <g:each in="${Insitude.findAll()}" var="institude">
                        <option value="${institude.getId()}">${institude.getName()}</option>
                    </g:each>
                </select>
            </td>
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