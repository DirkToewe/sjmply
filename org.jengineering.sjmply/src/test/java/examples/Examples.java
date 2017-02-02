package examples;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;

import org.jengineering.sjmply.PLY;
import org.jengineering.sjmply.PLYElementList;
import org.jengineering.sjmply.PLY_Plotly;
import static org.jengineering.sjmply.PLYType.*;
import static org.jengineering.sjmply.PLYFormat.*;

public class Examples
{
  public static void main( String... args ) throws IOException
  {
    loading();
//    visualize_mesh();
//    visualize_point_cloud();
//    access_elements_and_properties();
//    convert_properties();
//    write_a_file();
//    create_a_ply();
//    add_element_list();
//    add_property();
//    changing_output_format();
  }

  public static void loading() throws IOException
  {
    Path path = Paths.get( System.getProperty("user.home"), "Documents/3d_models/bunny.ply" );
    PLY bunny = PLY.load(path);
    System.out.println(bunny);
  }

  public static void visualize_mesh() throws IOException
  {
    Path path = Paths.get( System.getProperty("user.home"), "Documents/3d_models/bunny.ply" );
    PLY bunny = PLY.load(path);
    PLY_Plotly.Mesh3d_show("Bunneh",bunny);
  }

  public static void visualize_point_cloud() throws IOException
  {
    Path path = Paths.get( System.getProperty("user.home"), "Documents/3d_models/bunny.ply" );
    PLY bunny = PLY.load(path);
    PLY_Plotly.Scatter3d_show("Bunneh",bunny);
  }

  public static void access_elements_and_properties() throws IOException
  {
    Path path = Paths.get( System.getProperty("user.home"), "Documents/3d_models/bunny.ply" );
    PLY bunny = PLY.load(path);
    PLYElementList
      vertex = bunny.elements("vertex"),
      face = bunny.elements("face");
    System.out.println(vertex);
    System.out.println(face);
    float[] x = vertex.property(FLOAT32,"x");
    int[][] vertex_indices = face.property(LIST(INT32),"vertex_indices");
    System.out.println( Arrays.toString(x) );
    System.out.println( Arrays.deepToString(vertex_indices) );
    double[] x_double = vertex.propertyAs(FLOAT64,"x");
    short[][] vertex_indices_short = face.propertyAs(LIST(UINT16),"vertex_indices");
    System.out.println( Arrays.toString(x_double) );
    System.out.println( Arrays.deepToString(vertex_indices_short) );
  }

  public static void convert_properties() throws IOException
  {
    Path path = Paths.get( System.getProperty("user.home"), "Documents/3d_models/bunny.ply" );
    PLY bunny = PLY.load(path);
    bunny.elements("face").convertProperty( "vertex_indices", LIST(UINT8,UINT16) );
    System.out.println( bunny.elements("face") );
  }

  public static void write_a_file() throws IOException
  {
    Path path = Paths.get( System.getProperty("user.home"), "Documents/3d_models/bunny.ply" );
    PLY bunny = PLY.load(path);
    Path out = Paths.get("/tmp/bunny.ply");
    bunny.save(out);
  }

  public static void create_a_ply() throws IOException
  {
    PLY ply = new PLY();
    System.out.println(ply);
  }

  public static void add_element_list() throws IOException
  {
    PLY ply = new PLY();
    PLYElementList edge = new PLYElementList(1337);
    ply.elements.put("edge",edge);
    System.out.println(ply);
  }

  public static void add_property() throws IOException
  {
    PLY ply = new PLY();
    PLYElementList edge = new PLYElementList(1337);
    ply.elements.put("edge",edge);
    edge.addProperty(LIST(UINT8,UINT32),"vertex_indices");
    System.out.println(ply);
  }

  public static void changing_output_format() throws IOException
  {
    Path path = Paths.get( System.getProperty("user.home"), "Documents/3d_models/bunny.ply" );
    PLY bunny = PLY.load(path);
    bunny.setFormat(ASCII);
    System.out.println(bunny);
  }
}