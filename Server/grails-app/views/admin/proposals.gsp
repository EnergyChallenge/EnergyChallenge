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
        <td></td>
        <td></td>
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
                ${proposal.getAuthor().getName()}
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
            <td>
               <a href="<g:createLink action="editActivity" params="[proposalId: "${proposal.id}", description: "${proposal.getDescription()}", points: "${proposal.getPoints()}"]"/>">umwandeln</a>

            </td>
            <td>
               <a href="<g:createLink action="deleteProposal" params="[proposalId: "${proposal.id}"]"/>">loeschen</a>
            </td>
        </tr>
    </g:each>
    </tbody>
</table>
</body>
</html>
