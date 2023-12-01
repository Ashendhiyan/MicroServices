package com.example.itemservice.util;

import com.example.itemservice.dto.ItemDTO;
import com.example.itemservice.entity.Item;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class Convertor {

    @Autowired
    ModelMapper modelMapper;

    public Item itemDtoToItemEntity(ItemDTO itemDTO) {
        return modelMapper.map(itemDTO, Item.class);
    }

    public ItemDTO itemEntityToItemDTO(Item item){
        return modelMapper.map(item, ItemDTO.class);
    }

    public List<ItemDTO> itemEntityListToItemDTOList(List<Item> users){
        return modelMapper.map(users,new TypeToken<List<ItemDTO>>(){}.getType());
    }
}