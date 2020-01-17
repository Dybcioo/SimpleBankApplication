package com.example.bankapplication.repository.userRepo;

import com.example.bankapplication.model.User.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {
}
