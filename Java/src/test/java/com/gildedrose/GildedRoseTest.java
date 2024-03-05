package com.gildedrose;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class GildedRoseTest {
    private void verifyItem(Item item, Item expected) {
        Item[] items = new Item[] { item };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(expected.sellIn, app.items[0].sellIn);
        assertEquals(expected.quality, app.items[0].quality);
    }

    @Test
    void given_normalItem_when_endOfDay_then_decreaseQualityAndSellInBy1() {
        verifyItem(
            new Item("Normal Item", 10, 25),
            new Item("Normal Item", 9, 24)
        );
    }

    @Test
    void given_itemWithLowestQuality_when_endOfDay_then_qualityNotChangedAndSellInDecreases() {
        verifyItem(
            new Item("Normal Item", 10, 0),
            new Item("Normal Item", 9, 0)
        );
    }

    @Test
    void given_itemWithNegativeSellIn_when_endOfDay_then_qualityDecreasedDouble() {
        verifyItem(
            new Item("Normal Item", -5, 4),
            new Item("Normal Item", -6, 2)
        );
    }

    @Test
    void given_agedBrieItem_when_endOfDay_then_qualityIncreases() {
        verifyItem(
            new Item("Aged Brie", 20, 4),
            new Item("Aged Brie", 19, 5)
        );
    }

    @Test
    void given_agedBrieItemWithHighestQuality_when_endOfDay_then_qualityNotChanged() {
        verifyItem(
            new Item("Aged Brie", 20, 50),
            new Item("Aged Brie", 19, 50)
        );
    }

    @Test
    void given_legendaryItem_when_endOfDay_then_nothingChanged() {
        verifyItem(
            new Item("Sulfuras, Hand of Ragnaros", 20, 80),
            new Item("Sulfuras, Hand of Ragnaros", 20, 80)
        );
    }

    @Test
    void given_backstagePassItemWithSellInBetween5And11_when_endOfDay_then_qualityIncreasesBy2() {
        verifyItem(
            new Item("Backstage passes to a TAFKAL80ETC concert", 6, 30),
            new Item("Backstage passes to a TAFKAL80ETC concert", 5, 32)
        );
    }

    @Test
    void given_backstagePassItemWithSellInBetween0And5_when_endOfDay_then_qualityIncreasesBy3() {
        verifyItem(
            new Item("Backstage passes to a TAFKAL80ETC concert", 2, 30),
            new Item("Backstage passes to a TAFKAL80ETC concert", 1, 33)
        );
    }

    @Test
    void given_backstagePassItemWithSellInLowerThan0_when_endOfDay_then_qualityIs0() {
        verifyItem(
            new Item("Backstage passes to a TAFKAL80ETC concert", 0, 40),
            new Item("Backstage passes to a TAFKAL80ETC concert", -1, 0)
        );
    }

}
