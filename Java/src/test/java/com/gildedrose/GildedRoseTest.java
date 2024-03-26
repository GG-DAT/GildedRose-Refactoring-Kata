package com.gildedrose;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.Test;

class GildedRoseTest {
  private static final String ITEM_NAME = "item1";
  private static final String BACKSTAGE_PASSES = "Backstage passes to a TAFKAL80ETC concert";

  @Test
  void testItemNameDoesNotChangeWhenUpdatingQuality() {
    // GIVEN
    final Item item1 = new Item(ITEM_NAME, 0, 0);
    final Item[] items = new Item[] {item1};
    final GildedRose app = new GildedRose(items);

    // WHEN
    app.updateQuality();

    // THEN
    assertEquals(ITEM_NAME, app.items[0].name);
  }

  @Test
  void testQualityDecreasesByOneAfterAnUpdateWhenSellInIsPositive() {
    // GIVEN
    final Item item1 = new Item(ITEM_NAME, 2, 2);
    final Item[] items = new Item[] {item1};
    final GildedRose app = new GildedRose(items);

    // WHEN
    app.updateQuality();

    // THEN
    assertEquals(1, app.items[0].quality);
  }

  @Test
  void testQualityNeverNegative() {
    // GIVEN
    final Item item1 = new Item(ITEM_NAME, 2, 0);
    final Item[] items = new Item[] {item1};
    final GildedRose app = new GildedRose(items);

    // WHEN
    app.updateQuality();

    // THEN
    assertEquals(0, app.items[0].quality);
  }

  @Test
  void testSellinDecreasesByOneAfterAnUpdate() {
    // GIVEN
    final Item item1 = new Item(ITEM_NAME, 2, 2);
    final Item[] items = new Item[] {item1};
    final GildedRose app = new GildedRose(items);

    // WHEN
    app.updateQuality();

    // THEN
    assertEquals(1, app.items[0].sellIn);
  }

  @Test
  void testQualityDecreasesTwiceAsFastWhenSellInIsZero() {
    // GIVEN
    final Item item1 = new Item(ITEM_NAME, 0, 5);
    final Item[] items = new Item[] {item1};
    final GildedRose app = new GildedRose(items);

    // WHEN
    app.updateQuality();

    // THEN
    assertEquals(3, app.items[0].quality);
  }

  @Test
  void testQualityDecreasesTwiceAsFastWhenSellInIsZeroButDoesNotGoBelowZero() {
    // GIVEN
    final Item item1 = new Item(ITEM_NAME, 0, 1);
    final Item[] items = new Item[] {item1};
    final GildedRose app = new GildedRose(items);

    // WHEN
    app.updateQuality();

    // THEN
    assertEquals(0, app.items[0].quality);
  }

  @Test
  void testQualityDecreasesTwiceAsFastWhenSellInIsNegative() {
    // GIVEN
    final Item item1 = new Item(ITEM_NAME, -4, 5);
    final Item[] items = new Item[] {item1};
    final GildedRose app = new GildedRose(items);

    // WHEN
    app.updateQuality();

    // THEN
    assertEquals(3, app.items[0].quality);
  }

  @Test
  void testQualityDecreasesTwiceAsFastWhenSellInIsNegativeButDoesNotGoBelowZero() {
    // GIVEN
    final Item item1 = new Item(ITEM_NAME, -4, 1);
    final Item[] items = new Item[] {item1};
    final GildedRose app = new GildedRose(items);

    // WHEN
    app.updateQuality();

    // THEN
    assertEquals(0, app.items[0].quality);
  }

  @Test
  void agedBrieIncreasesInQualityTheOlderItGets() {
    final Item[] items = new Item[] {new Item("Aged Brie", 5, 25)};
    final GildedRose app = new GildedRose(items);
    app.updateQuality();
    assertEquals(26, app.items[0].quality);
    assertEquals(4, app.items[0].sellIn);
    app.updateQuality();
    assertEquals(27, app.items[0].quality);
    assertEquals(3, app.items[0].sellIn);
  }

  @Test
  void agedBrieQualityDoesNotIncreaseAbove50() {
    final Item[] items = new Item[] {new Item("Aged Brie", 5, 49)};
    final GildedRose app = new GildedRose(items);
    app.updateQuality();
    assertEquals(50, app.items[0].quality);
    assertEquals(4, app.items[0].sellIn);
    app.updateQuality();
    assertEquals(50, app.items[0].quality);
    assertEquals(3, app.items[0].sellIn);
  }

  @Test
  void agedBrieQualityIncreasesTwiceAsFastAfterSellDatePassed() {
    final Item[] items = new Item[] {new Item("Aged Brie", 1, 25)};
    final GildedRose app = new GildedRose(items);
    app.updateQuality();
    assertEquals(26, app.items[0].quality);
    assertEquals(0, app.items[0].sellIn);
    app.updateQuality();
    assertEquals(28, app.items[0].quality);
    assertEquals(-1, app.items[0].sellIn);
    app.updateQuality();
    assertEquals(30, app.items[0].quality);
    assertEquals(-2, app.items[0].sellIn);
  }

  @Test
  void agedBrieQualityIncreasesTwiceAsFastAfterSellDatePassedButNotAbove50() {
    final Item[] items = new Item[] {new Item("Aged Brie", -3, 47)};
    final GildedRose app = new GildedRose(items);
    app.updateQuality();
    assertEquals(49, app.items[0].quality);
    assertEquals(-4, app.items[0].sellIn);
    app.updateQuality();
    assertEquals(50, app.items[0].quality);
    assertEquals(-5, app.items[0].sellIn);
  }

  @Test
  void sulfurasNeverSellsOrDecreasesInQuality() {
    final Item[] items = new Item[] {new Item("Sulfuras, Hand of Ragnaros", 1, 80)};
    final GildedRose app = new GildedRose(items);
    app.updateQuality();
    assertEquals(80, app.items[0].quality);
    assertEquals(1, app.items[0].sellIn);
    app.updateQuality();
    assertEquals(80, app.items[0].quality);
    assertEquals(1, app.items[0].sellIn);
  }

  @Test
  void sulfurasNeverSellsOrDecreasesInQualityWhateverInitialValue() {
    final Item[] items = new Item[] {new Item("Sulfuras, Hand of Ragnaros", -5, 25)};
    final GildedRose app = new GildedRose(items);
    app.updateQuality();
    assertEquals(25, app.items[0].quality);
    assertEquals(-5, app.items[0].sellIn);
  }

  @Test
  void sulfurasIsMatchedByFullNameOnly() {
    final Item[] items = new Item[] {new Item("Sulfuras, Bla bla", 2, 80)};
    final GildedRose app = new GildedRose(items);
    app.updateQuality();
    assertNotEquals(80, app.items[0].quality);
    assertNotEquals(2, app.items[0].sellIn);
  }

  @Test
  void backstagePassesNormalQualityIncrementRate() {
    // GIVEN
    final int sellIn = 50;
    final int quality = 10;
    final Item[] items = new Item[] {new Item(BACKSTAGE_PASSES, sellIn, quality)};
    final GildedRose app = new GildedRose(items);

    // WHEN the concert is more than ten days away
    app.updateQuality();

    // THEN quality should increase like Aged brie (so by one)
    assertEquals(BACKSTAGE_PASSES, app.items[0].name);
    assertEquals(11, app.items[0].quality);
    assertEquals(49, app.items[0].sellIn);

    // WHEN the concert is still more than ten days away
    app.updateQuality();

    // THEN quality should still increase like Aged brie (so by one)
    assertEquals(BACKSTAGE_PASSES, app.items[0].name);
    assertEquals(12, app.items[0].quality);
    assertEquals(48, app.items[0].sellIn);
  }

  @Test
  void backstagePassesQualityIncrementRateWhenConcertWithinTenDays() {
    // GIVEN
    final int sellIn = 11;
    final int quality = 10;
    final Item[] items = new Item[] {new Item(BACKSTAGE_PASSES, sellIn, quality)};
    final GildedRose app = new GildedRose(items);

    // WHEN the concert is more than ten days away
    app.updateQuality();

    // THEN quality should increase like Aged brie (so by one)
    assertEquals(BACKSTAGE_PASSES, app.items[0].name);
    assertEquals(11, app.items[0].quality);
    assertEquals(10, app.items[0].sellIn);

    // WHEN the concert is within ten days away
    app.updateQuality();

    // THEN the quality should increase by two
    assertEquals(BACKSTAGE_PASSES, app.items[0].name);
    assertEquals(13, app.items[0].quality);
    assertEquals(9, app.items[0].sellIn);
  }

  @Test
  void backstagePassesQualityIncrementRateWhenConcertWithinFiveDays() {
    // GIVEN
    final int sellIn = 6;
    final int quality = 10;
    final Item[] items = new Item[] {new Item(BACKSTAGE_PASSES, sellIn, quality)};
    final GildedRose app = new GildedRose(items);

    // WHEN the concert is less than ten days away
    app.updateQuality();

    // THEN quality should increase by two
    assertEquals(BACKSTAGE_PASSES, app.items[0].name);
    assertEquals(12, app.items[0].quality);
    assertEquals(5, app.items[0].sellIn);

    // WHEN the concert is less than five days away
    app.updateQuality();

    // THEN the quality should increase by three
    assertEquals(BACKSTAGE_PASSES, app.items[0].name);
    assertEquals(15, app.items[0].quality);
    assertEquals(4, app.items[0].sellIn);
  }

  @Test
  void backstagePassesQualityDropsToZeroAfterTheConcert() {
    // GIVEN
    final int sellIn = 0;
    final int quality = 10;
    final Item[] items = new Item[] {new Item(BACKSTAGE_PASSES, sellIn, quality)};
    final GildedRose app = new GildedRose(items);

    // WHEN the concert has already occured
    app.updateQuality();

    // THEN the quality should drop to zero
    assertEquals(BACKSTAGE_PASSES, app.items[0].name);
    assertEquals(0, app.items[0].quality);
    assertEquals(-1, app.items[0].sellIn);
  }

  @Test
  void backstagePassesQualityNeverAbove50() {
    // GIVEN
    final int sellIn = 2;
    final int quality = 49;
    final Item[] items = new Item[] {new Item(BACKSTAGE_PASSES, sellIn, quality)};
    final GildedRose app = new GildedRose(items);

    // WHEN quality is near 50
    app.updateQuality();

    // THEN the quality should not surpass 50
    assertEquals(BACKSTAGE_PASSES, app.items[0].name);
    assertEquals(50, app.items[0].quality);
    assertEquals(1, app.items[0].sellIn);
  }

}
