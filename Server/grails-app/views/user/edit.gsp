<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="main" />
    <title>EnergyChallenge</title>
    <asset:stylesheet src="table.css"/>
</head>
<body>

<h1>Benutzer Bearbeiten</h1>

<g:if test="${flash.message}">
    <div class="message">${flash.message}</div>
</g:if>
<g:form action="edit">
<input type="hidden" name="targetUri" value="${targetUri}" />
    <table>
        <tr>
            <td>Title:</td>
            <td>
                <select name="title" >
                    <option value="">-</option>
                    <option value="Prof">Prof.</option>
                    <option value="Doktor">Dr.</option>
                </select>
            </td>
        </tr>
        <tr>
            <td>Vorname:</td>
            <td><input type="text" name="firstName" value="${firstName}" /></td>
        </tr>
        <tr>
            <td>Nachname:</td>
            <td><input type="text" name="lastName" value="${lastName}" /></td>
        </tr>
        <tr>
            <td />
            <td><input type="submit" value="Save" /></td>
        </tr>
    </table>
    <!-- Test Avatar Uploader -->
    <!-- <fieldset>
      <legend>Avatar Upload</legend>
      <g:uploadForm action="upload_avatar">
        <label for="avatar">Avatar (512k)</label>
        <input type="file" name="avatar" id="avatar" />
        <div style="font-size:0.8em; margin: 1.0em;">
          For best results, your avatar should have a width-to-height ratio of 4:5.
          For example, if your image is 80 pixels wide, it should be 100 pixels high.
        </div>
        <input type="submit" class="buttons" value="Upload" />
      </g:uploadForm>
    </fieldset> -->

</g:form>
</body>
</html>