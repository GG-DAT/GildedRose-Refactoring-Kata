package com.gildedrose;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

class GildedRoseTest {
	private static final String ITEM_NAME = "item1";

    @Test
    void testItemNameDoesNotChangeWhenUpdatingQuality() {
    	// GIVEN
    	final Item item1 = new Item(ITEM_NAME, 0, 0);
        final Item[] items = new Item[] { item1 };
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
        final Item[] items = new Item[] { item1 };
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
        final Item[] items = new Item[] { item1 };
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
        final Item[] items = new Item[] { item1 };
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
        final Item[] items = new Item[] { item1 };
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
        final Item[] items = new Item[] { item1 };
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
        final Item[] items = new Item[] { item1 };
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
        final Item[] items = new Item[] { item1 };
    	final GildedRose app = new GildedRose(items);
        
    	// WHEN
    	app.updateQuality();
    	
    	// THEN
        assertEquals(0, app.items[0].quality);
    }

    @Test
    void agedBrieIncreasesInQualityTheOlderItGets() {
        Item[] items = new Item[] { new Item("Aged Brie", 5, 25) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(26, app.items[0].quality);
        assertEquals(4, app.items[0].sellIn);
        app.updateQuality();
        assertEquals(27, app.items[0].quality);
        assertEquals(3, app.items[0].sellIn);
    }

    @Test
    void agedBrieQualityDoesNotIncreaseAbove50() {
        Item[] items = new Item[] { new Item("Aged Brie", 5, 49) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(50, app.items[0].quality);
        assertEquals(4, app.items[0].sellIn);
        app.updateQuality();
        assertEquals(50, app.items[0].quality);
        assertEquals(3, app.items[0].sellIn);
    }
    
    @Test
    void agedBrieQualityIncreasesTwiceAsFastAfterSellDatePassed() {
        Item[] items = new Item[] { new Item("Aged Brie", 1, 25) };
        GildedRose app = new GildedRose(items);
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
    void sulfurasNeverSellsOrDecreasesInQuality() {
        Item[] items = new Item[] { new Item("Sulfuras, Hand of Ragnaros", 1, 80) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(80, app.items[0].quality);
        assertEquals(1, app.items[0].sellIn);
        app.updateQuality();
        assertEquals(80, app.items[0].quality);
        assertEquals(1, app.items[0].sellIn);
    }
    
    @Test
    void sulfurasNeverSellsOrDecreasesInQualityWhateverInitialValue() {
        Item[] items = new Item[] { new Item("Sulfuras, Hand of Ragnaros", -5, 25) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(25, app.items[0].quality);
        assertEquals(-5, app.items[0].sellIn);
    }
    
    @Test
    void sulfurasIsMatchedByFullNameOnly() {
        Item[] items = new Item[] { new Item("Sulfuras, Bla bla", 2, 80) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertNotEquals(80, app.items[0].quality);
        assertNotEquals(2, app.items[0].sellIn);
    }

}
