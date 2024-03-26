package com.gildedrose;

class GildedRose {

  private static final String SULFURAS = "Sulfuras, Hand of Ragnaros";
  private static final String BACKSTAGE_PASSES = "Backstage passes to a TAFKAL80ETC concert";
  private static final String AGED_BRIE = "Aged Brie";

	Item[] items;

	public GildedRose(final Item[] items) {
		this.items = items;
	}

	public void updateQuality() {
		for(final Item item : items ) {
		  AbstractItemHandler handler;
		  switch(item.name) {
		    case AGED_BRIE:
		      handler = new AgedBrieItemHandler(item);
		      break;
		    case SULFURAS:
          handler = new SulfurasItemHandler(item);
		      break;
		    case BACKSTAGE_PASSES:
		      handler = new BackstagePassesItemHandler(item);
		      break;
		    default:
		      handler = new RegularItemHandler(item);
		      break;
		  }
		  handler.update();
		}
	}

//	private int getQualityAmount(Item item) {
//	  return item.sellIn <= SELL_IN_DAYS_REACHED ? DOUBLE_QUALITY_RATE : QUALITY_RATE;
//	}
//
//	private void handleRegularItem(Item item) {
//	  int qualityAmount = getQualityAmount(item);
//	  decreaseQuality(item, qualityAmount);
//	  decreaseSellIn(item);
//	}
//
//	 private void handleAgedBrie(Item item) {
//	   int qualityAmount = getQualityAmount(item);
//	   increaseQuality(item, qualityAmount);
//	   decreaseSellIn(item);
//	 }
//
//	  private int getBackstagePassesQualityAmount(Item item) {
//	    if (item.sellIn <= NUM_DAYS_TRIPLE_QUALITY_RATE) {
//	      return TRIPLE_QUALITY_RATE;
//	    } else if (item.sellIn <= NUM_DAYS_DOUBLE_QUALITY_RATE) {
//	      return DOUBLE_QUALITY_RATE;
//	    } else {
//	      return QUALITY_RATE;
//	    }
//	  }
//
//   private void handleBackstagePasses(Item item) {
//     if(item.sellIn <= SELL_IN_DAYS_REACHED) {
//       item.quality = 0;
//     } else {
//       int qualityAmount= getBackstagePassesQualityAmount(item);
//       increaseQuality(item, qualityAmount);
//     }
//     decreaseSellIn(item);
//   }

//			if (!item.name.equals(AGED_BRIE) && !item.name.equals(BACKSTAGE_PASSES)) {
//				if (item.quality > 0) {
//					if (!item.name.equals(SULFURAS)) {
//					  decreaseQuality(item, 1);
//					}
//				}
//			} else {
//				if (item.quality < MAX_QUALITY) {
//					item.quality = item.quality + 1;
//
//					if (item.name.equals(BACKSTAGE_PASSES)) {
//						if (item.sellIn < 11) {
//						  increaseQuality(item, 1);
//						}
//
//						if (item.sellIn < 6) {
//						  increaseQuality(item, 1);
//						}
//					}
//				}
//			}
//
//			if (!item.name.equals(SULFURAS)) {
//				item.sellIn = item.sellIn - 1;
//			}
//
//			if (item.sellIn < 0) {
//				if (!item.name.equals(AGED_BRIE)) {
//					if (!item.name.equals(BACKSTAGE_PASSES)) {
//						if (item.quality > 0) {
//							if (!item.name.equals(SULFURAS)) {
//							  decreaseQuality(item, 1);
//							}
//						}
//					} else {
//						item.quality = item.quality - item.quality;
//					}
//				} else {
//				  increaseQuality(item, 1);
//				}
//			}
//		}
//	}
//
//	private void increaseQuality(Item item, int amount) {
//			item.quality = Math.min(item.quality + amount, MAX_QUALITY);
//	}
//
//	private void decreaseQuality(Item item, int amount) {
//		item.quality = Math.max(item.quality - amount, 0);
//	}
//
//	private void decreaseSellIn(Item item) {
//	  item.sellIn = item.sellIn - 1;
//	}
}
