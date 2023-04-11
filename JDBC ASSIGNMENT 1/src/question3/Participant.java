package question3;

public class Participant {
	private int eid;
	private String pName;
	private String edate;
	private int cId;
	public Participant() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Participant(int eid, String pName, String edate, int cId) {
		super();
		this.eid = eid;
		this.pName = pName;
		this.edate = edate;
		this.cId=cId;
	}
	public int getEid() {
		return eid;
	}
	public void setEid(int eid) {
		this.eid = eid;
	}
	public String getpName() {
		return pName;
	}
	public void setpName(String pName) {
		this.pName = pName;
	}
	public String getEdate() {
		return edate;
	}
	public void setEdate(String edate) {
		this.edate = edate;
	}
	
	public int getcId() {
		return cId;
	}
	public void setcId(int cId) {
		this.cId = cId;
	}
	@Override
	public String toString() {
		return "Participant [eid=" + eid + ", pName=" + pName + ", edate=" + edate + ", cId=" + cId + "]";
	}
	
}
