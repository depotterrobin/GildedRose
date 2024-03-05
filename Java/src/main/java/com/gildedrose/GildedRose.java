package com.gildedrose;

import java.util.List;

class GildedRose {
    List<Item> items;

    public GildedRose(List<Item> items) {
        this.items = items;
    }

    public void updateQuality() {
        for (Item item : items) { // Loop through all items
            // Start quality decrease / increase logic
            if (!item.name.equals("Aged Brie")
                    && !item.name.equals("Backstage passes to a TAFKAL80ETC concert")) {  // Check type for normal item and quality higher than 0
                if (item.quality > 0) { // Quality can't go below 0
                    if (!item.name.equals("Sulfuras, Hand of Ragnaros")) {
                        item.quality = item.quality - 1; // Decrease quantity by 1
                    }
                }
            } else {
                if (item.quality < 50) { // if quality is below maximum (quality never goes above 50)
                    item.quality = item.quality + 1; // increase quality by 1

                    if (item.name.equals("Backstage passes to a TAFKAL80ETC concert")) { // Check type for backstage item
                        if (item.sellIn < 11) {
                            if (item.quality < 50) {
                                item.quality = item.quality + 1; // if sellIn is below 11 days and quality is below 50, then increase quality by 1
                            }
                        }

                        if (item.sellIn < 6) {
                            if (item.quality < 50) {
                                item.quality = item.quality + 1; // if sellIn is below 6 and quality is below 50, then increase quality AGAIN by 1
                            }
                        }
                    }
                }
            }

            // Start sellIn decrease logic
            if (!item.name.equals("Sulfuras, Hand of Ragnaros")) { // if legendary item, then DON'T decrease sellIn (they don't have a sellIn)
                item.sellIn = item.sellIn - 1;
            }

            // sellIn exceeded below 0 logic
            if (item.sellIn < 0) {
                if (!item.name.equals("Aged Brie")) {
                    if (!item.name.equals("Backstage passes to a TAFKAL80ETC concert")) {
                        if (item.quality > 0) {
                            if (!item.name.equals("Sulfuras, Hand of Ragnaros")) { // Quality of legendary item never changes
                                item.quality = item.quality - 1;
                            }
                        }
                    } else { // Backstage passes quality logic
                        item.quality = item.quality - item.quality; // set to 0
                    }
                } else { // Aged Brie logic quality logic
                    if (item.quality < 50) {
                        item.quality = item.quality + 1;
                    }
                }
            }
        }
    }
}
