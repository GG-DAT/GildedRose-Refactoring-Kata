package com.gildedrose;

class GildedRose {

  private static final String SULFURAS = "Sulfuras, Hand of Ragnaros";
  private static final String BACKSTAGE_PASSES = "Backstage passes to a TAFKAL80ETC concert";
  private static final String AGED_BRIE = "Aged Brie";

  private static final int QUALITY_RATE = 1;
  private static final int DOUBLE_QUALITY_RATE = 2;
  private static final int TRIPLE_QUALITY_RATE = 3;
  private static final int NUM_DAYS_DOUBLE_QUALITY_RATE = 10;
  private static final int NUM_DAYS_TRIPLE_QUALITY_RATE = 5;
  private static final int MAX_QUALITY = 50;
  private static final int SELL_IN_DAYS_REACHED = 0;

	Item[] items;

	public GildedRose(Item[] items) {
		this.items = items;
	}

	public void updateQuality() {
		for(final Item item : items ) {		  
		  switch(item.name) {
		    case AGED_BRIE:
		      handleAgedBrie(item);
		      break;
		    case SULFURAS:
		      // no-op
		      break;
		    case BACKSTAGE_PASSES:
		      handleBackstagePasses(item);
		      break;
		    default:
		      handleRegularItem(item);
		      break;		        
		  }
		}
	}
	
	private int getQualityAmount(Item item) {
	  return item.sellIn <= SELL_IN_DAYS_REACHED ? DOUBLE_QUALITY_RATE : QUALITY_RATE;
	}
	
	private void handleRegularItem(Item item) {
	  int qualityAmount = getQualityAmount(item);
	  decreaseQuality(item, qualityAmount);
	  decreaseSellIn(item);	  
	}

	 private void handleAgedBrie(Item item) {
	   int qualityAmount = getQualityAmount(item);
	   increaseQuality(item, qualityAmount);
	   decreaseSellIn(item);
	 }
	 
	  private int getBackstagePassesQualityAmount(Item item) {
	    if (item.sellIn <= NUM_DAYS_TRIPLE_QUALITY_RATE) {
	      return TRIPLE_QUALITY_RATE;
	    } else if (item.sellIn <= NUM_DAYS_DOUBLE_QUALITY_RATE) {
	      return DOUBLE_QUALITY_RATE;
	    } else {
	      return QUALITY_RATE;
	    }
	  }	  

   private void handleBackstagePasses(Item item) {
     if(item.sellIn <= SELL_IN_DAYS_REACHED) {
       item.quality = 0;
     } else {
       int qualityAmount= getBackstagePassesQualityAmount(item);
       increaseQuality(item, qualityAmount);
     }       
     decreaseSellIn(item);
   }
		  
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
	
	private void increaseQuality(Item item, int amount) {
			item.quality = Math.min(item.quality + amount, MAX_QUALITY);
	}
	
	private void decreaseQuality(Item item, int amount) {
		item.quality = Math.max(item.quality - amount, 0);
	}
	
	private void decreaseSellIn(Item item) {
	  item.sellIn = item.sellIn - 1;
	}
}
