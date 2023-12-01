package com.example.userservice.service;

import com.example.userservice.dto.UserDTO;

import java.util.List;

public interface UserService {
    void save(UserDTO userDTO);
    void update(UserDTO userDTO);

    UserDTO get(String id);
    List<UserDTO> getAll();

    void delete(String id);

}
