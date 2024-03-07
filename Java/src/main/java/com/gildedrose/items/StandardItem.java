package com.gildedrose.items;

import com.gildedrose.Item;

public class StandardItem implements ItemI {
    private static final int LOWEST_QUALITY = 0;
    private static final int DECREASE_BY_VALUE = 1;

    private final Item item;

    public StandardItem(Item item) {
        this.item = item;
    }

    public int getDecreaseByValue() {
        return DECREASE_BY_VALUE;
    }

    private void decreaseSellIn() {
        this.item.sellIn--;
    }

    private void decreaseQuality() {
        if (item.quality < LOWEST_QUALITY) {
            return;
        }
        int newQuality = item.quality - getDecreaseByValue();
        this.item.quality = Math.max(newQuality, LOWEST_QUALITY);
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
