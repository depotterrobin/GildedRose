package com.gildedrose;

class GildedRose {
    Item[] items;

    public GildedRose(Item[] items) {
        this.items = items;
    }

    public void updateQuality() {
        for (int i = 0; i < items.length; i++) { // Loop through all items
            // Start quality decrease / increase logic
            if (!items[i].name.equals("Aged Brie")
                    && !items[i].name.equals("Backstage passes to a TAFKAL80ETC concert")) {  // Check type for normal item and quality higher than 0
                if (items[i].quality > 0) { // Quality can't go below 0
                    if (!items[i].name.equals("Sulfuras, Hand of Ragnaros")) {
                        items[i].quality = items[i].quality - 1; // Decrease quantity by 1
                    }
                }
            } else {
                if (items[i].quality < 50) { // if quality is below maximum (quality never goes above 50)
                    items[i].quality = items[i].quality + 1; // increase quality by 1

                    if (items[i].name.equals("Backstage passes to a TAFKAL80ETC concert")) { // Check type for backstage item
                        if (items[i].sellIn < 11) {
                            if (items[i].quality < 50) {
                                items[i].quality = items[i].quality + 1; // if sellIn is below 11 days and quality is below 50, then increase quality by 1
                            }
                        }

                        if (items[i].sellIn < 6) {
                            if (items[i].quality < 50) {
                                items[i].quality = items[i].quality + 1; // if sellIn is below 6 and quality is below 50, then increase quality AGAIN by 1
                            }
                        }
                    }
                }
            }

            // Start sellIn decrease logic
            if (!items[i].name.equals("Sulfuras, Hand of Ragnaros")) { // if legendary item, then DON'T decrease sellIn (they don't have a sellIn)
                items[i].sellIn = items[i].sellIn - 1;
            }

            // sellIn exceeded below 0 logic
            if (items[i].sellIn < 0) {
                if (!items[i].name.equals("Aged Brie")) {
                    if (!items[i].name.equals("Backstage passes to a TAFKAL80ETC concert")) {
                        if (items[i].quality > 0) {
                            if (!items[i].name.equals("Sulfuras, Hand of Ragnaros")) { // Quality of legendary item never changes
                                items[i].quality = items[i].quality - 1;
                            }
                        }
                    } else { // Backstage passes quality logic
                        items[i].quality = items[i].quality - items[i].quality; // set to 0
                    }
                } else { // Aged Brie logic quality logic
                    if (items[i].quality < 50) {
                        items[i].quality = items[i].quality + 1;
                    }
                }
            }
        }
    }
}
