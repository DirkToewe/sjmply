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

import static java.lang.System.out;
import static java.nio.charset.StandardCharsets.US_ASCII;
import static org.jengineering.sjmply.PLYFormat.ASCII;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Map.Entry;
import java.util.stream.Stream;

import org.jengineering.sjmply.PLY;
import org.jengineering.sjmply.PLYElementList;
import org.jengineering.sjmply.PLYType;

public class PLY_test
{
  public static void main( String... args )
  {
    Path dir = Paths.get( System.getProperty("user.home"), "Documents", "3d_models" );

    Stream.of("big_porsche", "Armadillo", "blade", "bunny", "dragon", "hand", "happy", "horse", "xyzrgb_dragon")
      .parallel()
      .forEach(
         file -> {
           try {
             Path tmp = Files.createTempFile("sjmply_test_", ".ply");
             try {
               out.println(file);
               Path path = dir.resolve(file+".ply");
               PLY ply1 = PLY.load(path);
               ply1.save(tmp);
               PLY ply2 = PLY.load(tmp);
               if( ply1.getFormat() == ASCII )
                 assert Files.lines(path,US_ASCII).count() == Files.lines(tmp,US_ASCII).count();
               assert ply1.getFormat () .equals( ply2.getFormat () );
               assert ply1.getVersion() .equals( ply2.getVersion() );
               assert ply1.comments  .equals( ply2.comments  );
               assert ply1.obj_infos .equals( ply2.obj_infos );
               assert ply1.elements.keySet() .equals( ply2.elements.keySet() );
               for( String elemName: ply1.elements.keySet() )
               {
                 PLYElementList
                   elem1 = ply1.elements(elemName),
                   elem2 = ply2.elements(elemName);
                 assert elem1.properties .equals( elem2.properties );
                 for( Entry<String,PLYType<?>> prop: elem1.properties.entrySet() )
                 {
                   Object
                     property1 = elem1.property( prop.getValue(), prop.getKey() ),
                     property2 = elem2.property( prop.getValue(), prop.getKey() );
                   if( property1 instanceof Object[] )
                     assert Arrays.deepEquals( (Object[]) property1, (Object[]) property2 );
                   else
                     assert (Boolean) Arrays.class.getMethod("equals", property1.getClass(), property2.getClass() ).invoke(null,property1,property2);
                 }
               }
             }
             finally {
               Files.delete(tmp);
             }
           }
           catch( IOException | ReflectiveOperationException e ) { throw new AssertionError(e); }
         }
       );
  }
}