package wuxc.single.railwayparty.model;

public class TipsDetailModel {
	private String NickName;
	private String ImageHeadimg;
	private String Image;
	private String Content;
	private String Time;
	private String Detail;
	private int type;
	private int number;

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public String getNickName() {
		return NickName;
	}

	public void setNickName(String nickName) {
		NickName = nickName;
	}

	public String getImageHeadimg() {
		return ImageHeadimg;
	}

	public void setImageHeadimg(String imageHeadimg) {
		ImageHeadimg = imageHeadimg;
	}

	public String getImage() {
		return Image;
	}

	public void setImage(String image) {
		Image = image;
	}

	public String getContent() {
		return Content;
	}

	public void setContent(String content) {
		Content = content;
	}

	public String getTime() {
		return Time;
	}

	public void setTime(String time) {
		Time = time;
	}

	public String getDetail() {
		return Detail;
	}

	public void setDetail(String detail) {
		Detail = detail;
	}
}
