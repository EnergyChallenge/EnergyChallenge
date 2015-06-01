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
  <div id="center">
	  <g:if test="${params.token == null}">
	    <g:form action="requestPassword">
	      Ihr E-Mail Adresse:
	      <input type="text" name="email" value="${email}" />
	      <input type="submit" value="Neues Password anfordern" />
	    </g:form>
	  </g:if>
	  <g:else>
	    <g:form action="changePassword">
	      <input type="hidden" name="token" value="${params.token}" />
	      <table>
	        <tbody>
	          <tr>
	            <td>E-Mail:</td>
	            <td><input type="text" name="email" value="" /></td>
	          </tr>
	          <tr>
	            <td>Neues Passwort:</td>
	            <td><input type="password" name="password" value="" /></td>
	          </tr>
	          <tr>
	            <td>Passwort (wiederholen):</td>
	            <td><input type="password" name="password2" value="" /></td>
	          </tr>
	          <tr>
	            <td />
	            <td><input type="submit" value="Neues Password speichern" /></td>
	          </tr>
	        </tbody>
	      </table>
	    </g:form>
	  </g:else>
	 </div>
</body>
</html>
