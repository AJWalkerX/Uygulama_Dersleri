package projects.kullanici_kayit_sistemi.original;

import java.time.LocalDate;

public class Mail extends Massage {
	private static int mailIndex = 0;
	
	private String title;
	
	public Mail() {
		this.id = mailIndex++;
		this.sentDate = LocalDate.now();
	}
	
	
}