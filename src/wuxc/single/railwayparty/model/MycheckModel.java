package wuxc.single.railwayparty.model;

import android.R.string;

public class MycheckModel {

	private String Title;
	private String Time;
	private String Id;

	private String detail;	private boolean Cont;
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


	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}

	public String getTime() {
		return Time;
	}

	public void setTime(String time) {
		Time = time;
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
