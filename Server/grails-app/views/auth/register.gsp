<html>
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
  <meta name="layout" content="public" />
  <title>Login</title>
</head>
<body>
  <g:if test="${flash.message}">
    <div class="message">${flash.message}</div>
  </g:if>
  <g:form action="signUp">
    <input type="hidden" name="targetUri" value="${targetUri}" />
    <table>
      <tbody>
        <tr>
          <td>Email:</td>
          <td><input type="text" name="email" value="${email}" /></td>
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
          <td>Passwort:</td>
          <td><input type="password" name="password" value="" /></td>
        </tr>
        <tr>
          <td>Passwort wiederholen:</td>
          <td><input type="password" name="password2" value="" /></td>
        </tr>
        <tr>
          <td>Fakultät:</td>
          <td>
          <td><select name="instituteId" >
          <g:each in="${institutes}" var="institute">
          <option value="${institute.getId()}">${institute.getName()}</option>
          </g:each>
          	
          </select></td>
        </tr>
        <tr>
          <td />
          <td><input type="submit" value="Registrieren" /></td>
        </tr>
      </tbody>
    </table>
  </g:form>
</body>
</html>
