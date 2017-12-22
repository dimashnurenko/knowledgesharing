package audit;

public class User {
	private final Long id;
	private final String name;
	private final String password;

	public User(Long id, String name, String password) {
		this.id = id;
		this.name = name;
		this.password = password;
	}

	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getPassword() {
		return password;
	}
}
