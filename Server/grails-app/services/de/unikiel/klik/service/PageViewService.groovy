package de.unikiel.klik.service

import de.unikiel.klik.persistence.PageView
import org.joda.time.DateTime
import org.joda.time.LocalDate

import grails.transaction.Transactional

@Transactional
class PageViewService {

    static void visitPage(String url) {
	PageView pageView = PageView.findByUrl(url)
	if(pageView && relevant(pageView)){
		pageView.inc()
		pageView.save()
	}else{
		pageView = new PageView(url: url)
		pageView.inc()
		pageView.save()
	}
    }
    static private boolean relevant(PageView pageView){
        return pageView.getDateCreated().toLocalDate().equals(new LocalDate())
    }
}
