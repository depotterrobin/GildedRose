package com.gildedrose.items;

import com.gildedrose.Item;

public class ConjuredItem extends StandardItem {
    private static final int DECREASE_BY_VALUE = 2;

    public ConjuredItem(Item item) {
        super(item);
    }

    @Override
    public int getDecreaseByValue() {
        return DECREASE_BY_VALUE;
    }
}
