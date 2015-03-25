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
  <g:if test="${params.token == null}">
    <g:form action="requestPassword">
      <table>
        <tbody>
          <tr>
            <td>Email:</td>
            <td><input type="text" name="email" value="${email}" /></td>
          </tr>
            <td />
            <td><input type="submit" value="Neues Password anfordern" /></td>
          </tr>
        </tbody>
      </table>
    </g:form>
  </g:if>
  <g:else>
    <g:form action="changePassword">
      <input type="hidden" name="token" value="${params.token}" />
      <table>
        <tbody>
          <tr>
            <td>Email:</td>
            <td><input type="text" name="email" value="" /></td>
          </tr>
          <tr>
            <td>Neues Password:</td>
            <td><input type="password" name="password" value="" /></td>
          </tr>
          <tr>
            <td>Password Wiederholen:</td>
            <td><input type="password" name="password2" value="" /></td>
          </tr>
          <tr>
            <td />
            <td><input type="submit" value="Neues Password anfordern" /></td>
          </tr>
        </tbody>
      </table>
    </g:form>
  </g:else>
</body>
</html>
