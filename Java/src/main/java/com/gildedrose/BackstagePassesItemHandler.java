package com.gildedrose;

public class BackstagePassesItemHandler extends AbstractItemHandler {

  public BackstagePassesItemHandler(final Item item) {
    super(item);
  }

  @Override
  public void update() {
    updateQuality();
    decreaseSellIn();
  }

  private void updateQuality() {
    if(item.sellIn <= SELL_IN_DAYS_REACHED) {
      item.quality = 0;
    } else {
      increaseQuality(getQualityAmount());
    }
  }

  @Override
  protected int getQualityAmount() {
    if (item.sellIn <= NUM_DAYS_TRIPLE_QUALITY_RATE) {
      return TRIPLE_QUALITY_RATE;
    } else if (item.sellIn <= NUM_DAYS_DOUBLE_QUALITY_RATE) {
      return DOUBLE_QUALITY_RATE;
    } else {
      return QUALITY_RATE;
    }
  }
}
