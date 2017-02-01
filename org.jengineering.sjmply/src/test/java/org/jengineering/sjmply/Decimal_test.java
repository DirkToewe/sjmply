package org.jengineering.sjmply;
/* Copyright 2016 Dirk Toewe
 * 
 * This file is part of org.jengineering.sjmply.
 *
 * org.jengineering.sjmply is free software: you can redistribute it and/or
 * modify it under the terms of the GNU General Public License as published
 * by the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * org.jengineering.sjmply is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY
 * or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU General Public License
 * for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with org.jengineering.sjmply. If not, see <http://www.gnu.org/licenses/>.
 */

import static org.junit.gen5.api.Assertions.assertEquals;
import static org.junit.gen5.api.Assertions.assertTrue;

import java.io.IOException;
import java.util.Random;

import org.jengineering.sjmply.Decimal;
import org.junit.gen5.api.Test;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;

import static java.lang.System.out;

@RunWith(JUnitPlatform.class)
public class Decimal_test
{
  public static void main( String... args ) throws IOException
  {
    out.println("test_large");
    test_large();
    out.println("test_small");
    test_small();
    out.println("test_random");
    test_random();
    out.println("test_inf");
    test_inf();
    out.println("test_zero");
    test_zero();
    out.println("test_nan");
    test_nan();
    out.println("DONE!");
  }

  @Test public static void test_large() throws IOException
  {
    double
      large0 = Double.NEGATIVE_INFINITY,
      large1 = Double.POSITIVE_INFINITY;
    for( int j=0; j <= 128*1024*1024; j++ )
    {
      large0 = Math.nextUp  (large0);
      large1 = Math.nextDown(large1);
      assertEquals( large0, Decimal.read( Double.toString(large0) ) );
      assertEquals( large1, Decimal.read( Double.toString(large1) ) );
    }
  }

  @Test public static void test_small() throws IOException
  {
    double
      small0 = 0,
      small1 = 0;
    for( int j=0; j <= 128*1024*1024; j++ )
    {
      small0 = Math.nextDown(small0);
      small1 = Math.nextUp  (small1);
      assertEquals( small0, Decimal.read( Double.toString(small0) ) );
      assertEquals( small1, Decimal.read( Double.toString(small1) ) );
    }
  }

  @Test public static void test_random() throws IOException
  {
    Random rng = new Random();
    for( int j=0; j <= 128*1024*1024; j++ )
    {
      double rand = rng.nextDouble();
      assertEquals( rand, Decimal.read( Double.toString(rand) ) );
    }
  }

  @Test public static void test_inf() throws IOException
  {
    double
      inf_pos = Decimal.read("+INF"),
      inf_neg = Decimal.read("-iNF");
    assertTrue( Double.isInfinite(inf_pos) );
    assertTrue( Double.isInfinite(inf_neg) );
    assertTrue( 0 < inf_pos );
    assertTrue( 0 > inf_neg );
    inf_pos = Decimal.read("+INFinItY");
    inf_neg = Decimal.read("-iNFiniTy");
    assertTrue( Double.isInfinite(inf_pos) );
    assertTrue( Double.isInfinite(inf_neg) );
    assertTrue( 0 < inf_pos );
    assertTrue( 0 > inf_neg );
  }

  @Test public static void test_zero() throws IOException
  {
    assertTrue( Double.compare( -0.0, Decimal.read("+0"  ) ) < 0 );
    assertTrue( Double.compare( +0.0, Decimal.read("-0"  ) ) > 0 );
    assertTrue( Double.compare( -0.0, Decimal.read("+0.0") ) < 0 );
    assertTrue( Double.compare( +0.0, Decimal.read("-0.0") ) > 0 );
  }

  @Test public static void test_nan() throws IOException
  {
    assertTrue( Double.isNaN( Decimal.read("NaN") ) );
    assertTrue( Double.isNaN( Decimal.read("+NAN") ) );
    assertTrue( Double.isNaN( Decimal.read("-NAN") ) );
  }
}