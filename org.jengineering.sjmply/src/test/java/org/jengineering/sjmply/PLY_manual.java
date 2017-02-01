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
import static java.util.Arrays.asList;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.jengineering.sjmply.PLY;
import org.jengineering.sjmply.PLY_Plotly;

/** Manual, i.e. non-automated tests of the {@link PLY} class.
 *  
 *  @author Dirk Toewe
 */
public class PLY_manual
{
// STATIC FIELDS

// STATIC CONSTRUCTOR

// STATIC METHODS
  public static void main( String... args ) throws IOException, InterruptedException
  {
    Path dir = Paths.get( System.getProperty("user.home"), "Documents", "3d_models" );

    for( String file: asList("dragon") )//"big_porsche", "Armadillo", "blade", "bunny", "dragon", "hand", "happy", "horse", "xyzrgb_dragon") )
    {
      Path tmp = Files.createTempFile("sjmply_test_", ".ply");
      try {
        out.println(file);
        Path path = dir.resolve(file+".ply");
        PLY ply1 = PLY.load(path);
        ply1.save(tmp);
        PLY ply2 = PLY.load(tmp);
        PLY_Plotly.Mesh3d_show(file,ply2);
        Thread.sleep(4000);
      }
      finally {
        Files.delete(tmp);
      }
    }
  }
}