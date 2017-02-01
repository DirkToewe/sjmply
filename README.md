# sjmply

sjmply is a simple Java-based model for [PLY files](https://en.wikipedia.org/wiki/PLY_(file_format)).

## Reading a PLY File
```Java
import java.nio.file.Path;
import java.nio.file.Paths;
import org.jengineering.sjmply.PLY;

Path path = Paths.get( System.getProperty("user.home"), "Documents/3d_models/bunny.ply" );
PLY bunny = PLY.load(path);
System.out.println(bunny);
```

## Visualizing a PLY File
sjmply offers convenience methods that generate standalone HTML visualizations of a PLY file,
either as triangle mesh or point cloud (scatter plot). The visualization uses [Plotly](Plot.ly).
As of now these methods are only meant for debugging and quick visualization. They do not support
the full configurability and features that Plotly itself offers.

In order to minimize the HTML file size, the data is compressed as zip archive.
[JSZip](https://stuk.github.io/jszip/) is used to decompress the data during loading.

### Mesh
```Java
import org.jengineering.sjmply.PLY_Plotly;

PLY_Plotly.Mesh3d_show("Bunneh",bunny);
```

### Point Cloud
```Java
import org.jengineering.sjmply.PLY_Plotly;

PLY_Plotly.Scatter3d_show("Bunneh",bunny);
```

## Accessing Elements and Properties
The data in PLY files is organized in element lists. In an element list, all elements
have the same properties. The element lists and their properties are declared in the
file header. The format can be extended with additional element lists and properties.

Supported property types are:
  * int8 (char)
  * int16 (short)
  * int32 (int)
  * uint8 (uchar)
  * uint16 (ushort)
  * uint32 (uint)
  * float32 (float)
  * float64 (double)
  * list

The list type has a size type and an element type that need to be declare alongside it,
e.g. `property list uint8 uint32 vertex_indices` is a property of name `vertex_indices`
whose type is a list with a size of type `uint8` and an element type `uint32`. It is
unclear to the author of sjmply whether or not nested lists (e.g. `list uint8 list uint8 float32`)
are allowed according to the PLY file specification. sjmply however supports them,
other PLY file libraries might not.

## Writing a PLY File

## Creating a PLY File

## Adding an Element List

## Adding a Property

## Converting a Property

## Changing the Output Format
