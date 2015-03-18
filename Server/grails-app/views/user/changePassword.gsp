<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="main" />
    <title>EnergyChallenge</title>
    <asset:stylesheet src="table.css"/>
</head>
<body>

<h1>Passwort Bearbeiten</h1>

<g:if test="${flash.message}">
    <div class="message">${flash.message}</div>
</g:if>
<g:form action="changePassword">
<input type="hidden" name="targetUri" value="${targetUri}" />
    <table>
        <tr>
            <td>Password:</td>
            <td><input type="password" name="password" value="${password}" /></td>
        </tr>
        <tr>
            <td>Password (repeat):</td>
            <td><input type="password" name="password2" value="${password}2" /></td>
        </tr>
        <tr>
            <td />
            <td><input type="submit" value="Save" /></td>
        </tr>
    </table>
</g:form>
</body>
</html>