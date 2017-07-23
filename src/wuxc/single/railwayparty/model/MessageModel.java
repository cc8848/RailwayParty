package wuxc.single.railwayparty.model;

public class MessageModel {

	private String Title;
	private String Time;
	private String Id;	private boolean Cont;
	private String Summary;
	public String getSummary() {
		return Summary;
	}

	public void setSummary(String summary) {
		Summary = summary;
	}

	public boolean isCont() {
		return Cont;
	}

	public void setCont(boolean cont) {
		Cont = cont;
	}

	private String Link;

	 

	public String getLink() {
		return Link;
	}

	public void setLink(String link) {
		Link = link;
	}

	private boolean Read;

	public String getTime() {
		return Time;
	}

	public void setTime(String time) {
		Time = time;
	}

	public boolean isRead() {
		return Read;
	}

	public void setRead(boolean read) {
		Read = read;
	}

	public String getId() {
		return Id;
	}

	public void setId(String id) {
		Id = id;
	}

	public String getTitle() {
		return Title;
	}

	public void setTitle(String title) {
		Title = title;
	}

}
