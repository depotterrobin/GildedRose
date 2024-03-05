package com.gildedrose;

import com.gildedrose.items.ItemFactory;
import com.gildedrose.items.ItemI;

import java.util.List;

class GildedRose {
    List<Item> items;

    public GildedRose(List<Item> items) {
        this.items = items;
    }

    public void updateQuality() {
        items.stream()
            .map(ItemFactory::createNewItem)
            .forEach(ItemI::update);
    }
}
