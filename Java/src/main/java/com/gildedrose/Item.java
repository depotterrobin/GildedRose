package com.gildedrose;

public class Item {

    private final String name;

    private int sellIn;

    private int quality;

    public Item(String name, int sellIn, int quality) {
        this.name = name;
        this.sellIn = sellIn;
        this.quality = quality;
    }

    public String getName() {
        return name;
    }

    public int getSellIn() {
        return sellIn;
    }

    public int getQuality() {
        return quality;
    }

    public void setQualityToZero() {
        this.quality = 0;
    }

    public void decreaseSellIn() {
        this.sellIn--;
    }

    public void increaseQuality() {
        this.quality++;
    }

    public void decreaseQuality() {
        this.quality--;
    }

   @Override
   public String toString() {
        return this.name + ", " + this.sellIn + ", " + this.quality;
    }
}
