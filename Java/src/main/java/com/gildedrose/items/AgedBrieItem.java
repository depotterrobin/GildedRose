package com.gildedrose.items;

import com.gildedrose.Item;

public class AgedBrieItem implements ItemI {
    private static final int MAXIMUM_QUALITY = 50;

    private final Item item;

    public AgedBrieItem(Item item) {
        this.item = item;
    }

    private void decreaseSellIn() {
        this.item.sellIn--;
    }

    private void increaseQuality() {
        if(item.quality < MAXIMUM_QUALITY) {
            this.item.quality++;
        }
    }

    @Override
    public void update() {
        increaseQuality();
        if (item.sellIn < 0) {
            increaseQuality();
        }

        decreaseSellIn();
    }
}
