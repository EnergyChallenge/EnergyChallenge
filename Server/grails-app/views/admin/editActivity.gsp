<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="main" />
    <title>EnergyChallenge</title>
    <asset:stylesheet src="table.css"/>
</head>
<body>

<g:if test="${newActivity == 'true'}">
	<h1>Neue Aktivität anlegen</h1>
</g:if>
<g:else>
	<h1>Aktivität ändern</h1>
</g:else>

<p>
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
            <td>Dauer:</td>
            <td><input type="number" name="durationUnits" value="${params.duration}" />
                <select name="durationUnitInSeconds" >
                    <option value="1">Sekunde(n)</option>
                    <option value="60">Minute(n)</option>
                    <option value="3600">Stunde(n)</option>
                    <option value="86400">Tag(e)</option>
                    <option value="604800">Woche(n)</option>
                    <option value="31536000">Jahr(e)</option>
               </select>
            </td>
        </tr>
        <tr>
            <td />
            <td><input type="submit" value="Ändern" /></td>
        </tr>
        </tbody>
    </table>
</g:form>
</p>
</body>
</html>
