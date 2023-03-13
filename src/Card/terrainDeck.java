package Card;
import java.util.*;
public class terrainDeck {
    private ArrayList<terrain> terrains;

    public terrainDeck(){
        terrains = new ArrayList<>();
        resetTerrainDeck();
    }
    public void resetTerrainDeck(){
        for(int i = 1; i<6; i++){
            for(int j = 0; j<4; j++){
                terrains.add(new terrain(i));
            }
        }
        Collections.shuffle(terrains);
    }
    public terrain getNext(){
        return terrains.remove(0);
    }
}
