<!DOCTYPE html>
<html>
  <head>
      <meta name="layout" content="main" />
      <title>EnergyChallenge</title>
      <asset:stylesheet src="table.css"/>
  </head>
  <body>
  
    <h1>Team Name Bearbeiten</h1>
    
    <g:form action="edit">
        <input type="hidden" name="targetUri" value="${targetUri}" />
        <table>
            <tbody>
            <tr>
                <td>Name:</td>
                <td><input type="text" name="name" value="${name}" /></td>
            </tr>
            <tr>
                <td />
                <td><input type="submit" value="Save" /></td>
            </tr>
            </tbody>
        </table>
    </g:form>
    <h2>TODO invite User</h2>
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
