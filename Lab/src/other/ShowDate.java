package other;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ShowDate {
	public String showDate() {
		Date date = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		return dateFormat.format(date);
	}
}
