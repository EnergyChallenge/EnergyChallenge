<!DOCTYPE html>
<html>
  <head>
      <meta name="layout" content="main" />
      <title>EnergyChallenge</title>
      <asset:stylesheet src="table.css"/>
  </head>
  <body>
    <h1>Benutzer Bearbeiten</h1>
    <g:form action="save">
        <table>
            <tr>
                <td>Title:</td>
                <td><input type="text" name="title" value="${user.getTitle()}" /></td>
            </tr>
            <tr>
                <td>Vorname:</td>
                <td><input type="text" name="firstName" value="${user.getFirstName()}" /></td>
            </tr>
            <tr>
                <td>Nachname:</td>
                <td><input type="text" name="lastName" value="${user.getLastName()}" /></td>
            </tr>
            <tr>
                <td />
                <td><input type="submit" value="Speichern" /></td>
            </tr>
        </table>
    </g:form>
    <g:form action="changePassword">
        <table>
            <tr>
                <td>Passwort:</td>
                <td><input type="password" name="password" value="" /></td>
            </tr>
            <tr>
                <td>Passwort (wiederholen):</td>
                <td><input type="password" name="password2" value="" /></td>
            </tr>
            <tr>
                <td />
                <td><input type="submit" value="Ändern" /></td>
            </tr>
        </table>
    </g:form>
    <g:if test="${team}">
      <a href="<g:createLink controller="team" action="index" />">Mein Team Bearbeiten</a>
    </g:if>
    <g:else>
      <g:form action="newTeam">
          <table>
              <tr>
                  <td>Teamname:</td>
                  <td><input type="text" name="name" value="" /></td>
              </tr>
              <tr>
                  <td />
                  <td><input type="submit" value="Team erstellen" /></td>
              </tr>
          </table>
      </g:form>
    </g:else>
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
