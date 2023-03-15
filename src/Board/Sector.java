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
    public void createSector(){
     //   if(id == 1){
            int temp[][] = {{4, 1, 1, 1, 1, 1, 1, 1, 1, 1}, 
                              {4, 4, 4, 1, 1, 1, 1, 1, 4, 1},
                             {6, 6, 6, 1, 6, 6, 9, 1, 1, 4},
                              {4, 6, 6, 6, 6, 6, 3, 3, 4, 4},
                             {4, 4, 4, 6, 6, 7, 3, 3, 3, 4},
                              {2, 4, 4, 4, 6, 3, 3, 7, 5, 4},
                             {2, 2, 9, 5, 3, 3, 7, 3, 3, 5},
                              {2, 2, 5, 5, 3, 3, 2, 8, 5, 5},
                             {2, 2, 2, 5, 5, 7, 2, 2, 5, 5},
                              {2, 2, 2, 5, 7, 2, 2, 5, 5, 5}
                             };
            types = temp;
            
            returnToHex(types);
            setLocType();
       // }
        //1. Desert 
        // 2. Grass 
        // 3. Flower 
        // 4. Canyon
        // 5. Forest
        // 6. Mountain
        // 7. Water
        // 8. Castle
        // 9. Farm (Location Tile)

        // if(id ==2){
        //     int types[][] = {{1, 1, 4, 7, 7, 5, 5, 5, 2, 2}
        //     ,{1, 8, 4, 7, 5, 5, 5, 9, 2,2}, 
        //     {4, 4, 4, 3, 3, 3, 5, 4, 3, 3}
        //     , {4, 4, 3, 3, 7, 1, 1, 4, 4, 3},
        //     {4, 2, 2, 7, 3, 3, 1, 1, 4, 4,}, {
        //         2, 2, 9, 3, 7, 3, 7, 1, 1, 4},
        //         {2, 2, 2, 5, 3, 3, 7,7, 1, 1},
        //         {2, 2, 5, 5, 6, 7, 7, 7, 1, 7},
        //         {2, 6, 5, 5, 7, 7, 7, 7, 7, 7},
        //         {5, 5, 5, 7, 7, 7, 7, 7, 7, 7, 7}};
        //     returnToHex(types);
        // }
        
        
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
