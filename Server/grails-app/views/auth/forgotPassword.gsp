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
  <g:form action="requesPassword">
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
</body>
</html>