<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="main" />
    <title>EnergyChallenge</title>
    <asset:stylesheet src="table.css"/>
</head>
<body>

<h1>Email-Versand</h1>

<g:if test="${flash.message}">
    <div class="message">${flash.message}</div>
</g:if>
<g:form action="emailMessage">
    <input type="hidden" name="targetUri" value="${targetUri}" />
    <table>
        <tbody>
        <tr>
            <td>Betreff:</td>
            <td><input type="text" name="subject" value="${subject}" /></td>
        </tr>
        <tr>
            <td>Nachricht:</td>
            <td><textarea name="message">${message}</textarea></td>
        </tr>
        <tr>
            <td />
            <td><input type="submit" value="Abschicken" /></td>
        </tr>
        </tbody>
    </table>
</g:form>
</body>
</html>