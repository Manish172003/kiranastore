package com.kiranastore.kirana_store.services;

import java.util.List;

import com.kiranastore.kirana_store.dtos.ItemRequest;
import com.kiranastore.kirana_store.dtos.ItemResponse;

public interface ItemService {
    ItemResponse createItem(ItemRequest item);
    ItemResponse getItemById(Long id);
    List<ItemResponse> getAllItems();
    ItemResponse updateItem(Long id, ItemRequest item);
    void deleteItem(Long id);
}
