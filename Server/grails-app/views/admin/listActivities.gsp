<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="main" />
    <title>EnergyChallenge</title>
    <asset:stylesheet src="table.css"/>
</head>
<body>

<h1>Activitäten</h1>

<table class="list">
    <thead>
    <tr>
        <td>Activitäten Beschreibung</td>
        <td>Punkte</td>
        <td>Dauer</td>
    </tr>
    </thead>
    <tbody>
    <g:each in="${activities}" var="activity">
        <%-- TODO Link to Activities --%>
        <tr>
            <td>
                ${activity.getDescription()}
            </td>
            <td>
                ${activity.getPoints()}
            </td>
            <td>
                ${activity.getDuration()}
            </td>
        </tr>
    </g:each>
    </tbody>
</table>
</body>
</html>