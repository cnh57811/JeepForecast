package com.cgavlabs.jeepforecast;

import org.junit.Ignore;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class UtilsTest {

  @Test public void testRoundDouble() throws Exception {
    final String error = "Invalid rounding";

    String value_0 = Utils.roundDouble(-0.4);
    assertEquals(error, "0", value_0);

    String value0 = Utils.roundDouble(0.4);
    assertEquals(error, "0", value0);

    String value1 = Utils.roundDouble(0.5);
    assertEquals(error, "1", value1);

    String value3 = Utils.roundDouble(3.33333333);
    assertEquals(error, "3", value3);
  }

  @Ignore
  @Test public void testGetStartOfTodayInSeconds() throws Exception {
    assertEquals("NOT YET TESTED", 1, 2);
  }

  @Ignore
  @Test public void testGetEndOfTodayInSeconds() throws Exception {
    assertEquals("NOT YET TESTED", 1, 2);
  }
}