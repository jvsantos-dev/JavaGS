package com.gs.CareerBooster.service;

import com.gs.CareerBooster.dto.CreateUserDto;
import com.gs.CareerBooster.dto.UpdateUserDto;
import com.gs.CareerBooster.dto.UserResponseDto;
import com.gs.CareerBooster.exception.UserNotFoundExcepetion;
import com.gs.CareerBooster.model.UserModel;
import com.gs.CareerBooster.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public UserResponseDto createUser(CreateUserDto dto) {

        if (userRepository.existsByEmail(dto.getEmail())) {
            throw new RuntimeException("E-mail já cadastrado");
        }

        if (userRepository.existsByUsername(dto.getUsername())) {
            throw new RuntimeException("Username já está em uso");
        }

        UserModel user = new UserModel();
        user.setName(dto.getName());
        user.setEmail(dto.getEmail());
        user.setUsername(dto.getUsername());
        user.setPassword(passwordEncoder.encode(dto.getPassword()));
        user.setArea_interest(dto.getAreaInterest());

        userRepository.save(user);

        return toResponse(user);
    }

    public UserResponseDto getUserById(Integer id) {
        UserModel user = userRepository.findById(id);

        if (user == null) {
            throw new UserNotFoundExcepetion(id);
        }

        return toResponse(user);
    }

    public UserResponseDto updateUser(Integer id, UpdateUserDto dto) {

        UserModel existing = userRepository.findById(id);

        if (existing == null) {
            throw new UserNotFoundExcepetion(id);
        }

        // Verifica se email é diferente E já existe no sistema
        if (!existing.getEmail().equals(dto.getEmail()) &&
                userRepository.existsByEmail(dto.getEmail())) {
            throw new RuntimeException("E-mail já cadastrado");
        }

        // Verifica se username é diferente E já existe no sistema
        if (!existing.getUsername().equals(dto.getUsername()) &&
                userRepository.existsByUsername(dto.getUsername())) {
            throw new RuntimeException("Username já cadastrado");
        }

        existing.setName(dto.getName());
        existing.setEmail(dto.getEmail());
        existing.setUsername(dto.getUsername());
        existing.setArea_interest(dto.getAreaInterest());

        boolean updated = userRepository.update(existing);

        if (!updated) {
            throw new RuntimeException("Falha ao atualizar usuário");
        }

        return toResponse(existing);
    }

    private UserResponseDto toResponse(UserModel user) {
        UserResponseDto dto = new UserResponseDto();
        dto.setId(user.getId());
        dto.setName(user.getName());
        dto.setEmail(user.getEmail());
        dto.setUsername(user.getUsername());
        dto.setArea_interest(user.getArea_interest());
        return dto;
    }

    public List<UserResponseDto> getAllUsers() {
        List<UserModel> users = userRepository.findAll();

        return users.stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

    public void deleteUser(Integer id) {
        UserModel existing = userRepository.findById(id);

        if (existing == null) {
            throw new UserNotFoundExcepetion(id);
        }

        userRepository.delete(id);
    }
}
