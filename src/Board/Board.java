package Board;

public class Board {
    public Sector one, two, three, four;
    Hex[][] board;
    double gridHeight = 31.25;
    double gridWidth = 36.25;
    int oppX[] = {-1, 0, 1, 0, -1, 1, 1, -1};
    int oppY[] = {0, -1, 0, 1, -1, -1, 1, 1};
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
    public boolean[][] getAvailablePaddock(int x, int y){
        boolean[][] avail = new boolean[20][20];
        int[] xNums = {-2, -2, 0, 0, 2, 2};
        int[] yNums = {-1, 1, -2, 2, -1, 1};   
        int count = 0;
        for(int c = 0; c < 6; c++){
            if(validBounds(x + xNums[c], y + yNums[c])){
                if(board[x + xNums[c]][ y + yNums[c]].getType() < 6 && board[x + xNums[c]][ y + yNums[c]].getColor().equals("")){
                    avail[x + xNums[c]][y + yNums[c]] = true;
                    count++;
                }
            }
        }
        if(count == 0) return new boolean[1][1];
        else return avail;
    }
    public boolean[][] getAvailableTower(int x, String color){
        boolean [][] avail = getAvailable(x,color);
        boolean [][] borders = new boolean[20][20];
        
        System.out.println("AWDAWD" + x + " " + color);
        //top across 
        int count = 0;
        for(int c = 0; c<20; c++){
            //top across
            borders[0][c] = avail[0][c];
            if(borders[0][c]) count++;
            //left down
            borders[c][0] = avail[c][0];
            if(borders[c][0]) count++;

            //down accross
            borders[19][c] = avail[19][c];
            if(borders[19][c]) count++;

            //right down
            borders[c][19] = avail[c][19];
            if(borders[c][19]) count++;

        }
        if(count!=0)
        return borders;
        if(count==0){
            for(int c = 0; c<20; c++){
                //top across
                if(board[0][c].getType() == x && board[0][c].getColor().equals("")){
                    borders[0][c] = true;
                }
                //left down
                if(board[c][0].getType() == x && board[c][0].getColor().equals(""))
                borders[c][0] = true;
    
                //down accross
                if(board[19][c].getType() == x && board[19][c].getColor().equals(""))
                borders[19][c] = true;
    
                //right down
                if(board[c][19].getType() == x && board[c][19].getColor().equals(""))
                borders[c][19] = true;
                }   
            }
        return borders;
    }
  
    public boolean[][] getAvailableTavern(String color){
        boolean [][] avail = new boolean[20][20];
        int curTavernCount = 0;

        //top left, top right, mid left, mid right, bottom l, bottom r
        int[] xNumsEven1 = {-1, -1, 0, 0, 1, 1};
        int[] yNumsEven1 = {-1, 0, -1, 1, -1, 0};

        int[] xNumsOdd1 = {-1, -1, 0, 0, 1, 1};
        int[] yNumsOdd1 = {0, 1, -1, 1, 0, 1};
       
        int[] xNums2 = {-2, -2, 0, 0, 2, 2};
        int[] yNums2 = {-1, 1, -2, 2, -1, 1};   
       
        int[] xNumsOdd3 = {-3, -3, 0, 0, 3, 3};
        int[] yNumsOdd3= {-1, 2, -3, 3, -1, 2};

        int[] xNumsEven3 = {-3, -3, 0, 0, 3, 3};
        int[] yNumsEven3 = {-2, 1, -3, 3, -2, 1};
        //do something to check if tehre are no available 3 rows
        for(int i = 0; i<20; i++){
            for(int j = 0; j<20; j++){
                Hex curHex = board[i][j];
                    if(color.equals(curHex.getColor())){ // if hex has the players settlement
                        if(i % 2 == 0){
                            for(int c = 0; c < 6; c++){
                                int x = i+ xNumsEven1[c];
                                int y = j + yNumsEven1[c];
                                if(validBounds(x, y) && board[x][y].getColor().equals(color)){
                                     x = i+ xNums2[c];
                                     y = j + yNums2[c];
                                    if(validBounds(x, y) && board[x][y].getColor().equals(color)){
                                        x = i+ xNumsEven3[c];
                                        y = j + yNumsEven3[c];
                                        if(validBounds(x, y) && board[x][y].getColor().equals("") && board[x][y].getType() < 6){
                                            avail[x][y] = true;
                                            curTavernCount++;
                                        }
                                    }
                                }
                            }
                        }
                        if(i % 2 == 1){
                            for(int c = 0; c < 6; c++){
                                int x = i+ xNumsOdd1[c];
                                int y = j + yNumsOdd1[c];
                                if(validBounds(x, y) && board[x][y].getColor().equals(color)){
                                     x = i+ xNums2[c];
                                     y = j + yNums2[c];
                                    if(validBounds(x, y) && board[x][y].getColor().equals(color)){
                                        x = i+ xNumsOdd3[c];
                                        y = j + yNumsOdd3[c];
                                        if(validBounds(x, y) && board[x][y].getColor().equals("") && board[x][y].getType() < 6){
                                            avail[x][y] = true;
                                            curTavernCount++;

                                        }
                                    }
                                }
                            }
                        
                        }
                    }
                 }
             }

            if(curTavernCount>0) return avail;
            else{
               return new boolean[1][1];
            }
        }
    
    public boolean[][] getAvailableBarnHarb(String color){
            boolean [][] avail = new boolean[20][20];
            for(int i = 0; i<20; i++){
                for(int j = 0; j<20; j++){
                    Hex curHex = board[i][j];
                    if(curHex.getColor().equals(color)){
                       avail[i][j] = true;
                    }
                }
            }
            return avail;
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
                        if(validBounds(toppX+i, toppY+j)){
                            if(valid(i+toppX, j+toppY, x)){
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
    public boolean validBounds(int i, int j){
        if(i>=0 && j>=0 && j<=19 && i<=19)
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
        board[row][column].setXY(row, column);
        return board[row][column];
    }   
}
