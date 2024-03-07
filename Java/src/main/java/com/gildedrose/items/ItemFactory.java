package com.gildedrose.items;

import com.gildedrose.Item;

public class ItemFactory {
    private static final String AGED_BRIE = "Aged Brie";
    private static final String BACKSTAGE_PASS = "Backstage passes to a TAFKAL80ETC concert";
    private static final String LEGENDARY_ITEM = "Sulfuras, Hand of Ragnaros";
    private static final String CONJURED_ITEM = "Conjured Item";

    private ItemFactory() {
    }

    public static ItemI createNewItem(Item item) {
        return switch (item.name) {
            case LEGENDARY_ITEM -> new SulfurasItem();
            case AGED_BRIE -> new AgedBrieItem(item);
            case BACKSTAGE_PASS -> new BackStagePassItem(item);
            case CONJURED_ITEM -> new ConjuredItem(item);
            default -> new StandardItem(item);
        };
    }
}
