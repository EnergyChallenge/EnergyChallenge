<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="main" />
    <title>EnergyChallenge</title>
    <asset:stylesheet src="table.css"/>
</head>
<body>

<h1>Edit Proposal</h1>

<g:if test="${flash.message}">
    <div class="message">${flash.message}</div>
</g:if>
<g:form action="editProposal">
    <input type="hidden" name="targetUri" value="${targetUri}" />
    <table>
        <tbody>
        <tr>
            <td>Beschreibung:</td>
            <td><textarea name="description">${description}</textarea></td>
        </tr>
        <tr>
            <td>Punkte:</td>
            <td><input type="number" name="points" value="${points}" min="1" max="5" /></td>
        </tr>
        <tr>
            <td />
            <td><input type="submit" value="Save" /></td>
        </tr>
        </tbody>
    </table>
</g:form>
</body>
</html>