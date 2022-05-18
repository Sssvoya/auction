package ru.guwfa.auction.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ru.guwfa.auction.domain.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String Username);
    User findByActivationCode(String code);
    User findByEmail(String email);
}

