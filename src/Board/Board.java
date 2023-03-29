package Board;

public class Board {
    public Sector one, two, three, four;
    Hex[][] board;
    LocationTiles locTile;
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

    public boolean[][] combineAvailable(int x, String color){
      
        int[][] combined = new int[20][20]; 
        int[][] t1 = one.getTypes();
        int[][] t2 = two.getTypes();
        int[][] t3 = three.getTypes();
        int[][] t4 = four.getTypes();

        //lets combine ^ into one big [][] array
        for(int c = 0; c < 20; c++){
            for(int d = 0; d < 20; d++){
                if(c < 10){
                    if(d < 10){
                        combined[c][d] = t1[c][d];
                    }
                    if(d >= 10){
                        combined[c][d] = t2[c][d%10];
                    }
                }
                else if (c >= 10){
                    if(d < 10){
                        combined[c][d] = t3[c%10][d];
                    }
                    if(d >= 10){
                        combined[c][d] = t4[c%10][d%10];
                    }
                }
            }
        }
        return getAvailable(combined, x, color);
    }
    public boolean[][] getAvailable(int[][] combined, int x, String color){
        boolean [][] avail = new boolean[20][20];
        int numAvail = 0;
        boolean hasSettle = false;
        for(int i = 0; i<20; i++){
            for(int j = 0; j<20; j++){
                Hex curHex = board[i][j];
                if(curHex.getColor().equals(color)){
                    hasSettle = true;
                    if(i>=1){
                        if(valid(i-1, j, x)){
                            avail[i-1][j] = true;
                            numAvail++;
                        }
                    }
                    if(j>=1){
                        if(valid(i, j-1, x)){
                            avail[i][j-1] = true;
                            numAvail++;
                        }
                    }
                    if(i<19){
                        if(valid(i+1, j, x)){
                            avail[i+1][j] = true;
                            numAvail++;
                        }
                    }
                    if(j<19){
                        if(valid(i, j+1, x)){
                            avail[i][j+1] = true;
                            numAvail++;
                        }
                    }
            
                    if(i%2 == 0){
                        if(i>=1 && j>=1){
                            if(valid(i-1, j-1, x)){
                                numAvail++;
                                avail[i-1][j-1] = true;
                            }
                        }
                        if(i<19 && j>=1){
                            if(valid(i+1, j-1, x)){
                                numAvail++;
                                avail[i+1][j-1] = true;
                            }
                        }
                    }
                    else{
                        if(i<19 && j<19){
                            if(valid(i+1, j+1, x)){
                                numAvail++;
                                avail[i+1][j+1] = true;
                            } 
                    }
                        if(i>=1 && j<19){
                            if(valid(i-1, j+1, x)){
                                numAvail++;
                                avail[i-1][j+1] = true;
                            }
                        }
                    }
                }
            }
        }
        //make an int and increment it every time 
        //if player has no settlementes / int is 0
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
    public boolean valid(int x, int y, int type){
        Hex curHex = board[x][y];
        if(curHex.getColor().length()==0 && curHex.getType() == type){
            return true;
        }
        return false;
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
        board[row][column].setXY(row, column);
       // System.out.println("JO:AWE"+board[row][column].getX()+" " + board[row][column].getY());
        return board[row][column];
    }   
}
