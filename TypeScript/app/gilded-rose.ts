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
  items: Array<Item>;

  constructor(items = [] as Array<Item>) {
    this.items = items;
  }

  public updateQuality() {
    this.items.forEach(item => {
      this.updateItemQuality(item);
      this.updateSellInDate(item);
      this.checkIfSellInDatePassed(item);
    })
    return this.items;
  }

  private updateItemQuality(item: Item) {
    if (!this.isAgingItem(item) && !this.isBackstagePass(item)) {
      if (item.quality > 0) {
        if (!this.isLegendaryItem(item)) {
          item.quality = item.quality - 1;
        }
      }
    } else {
      if (item.quality < 50) {
        item.quality = item.quality + 1;
        if (this.isBackstagePass(item)) {
          if (item.sellIn < 11) {
            if (item.quality < 50) {
              item.quality = item.quality + 1;
            }
          }
          if (item.sellIn < 6) {
            if (item.quality < 50) {
              item.quality = item.quality + 1;
            }
          }
        }
      }
    }
  }

  private updateSellInDate(item: Item) {
    if (!this.isLegendaryItem(item)) {
      item.sellIn = item.sellIn - 1;
    }
  }

  private checkIfSellInDatePassed(item: Item) {
    if (item.sellIn < 0) {
      if (!this.isAgingItem(item)) {
        if (!this.isBackstagePass(item)) {
          if (item.quality > 0) {
            if (!this.isLegendaryItem(item)) {
              item.quality = item.quality - 1;
            }
          }
        } else {
          item.quality = item.quality - item.quality;
        }
      } else {
        if (item.quality < 50) {
          item.quality = item.quality + 1;
        }
      }
    }
  }

  private isAgingItem(item: Item) {
    return ['Aged Brie'].includes(item.name);
  }

  private isBackstagePass(item: Item) {
    return item.name.startsWith("Backstage passes");
  }

  private isLegendaryItem(item: Item) {
    return ['Sulfuras, Hand of Ragnaros'].includes(item.name);
  }
}
