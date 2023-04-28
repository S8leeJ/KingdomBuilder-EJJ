package Board;

public class Board {
    public Sector one, two, three, four;
    Hex[][] board;
    LocationTiles locTile;
    double gridHeight = 31.25;
    double gridWidth = 36.25;
    int oppX[] = {-1, 0, 1, 0, -1, 1, 1, -1};
    int oppY[] = {0, -1, 0, 1, -1, -1, 1, 1};
   
    public Board(int id1, int id2, int id3, int id4){
        locTile = new LocationTiles();
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
    public boolean[][] getAvailableTower(int x, String color){
        boolean [][] avail = getAvailable(x,color);
        boolean [][] borders = new boolean[20][20];
        //top across 
        for(int c = 0; c<20; c++){
            //top across
            borders[0][c] = avail[0][c];
            //left down
            borders[c][0] = avail[c][0];
            //down accross
            borders[19][c] = avail[19][c];
            //right down
            borders[c][19] = avail[c][19];
        }
        return borders;
    }
  
    public boolean[][] getAvailable(int x, String color){
        boolean [][] avail = new boolean[20][20];
        int numAvail = 0;
        for(int i = 0; i<20; i++){
            for(int j = 0; j<20; j++){
                Hex curHex = board[i][j];
                if(curHex.getColor().equals(color)){
                    for(int l= 0; l<8; l++){
                        int toppX = oppX[l];
                        int toppY = oppY[l];                       
                        if(validBounds(toppX, toppY, i, j)){
                            System.out.println("WEEE 1 : " + l+" " + (i+toppX) + " " + (j+toppY));

                            if(valid(i+toppX, j+toppY, x)){
                               // System.out.println("WEEE 2 : " + (i+toppX + " " + (j+toppY));
                                avail[i+toppX][j+toppY] = true;
                                numAvail++;
                            }
                            
                            if(i%2!=0 && l==3){
                                l=5;
                            }
                            if(i%2 ==0 && l==5){
                                break;
                            }
                         }
                     }
                }
            }
        }
           if(numAvail == 0){
            for(int i = 0; i<20; i++){
                for(int j = 0; j<20; j++){
                    Hex curHex = board[i][j];
                    if(curHex.getType() == x && curHex.getColor().length() == 0){
                        avail[i][j] = true;
                    }
                }
            } 
        }
        return avail;
    }
    public boolean validBounds(int i, int j, int x, int y){
        if((x+i)>=0 && (y+i)>=0 && (y+j)<=19 && (x+j)<=19)
            return true;
        return false;
    }
    public boolean valid(int x, int y, int type){
        Hex curHex = board[x][y];
        if(curHex.getColor().length()==0 && curHex.getType() == type){
            return true;
        }
        return false;
    }
    
    public Hex getHex(int x, int y){
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
        //System.out.println(row + " " + column);
        board[row][column].setXY(row, column);
       // System.out.println("JO:AWE"+board[row][column].getX()+" " + board[row][column].getY());
        return board[row][column];
    }   
}
