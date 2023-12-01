package com.example.userservice.service.util;

import com.example.userservice.dto.UserDTO;
import com.example.userservice.entity.User;
import com.example.userservice.repo.UserRepo;
import com.example.userservice.service.UserService;
import com.example.userservice.util.Convertor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepo userRepo;

    @Autowired
    Convertor convertor;

    @Override
    public void save(UserDTO userDTO) {
        if (userRepo.existsById(userDTO.getUserId())){
            throw new RuntimeException(userDTO.getUserId()+" User already in the System..!");
        }
        userRepo.save(convertor.userDtoToUserEntity(userDTO));
    }

    @Override
    public void update(UserDTO userDTO) {
        if (!userRepo.existsById(userDTO.getUserId())){
            throw new RuntimeException(userDTO.getUserId()+" User Not found..!");
        }
        userRepo.save(convertor.userDtoToUserEntity(userDTO));

    }

    @Override
    public UserDTO get(String id) {
        if (!userRepo.existsById(id)){
            throw new RuntimeException(id + "User not fount..!");
        }
        return convertor.userEntityToUserDTO(userRepo.findById(id).get());
    }

    @Override
    public List<UserDTO> getAll() {
        return convertor.userEntityListToUserDTOList(userRepo.findAll());
    }

    @Override
    public void delete(String id) {
        if (!userRepo.existsById(id)){
            throw new RuntimeException(id + "User not found..!");
        }
        userRepo.deleteById(id);
    }
}
