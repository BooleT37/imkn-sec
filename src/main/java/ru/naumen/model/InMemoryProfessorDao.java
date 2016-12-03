package ru.naumen.model;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import ru.naumen.entities.Professor;

import javax.inject.Named;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;

@Named
public class InMemoryProfessorDao implements ProfessorDao, UserDetailsService {

    private List<Professor> storage = new ArrayList<>();
    private AtomicInteger counter = new AtomicInteger(0);

    @Override
    public Optional<Professor> find(int id) {
        return storage.stream()
            .filter(s -> s.getId() == id)
            .findFirst();
    }

    @Override
    public void save(Professor professor) {
        professor.setId(counter.incrementAndGet());
        storage.add(professor);
    }

    @Override
    public Optional<Professor> findByLogin(String login) {
        return storage.stream()
                .filter(s -> Objects.equals(s.getLogin(), login))
                .findFirst();
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        Optional<Professor> professor = this.findByLogin(s);
        if (professor.isPresent()) {
			List<GrantedAuthority> grantedAuths = new ArrayList<>();
			grantedAuths.add(new SimpleGrantedAuthority("ROLE_USER"));
			return new User(professor.get().getLogin(), professor.get().getPassword(), grantedAuths);
		}
        throw new UsernameNotFoundException("Cannot find professor with name " + s);
    }
}
