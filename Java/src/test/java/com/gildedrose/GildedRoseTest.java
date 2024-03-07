package com.gildedrose;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class GildedRoseTest {
    private void verifyItem(Item item, Item expected) {
        List<Item> items = List.of(item);
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(expected.sellIn, items.get(0).sellIn);
        assertEquals(expected.quality, items.get(0).quality);
        assertEquals(expected.name, items.get(0).name);
    }

    private static Stream<Arguments> given_normalItem_when_endOfDay_then_decreaseQualityAndSellInBy1() {
        return Stream.of(
            Arguments.of(new Item("Normal Item", 10, 25), new Item("Normal Item", 9, 24)),
            Arguments.of(new Item("Normal Item", 1, 1), new Item("Normal Item", 0, 0)),
            Arguments.of(new Item("Normal Item", 0, 50), new Item("Normal Item", -1, 49))
        );
    }

    private static Stream<Arguments> given_agedBrieItem_when_endOfDay_then_qualityIncreases() {
        return Stream.of(
            Arguments.of(new Item("Aged Brie", 10, 4), new Item("Aged Brie", 9, 5)),
            Arguments.of(new Item("Aged Brie", 1, 4), new Item("Aged Brie", 0, 5)),
            Arguments.of(new Item("Aged Brie", 0, 4), new Item("Aged Brie", -1, 5))
            );
    }

    @ParameterizedTest
    @MethodSource
    void given_normalItem_when_endOfDay_then_decreaseQualityAndSellInBy1(Item input, Item expected) {
        verifyItem(input, expected);
    }

    @Test
    void given_itemWithLowestQuality_when_endOfDay_then_qualityNotChangedAndSellInDecreases() {
        verifyItem(
            new Item("Normal Item", 0, 0), new Item("Normal Item", -1, 0)
        );
    }

    @Test
    void given_itemWithNegativeSellIn_when_endOfDay_then_qualityDecreasesBy2() {
        verifyItem(
            new Item("Normal Item", -5, 4),
            new Item("Normal Item", -6, 2)
        );
    }

    @ParameterizedTest
    @MethodSource
    void given_agedBrieItem_when_endOfDay_then_qualityIncreases(Item input, Item expected) {
        verifyItem(input, expected);
    }

    @Test
    void given_agedBrieItemWithNegativeSellIn_when_endOfDay_then_qualityIncreasesBy2() {
        verifyItem(
            new Item("Aged Brie", -1, 4),
            new Item("Aged Brie", -2, 6)
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
    void given_backstagePassItemWithSellInHigherThan10_when_endOfDay_then_qualityIncreasesBy1() {
        verifyItem(
            new Item("Backstage passes to a TAFKAL80ETC concert", 11, 30),
            new Item("Backstage passes to a TAFKAL80ETC concert", 10, 31)
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
            new Item("Backstage passes to a TAFKAL80ETC concert", 1, 30),
            new Item("Backstage passes to a TAFKAL80ETC concert", 0, 33)
        );
    }

    @Test
    void given_backstagePassItemWithSellInLowerThan0_when_endOfDay_then_qualityIs0() {
        verifyItem(
            new Item("Backstage passes to a TAFKAL80ETC concert", 0, 40),
            new Item("Backstage passes to a TAFKAL80ETC concert", -1, 0)
        );
    }

    @Test
    void given_itemWithNegativeQuality_when_endOfDay_then_onlySellInDecreases() {
        verifyItem(
            new Item("Corrupt item", -100, -10),
            new Item("Corrupt item", -101, -10)
        );
    }

    @Test
    void given_backstagePassItemWithMaximumQuality_when_endOfDay_then_qualityNotChanged() {
        verifyItem(
            new Item("Backstage passes to a TAFKAL80ETC concert", 11, 50),
            new Item("Backstage passes to a TAFKAL80ETC concert", 10, 50)
        );
    }
}
