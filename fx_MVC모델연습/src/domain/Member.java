package domain;

public class Member {
	
	//private int num : db와 member클래스가 꼭 같을 필요는 없음! num은 ai기 때문.ㄴ
	private String id;
	private String password;
	private String name;
	private String email;
	
	
	public Member() {
		// TODO Auto-generated constructor stub
	}

	public Member(String id, String name, String password, String email) {
		this.id = id;
		this.password = password;
		this.name = name;
		this.email = email;
	}

	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	
	
	
}
