package com.nervii.brutuscrypt;

import android.test.AndroidTestCase;
import android.test.InstrumentationTestCase;

import com.nervii.brutuscrypt.WordList;

import junit.framework.TestCase;

/**
 * Created by Brett on 7/6/2015.
 */
public class WordListTest extends InstrumentationTestCase {

    public void testGetInstance() throws Exception {
        WordList instance = WordList.getInstance();
        assertNotNull(instance);
    }

    public void testIsWordInList() throws Exception {

    }
}