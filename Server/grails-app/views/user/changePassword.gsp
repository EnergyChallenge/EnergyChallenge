<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="main" />
    <title>EnergyChallenge</title>
    <asset:stylesheet src="table.css"/>
</head>
<body>

<h1>Passwort Bearbeiten</h1>

<g:form action="changePassword">
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
