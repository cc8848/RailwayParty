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
