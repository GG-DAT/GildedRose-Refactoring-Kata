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
  static readonly MAX_ITEM_QUALITY = 50;

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
    if (this.isAgingItem(item)) {
      this.adjustQuality(item, 1);
    } else if (this.isBackstagePass(item)) {
      this.updateBackstagePassQuality(item);
    } else {
      this.adjustQuality(item, -1);
    }
  }

  private updateBackstagePassQuality(item: Item) {
    if (item.sellIn < 6) {
      this.adjustQuality(item, 3);
    } else if (item.sellIn < 11) {
      this.adjustQuality(item, 2);
    } else {
      this.adjustQuality(item, 1);
    }
  }

  private updateSellInDate(item: Item) {
    if (!this.isLegendaryItem(item)) {
      item.sellIn = item.sellIn - 1;
    }
  }

  private checkIfSellInDatePassed(item: Item) {
    if (item.sellIn < 0) {
      if (this.isBackstagePass(item)) {
        item.quality = 0;
      } else if (this.isAgingItem(item)) {
        this.adjustQuality(item, 1);
      } else {
        this.adjustQuality(item, -1);
      }
    }
  }

  private adjustQuality(item: Item, amount: number) {
    if (!this.isLegendaryItem(item)) {
      item.quality = Math.min(item.quality + amount, GildedRose.MAX_ITEM_QUALITY);
      item.quality = Math.max(item.quality, 0);
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
