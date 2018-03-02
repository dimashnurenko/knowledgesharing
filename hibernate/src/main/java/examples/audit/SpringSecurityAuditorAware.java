package examples.audit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.AuditorAware;
import org.springframework.stereotype.Component;

@Component
public class SpringSecurityAuditorAware implements AuditorAware<Long> {
	private final UserRepo repo;

	@Autowired
	public SpringSecurityAuditorAware(UserRepo repo) {
		this.repo = repo;
	}

	public Long getCurrentAuditor() {
		String token = SecurityContext.token;

		User user = repo.getByToken(token);
		return user == null ? null : user.getId();
	}
}
