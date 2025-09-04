package com.kennet.Expense.Manager.service;

import com.kennet.Expense.Manager.model.User;
import com.kennet.Expense.Manager.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    //Crear o actualizar usuario
    public User saveUser(User user){
        return userRepository.save(user);
    }

    //Obtener todos los usuarios
    public List<User> getAllUsers(){
        return userRepository.findAll();
    }

    //Obtener usuario por Id
    public Optional<User> getUserById(Long id){
        return userRepository.findById(id);
    }

    //Eliminar usuario
    public void deleteUser(Long id){
        userRepository.deleteById(id);
    }

    //Buscar usuario por email
    public Optional<User> getUserByEmail(String email){
        return userRepository.findByEmail(email);
    }



}
