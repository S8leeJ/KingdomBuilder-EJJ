package Board;

public class Sector {
    Hex[][] sector;
    int id;
    int types[][];
    int locType;
    public Sector(int id){
        this.id = id;
        sector = new Hex[10][10];
        createSector();
        types = new int [10][10];
    }
    public int getLocType(){
        return locType;
    }
    /*
     *     else if (x == 10){
            return Boat;
        }
        else if (x == 11){
            return Estate;
        }
        else if (x == 12){
            return Horse;
        }
        else if (x == 13){
            return Oasis;
        }
        else if (x == 14){
            return Oracle;
        }
        else if (x == 15){
            return Tower;
        }
        else if (x == 16){
            return House;
        }
     */
    public void createSector(){
        if(id == 1){
            int temp[][] = {{4, 1, 1, 1, 1, 1, 1, 1, 1, 1}, 
                              {4, 4, 4, 1, 1, 1, 1, 1, 4, 1},
                             {6, 6, 6, 1, 6, 6, 11, 1, 1, 4},
                              {4, 6, 6, 6, 6, 6, 3, 3, 4, 4},
                             {4, 4, 4, 6, 6, 7, 3, 3, 3, 4},
                              {2, 4, 4, 4, 6, 3, 3, 7, 5, 4},
                             {2, 2, 11, 5, 3, 3, 7, 3, 3, 5},
                              {2, 2, 5, 5, 3, 3, 2, 8, 5, 5},
                             {2, 2, 2, 5, 5, 7, 2, 2, 5, 5},
                              {2, 2, 2, 5, 7, 2, 2, 5, 5, 5}
                             };
            types = temp;
            returnToHex(temp);
        }  
        if(id ==2){
            int temp[][] = {{1, 1, 4, 7, 7, 5, 5, 5, 2, 2}
            ,{1, 8, 4, 7, 5, 5, 5, 9, 2,2}, 
            {4, 4, 4, 3, 3, 3, 5, 4, 3, 3}
            , {4, 4, 3, 3, 7, 1, 1, 4, 4, 3},
            {4, 2, 2, 7, 3, 3, 1, 1, 4, 4,}, {
                2, 2, 9, 3, 7, 3, 7, 1, 1, 4},
                {2, 2, 2, 5, 3, 3, 7,7, 1, 1},
                {2, 2, 5, 5, 6, 7, 7, 7, 1, 7},
                {2, 6, 5, 5, 7, 7, 7, 7, 7, 7},
                {5, 5, 5, 7, 7, 7, 7, 7, 7, 7, 7}};
            returnToHex(temp);
            types = temp;

        }

        if(id == 3){
            int temp[][] = {{5, 5, 5, 5, 6, 6, 2, 6, 4, 4}, 
            {5, 6, 5, 5, 3, 2, 6, 6, 6, 4},
            {3, 3, 5, 3, 3, 3, 2, 2, 7, 6},
             {1, 3, 3, 3, 7, 15, 2, 7,6, 6}, 
             {1, 1, 1, 1, 3, 7, 2, 7, 4, 4}, 
             {1, 4, 1, 1, 1, 7, 7, 4, 2, 4},
             {1, 1, 4, 1, 1, 7, 3, 8, 2, 4}, 
             {4, 4, 15, 1, 7, 3, 3, 3, 2, 2},
            {1, 4, 7, 7, 7, 5, 5, 3, 2, 2},
            {1, 4, 4, 7, 5, 5, 5, 2, 2, 2}};
            returnToHex(temp);
            types = temp;

        }
        if(id == 4){
            int[][] temp = {{1, 1, 4, 7, 7, 5, 5, 2, 2, 2}, 
                            {1, 4, 7, 3, 3, 5, 5, 5, 2,2 },
                            {1, 1, 7, 3, 3, 5, 5, 13, 3, 2},
                            {7, 7, 7, 3, 2, 5, 3, 3, 3, 3},
                            {7, 7, 7, 7, 2, 2, 2, 2, 3, 3},
                            {7, 5, 5, 7, 2, 2, 4, 4, 1, 4},
                            {7, 5, 4, 5, 7, 2, 4, 4, 1, 4},
                            {7, 8, 4, 3, 7, 13, 1, 1, 4, 7},
                            {7, 7, 4, 3, 7, 7, 7, 1, 1, 7},
                            {7, 7, 7, 7, 7, 7, 7, 7, 7, 7}};
            returnToHex(temp);
            types = temp;

        }
        setLocType();
        
    }
    public void returnToHex(int arr[][]){
        int [][] types = arr;
        for(int i = 0; i<10; i++){
            for(int j = 0; j<10; j++){
                sector[i][j] = new Hex(types[i][j]);
            }
        }
    }
    public Hex getHex(int x, int y){
        return sector[x][y];
    }
    public boolean[][] getAvailable(int x){
        boolean[][] avail = new boolean[10][10];
        for(int i = 0; i<10; i++){
            for(int j = 0; j<10; j++){
                if(sector[i][j].getType() == x){
                    avail[i][j] = true;
                }
            }
        } 
        return avail;
    }
    public Hex[][] getSector(){
        return sector;
    }
    public void setLocType(){
        for(int i = 0; i<10; i++){
            for(int j = 0; j<10; j++){
                if(sector[i][j].getType() > 8){
                    locType = sector[i][j].getType();
                }
            }
        }
    }
}
