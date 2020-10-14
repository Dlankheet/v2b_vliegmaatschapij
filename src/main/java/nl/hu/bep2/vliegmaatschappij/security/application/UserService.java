package nl.hu.bep2.vliegmaatschappij.security.application;

import nl.hu.bep2.vliegmaatschappij.security.data.SpringUserRepository;
import nl.hu.bep2.vliegmaatschappij.security.data.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

/**
 *  Implements UserDetailsService in order to make it usable
 *  as login/registration service for Spring.
 *  (see AuthenticationFilter)
 */
@Service
@Transactional
public class UserService implements UserDetailsService {
	private final SpringUserRepository userRepository;
	private final PasswordEncoder passwordEncoder;


	public UserService(SpringUserRepository repository, PasswordEncoder passwordEncoder) {
		this.userRepository = repository;
		this.passwordEncoder = passwordEncoder;
	}

	public void register(String username, String password, String firstName, String lastName) {
		String encodedPassword = this.passwordEncoder.encode(password);

		User user = new User(username, encodedPassword, firstName, lastName);

		this.userRepository.save(user);
	}

	public void delete(long id) {
		this.userRepository.deleteById(id);
	}

	public void delete(String username) {
		this.userRepository.delete(loadUserByUsername(username));
	}

	@Override
	public User loadUserByUsername(String username) {
		return this.userRepository.findByUsername(username)
				.orElseThrow(() -> new UsernameNotFoundException(username));
	}
}
