class UrlMappings {

	static mappings = {
        "/$controller/$action?/$id?(.$format)?"{
            constraints {
                // apply constraints here
            }
        }
	    "/"(controller:"startpage")
        "500"(view:'/error')
	}
}
