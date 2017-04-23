package edu.hm.cs.swa.projekt_2.persistence;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by christian on 23.04.17.
 */
public class DataStoreTest {
    @Test
    public void getBooks() throws Exception {
        Assert.assertNotNull(DataStore.INSTANCE.getBooks());
        Assert.assertEquals(0, DataStore.INSTANCE.getBooks().length);
    }

    @Test
    public void getDiscs() throws Exception {
        Assert.assertNotNull(DataStore.INSTANCE.getDiscs());
        Assert.assertEquals(0, DataStore.INSTANCE.getDiscs().length);
    }

    @Test
    public void getDisc() throws Exception {
    }

    @Test
    public void getCopies() throws Exception {
    }

    @Test
    public void addBook() throws Exception {
    }

    @Test
    public void getBook() throws Exception {
    }

    @Test
    public void addCopy() throws Exception {
    }

    @Test
    public void addDisc() throws Exception {
    }

}