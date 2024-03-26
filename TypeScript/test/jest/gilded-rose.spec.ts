import { Item, GildedRose } from '@/gilded-rose';

describe('Gilded Rose', () => {
  it('should foo', () => {
    const gildedRose = new GildedRose([new Item('foo', 0, 0)]);
    const items = gildedRose.updateQuality();
    expect(items[0].name).toBe('foo');
    expect(items[0].quality).toBe(0);
    expect(items[0].sellIn).toBe(-1);
  });

  it('should degrade Quality and SellIn for normal items', () => {
    const gildedRose = new GildedRose([new Item('Normal Item', 10, 20)]);
    const updatedItems = gildedRose.updateQuality();
    expect(updatedItems[0].quality).toBe(19);
    expect(updatedItems[0].sellIn).toBe(9);
  });

  it('should degrade Quality twice as fast after SellIn for normal items', () => {
    const gildedRose = new GildedRose([new Item('Normal Item', 0, 20)]);
    const updatedItems = gildedRose.updateQuality();
    expect(updatedItems[0].quality).toBe(18);
    expect(updatedItems[0].sellIn).toBe(-1);
  });

  it('should degrade normaly, because we are for SellIn date', () => {
    const gildedRose = new GildedRose([new Item('Normal Item', 1, 20)]);
    const updatedItems = gildedRose.updateQuality();
    expect(updatedItems[0].quality).toBe(19);
    expect(updatedItems[0].sellIn).toBe(0);
  });

  it('should not degrade Quality below 0 for normal items', () => {
    const gildedRose = new GildedRose([new Item('Normal Item', 5, 0)]);
    const updatedItems = gildedRose.updateQuality();
    expect(updatedItems[0].quality).toBe(0);
  });

  it('should increase Quality for Aged Brie', () => {
    const gildedRose = new GildedRose([new Item('Aged Brie', 10, 20)]);
    const updatedItems = gildedRose.updateQuality();
    expect(updatedItems[0].quality).toBe(21);
  });

  it('should not increase Quality above 50 for Aged Brie', () => {
    const gildedRose = new GildedRose([new Item('Aged Brie', 10, 50)]);
    const updatedItems = gildedRose.updateQuality();
    expect(updatedItems[0].quality).toBe(50);
  });

  it('should not alter SellIn or Quality for Sulfuras', () => {
    const gildedRose = new GildedRose([new Item('Sulfuras, Hand of Ragnaros', 10, 80)]);
    const updatedItems = gildedRose.updateQuality();
    expect(updatedItems[0].quality).toBe(80);
    expect(updatedItems[0].sellIn).toBe(10);
  });

  it('should increase Quality for Backstage passes', () => {
    const gildedRose = new GildedRose([new Item('Backstage passes to a TAFKAL80ETC concert', 15, 20)]);
    const updatedItems = gildedRose.updateQuality();
    expect(updatedItems[0].quality).toBe(21);
  });

  it('should increase Quality by 2 when SellIn is 10 days or less for Backstage passes', () => {
    const gildedRose = new GildedRose([new Item('Backstage passes to a TAFKAL80ETC concert', 10, 20)]);
    const updatedItems = gildedRose.updateQuality();
    expect(updatedItems[0].quality).toBe(22);
  });

  it('should increase Quality by 3 when SellIn is 5 days or less for Backstage passes', () => {
    const gildedRose = new GildedRose([new Item('Backstage passes to a TAFKAL80ETC concert', 5, 20)]);
    const updatedItems = gildedRose.updateQuality();
    expect(updatedItems[0].quality).toBe(23);
  });

  it('should drop Quality to 0 after the concert for Backstage passes', () => {
    const gildedRose = new GildedRose([new Item('Backstage passes to a TAFKAL80ETC concert', 0, 20)]);
    const updatedItems = gildedRose.updateQuality();
    expect(updatedItems[0].quality).toBe(0);
  });

  /*it('should degrade Quality twice as fast for Conjured items', () => {
    const gildedRose = new GildedRose([new Item('Conjured Mana Cake', 10, 20)]);
    const updatedItems = gildedRose.updateQuality();
    expect(updatedItems[0].quality).toBe(18);
  });*/
});
