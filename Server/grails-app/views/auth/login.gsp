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
  <g:form action="signIn">
    <input type="hidden" name="targetUri" value="${targetUri}" />
    <table>
      <tbody>
        <tr>
          <td>Benutzername:</td>
          <td><input type="text" name="email" value="${username}" /></td>
        </tr>
        <tr>
          <td>Passwort:</td>
          <td><input type="password" name="password" value="" /></td>
        </tr>
        <tr>
          <td><label for="rememberMe">Angemeldet bleiben:</label></td>
          <td><g:checkBox name="rememberMe" value="${rememberMe}" /></td>
        </tr>
        <tr>
          <td />
          <td><input type="submit" value="Anmelden" /></td>
        </tr>
      </tbody>
    </table>
  </g:form>
</body>
</html>
