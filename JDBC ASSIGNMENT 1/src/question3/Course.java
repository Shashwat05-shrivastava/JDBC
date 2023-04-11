package question3;

public class Course {
	private int courseId;
	private String name;
	private String startDate;
	private String endDate;
	public Course() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Course(int courseId, String name, String startDate, String endDate) {
		super();
		this.courseId = courseId;
		this.name = name;
		this.startDate = startDate;
		this.endDate = endDate;
	}
	public int getCourseId() {
		return courseId;
	}
	public void setCourseId(int courseId) {
		this.courseId = courseId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	@Override
	public String toString() {
		return "Course [courseId=" + courseId + ", name=" + name + ", startDate=" + startDate + ", endDate=" + endDate
				+ "]";
	}
	
}
