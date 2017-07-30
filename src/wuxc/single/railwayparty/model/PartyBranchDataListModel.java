package wuxc.single.railwayparty.model;

public class PartyBranchDataListModel {
	private String PartyName;
	private String PartyAddress;
	private String PartyPhonenumber;
	private String Id;
	private boolean Cont;
	private String Summary;
	private String Pid;
	private boolean hasparent;

	public String getstring() {

		return Id + "  " + PartyName + "  " + isHasparent() + "  " + isHaschild() + "  " + Pid;

	}

	public String getPid() {
		return Pid;
	}

	public void setPid(String pid) {
		Pid = pid;
	}

	public boolean isHasparent() {
		return hasparent;
	}

	public void setHasparent(boolean hasparent) {
		this.hasparent = hasparent;
	}

	public boolean isHaschild() {
		return haschild;
	}

	public void setHaschild(boolean haschild) {
		this.haschild = haschild;
	}

	private boolean haschild;

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

	private boolean IsSelected;

	public String getPartyName() {
		return PartyName;
	}

	public void setPartyName(String partyName) {
		PartyName = partyName;
	}

	public String getPartyAddress() {
		return PartyAddress;
	}

	public void setPartyAddress(String partyAddress) {
		PartyAddress = partyAddress;
	}

	public String getPartyPhonenumber() {
		return PartyPhonenumber;
	}

	public void setPartyPhonenumber(String partyPhonenumber) {
		PartyPhonenumber = partyPhonenumber;
	}

	public String getId() {
		return Id;
	}

	public void setId(String id) {
		Id = id;
	}

	public boolean isIsSelected() {
		return IsSelected;
	}

	public void setIsSelected(boolean isSelected) {
		IsSelected = isSelected;
	}
}
