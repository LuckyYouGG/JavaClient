package team.lj.Utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public final class TimeUtil {

	// get the current system date and time.
	public static String getSysTime() {
		System.out.println(new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(Calendar.getInstance().getTime()));
		return new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(Calendar.getInstance().getTime());
	}
}
