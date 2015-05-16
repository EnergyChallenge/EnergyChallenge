<html>
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
  <meta name="layout" content="public" />
</head>
<body>
  <g:if test="${flash.message}">
    <div class="message">${flash.message}</div>
  </g:if>
  <div id="center">
  <h1>Jetzt registrieren!</h1>
  <g:form action="signUp" params="[id: params.id]">
    <input type="hidden" name="targetUri" value="${targetUri}" />
    <table>
      <tbody>
        <tr>
          <td>E-Mail:</td>
          <td><input type="text" name="email" value="${params.email}" /></td>
        </tr>
        <tr>
          <td>Vorname:</td>
          <td><input type="text" name="firstName" value="${params.firstName}" /></td>
        </tr>
        <tr>
          <td>Nachname:</td>
          <td><input type="text" name="lastName" value="${params.lastName}" /></td>
        </tr>
        <tr>
          <td>Passwort:</td>
          <td><input type="password" name="password" value="" /></td>
        </tr>
        <tr>
          <td>Passwort wiederholen:</td>
          <td><input type="password" name="password2" value="" /></td>
        </tr>
        <tr>
          <td>Fakultät/Institut:</td>
          <td><select name="instituteId" >
          <g:each in="${institutes}" var="institute">
          <option value="${institute.getId()}">${institute.getName()}</option>
          </g:each>
          	
          </select></td>
        </tr>
        <tr>
        	<td colspan="2">
        		<textarea>
        			Regeln folgen hier...
        		</textarea>
        	</td>
        </tr>
        <tr>
          <td>
          	<input type="checkbox" name="agreeRules" id="agreeRules" value="1" />
          	<label for="agreeRules">Ich akzeptiere die Regeln...</label>
          </td>
          <td><input type="submit" value="Registrieren" /></td>
        </tr>
      </tbody>
    </table>
  </g:form>
  </div>
</body>
</html>
