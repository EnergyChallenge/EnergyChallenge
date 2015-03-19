<!DOCTYPE html>
<html>
  <head>
    <meta name="layout" content="main"/>
    <title>EnergyChallenge</title>
  </head>
  <body>
    <h1>${user.getName()}</h1><br>
    <h1>Avatar Type: ${user.getAvatarType()}</h1
    <g:if test="${user.getAvatar()}">
    <h1>The Users Image</h1>
      <img class="avatar" src="${createLink(controller:'user', action:'avatar_image', id:user.ident())}" />
    </g:if>
  </body>
</html>
