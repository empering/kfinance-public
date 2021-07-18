package kr.co.kfinance.managers;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ManagerReository extends JpaRepository<Manager, Long> {
	Optional<Manager> findByName(String name);
}
