package com.gildedrose;

public class RegularItemHandler extends AbstractItemHandler {

  public RegularItemHandler(final Item item) {
    super(item);
  }

  @Override
  public void update() {
    decreaseQuality(getQualityAmount());
    decreaseSellIn();
  }

}
