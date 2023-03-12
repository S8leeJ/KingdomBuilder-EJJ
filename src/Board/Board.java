package Board;

public class Board {
    Sector one, two, three, four;
    public Board(int id1, int id2, int id3, int id4){
        one = new Sector(id1);
        two = new Sector(id2);
        three = new Sector(id3);
        four = new Sector(id4);
    }
}
