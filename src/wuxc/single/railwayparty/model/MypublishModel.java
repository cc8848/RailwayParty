package wuxc.single.railwayparty.model;

public class MypublishModel {

	private String Id;
	private String Label;
	private int number;
	private String Time;
	private String title;
	private String detail;

	public String getId() {
		return Id;
	}

	public void setId(String id) {
		Id = id;
	}

	public String getLabel() {
		return Label;
	}

	public void setLabel(String label) {
		Label = label;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public String getTime() {
		return Time;
	}

	public void setTime(String time) {
		Time = time;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}

}
