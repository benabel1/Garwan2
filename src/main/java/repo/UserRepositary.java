package repo;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

import data.Garwan_User;

@Repository
public interface UserRepositary extends JpaRepository<Garwan_User, Long> {

	Garwan_User findByEmail(String email);

	Garwan_User findByUsername(String username);

}
