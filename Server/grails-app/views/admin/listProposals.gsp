<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="main" />
    <title>EnergyChallenge</title>
    <asset:stylesheet src="table.css"/>
</head>
<body>

<h1>Vorschlag</h1>

<table class="list">
    <thead>
    <tr>
        <td>Vorschlag Beschreibung</td>
        <td>Punkte</td>
        <td>Author</td>
        <td>Erstellungsdatum</td>
        <td>Rating</td>
        <td>Kommentare</td>
    </tr>
    </thead>
    <tbody>
    <g:each in="${proposals}" var="proposal">
        <%-- TODO Link to Proposals --%>
        <tr>
            <td>
                ${proposal.getDescription()}
            </td>
            <td>
                ${proposal.getPoints()}
            </td>
            <td>
                ${proposal.getAuthor()}
            </td>
            <td>
                ${proposal.getDateCreated()}
            </td>
            <td>
                ${proposal.getRating()}
            </td>
            <td>
                ${proposal.getComments()}
            </td>
        </tr>
    </g:each>
    </tbody>
</table>
</body>
</html>