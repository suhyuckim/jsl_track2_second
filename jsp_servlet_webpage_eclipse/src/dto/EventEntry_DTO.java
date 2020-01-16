package dto;

public class EventEntry_DTO {
	String eventNo, eventTitle, entryName, gubun;

	public String getGubun() {
		return gubun;
	}

	public EventEntry_DTO(String eventNo, String eventTitle, String entryName,String gubun) {
		super();
		this.eventNo = eventNo;
		this.eventTitle = eventTitle;
		this.entryName = entryName;
		this.gubun = gubun;
	}

	public String getEventNo() {
		return eventNo;
	}

	public String getEventTitle() {
		return eventTitle;
	}

	public String getEntryName() {
		return entryName;
	}
	
}
