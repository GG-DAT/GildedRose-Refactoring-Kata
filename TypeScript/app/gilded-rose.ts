export class Item {
  name: string;
  sellIn: number;
  quality: number;

  constructor(name, sellIn, quality) {
    this.name = name;
    this.sellIn = sellIn;
    this.quality = quality;
  }
}

export class GildedRose {
  private readonly AGED_BRIE = 'Aged Brie';
  private readonly BACKSTAGE_PASS = 'Backstage passes to a TAFKAL80ETC concert';
  private readonly SULFURAS = 'Sulfuras, Hand of Ragnaros';
  private readonly MAX_QUALITY = 50;

  //backstage passes
  private readonly CLOSE_TO_CONCERT_IN_DAYS = 10;
  private readonly LAST_MINUTE_CONCERT_IN_DAYS = 5

  private items: Array<Item>;

  constructor(items = [] as Array<Item>) {
    this.items = items;
  }

  updateQuality() {
    const specialQualityIncreases = [this.AGED_BRIE, this.BACKSTAGE_PASS];
    this.items.filter(item => item.name != this.SULFURAS).forEach(item => {
      item.sellIn--;
      if (specialQualityIncreases.includes(item.name)) {
        if (item.name === this.BACKSTAGE_PASS) {
          if (item.sellIn < 0) {
            item.quality = 0;
          } else {
            this.increaseQualityBackstage(item);
          }
        } else if (item.sellIn < 0) {
          this.increaseQuality(item, 2);
        } else {
          this.increaseQuality(item, 1);
        }
      } else {
        this.decreaseQuality(item);
      }
    });

    return this.items;
  }

  private increaseQualityBackstage(backstageItem: Item) {
    if (backstageItem.sellIn <= this.LAST_MINUTE_CONCERT_IN_DAYS) {
      this.increaseQuality(backstageItem, 3);
    } else if (backstageItem.sellIn <= this.CLOSE_TO_CONCERT_IN_DAYS) {
      this.increaseQuality(backstageItem, 2);
    } else {
      this.increaseQuality(backstageItem, 1);
    }
  }

  private increaseQuality(item: Item, value: number) {
    item.quality += value;
    item.quality = Math.min(item.quality, this.MAX_QUALITY);
  }

  private decreaseQuality(item: Item) {
    if (item.sellIn < 0) {
      item.quality -= 2;
    } else {
      item.quality--;
    }
    item.quality = Math.max(item.quality, 0);
  }
}
