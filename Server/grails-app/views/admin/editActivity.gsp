<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="main" />
    <title>EnergyChallenge</title>
    <asset:stylesheet src="table.css"/>
</head>
<body>

<h1>Edit Activity</h1>

<g:form action="changeActivity">
    <input type="hidden" name="proposalId" value="${params.proposalId}" />
    <input type="hidden" name="activityId" value="${params.activityId}" />
    <table>
        <tbody>
        <tr>
            <td>Beschreibung:</td>
	    <td><textarea name="description">${params.description}</textarea></td>
        </tr>
        <tr>
            <td>Punkte:</td>
            <td><input type="number" name="points" value="${params.points}" min="1" max="5" /></td>
        </tr>
        <tr>
            <td>Dauer (tage):</td>
            <td><input type="number" name="duration" value="${params.duration}" /></td>
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
