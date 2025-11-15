package com.gs.CareerBooster.service;

import com.gs.CareerBooster.exception.UserNotFoundExcepetion;
import com.gs.CareerBooster.model.UserModel;
import com.gs.CareerBooster.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public void createUser(UserModel user) {
        if (userRepository.existsByEmail(user.getEmail())){
            throw new RuntimeException("E-mail já cadastrado");
        }

        if (userRepository.existsByUsername(user.getUsername())){
            throw new RuntimeException("Username já está em uso");
        }

        // Se quiser criptografar senha no futuro:
        // Procurar algo legal para criptografia
        // user.setPassword(passwordEncoder.encode(user.getPassword()));

        userRepository.save(user);
    }

    public UserModel findUserById(Integer id) {
        UserModel user = userRepository.findById(id);

        if (user == null) {
            throw new UserNotFoundExcepetion(id);
        }
        return user;
    }

    public void updateUser(UserModel user) {
        UserModel existing = findUserById(user.getId());

        if (!existing.getEmail().equals(user.getEmail()) &&
                userRepository.existsByEmail(user.getEmail())) {
            throw new RuntimeException("E-mail já cadastrado");
        }

        if (!existing.getUsername().equals(user.getUsername()) &&
                userRepository.existsByUsername(user.getUsername())) {
            throw new RuntimeException("Username já cadastrado");
        }

        boolean updated = userRepository.update(user);

        if (!updated) {
            throw new RuntimeException("Falha ao atualizar usuário");
        }
    }


}
