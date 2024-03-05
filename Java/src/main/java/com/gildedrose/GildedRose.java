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
                        item.quality = item.quality - 1; // Decrease quantity by 1
                    }
                }
            } else {
                if (item.quality < HIGHEST_QUALITY) { // if quality is below maximum (quality never goes above 50)
                    item.quality = item.quality + 1; // increase quality by 1

                    if (item.name.equals(BACKSTAGE_PASS)) { // Check type for backstage item
                        if (item.sellIn < 11) {
                            if (item.quality < HIGHEST_QUALITY) {
                                item.quality = item.quality + 1; // if sellIn is below 11 days and quality is below 50, then increase quality by 1
                            }
                        }

                        if (item.sellIn < 6) {
                            if (item.quality < HIGHEST_QUALITY) {
                                item.quality = item.quality + 1; // if sellIn is below 6 and quality is below 50, then increase quality AGAIN by 1
                            }
                        }
                    }
                }
            }

            // Start sellIn decrease logic
            if (!item.name.equals(LEGENDARY_ITEM)) { // if legendary item, then DON'T decrease sellIn (they don't have a sellIn)
                item.sellIn = item.sellIn - 1;
            }

            // sellIn exceeded below 0 logic
            if (item.sellIn < 0) {
                if (!item.name.equals(AGED_BRIE)) {
                    if (!item.name.equals(BACKSTAGE_PASS)) {
                        if (item.quality > LOWEST_QUALITY) {
                            if (!item.name.equals(LEGENDARY_ITEM)) { // Quality of legendary item never changes
                                item.quality = item.quality - 1;
                            }
                        }
                    } else { // Backstage passes quality logic
                        item.quality = item.quality - item.quality; // set to 0
                    }
                } else { // Aged Brie logic quality logic
                    if (item.quality < HIGHEST_QUALITY) {
                        item.quality = item.quality + 1;
                    }
                }
            }
        }
    }
}
