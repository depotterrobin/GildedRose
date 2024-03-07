package com.gildedrose.items;

import com.gildedrose.Item;

public class StandardItem implements ItemI {
    private static final int LOWEST_QUALITY = 0;

    private final Item item;

    public StandardItem(Item item) {
        this.item = item;
    }

    private void decreaseSellIn() {
        this.item.sellIn--;
    }

    private void decreaseQuality() {
        if(item.quality > LOWEST_QUALITY) {
            this.item.quality--;
        }
    }

    @Override
    public void update() {
        decreaseQuality();
        if (item.sellIn < 0) {
            decreaseQuality();
        }

        decreaseSellIn();
    }
}
