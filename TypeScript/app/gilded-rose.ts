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

  private items: Array<Item>;

  constructor(items = [] as Array<Item>) {
    this.items = items;
  }

  updateQuality() {
    const specialQualityIncreases = [this.AGED_BRIE, this.BACKSTAGE_PASS];
    this.items.filter(item => item.name != this.SULFURAS).forEach(item => {
      if (specialQualityIncreases.includes(item.name)) {
        if (item.name == this.BACKSTAGE_PASS) {
          this.increaseQualityBackstage(item);
        } else {
          this.increaseQuality(item, 1);
        }
      } else {
        if (item.quality > 0) {
          item.quality = item.quality - 1
        }
      }
      item.sellIn = item.sellIn - 1;
      if (item.sellIn < 0) {
        if (item.name != this.AGED_BRIE) {
          if (item.name != this.BACKSTAGE_PASS) {
            if (item.quality > 0) {
              item.quality = item.quality - 1
            }
          } else {
            item.quality = item.quality - item.quality
          }
        } else {
          if (item.quality < this.MAX_QUALITY) {
            item.quality = item.quality + 1
          }
        }
      }
    });

    return this.items;
  }

  private increaseQualityBackstage(backstageItem: Item) {
    if (backstageItem.sellIn < 6) {
      this.increaseQuality(backstageItem, 3);
    } else if (backstageItem.sellIn < 11) {
      this.increaseQuality(backstageItem, 2);
    } else {
      this.increaseQuality(backstageItem, 1);
    }
  }

  private increaseQuality(item: Item, value: number) {
    item.quality += value;
    item.quality = Math.min(item.quality, this.MAX_QUALITY);
  }
}
