package Card;
import java.util.*;
public class terrainDeck {
    private ArrayList<TerrainCard> terrains;

    public terrainDeck(){
        terrains = new ArrayList<>();
        resetTerrainDeck();
    }
    public void resetTerrainDeck(){
        for(int i = 1; i<6; i++){
            for(int j = 0; j<5; j++){
                terrains.add(new TerrainCard(i));
            }
        }
        Collections.shuffle(terrains);
    }
    public TerrainCard getNext(){
        return terrains.remove(0);
    }
    public boolean empty(){
        return terrains.size() == 0;
    }
    public int getSize(){
        return terrains.size();
    }
}
