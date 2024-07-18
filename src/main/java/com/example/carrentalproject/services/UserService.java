package com.example.carrentalproject.services;

import com.example.carrentalproject.exception.UserNotFoundException;
import com.example.carrentalproject.model.User;
import com.example.carrentalproject.repository.UserRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    public User save(User user) {
        return userRepository.save(user);
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public Page<User> findAll(int page) {
        Sort sort = Sort.by(Sort.Direction.DESC, "id");
        PageRequest pageable = PageRequest.of(page, 10, sort);
        return userRepository.findAll(pageable);
    }

    public User findById(Long id) {
        return userRepository.findById(id).orElseThrow(UserNotFoundException::new);
    }

    public List<User> findUserByEmailFragment(String search) {
        return userRepository.findByEmailContaining(search);
    }

    public User findByEmail(String email) {
        return userRepository.findByEmail(email).orElseThrow(UserNotFoundException::new);
    }

    public List<User> findAllByType(String type) {
        return userRepository.findAllByType(type);
    }

    public void update(User user){
        userRepository.update(user.getFirstName(), user.getLastName(), user.getEmail(), user.getId());
    }

    public void updatePassword(String password, Long id){
        userRepository.updatePassword(password, id);
    }

    public void delete(Long id) {
        userRepository.deleteUserById(id);
    }
}
