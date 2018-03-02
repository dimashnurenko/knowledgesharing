package examples.audit;

import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class UserRepo {
	private final Map<String, User> users = new ConcurrentHashMap<>();

	public UserRepo() {
		users.put("token1", new User(1L, "user1", "123456"));
		users.put("token2", new User(2L, "user2", "654321"));
	}

	public User getByToken(String token) {
		return users.get(token);
	}
}
