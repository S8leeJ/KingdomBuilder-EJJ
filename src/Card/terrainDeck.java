package Card;
import java.util.*;
public class TerrainDeck {
    private ArrayList<TerrainCard> terrains;

    public TerrainDeck(){
        terrains = new ArrayList<>();
        resetTerrainDeck();
    }
    public void resetTerrainDeck(){
        for(int i = 1; i<6; i++){
            for(int j = 0; j<4; j++){
                terrains.add(new TerrainCard(i));
            }
        }
        Collections.shuffle(terrains);
        for(TerrainCard card : terrains){
            System.out.print(card.type + " ");
        }
    }
    public TerrainCard getNext(){
        return terrains.remove(0);
    }
}
