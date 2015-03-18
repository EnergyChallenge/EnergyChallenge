<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="main" />
    <title>EnergyChallenge</title>
    <asset:stylesheet src="table.css"/>
</head>
<body>

<h1>Benutzer Sperren</h1>

<g:if test="${flash.message}">
    <div class="message">${flash.message}</div>
</g:if>
<g:form action="blockUser">
    <input type="hidden" name="targetUri" value="${targetUri}" />
    <table>
        <tbody>
        <tr>
            <td>Benutzer:</td>
            <td>
                <select name="userId" >
                    <g:each in="${User.findAll()}" var="userId">
                        <option value="${user.getId()}">${user.getName()}</option>
                    </g:each>
                </select>
            </td>
        </tr>
        <tr>
            <td />
            <td><input type="submit" value="Sperren" /></td>
        </tr>
        </tbody>
    </table>
</g:form>
</body>
</html>