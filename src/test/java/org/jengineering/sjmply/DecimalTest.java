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

import net.jqwik.api.ForAll;
import net.jqwik.api.Property;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;


public class DecimalTest
{
  @Property( tries = 100_000_000 ) void test_read( @ForAll double x ) throws IOException {
    assertThat( Decimal.read(Double.toString(x)) ).isEqualTo(x);
  }
}