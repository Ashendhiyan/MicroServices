package com.example.itemservice.service.impl;

import com.example.itemservice.dto.ItemDTO;
import com.example.itemservice.entity.Item;
import com.example.itemservice.repo.ItemRepo;
import com.example.itemservice.service.ItemService;
import com.example.itemservice.util.Convertor;
import com.netflix.discovery.converters.Auto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Service
public class ItemServiceImpl implements ItemService {

    @Autowired
    ItemRepo itemRepo;

    @Autowired
    Convertor convertor;

    @Override
    public void saveItem(ItemDTO itemDTO) {
        if (itemRepo.existsById(itemDTO.getItemId())){
            throw new RuntimeException(itemDTO.getItemId()+" Item is already exists..!");
        }
        itemRepo.save(convertor.itemDtoToItemEntity(itemDTO));
    }

    @Override
    public void update(ItemDTO itemDTO) {
        if (!itemRepo.existsById(itemDTO.getItemId())){
            throw new RuntimeException(itemDTO.getItemId()+" Item not found..!");
        }
        itemRepo.save(convertor.itemDtoToItemEntity(itemDTO));
    }

    @Override
    public ItemDTO get(String id) {
        if (!itemRepo.existsById(id)){
            throw new RuntimeException(id+" Item not found..!");
        }
        return convertor.itemEntityToItemDTO(itemRepo.findById(id).get());
    }

    @Override
    public List<ItemDTO> getAll() {
        return convertor.itemEntityListToItemDTOList(itemRepo.findAll());
    }

    @Override
    public void delete(String id) {
        if (!itemRepo.existsById(id)){
            throw new RuntimeException(id+" Item not found..!");
        }
        itemRepo.deleteById(id);
    }
}
