package com.gildedrose;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class GildedRoseTest {
	private static final String ITEM_NAME = "item1";

    @Test
    void testItemNameDoesNotChangeWhenUpdatingQuality() {
        final Item[] items = new Item[] { new Item(ITEM_NAME, 0, 0) };
        final GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(ITEM_NAME, app.items[0].name);
    }

}
