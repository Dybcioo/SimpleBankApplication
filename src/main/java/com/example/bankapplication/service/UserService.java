package com.example.bankapplication.service;


import com.example.bankapplication.model.User.Role;
import com.example.bankapplication.model.User.User;
import com.example.bankapplication.repository.userRepo.AddressRepository;
import com.example.bankapplication.repository.userRepo.RoleRepository;
import com.example.bankapplication.repository.userRepo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashSet;

@Service
public class UserService {

    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private AddressRepository addressRepository;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public UserService(UserRepository userRepository,
                       RoleRepository roleRepository,
                       BCryptPasswordEncoder bCryptPasswordEncoder,
                       AddressRepository addressRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.addressRepository = addressRepository;
    }

    public User findUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public User findUserByUserName(String userName) {
        return userRepository.findByLogin(userName);
    }

    public User findUserById(Long id) {
        return userRepository.findFirstById(id);
    }

    public User saveUser(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.setActive(true);
        Role userRole = roleRepository.findRoleByName("USER");
        user.setRoles(new HashSet<Role>(Arrays.asList(userRole)));
        return userRepository.save(user);
    }

    public User editUser(User user){
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        User orginUser = userRepository.findFirstById(user.getId());
        orginUser.setName(user.getName());
        orginUser.setPassword(user.getPassword());
        orginUser.setLogin(user.getLogin());
        orginUser.setLastName(user.getLastName());
        orginUser.setEmail(user.getEmail());
        orginUser.setBirthdayDate(user.getBirthdayDate());

        orginUser.setAddress(user.getAddress());
        orginUser.setAddress(addressRepository.save(orginUser.getAddress()));

        return userRepository.save(orginUser);
    }

}