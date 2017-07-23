package wuxc.single.railwayparty.model;

public class ExamTopicModel {
	private String Id;
	private String Topic;
	private String AString;
	private String BString;
	private String CString;
	private String DString;
	private int UserAnswer;
	private int RightAnswer;
	private String detail;
	private int score;
	private String Answer;
	private String Aid;
	private String Bid;	private boolean Cont;
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

	private String Cid;
	private String Did;

	public String getAid() {
		return Aid;
	}

	public void setAid(String aid) {
		Aid = aid;
	}

	public String getBid() {
		return Bid;
	}

	public void setBid(String bid) {
		Bid = bid;
	}

	public String getCid() {
		return Cid;
	}

	public void setCid(String cid) {
		Cid = cid;
	}

	public String getDid() {
		return Did;
	}

	public void setDid(String did) {
		Did = did;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public String getAnswer() {
		return Answer;
	}

	public void setAnswer(String answer) {
		Answer = answer;
	}

	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}

	public String getId() {
		return Id;
	}

	public void setId(String id) {
		Id = id;
	}

	public String getTopic() {
		return Topic;
	}

	public void setTopic(String topic) {
		Topic = topic;
	}

	public String getAString() {
		return AString;
	}

	public void setAString(String aString) {
		AString = aString;
	}

	public String getBString() {
		return BString;
	}

	public void setBString(String bString) {
		BString = bString;
	}

	public String getCString() {
		return CString;
	}

	public void setCString(String cString) {
		CString = cString;
	}

	public String getDString() {
		return DString;
	}

	public void setDString(String dString) {
		DString = dString;
	}

	public int getUserAnswer() {
		return UserAnswer;
	}

	public void setUserAnswer(int userAnswer) {
		UserAnswer = userAnswer;
	}

	public int getRightAnswer() {
		return RightAnswer;
	}

	public void setRightAnswer(int rightAnswer) {
		RightAnswer = rightAnswer;
	}
}
