<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="main" />
    <title>EnergyChallenge</title>
    <asset:stylesheet src="table.css"/>
</head>
<body>

<h1>E-Mail versenden</h1>
<g:form action="emailMessage">
    <input type="hidden" name="targetUri" value="${targetUri}" />
    <table style="width: 100%;">
        <tr>
            <td style="width: 100px; vertical-align: top;">
            	Betreff:
            </td>
            <td>
            	<input type="text" name="subject" value="${subject}"  style="width: 100%;"/>
            </td>
        </tr>
        <tr>
            <td style="width: 100px; vertical-align: top;">
            	Nachricht:
            </td>
            <td>
            	<textarea name="message" style="width: 100%; height: 300px;">${message}</textarea>
            </td>
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
