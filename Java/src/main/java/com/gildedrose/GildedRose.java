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
            switch (item.getName()) {
                case AGED_BRIE -> {
                    item.decreaseSellIn();

                    if (item.getQuality() < HIGHEST_QUALITY) { // if quality is below maximum (quality never goes above 50)
                        item.increaseQuality();
                    }

                    if (item.getSellIn() < 0) {
                        if (item.getQuality() < HIGHEST_QUALITY) {
                            item.increaseQuality();
                        }
                    }
                }
                case BACKSTAGE_PASS -> {
                    if (item.getQuality() < HIGHEST_QUALITY && item.getSellIn() > 0) { // if quality is below maximum (quality never goes above 50)
                        item.increaseQuality();

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
                    } else {
                        item.setQualityToZero();
                    }

                    item.decreaseSellIn();
                }
                case LEGENDARY_ITEM -> {
                }
                default -> {
                    if (item.getQuality() > LOWEST_QUALITY) { // Quality can't go below 0
                        item.decreaseQuality();
                    }
                    if (item.getSellIn() < 0 && item.getQuality() > LOWEST_QUALITY) {
                        item.decreaseQuality();
                    }
                    item.decreaseSellIn();
                }
            }
        }
    }
}
