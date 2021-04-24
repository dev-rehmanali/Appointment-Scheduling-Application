package app;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

public class TimeAPI {

	private Date localDate;
	private Date utcDate;
	private Date estDate;

	private String utcStr;
	private String localStr;
	private String estStr;

	private SimpleDateFormat localDateFormatter;
	private SimpleDateFormat utcDateFormatter;
	private SimpleDateFormat estDateFormatter;
	
	private static final String DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";


	public TimeAPI() {
		
		this.localDateFormatter = new SimpleDateFormat(DATE_FORMAT);
		this.utcDateFormatter = new SimpleDateFormat(DATE_FORMAT);
		this.estDateFormatter = new SimpleDateFormat(DATE_FORMAT);
		
		this.utcDateFormatter.setTimeZone(TimeZone.getTimeZone(ZoneId.of("UTC")));
		this.estDateFormatter.setTimeZone(TimeZone.getTimeZone(ZoneId.of("EST",ZoneId.SHORT_IDS)));
	}

	public Date getLocalDate() {
		return localDate;
	}

	public Date getUtcDate() {
		return utcDate;
	}


	public Date getEstDate() {
		return estDate;
	}

	public String getUtcStr() {
		return utcStr;
	}

	public void setUtcStr(String utcStr) {
		this.setDatesFromUTC(utcStr);
	}

	public String getLocalStr() {
		return localStr;
	}

	public void setLocalStr(String localStr) {
		setDatesFromLocal(localStr);
	}

	public String getEstStr() {
		return estStr;
	}

	public void setEstStr(String estStr) {
		this.setDatesFromEST(estStr);
	}



	private void setDatesFromLocal(String localStr) {


		Date localDate=null;
		try {
			localDate = localDateFormatter.parse(localStr);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		String utcStr = utcDateFormatter.format(localDate);
		Date utcDate = null;
		try {
			utcDate = utcDateFormatter.parse(utcStr);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		String estStr = estDateFormatter.format(localDate);
		Date estDate=null;
		try {
			estDate = estDateFormatter.parse(estStr);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		this.localStr = localStr;
		this.utcStr = utcStr;
		this.estStr = estStr;

		this.setDates();
	}
	private void setDatesFromUTC(String utcStr) {

		Date utcDate=null;
		try {
			utcDate = utcDateFormatter.parse(utcStr);
		} catch (ParseException e) {
			e.printStackTrace();
		}

		String localStr = localDateFormatter.format(utcDate);
		Date localDate = null;
		try {
			localDate = localDateFormatter.parse(localStr);
		} catch (ParseException e) {
			e.printStackTrace();
		}

		String estStr = estDateFormatter.format(utcDate);
		Date estDate=null;
		try {
			estDate = estDateFormatter.parse(estStr);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		this.localStr = localStr;
		this.utcStr = utcStr;
		this.estStr = estStr;

		this.setDates();
	}
	private void setDatesFromEST(String estStr) {


		Date estDate=null;
		try {
			estDate = this.estDateFormatter.parse(estStr);
			//System.out.println("EST-Date: "+estDate);
		} catch (ParseException e) {
			e.printStackTrace();
		}

		String localStr = this.localDateFormatter.format(estDate);
		Date localDate = null;
		try {
			localDate = this.localDateFormatter.parse(localStr);
			//System.out.println("Local-Date: "+localDate);
		} catch (ParseException e) {
			e.printStackTrace();
		}

		String utcStr = this.utcDateFormatter.format(estDate);
		Date utcDate=null;
		try {
			utcDate = this.utcDateFormatter.parse(utcStr);
		} catch (ParseException e) {
			e.printStackTrace();
		}

		this.localStr = localStr;
		this.utcStr = utcStr;
		this.estStr = estStr;

		this.setDates();
	}


	public boolean isWeekend(Date date, TimeZone zone) {

		boolean isWeekend = false;
		Calendar c1 = Calendar.getInstance();
		c1.setTimeZone(zone);
		c1.setTime(date);
		int day = c1.get(Calendar.DAY_OF_WEEK);
		if(day==Calendar.SATURDAY || day==Calendar.SUNDAY)
		{
			isWeekend = true;
		}

		return isWeekend;
	}
	public boolean isDateInBusinessHours(Date estDate) {
		boolean result = false;
		if (estDate!=null) {
			int hours = estDate.getHours();
			int min = estDate.getMinutes();
			if (min==0 && (hours>=8 && hours<=22)) {
				result = true;
			}else if (hours>=8 && hours<21) {
				result=true;
			}
		}
		return result;
	}
	private void setDates() {
		this.estDate=getParse(this.estStr);
		this.utcDate=getParse(this.utcStr);
		this.localDate=getParse(this.localStr);
	}
	private Date getParse(String dateStr) {
		SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);
		Date d = null;
		try {
			d = sdf.parse(dateStr);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return d;
	}
	public LocalTime getPickerLocalTimeOFLocalDate() {
		return this.localDate.toInstant().atZone(ZoneId.systemDefault()).toLocalTime();                                                        
	}
	public LocalDate getPickerLocalDateOFLocalDate() {
		return this.localDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();                                                        
	}
	
	
	public SimpleDateFormat getUtcFormatter() {
		return this.utcDateFormatter;
	}
	public SimpleDateFormat getLocalFormatter() {
		return this.localDateFormatter;
	}
	public SimpleDateFormat getEstFormatter() {
		return this.estDateFormatter;
	}
	

}//class
