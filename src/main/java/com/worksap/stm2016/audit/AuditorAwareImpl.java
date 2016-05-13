package com.worksap.stm2016.audit;

import com.worksap.stm2016.domain.User;
import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

public class AuditorAwareImpl implements AuditorAware<User> {
	//@Autowired
	//private Authentication authentication;

	@Override
	public User getCurrentAuditor() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		return ((CurrentUser) authentication.getPrincipal()).getUser();
	}
}
