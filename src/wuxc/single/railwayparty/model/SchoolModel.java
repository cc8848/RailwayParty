package wuxc.single.railwayparty.model;

public class SchoolModel {
	private String headimgUrl;
	private String title;
	private String content;
	private String zan;
	private String guanzhu;
	private boolean read;
	private String time;
	private String keyid;
	private int imageurl;
	private int type;
	private int number;
	private boolean bi;

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public boolean isBi() {
		return bi;
	}

	public void setBi(boolean bi) {
		this.bi = bi;
	}

	public int getImageurl() {
		return imageurl;
	}

	public void setImageurl(int imageurl) {
		this.imageurl = imageurl;
	}

	public String getZan() {
		return zan;
	}

	public void setZan(String zan) {
		this.zan = zan;
	}

	public String getGuanzhu() {
		return guanzhu;
	}

	public void setGuanzhu(String guanzhu) {
		this.guanzhu = guanzhu;
	}

	public boolean isRead() {
		return read;
	}

	public void setRead(boolean read) {
		this.read = read;
	}

	public String getHeadimgUrl() {
		return headimgUrl;
	}

	public void setHeadimgUrl(String headimgUrl) {
		this.headimgUrl = headimgUrl;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getKeyid() {
		return keyid;
	}

	public void setKeyid(String keyid) {
		this.keyid = keyid;
	}

}
