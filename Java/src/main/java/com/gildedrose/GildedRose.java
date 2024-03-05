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
            if (!item.getName().equals(AGED_BRIE)
                    && !item.getName().equals(BACKSTAGE_PASS)) {  // Check type for normal item and quality higher than 0
                if (item.getQuality() > LOWEST_QUALITY) { // Quality can't go below 0
                    if (!item.getName().equals(LEGENDARY_ITEM)) {
                        item.decreaseQuality();
                    }
                }
            } else {
                if (item.getQuality() < HIGHEST_QUALITY) { // if quality is below maximum (quality never goes above 50)
                    item.increaseQuality();

                    if (item.getName().equals(BACKSTAGE_PASS)) { // Check type for backstage item
                        if (item.getSellIn() < 11) {
                            if (item.getQuality() < HIGHEST_QUALITY) {
                                item.increaseQuality();
                            }
                        }

                        if (item.getSellIn() < 6) {
                            if (item.getQuality() < HIGHEST_QUALITY) {
                                item.increaseQuality();
                            }
                        }
                    }
                }
            }

            // Start sellIn decrease logic
            if (!item.getName().equals(LEGENDARY_ITEM)) { // if legendary item, then DON'T decrease sellIn (they don't have a sellIn)
                item.decreaseSellIn();
            }

            // sellIn exceeded below 0 logic
            if (item.getSellIn() < 0) {
                if (!item.getName().equals(AGED_BRIE)) {
                    if (!item.getName().equals(BACKSTAGE_PASS)) {
                        if (item.getQuality() > LOWEST_QUALITY) {
                            if (!item.getName().equals(LEGENDARY_ITEM)) { // Quality of legendary item never changes
                                item.decreaseQuality();
                            }
                        }
                    } else { // Backstage passes quality logic
                        item.setQualityToZero();
                    }
                } else { // Aged Brie logic quality logic
                    if (item.getQuality() < HIGHEST_QUALITY) {
                        item.increaseQuality();
                    }
                }
            }
        }
    }
}
