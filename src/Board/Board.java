package Board;

public class Board {
    Sector one, two, three, four;
    Hex[][] board;
    public Board(int id1, int id2, int id3, int id4){
        one = new Sector(id1);
        two = new Sector(id2);
        three = new Sector(id3);
        four = new Sector(id4);
        board = new Hex[20][20];
        combineSectors();
    }
    public Hex[][] getHexes(){
        return board;
    }
    public void combineSectors(){
        for(int c = 0; c < 20; c++){
            for(int d = 0; d < 20; d++){
                if(c < 10){
                    if(d < 10){
                        board[c][d] = one.getHex(c, d);
                    }
                    if(d >= 10){
                        board[c][d] = two.getHex(c, d%10);
                    }
                }
                else if (c >= 10){
                    if(d < 10){
                        board[c][d] = three.getHex(c % 10, d);
                    }
                    if(d >= 10){
                        board[c][d] = four.getHex(c % 10, d%10);
                    }
                }
            }
        }
    }

    public Hex getHex(int x, int y, double gridHeight, double gridWidth){
        y -=19;
        x-=515;
        // Find the row and column of the box that the point falls in.
        int row = Math.min((int) (y / gridHeight), 19);
        //if(row < 0) row = 0;
        int column;
        int c = 10;
        double m = c / (gridWidth/2);
        boolean rowIsOdd = row % 2 == 1;

        // Is the row an odd number?
        if (rowIsOdd)// Yes: Offset x to match the indent of the row
            column = Math.min((int) ((x - gridWidth/2) / gridWidth), 19);
        else// No: Calculate normally
            column = Math.min((int) (x / gridWidth), 19);
        // Work out the position of the point relative to the box it is in
        double relY = y - (row * gridHeight);
        double relX;
    
        if (rowIsOdd)
            relX = (x - (column * gridWidth)) - gridWidth/2;
        else
            relX = x - (column * gridWidth);

        // Work out if the point is above either of the hexagon's top edges
        if (relY < (-m * relX) + c) // LEFT edge
        {
            row--;
            if (!rowIsOdd) column--;
        }
        else if (relY < (m * relX) - c) // RIGHT edge
        {
            row--;
            if (rowIsOdd) column++;
        }
        if(row < 0) row = 0;
        if(column < 0) column = 0;
        System.out.println(row + " " + column);
        return board[row][column];
    }   
    
}
