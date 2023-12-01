package com.example.itemservice.service;

import com.example.itemservice.dto.ItemDTO;

import java.util.List;

public interface ItemService {
    void saveItem(ItemDTO itemDTO);
    void update(ItemDTO itemDTO);
    ItemDTO get(String id);
    List<ItemDTO> getAll();
    void delete(String id);
}
