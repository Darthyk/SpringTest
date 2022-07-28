package com.darthyk.springtest.controller;

import com.darthyk.springtest.dto.ItemDto;
import com.darthyk.springtest.service.ItemService;
import com.darthyk.springtest.utils.ItemParams;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/items")
public class ItemController {

    @Autowired
    private ItemService itemService;

    @PostMapping("/")
    public String createItem(@RequestBody ItemDto dto) {
        itemService.save(dto);
        return "OK";
    }

    @GetMapping("/")
    public List<ItemDto> getItems(ItemParams itemParams) {
        return itemService.findAllByParams(itemParams);
    }
}
