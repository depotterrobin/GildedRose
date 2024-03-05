package com.gildedrose;

import java.util.List;

class GildedRose {
    private static final int HIGHEST_QUALITY = 50;
    private static final int LOWEST_QUALITY = 0;
    private static final String AGED_BRIE = "Aged Brie";
    private static final String BACKSTAGE_PASS = "Backstage passes to a TAFKAL80ETC concert";
    private static final String LEGENDARY_ITEM = "Sulfuras, Hand of Ragnaros";
    List<Item> items;

    public GildedRose(List<Item> items) {
        this.items = items;
    }

    public void updateQuality() {
        for (Item item : items) { // Loop through all items
            // Start quality decrease / increase logic
            if (!item.name.equals(AGED_BRIE)
                    && !item.name.equals(BACKSTAGE_PASS)) {  // Check type for normal item and quality higher than 0
                if (item.quality > LOWEST_QUALITY) { // Quality can't go below 0
                    if (!item.name.equals(LEGENDARY_ITEM)) {
                        decreaseQuality(item);
                    }
                }
            } else {
                if (item.quality < HIGHEST_QUALITY) { // if quality is below maximum (quality never goes above 50)
                    increaseQuality(item);

                    if (item.name.equals(BACKSTAGE_PASS)) { // Check type for backstage item
                        if (item.sellIn < 11) {
                            if (item.quality < HIGHEST_QUALITY) {
                                increaseQuality(item);
                            }
                        }

                        if (item.sellIn < 6) {
                            if (item.quality < HIGHEST_QUALITY) {
                                increaseQuality(item);
                            }
                        }
                    }
                }
            }

            // Start sellIn decrease logic
            if (!item.name.equals(LEGENDARY_ITEM)) { // if legendary item, then DON'T decrease sellIn (they don't have a sellIn)
                decreaseSellIn(item);
            }

            // sellIn exceeded below 0 logic
            if (item.sellIn < 0) {
                if (!item.name.equals(AGED_BRIE)) {
                    if (!item.name.equals(BACKSTAGE_PASS)) {
                        if (item.quality > LOWEST_QUALITY) {
                            if (!item.name.equals(LEGENDARY_ITEM)) { // Quality of legendary item never changes
                                decreaseQuality(item);
                            }
                        }
                    } else { // Backstage passes quality logic
                        setQualityToZero(item);
                    }
                } else { // Aged Brie logic quality logic
                    if (item.quality < HIGHEST_QUALITY) {
                        increaseQuality(item);
                    }
                }
            }
        }
    }

    private static void setQualityToZero(Item item) {
        item.quality = 0;
    }

    private static void decreaseSellIn(Item item) {
        item.sellIn = item.sellIn - 1;
    }

    private static void increaseQuality(Item item) {
        item.quality = item.quality + 1;
    }

    private static void decreaseQuality(Item item) {
        item.quality = item.quality - 1;
    }
}
