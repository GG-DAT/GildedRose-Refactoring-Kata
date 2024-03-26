package com.gildedrose;

class GildedRose {

  private static final String SULFURAS = "Sulfuras, Hand of Ragnaros";
  private static final String BACKSTAGE_PASSES = "Backstage passes to a TAFKAL80ETC concert";
  private static final String AGED_BRIE = "Aged Brie";
  private static final String CONJURED = "Conjured Mana Cake";

  Item[] items;

  public GildedRose(final Item[] items) {
    this.items = items;
  }

  public void updateQuality() {
    for (final Item item : items) {
      AbstractItemHandler handler;
      switch (item.name) {
        case AGED_BRIE:
          handler = new AgedBrieItemHandler(item);
          break;
        case SULFURAS:
          handler = new SulfurasItemHandler(item);
          break;
        case BACKSTAGE_PASSES:
          handler = new BackstagePassesItemHandler(item);
          break;
        case CONJURED:
          handler = new ConjuredItemHandler(item);
          break;
        default:
          handler = new RegularItemHandler(item);
          break;
      }
      handler.update();
    }
  }

}
