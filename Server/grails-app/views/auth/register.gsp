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
          <td>Username:</td>
          <td><input type="text" name="username" value="${username}" /></td>
        </tr>
        <tr>
          <td>Password:</td>
          <td><input type="password" name="password" value="" /></td>
        </tr>
        <tr>
          <td>Password:</td>
          <td><input type="password" name="password2" value="" /></td>
        </tr>
        <tr>
          <td>Faculty:</td>
          <td><input type="text" name="faculty" value="${faculty}" /></td>
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
