package wuxc.single.railwayparty.model;

public class VoteModel {
	private boolean Cont;
	private String Summary;
	private String endtime;
	private String Starttime;

	public String getEndtime() {
		return endtime;
	}

	public void setEndtime(String endtime) {
		this.endtime = endtime;
	}

	public String getStarttime() {
		return Starttime;
	}

	public void setStarttime(String starttime) {
		Starttime = starttime;
	}

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

	private String ImageUrl;
	private String Title;
	private String Detail;
	private String Id;

	public String getId() {
		return Id;
	}

	public void setId(String id) {
		Id = id;
	}

	public String getImageUrl() {
		return ImageUrl;
	}

	public void setImageUrl(String imageUrl) {
		ImageUrl = imageUrl;
	}

	public String getTitle() {
		return Title;
	}

	public void setTitle(String title) {
		Title = title;
	}

	public String getDetail() {
		return Detail;
	}

	public void setDetail(String detail) {
		Detail = detail;
	}
}
