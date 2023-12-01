package com.example.userservice.util;

import com.example.userservice.dto.UserDTO;
import com.example.userservice.entity.User;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class Convertor {

    @Autowired
    ModelMapper modelMapper;

    public User userDtoToUserEntity(UserDTO userDTO){
        return modelMapper.map(userDTO, User.class);
    }

    public UserDTO userEntityToUserDTO(User userEntity){
        return modelMapper.map(userEntity, UserDTO.class);
    }

    public List<UserDTO> userEntityListToUserDTOList(List<User> users){
        return modelMapper.map(users,new TypeToken<List<UserDTO>>(){}.getType());
    }
}
