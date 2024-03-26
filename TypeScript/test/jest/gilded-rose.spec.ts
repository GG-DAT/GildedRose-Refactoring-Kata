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

  describe('Backstage Passes', () => {
    // const backstagePassName ='Backstage passes to a Toppling Boulders concert';
    const backstagePassName = 'Backstage passes to a TAFKAL80ETC concert';

    it('should increases in before the concert', () => {
      const gildedRose = new GildedRose([new Item(backstagePassName, 15, 17)]);
      const items = gildedRose.updateQuality();
      expect(items[0].quality).toBe(18);
    });

    it('should increases quicker close to the concert', () => {
      const gildedRose = new GildedRose([new Item(backstagePassName, 9, 17)]);
      const items = gildedRose.updateQuality();
      expect(items[0].quality).toBe(19);
    });
    it('should increases even quicker very close to the concert', () => {
      const gildedRose = new GildedRose([new Item(backstagePassName, 4, 17)]);
      const items = gildedRose.updateQuality();
      expect(items[0].quality).toBe(20);
    });

    it('should become worthless after the concert', () => {
      const gildedRose = new GildedRose([new Item(backstagePassName, 0, 17)]);
      const items = gildedRose.updateQuality();
      expect(items[0].quality).toBe(0);
    });

    it('should never increase above 50 quality', () => {
      const gildedRose = new GildedRose([
        new Item(backstagePassName, 15, 50),
        new Item(backstagePassName, 10, 49),
        new Item(backstagePassName, 10, 50),
        new Item(backstagePassName, 2, 48),
        new Item(backstagePassName, 2, 49),
        new Item(backstagePassName, 2, 50)
      ]);
      const items = gildedRose.updateQuality();
      expect(items[0].quality).toBe(50);
      expect(items[1].quality).toBe(50);
      expect(items[2].quality).toBe(50);
      expect(items[3].quality).toBe(50);
      expect(items[4].quality).toBe(50);
      expect(items[5].quality).toBe(50);
    });
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

  describe('Conjured', () => {
    it('Should decrease in quality twice as fast', () => {
      const gildedRose = new GildedRose([new Item('Conjured berries', 5, 25)]);
      const items = gildedRose.updateQuality();
      expect(items[0].quality).toBe(23);
    });
  })
});
