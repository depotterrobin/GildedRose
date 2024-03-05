package com.gildedrose;

import com.gildedrose.items.ItemFactory;

import java.util.List;

class GildedRose {
    List<Item> items;

    public GildedRose(List<Item> items) {
        this.items = items;
    }

    public void updateQuality() {
        for (Item item : items) {
            ItemFactory.createNewItem(item).update();
        }
    }
}
