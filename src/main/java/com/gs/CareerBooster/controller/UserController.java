package com.gs.CareerBooster.controller;

import com.gs.CareerBooster.dto.CreateUserDto;
import com.gs.CareerBooster.dto.UpdateUserDto;
import com.gs.CareerBooster.dto.UserResponseDto;
import com.gs.CareerBooster.service.UserService;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.Operation;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    // ➤ Criar usuário
    @PostMapping
    @Operation(summary = "Cria um novo usuário")
    public ResponseEntity<UserResponseDto> createUser(@Valid @RequestBody CreateUserDto dto) {
        UserResponseDto response = userService.createUser(dto);

        // retorna 201 Created + Location: /users/{id}
        return ResponseEntity
                .created(URI.create("/users/" + response.getId()))
                .body(response);
    }

    // ➤ Buscar por ID
    @GetMapping("/{id}")
    @Operation(summary = "Busca um usuário pelo ID")
    public ResponseEntity<UserResponseDto> getUserById(@PathVariable Integer id) {
        UserResponseDto response = userService.getUserById(id);
        return ResponseEntity.ok(response);
    }

    @GetMapping
    @Operation(summary = "Lista todos os usuários")
    public ResponseEntity<List<UserResponseDto>> getAllUsers() {
        List<UserResponseDto> response = userService.getAllUsers();
        return ResponseEntity.ok(response);
    }

    // ➤ Atualizar usuário
    @PutMapping("/{id}")
    @Operation(summary = "Atualiza os dados de um usuário")
    public ResponseEntity<UserResponseDto> updateUser(
            @PathVariable Integer id,
            @Valid @RequestBody UpdateUserDto dto
    ) {
        UserResponseDto response = userService.updateUser(id, dto);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Remove um usuário pelo ID")
    public ResponseEntity<Void> deleteUser(@PathVariable Integer id) {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build(); // 204 No Content
    }
}
