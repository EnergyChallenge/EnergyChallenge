package de.unikiel.klik.model

// TODO evtl. bessere Datumsklasse auf JDK 1.8 verwenden
import java.util.Date;

class CompletedActivity {

	private Date date;
	private Activity activity;
	 
    static constraints = {
    }
	
	public CompletedActivity(Activity activity, Date date) {
		this.date = date;
		this.activity = activity;
	}
	
	public Activity getActivity() {
		return this.activity;
	}
}
