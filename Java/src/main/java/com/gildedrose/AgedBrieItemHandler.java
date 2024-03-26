package com.gildedrose;

public class AgedBrieItemHandler extends AbstractItemHandler {

  public AgedBrieItemHandler(final Item item) {
    super(item);
  }

  @Override
  public void update() {
    increaseQuality(getQualityAmount());
    decreaseSellIn();
  }

}
