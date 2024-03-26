package com.gildedrose;

public class ConjuredItemHandler extends AbstractItemHandler {

  private static final int DOUBLE_RATE = 2;

  public ConjuredItemHandler(final Item item) {
    super(item);
  }

  @Override
  public void update() {
    decreaseQuality(getQualityAmount());
    decreaseSellIn();
  }

  @Override
  protected int getQualityAmount() {
    return super.getQualityAmount() * DOUBLE_RATE;
  }

}
