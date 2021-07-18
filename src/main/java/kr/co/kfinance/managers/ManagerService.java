package kr.co.kfinance.managers;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class ManagerService implements UserDetailsService {

	final ManagerReository managerReository;

	final PasswordEncoder passwordEncoder;

	@Override
	public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {
		Manager manager = managerReository.findByName(name)
				.orElseThrow(() -> new UsernameNotFoundException(name));
		return new ManagerAdapter(manager);
	}

	public Manager saveManager(Manager manager) {
		manager.setPassword(passwordEncoder.encode(manager.getPassword()));
		return this.managerReository.save(manager);
	}
}
