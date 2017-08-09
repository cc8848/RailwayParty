package wuxc.single.railwayparty.model;

public class TalkModel {
	private String ImageUrl;
	private String name;
	private String Id;
	private String Detail;
	private boolean my;
	private boolean Cont;
	private String Summary;
	private String Image;
	private boolean pic;

	public boolean isPic() {
		return pic;
	}

	public void setPic(boolean pic) {
		this.pic = pic;
	}

	public String getImage() {
		return Image;
	}

	public void setImage(String image) {
		Image = image;
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isMy() {
		return my;
	}

	public void setMy(boolean my) {
		this.my = my;
	}

	public String getDetail() {
		return Detail;
	}

	public void setDetail(String detail) {
		Detail = detail;
	}

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

}
