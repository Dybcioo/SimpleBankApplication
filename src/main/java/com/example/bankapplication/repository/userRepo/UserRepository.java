package com.example.bankapplication.repository.userRepo;

import com.example.bankapplication.model.User.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    public User findByEmail(String email);

    public User findByLogin(String login);

    public User findFirstById(Long id);

    @Transactional
    @Modifying
    @Query("UPDATE User SET active = :active WHERE id = :id")
    public void updateActive(@Param("active") boolean active, @Param("id") Long id);
}
