<g:applyLayout name="base">
  <head>
    <asset:stylesheet src="main.css" />
    <asset:stylesheet src="navigation.css" />    
    <g:layoutHead />
  </head>
  <content tag="topRight"> 
  <% def user = de.unikiel.klik.model.User.findByEmail(org.apache.shiro.SecurityUtils.getSubject().getPrincipal())
  %>
    <g:if test="${user}" >
    <div style="float: right">
        <g:form name="logoutFrom" url="[action:'signOut',controller:'auth']">
          <input type="submit" value="Logout" />
        </g:form> 
      </div>
      <div>
        <div style="float: left">
         <a href="<g:createLink controller="user" action="edit" />"><img src="<g:createLink controller="user" action="avatar" id="${user.getId()}"/>" alt="Image"/></a> 
        </div>
        <div style="float: right">
          ${user.getTitle()} ${user.getFirstName()} ${user.getLastName()}<br>
          ${user.getEmail()}<br>
          Punkte: ${user.getPoints()}<br>
        </div>
      </div>
    </g:if>
  </content>
  <body>
    <div class="mainBody">
      <nav>
        <ul>
          <li><a href="${createLink(controller:'Landing')}" >EnergyChallenge</a></li>
          <li><a href="${createLink(controller:'Activity')}" >Aktivitäten</a></li>
          <li><a href="${createLink(controller:'Ranking')}" >Rangliste</a></li>
          <li><a href="${createLink(controller:'Proposal')}" >Energiesparvorschläge</a></li>
          <li><a href="${createLink(controller:'Statistics')}" >Statistiken</a></li>
          <li><a href="${createLink(controller:'User', action: 'edit')}" >Mein Profil bearbeiten</a></li>
        </ul>
      </nav>
      <div id="content">
        <g:layoutBody />
      </div>
    </div>
  </body>
</g:applyLayout>
