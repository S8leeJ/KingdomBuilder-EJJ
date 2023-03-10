package Board;

public class Sector {
    Hex [][] Sector;
    int id;

    public Sector(int id){
        this.id = id;
        Sector = new Hex[10][10];
    }

    public void createSector(){
        if(id == 1){
            int types[][] = {{4, 1, 1, 1, 1, 1, 1, 1, 1, 1}, 
                              {4, 4, 4, 1, 1, 1, 1, 1, 4, 1},
                             {6, 6, 6, 1, 6, 6, 8, 1, 1, 4},
                              {4, 6, 6, 6, 6, 6, 3, 3, 4, 4},
                             {4, 4, 4, 6, 6, 7, 3, 3, 3, 4},
                              {2, 4, 4, 4, 6, 3, 3, 7, 3, 5},
                             {2, 2, 9, 5, 3, 3, 7, 3, 3, 5},
                              {2, 2, 5, 5, 3, 3, 2, 8, 5, 5},
                             {2, 2, 2, 5, 5, 7, 2, 2, 5, 5},
                              {2, 2, 2, 5, 7, 2, 2, 5, 5, 5}
                             };
        }
    }
}
