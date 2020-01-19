package com.example.bankapplication.repository.userRepo;

import com.example.bankapplication.model.User.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    public User findByEmail(String email);

    public User findByLogin(String login);

    public User findFirstById(Long id);
}
