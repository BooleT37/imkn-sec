package ru.naumen.model;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import ru.naumen.entities.Professor;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Named
public class ProfessorAuthenticationProvider implements AuthenticationProvider {
	@Inject
	private ProfessorDao professorDao;

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		String name = authentication.getName();
		String password = authentication.getCredentials().toString();
		Optional<Professor> professor = professorDao.findByLogin(name);
		if (!professor.isPresent())
			throw new ProfessorAuthenticationException("No user with name " + name);

		if (!Objects.equals(professor.get().getPassword(), password))
			throw new ProfessorAuthenticationException("Wrong password");

		List<GrantedAuthority> grantedAuths = new ArrayList<>();
		grantedAuths.add(new SimpleGrantedAuthority("ROLE_USER"));
		return new UsernamePasswordAuthenticationToken(name, password, grantedAuths);
	}

	@Override
	public boolean supports(Class<?> aClass) {
		return aClass.equals(UsernamePasswordAuthenticationToken.class);
	}

	class ProfessorAuthenticationException extends AuthenticationException {
		public ProfessorAuthenticationException(String msg, Throwable t) {
			super(msg, t);
		}

		ProfessorAuthenticationException(String msg) {
			super(msg);
		}
	}
}
