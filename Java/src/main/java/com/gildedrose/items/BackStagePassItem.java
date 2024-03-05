package com.gildedrose.items;

import com.gildedrose.Item;

public class BackStagePassItem implements ItemI {
    private static final int MAXIMUM_QUALITY = 50;

    private final Item item;

    public BackStagePassItem(Item item) {
        this.item = item;
    }

    private void setQualityToZero() {
        this.item.quality = 0;
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
        if (item.sellIn > 0) {
            increaseQuality();

            if (item.sellIn < 11) {
                increaseQuality();
            }

            if (item.sellIn < 6) {
                    increaseQuality();
            }
        } else {
            setQualityToZero();
        }

        decreaseSellIn();
    }
}
