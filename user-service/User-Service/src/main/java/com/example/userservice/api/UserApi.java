package com.example.userservice.api;

import com.example.userservice.dto.ItemDTO;
import com.example.userservice.dto.UserDTO;
import com.example.userservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
@RequestMapping("/api/v1/user")
public class UserApi {
    @Autowired
    UserService userService;

    @Autowired
    private RestTemplate restTemplate;

    @PostMapping
    public ResponseEntity<String> saveUser(@RequestBody UserDTO userDTO){
        userService.save(userDTO);
        return new ResponseEntity<>(userDTO.getUserId()+" User Saved..!", HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<String> updateUser(@RequestBody UserDTO userDTO){
        userService.update(userDTO);
        return new ResponseEntity<>(userDTO.getUserId()+" User updated..!",HttpStatus.OK);
    }

    @GetMapping(params = "id")
    public ResponseEntity<UserDTO> userFindById(String id){
        return new ResponseEntity<>(userService.get(id),HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<UserDTO>> getAllUsers(){
        return new ResponseEntity<>(userService.getAll(),HttpStatus.OK);
    }

    @DeleteMapping(params = "id")
    public ResponseEntity<String> deleteUser(String id){
        userService.delete(id);
        return new ResponseEntity<>(id+"User deleted..!",HttpStatus.OK);
    }

    //  CRUD for Item-Service

    @PostMapping("/save")
    public ResponseEntity<String> saveItem(@RequestBody ItemDTO itemDto){
        return new ResponseEntity<>(restTemplate.postForObject("http://itemService/itemService/api/v1/item",itemDto, String.class),HttpStatus.OK);
    }

    @PutMapping("/update")
    public ResponseEntity<String> updateItem(@RequestBody ItemDTO itemDTO){
        restTemplate.put("http://localhost:8080/itemService/api/v1/item",itemDTO,String.class);
        return new ResponseEntity<>("Update Successfully..!",HttpStatus.OK);
    }

    @GetMapping(value = "/get",params = "id")
    public ResponseEntity<ItemDTO> getItem(String id){
        return new ResponseEntity<>(restTemplate.getForObject("http://localhost:8080/itemService/api/v1/item?id="+id,ItemDTO.class),HttpStatus.OK);
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<ItemDTO>> getAllItems(){
        return new ResponseEntity<List<ItemDTO>>(restTemplate.getForObject("http://localhost:8080/itemService/api/v1/item",List.class),HttpStatus.OK);
    }

    @DeleteMapping(value = "/delete",params = "id")
    public ResponseEntity<String> deleteItem(String id){
        restTemplate.delete("http://localhost:8080/itemService/api/v1/item?id="+id);
        return new ResponseEntity<>("Delete Successfully",HttpStatus.OK);
    }

}
