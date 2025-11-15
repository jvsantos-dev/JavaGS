package com.gs.CareerBooster.exception;

public class UserNotFoundExcepetion extends UserServiceException {
    public UserNotFoundExcepetion(Integer id){
        super("Usuário com ID: " + id + " não foi encontrado.");
    }
}
