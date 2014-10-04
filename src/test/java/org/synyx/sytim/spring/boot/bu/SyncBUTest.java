/*
 * 01.08.2014
 */
package org.synyx.sytim.spring.boot.bu;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.synyx.sytim.spring.boot.SyTim;

/**
 * Tests for all sync logic.
 *
 * @author Joachim Arrasz synyx GmbH & Co. KG
 */
@RunWith(SpringJUnit4ClassRunner.class)

//@ContextConfiguration
@SpringApplicationConfiguration(classes = SyTim.class)
public class SyncBUTest {

    public SyncBUTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of writeSyncDataToPersistence method, of class SyncBU.
     */
    @Test
    public void testWriteSyncDataToPersistence() {

        System.out.println("writeSyncDataToPersistence");

        String content = "";

        String ident = "";
    }
}
