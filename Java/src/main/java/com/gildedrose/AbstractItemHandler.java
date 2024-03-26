package com.gildedrose;

public abstract class AbstractItemHandler {

  protected static final int QUALITY_RATE = 1;
  protected static final int DOUBLE_QUALITY_RATE = 2;
  protected static final int TRIPLE_QUALITY_RATE = 3;
  protected static final int NUM_DAYS_DOUBLE_QUALITY_RATE = 10;
  protected static final int NUM_DAYS_TRIPLE_QUALITY_RATE = 5;
  protected static final int MAX_QUALITY = 50;
  protected static final int SELL_IN_DAYS_REACHED = 0;

  protected Item item;

  public AbstractItemHandler(final Item item) {
    this.item = item;
  }

  public abstract void update();

  protected int getQualityAmount() {
    return item.sellIn <= SELL_IN_DAYS_REACHED ? DOUBLE_QUALITY_RATE : QUALITY_RATE;
  }

  protected void increaseQuality(final int amount) {
    item.quality = Math.min(item.quality + amount, MAX_QUALITY);
  }

  protected void decreaseQuality(final int amount) {
    item.quality = Math.max(item.quality - amount, 0);
  }

  protected void decreaseSellIn() {
    item.sellIn = item.sellIn - 1;
  }
}
