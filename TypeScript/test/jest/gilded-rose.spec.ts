import { Item, GildedRose } from '@/gilded-rose';

describe('Gilded Rose', () => {
  it('should foo', () => {
    const gildedRose = new GildedRose([new Item('foo', 0, 0)]);
    const items = gildedRose.updateQuality();
    expect(items[0].name).toBe('foo');
  });

  describe('Test item quality', () => {
    it('Should update item quality', () => {
      const gildedRose = new GildedRose([new Item('Chainmail', 5, 25)]);
      const items = gildedRose.updateQuality();
      expect(items[0].quality).toBe(24);

    });
    it('Should not decrease item quality below 0', () => {
      const gildedRose = new GildedRose([new Item('Chainmail', 5, 0), new Item('Apple', 0, 1)]);
      const items = gildedRose.updateQuality();
      expect(items[0].quality).toBe(0);
      expect(items[1].quality).toBe(0);
    });

    it('Should decrease item quality by 2 when sellIn date has passed', () => {
      const gildedRose = new GildedRose([new Item('Chainmail', 0, 10), new Item('Apple', 0, 1)]);
      const items = gildedRose.updateQuality();
      expect(items[0].quality).toBe(8);
      expect(items[1].quality).toBe(0);
    });

    it('Should not increase item quality above 50', () => {
      const gildedRose = new GildedRose([new Item('Aged Brie', 5, 50)]);
      const items = gildedRose.updateQuality();
      expect(items[0].quality).toBe(50);
    });
  })

  describe('Test item sellIn', () => {
    it('Should update item sellIn', () => {
      const gildedRose = new GildedRose([new Item('Chainmail', 5, 25)]);
      const items = gildedRose.updateQuality();
      expect(items[0].sellIn).toBe(4);
    });

    it('Should be able to decrease below 0', () => {
      const gildedRose = new GildedRose([new Item('Chainmail', 0, 25)]);
      const items = gildedRose.updateQuality();
      expect(items[0].sellIn).toBe(-1);
    });
  });

  describe('Aged Brie', () => {
    it('Should increase item quality on updateQuality', () => {
      const gildedRose = new GildedRose([new Item('Aged Brie', 5, 25)]);
      const items = gildedRose.updateQuality();
      expect(items[0].quality).toBe(26);
    });

    it('Should increase item quality for Aged Brie, but not above 50', () => {
      const gildedRose = new GildedRose([new Item('Aged Brie', 5, 50)]);
      const items = gildedRose.updateQuality();
      expect(items[0].quality).toBe(50);
    });

    it('should increases in quality twice as fast when sellIn date passed', () => {
      const gildedRose = new GildedRose([new Item('Aged Brie', 0, 25), new Item('Aged Brie', -25, 49)]);
      const items = gildedRose.updateQuality();
      expect(items[0].quality).toBe(27);
      expect(items[1].quality).toBe(50);
    });

  })

  describe('Concert Passes', () => {
  })

  describe('Sulfuras', () => {
    it('Should not update sellIn', () => {
      const gildedRose = new GildedRose([new Item('Sulfuras, Hand of Ragnaros', 5, 25)]);
      const items = gildedRose.updateQuality();
      expect(items[0].sellIn).toBe(5);
    });

    it('Should not update quality', () => {
      const gildedRose = new GildedRose([new Item('Sulfuras, Hand of Ragnaros', 5, 25)]);
      const items = gildedRose.updateQuality();
      expect(items[0].quality).toBe(25);
    });
  })


});
