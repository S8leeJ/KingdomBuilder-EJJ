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
            int types[][] = {{1,2}, {2,3}};
        }
    }
}
