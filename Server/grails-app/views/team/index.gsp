<!DOCTYPE html>
<html>
  <head>
      <meta name="layout" content="main" />
      <title>EnergyChallenge</title>
      <asset:stylesheet src="table.css"/>
  </head>
  <body>
  
    <h1>Team Bearbeiten</h1>
    
    <g:form action="edit">
        <input type="hidden" name="targetUri" value="${targetUri}" />
        <table>
            <tbody>
            <tr>
                <td>Team Name:</td>
                <td><input type="text" name="name" value="${name}" /></td>
            </tr>
            <tr>
                <td />
                <td><input type="submit" value="Speichern" /></td>
            </tr>
            </tbody>
        </table>
    </g:form>
    <fieldset>
        <legend>Avatar Hochladen</legend>
        <g:uploadForm action="uploadAvatar">
          <label for="avatar">Avatar (512k)</label>
          <input type="file" name="avatar" id="avatar" />
          <div style="font-size:0.8em; margin: 1.0em;">
            Zulässige Formate: <b>.jpg</b>, <b>.jpeg</b>, <b>.png</b> und <b>.gif</b>.<br />
            Für eine unverzerrte Darstellung muss das Bild ein <b>quadratisches Format</b> haben.
          </div>
          <input type="submit" class="buttons" value="Hochladen" />
        </g:uploadForm>
      </fieldset>
  </body>
</html>
