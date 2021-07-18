package kr.co.kfinance.managers;

import org.springframework.security.core.userdetails.User;

import java.util.Collections;

public class ManagerAdapter extends User {

	private final Manager manager;

	public ManagerAdapter(Manager manager) {
		super(manager.getName(), manager.getPassword(), Collections.emptyList());
		this.manager = manager;
	}

	public Manager getManager() {
		return manager;
	}
}
