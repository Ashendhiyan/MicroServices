package com.example.itemservice.api;

import com.example.itemservice.dto.ItemDTO;
import com.example.itemservice.dto.UserDTO;
import com.example.itemservice.service.ItemService;
import com.netflix.discovery.converters.Auto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
@RequestMapping("/api/v1/item")
public class ItemApi {

    @Autowired
    ItemService itemService;

    @Autowired
    private RestTemplate restTemplate;

    @PostMapping()
    public ResponseEntity<String> save(@RequestBody ItemDTO itemDTO){
        itemService.saveItem(itemDTO);
        return new ResponseEntity<>(itemDTO.getItemId()+" Item saved.!", HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<String> update(@RequestBody ItemDTO itemDTO){
        itemService.update(itemDTO);
        return new ResponseEntity<>(itemDTO.getItemId()+" Item updated..!",HttpStatus.OK);
    }

    @GetMapping(params = "id")
    public ResponseEntity<ItemDTO> itemFindById(String id){
        return new ResponseEntity<>(itemService.get(id),HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<ItemDTO>> getAllItems(){
        return new ResponseEntity<>(itemService.getAll(),HttpStatus.OK);
    }

    @DeleteMapping(params = "id")
    public ResponseEntity<String> deleteItem(String id){
        itemService.delete(id);
        return new ResponseEntity<>(id+" Item Deleted..!",HttpStatus.OK);
    }

    //  CRUD for user-Service

    @PostMapping("/save")
    public ResponseEntity<String> saveUser(@RequestBody UserDTO userDTO){
        return new ResponseEntity<>(restTemplate.postForObject("http://localhost:9083/userService/api/v1/user",userDTO, String.class),HttpStatus.OK);
    }

    @PutMapping("/update")
    public ResponseEntity<String> updateUser(@RequestBody UserDTO userDTO) {
        restTemplate.put("http://localhost:9083/userService/api/v1/user", userDTO, String.class);

        return new ResponseEntity<>("Update successfully..", HttpStatus.OK);
    }

    @GetMapping(value = "/get",params = "id")
    public ResponseEntity<UserDTO> getUser(String id){
        return new ResponseEntity<>(restTemplate.getForObject("http://localhost:9083/userService/api/v1/user?id="+id,UserDTO.class),HttpStatus.OK);
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<UserDTO>> getAllUsers(){
        return new ResponseEntity<List<UserDTO>>(restTemplate.getForObject("http://localhost:9083/userService/api/v1/user",List.class),HttpStatus.OK);
    }

    @DeleteMapping(value = "/delete",params = "id")
    public ResponseEntity<String> deleteUser(String id){
        restTemplate.delete("http://localhost:9083/userService/api/v1/user?id="+id);
        return new ResponseEntity<>("Delete success..!",HttpStatus.OK);
//        return ResponseEntity.ok("Delete success..!");
    }
}
