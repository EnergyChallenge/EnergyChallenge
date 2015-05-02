<!DOCTYPE html>
<html>
<head>
	<meta name="layout" content="main" />
	<asset:stylesheet src="proposal.css" />
</head>
<body>
	<a href="${createLink(controller:'Proposal')}" class="button allproposals">
		Alle Vorschläge
	</a>
	<h1>Energiesparvorschlag</h1>
	<p class="proposaltext">
		${proposal.getDescription()}
		<em>- 
			<g:if test="${proposal.getPoints()} == 1">
				1 Punkt
			</g:if>
			<g:else>
				${proposal.getPoints()} Punkte				
			</g:else>
		</em>
	</p>
	
	<g:form action="addComment" class="addcomment">
		<input type="hidden" name="id" value="${id}" />
		<div class="commenttext">
			<input type="text" name="text" value="${(ownComment != null) ? ownComment.getText() : ""}" placeholder="Ihr Kommentar"/>
		</div>
		<div class="commentrating">
			<div class="rating">
			    <input type="radio" id="star5" name="rating" value="5" ${(ownComment != null && ownComment.getRating() == 5) ? 'checked="checked"' : ''} /><label for="star5" ></label>
			    <input type="radio" id="star4" name="rating" value="4" ${(ownComment != null && ownComment.getRating() == 4) ? 'checked="checked"' : ''} /><label for="star4" ></label>
			    <input type="radio" id="star3" name="rating" value="3" ${(ownComment != null && ownComment.getRating() == 3) ? 'checked="checked"' : ''} /><label for="star3" ></label>
			    <input type="radio" id="star2" name="rating" value="2" ${(ownComment != null && ownComment.getRating() == 2) ? 'checked="checked"' : ''} /><label for="star2" ></label>
			    <input type="radio" id="star1" name="rating" value="1" ${(ownComment != null && ownComment.getRating() == 1) ? 'checked="checked"' : ''} /><label for="star1" ></label>
			</div>
		</div>
		<div class="commentsubmit">
			<input type="submit" value="Kommentieren" />
		</div>
		<div class="clear"></div>
	</g:form>
	<table class="list">
		<thead>
			<tr>
				<th>Kommentar</th>
				<th style="width: 100px;">Bewertung</th>
			</tr>
		</thead>
		<tbody>
			<g:each in="${comments}" var="comment">
				<tr>
					<td>
						${comment.getText()}
					</td>
					<td>
						<g:each var="i" in="${ (0..<5)}">
							<g:if test="${comment.getRating() <= i}">
								<i class="fa fa-star-o"></i>
							</g:if>
							<g:else>
								<i class="fa fa-star"></i>
							</g:else>
						</g:each>
					</td>
				</tr>
			</g:each>
		</tbody>
	</table>
</body>
</html>
