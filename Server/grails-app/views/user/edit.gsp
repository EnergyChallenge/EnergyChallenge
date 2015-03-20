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
                <td>
                    <select name="title" >
                        <option value=" ">-</option>
                        <option value="Prof">Prof.</option>
                        <option value="Doktor">Dr.</option>
                    </select>
                </td>
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
                <td>Password:</td>
                <td><input type="password" name="password" value="" /></td>
            </tr>
            <tr>
                <td>Password (repeat):</td>
                <td><input type="password" name="password2" value="" /></td>
            </tr>
            <tr>
                <td />
                <td><input type="submit" value="Save" /></td>
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
                  <td>Team Name:</td>
                  <td><input type="text" name="name" value="" /></td>
              </tr>
              <tr>
                  <td />
                  <td><input type="submit" value="Create" /></td>
              </tr>
          </table>
      </g:form>
    </g:else>
      <fieldset>
        <legend>Avatar Upload</legend>
        <g:uploadForm action="uploadAvatar">
          <label for="avatar">Avatar (512k)</label>
          <input type="file" name="avatar" id="avatar" />
          <div style="font-size:0.8em; margin: 1.0em;">
            For best results, your avatar should have a width-to-height ratio of 4:5.
            For example, if your image is 80 pixels wide, it should be 100 pixels high.
          </div>
          <input type="submit" class="buttons" value="Upload" />
        </g:uploadForm>
      </fieldset>
  </body>
</html>
