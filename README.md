# Alien Maze
Generates a random 4 dimensional maze for aliens! The four dimensions are time, z, y, and x.

## Cells
- Each cell has a location `(t, z, y, x)` and a byte representing which walls are open or not
- Every 2 bit pair in the byte represents a coordinate's direction
  - the first bit represents the coordinate's negative direction
  - the second bit represents the coordinate's positive direction

`00` means the alien can move in both of the coordinate's directions

`01` means the alien can move in the coordinate's negative direction

`10` means the alien can move in the coordinate's positive direction

`11` means the alien can't move in any of the coordinate's directions

### Example
`01001011` is a byte where walls prevent movement in the *x*-direction, movement is possible in the positive *y*-direction and negative *t*-direction, and all movement is possible in the *z*-direction.
