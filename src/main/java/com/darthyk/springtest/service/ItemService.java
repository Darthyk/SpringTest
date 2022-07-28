package com.darthyk.springtest.service;

import com.darthyk.springtest.dto.ItemDto;
import com.darthyk.springtest.utils.ItemParams;
import java.util.List;

public interface ItemService {
    void save(ItemDto dto);

    void deleteById(Long id);

    List<ItemDto> findAllByParams(ItemParams itemParams);

    List<ItemDto> findByParams(ItemParams itemParams);
}
