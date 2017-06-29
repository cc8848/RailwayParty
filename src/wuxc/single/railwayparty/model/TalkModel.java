package wuxc.single.railwayparty.model;

public class TalkModel {
	private String ImageUrl;
	private String name;
	private String Id;
	private String Detail;
	private boolean my;

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
