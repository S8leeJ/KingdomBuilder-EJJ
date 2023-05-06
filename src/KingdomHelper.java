import java.awt.*;
import java.awt.image.*;
import javax.imageio.ImageIO;
import Board.Hex;
import Board.LocationTiles;
import Game.Game;
import Game.Player;
import ObjectiveCards.ObjectiveCard;

import java.util.*;
import java.util.Map.Entry;

public class KingdomHelper {
    private static BufferedImage sector1,sector2, gray, sector3, sector4, sector5, sector6, sector7, sector8, hexagon, blackhouse, bluehouse, orangehouse, whitehouse, backTerrain, locOne, locTwo, locations;
  	public int hexwidth = 38, hexlength = 44;
	Game game;
	public static BufferedImage Citizens, Discoverers, Farmers, Fishermen, Hermits, Knights, Lords, Merchants, Miners, Workers, startingToken;
   	public ArrayList<String> objectiveNames;
   	public ArrayList<BufferedImage> objectives;	
    public KingdomHelper(Game game){
		this.game = game;
    	objectives = new ArrayList<>();
    	objectiveNames = new ArrayList<>();
    	Collections.addAll(objectiveNames,  "Discoverers", "Farmers", "Fishermen","Knights", "Lords", "Miners", "Workers");
        try {
            sector2 = ImageIO.read(getClass().getResourceAsStream("/Board/BoardImages/sector2.png"));
			sector3 = ImageIO.read(getClass().getResourceAsStream("/Board/BoardImages/sector3.png"));
			sector4 = ImageIO.read(getClass().getResourceAsStream("/Board/BoardImages/sector4.png"));
			sector5 = ImageIO.read(getClass().getResourceAsStream("/Board/BoardImages/sector5.png"));
			sector6 = ImageIO.read(getClass().getResourceAsStream("/Board/BoardImages/sector6.png"));
			sector7 = ImageIO.read(getClass().getResourceAsStream("/Board/BoardImages/sector7.png"));
			sector8 = ImageIO.read(getClass().getResourceAsStream("/Board/BoardImages/sector8.png"));
			blackhouse = ImageIO.read(getClass().getResourceAsStream("/Board/Images/blackhouse.png"));
			bluehouse = ImageIO.read(getClass().getResourceAsStream("/Board/Images/bluehouse.png"));
			orangehouse = ImageIO.read(getClass().getResourceAsStream("/Board/Images/orangehouse.png"));
			whitehouse = ImageIO.read(getClass().getResourceAsStream("/Board/Images/whitehouse.png"));
			sector1 = ImageIO.read(getClass().getResourceAsStream("/Board/BoardImages/sector1.png"));
			hexagon = ImageIO.read(getClass().getResourceAsStream("/Board/Images/hexagon.png"));
			backTerrain =  ImageIO.read(getClass().getResourceAsStream("/Card/TerrainImages/KB-Card-Back.png"));
			locOne =  ImageIO.read(getClass().getResourceAsStream("/Board/Images/1.png"));
			locTwo =  ImageIO.read(getClass().getResourceAsStream("/Board/Images/2.png"));
			locations = ImageIO.read(getClass().getResourceAsStream("/Board/Images/Locations.PNG"));
			gray = ImageIO.read(getClass().getResourceAsStream("/Board/Images/darkrect.png"));
			Citizens =  ImageIO.read(getClass().getResourceAsStream("/ObjectiveCards/CardImages/CitizensObjective.png"));
			Discoverers =  ImageIO.read(getClass().getResourceAsStream("/ObjectiveCards/CardImages/DiscoverersObjective.png"));
			Farmers =  ImageIO.read(getClass().getResourceAsStream("/ObjectiveCards/CardImages/FarmersObjective.png"));
			Fishermen =  ImageIO.read(getClass().getResourceAsStream("/ObjectiveCards/CardImages/FishermenObjective.png"));
			Hermits =  ImageIO.read(getClass().getResourceAsStream("/ObjectiveCards/CardImages/HermitsObjective.png"));
			Knights =  ImageIO.read(getClass().getResourceAsStream("/ObjectiveCards/CardImages/KnightsObjective.png"));
			Lords =  ImageIO.read(getClass().getResourceAsStream("/ObjectiveCards/CardImages/LordsObjective.png"));
			Merchants  =  ImageIO.read(getClass().getResourceAsStream("/ObjectiveCards/CardImages/MerchantsObjective.png"));
			Miners =  ImageIO.read(getClass().getResourceAsStream("/ObjectiveCards/CardImages/MinersObjective.png"));
			Workers =  ImageIO.read(getClass().getResourceAsStream("/ObjectiveCards/CardImages/WorkersObjective.png"));
			startingToken = ImageIO.read(getClass().getResourceAsStream("/Board/Images/startingToken.png"));
	
        } catch (Exception e) {
            System.out.println("oopsies");// TODO: handle exception
        }
		Collections.addAll(objectives, Discoverers, Farmers, Fishermen, Knights, Lords, Miners);

    }
	public void drawFirstPlayer(Graphics g){
		/*184 124
		218 149*/
		g.drawImage(startingToken, 184, 124, 34, 34, null);
	}
	public void testScore(){
		Hex[][] board = game.board.getHexes();
		for(int i = 0; i<20; i++){
			for(int j = 0; j<4; j++){
				for(int p = 0; p<2; p++){
					int r1 = (int)(Math.random()*20);
					Player player = game.getPlayers().get(j);
					String color = player.getColor();
					if(board[i][r1].getType()<6)
					board[i][r1].setColor(color);
				}
			}
		}
	}

	public void setGame(Game g){
		game = g;
	}
	public void drawTotal(Graphics g){
		g.setColor(new Color(48,81,110, 127));
		g.fillRoundRect(30, 601, 490-30, 58, 20, 20);
		g.setColor(Color.white);
		g.drawRoundRect(30, 601, 490-30, 58, 20, 20);
		setFontSize(g, 15);
		g.drawString("Total", 40, 564 + 68);	
		for(int c = 0; c < 4; c++){
			g.drawLine(162 + c * 82, 601, 162 + c * 82, 659);
		}	
		for(int c = 0; c < 4; c++){
			int total = 0;
			for(int d = 0; d < 4; d++){
				 total += game.getPlayers().get(c).getScores()[d];
			}
			setFontSize(g, 40);
			g.drawString(total + "", 187 + c * 82, 640);
		}
	}
	public ArrayList<ObjectiveCard> get3Obj(){
		//use this for the hack
        ArrayList<ObjectiveCard> cards = new ArrayList<>();
        int ran =(int) (Math.random()* objectives.size());
		cards.add(new ObjectiveCard(objectiveNames.remove(ran), objectives.remove(ran)));
		ran =(int) (Math.random()* objectives.size());
		cards.add(new ObjectiveCard(objectiveNames.remove(ran), objectives.remove(ran)));
		ran =(int) (Math.random()* objectives.size());
		cards.add(new ObjectiveCard(objectiveNames.remove(ran), objectives.remove(ran)));
        return cards;
    }
	public void drawEnd(Graphics g, int card, int scorePlayer, ArrayList<ObjectiveCard> objCard){
		int cardsize = 125;
		g.drawImage(objCard.get(0).getImage(), 27, 100, cardsize, (int)(cardsize * ((double)3/2)), null);
		g.drawImage(objCard.get(1).getImage(),  27+cardsize, 100, cardsize, (int)(cardsize *((double)3/2)), null);
		g.drawImage(objCard.get(2).getImage(), 27 + 2*cardsize, 100, cardsize,  (int)(cardsize *((double)3/2)), null);
		g.setColor(new Color(48,81,110, 127));
		g.fillRoundRect(30, 299, 490 - 30, 591-299, 20, 20);
		g.setColor(Color.white);
		
		g.drawRoundRect(30, 299, 490-30, 591-299, 20, 20);
		for(int c = 0; c < 4; c++){
			g.drawLine(30, 357 + 58 * c, 490, 357 + 58 * c);
		}
		g.drawLine(162, 299, 162, 591);
		for(int c = 0; c < 3; c++){
			g.drawLine(244 + c * 82, 299, 244 + c * 82, 591);
			setFontSize(g, 15);
			g.drawString(objCard.get(c).getType(), 40, 390 + 58 * c);
		}
		g.drawString("Castles", 40, 564);
		for(int c = 0; c < 4; c++){
			setFontSize(g, 40);
			getCol(game.getPlayers().get(c).getColor(), g);
			g.drawString(c + 1 + "", 190 + c * 82, 345);
		}g.setColor(Color.white);
		for(int c = 0; c < 4; c++){
			for(int d = 0; d < 4; d++){
				int score = game.getPlayers().get(d).getScores()[c];
				g.drawString(score + "", 187 + d * 82, 400 + 58 * c);
			}
		}
		g.setColor(new Color(255, 191, 0, 100));
		if(card <= 3 )g.fillRect(162 + 82 * (scorePlayer), 357 + 58 * (card), 82, 58);

		//162 357 242 414

	}
	public void getCol(String color, Graphics g){
		//one = new Player("blue");
			// two = new Player("white");
			// three = new Player("orange");
			// four = new Player("black");
		if(color.equals("blue")){
			g.setColor(new Color(73, 134, 231));
		}
		if(color.equals("black")){
			g.setColor(Color.black);
		}
		if(color.equals("orange")){
			g.setColor(new Color(255, 180, 0));
		}
		if(color.equals("white")){
			g.setColor(Color.white);
		}
	}
	public void setFontSize(Graphics g, int size){
		g.setFont(new Font("Castellar", 1, size));

	}
	public void drawTokenList(Graphics g){
		g.setColor(Color.white);
		setFontSize(g, 20);
		g.drawString("Tokens", 300, 580);
		g.drawRoundRect(224, 558, 471-224, 643-558, 20, 20);
		ArrayList<Integer> curLocs =  game.curPlayer().getLoc();
		if(curLocs.size() > 0){
			for(int i = 0; i<curLocs.size(); i++){
				g.drawImage(LocationTiles.getLoc(curLocs.get(i)), 234 + i*40, 600, 35, 35, null);
			}
		}
	}
    public void drawSettlement(Graphics g, String color){
		
		g.setColor(Color.white);
		g.drawRoundRect(224, 509, 471-224, 548-509, 20, 20);

		setFontSize(g, 15);
		g.drawString("Settlements", 240, 535);
		g.drawString("X "+game.curPlayer().getSettlement(), 410, 535);

		int adsf = 370;
		int das = 515;
		if(color == "orange"){
			g.setColor(new Color(255, 180, 0));
			g.drawImage(orangehouse, adsf, das,25,25, null);
		}
		else if(color == "black"){
			g.setColor(Color.black);
			g.drawImage(blackhouse, adsf, das, 25,25, null);
		}
		else if(color == "blue"){
			g.setColor(new Color(73, 134, 231));
			g.drawImage(bluehouse, adsf, das, 25,25, null);
		}
		else{
			g.setColor(Color.white);
			g.drawImage(whitehouse, adsf, das, 25,25, null);
		}
	}
    public void drawGray(Graphics g, boolean combined[][], Game game){
		for(int c = 0; c < 20; c++){
			for(int d = 0; d < 20; d++){
				Hex board[][] = game.getBoard().getHexes();
				if(!combined[c][d]){
					board[c][d].setGray(false);
					if(c%2 == 0)g.drawImage(hexagon, 515 + d * (hexwidth - 2), 19 + c * (hexlength - 13), hexwidth, hexlength, null);
					else g.drawImage(hexagon, 533 + d * (hexwidth - 2), 19 + c * (hexlength-13), hexwidth, hexlength, null);
				}
				else board[c][d].setGray(true);
					
			}
		}
	}
    
    public void drawObjective(Graphics g, ArrayList<ObjectiveCard> objCard){
		g.drawImage(objCard.get(0).getImage(), 12, 13, 65, 100, null);
		g.drawImage(objCard.get(1).getImage(), 77, 13, 65, 100, null);
		g.drawImage(objCard.get(2).getImage(), 142, 13, 65, 100, null);
		g.drawImage(backTerrain, 27, 503, 94, 150, null);
		g.setFont(new Font("Castellar", 1, 15));
		g.setColor(Color.white);
	}
	
	public void drawViewCards(Graphics g, ArrayList<ObjectiveCard> objCard){//🐒 
		g.setColor(new Color(0, 0, 0, 127)); //here bro the 127 is 50% transparency  💀 
		g.fillRect(0, 0, 1280, 720);
		int cardsize = 180;
		//wheres the like transparentcy part fr where
		g.setColor(new Color(0, 0, 50));
		g.fillRoundRect(365, 184, 915 -365, 465-184, 20, 20);
		g.drawImage(objCard.get(0).getImage(), 369, 190, cardsize, (int)(cardsize * ((double)3/2)), null);
		g.drawImage(objCard.get(1).getImage(),  369+cardsize, 190, cardsize, (int)(cardsize *((double)3/2)), null);
		g.drawImage(objCard.get(2).getImage(), 369 + 2*cardsize, 190, cardsize,  (int)(cardsize *((double)3/2)), null);
		//g.fillRect(0, 0, 500, 500);
		//g.drawImage(objCard.get(0), 50, 50, null);

	}
	public void viewStandings(Graphics g){//🐒 
		g.setColor(new Color(0, 0, 0, 127)); //here bro the 127 is 50% transparency  💀 
		g.fillRect(0, 0, 1280, 720);		
		g.setColor(new Color(48,81,110, 127));
		g.fillRoundRect(330, 158, 967 - 330, 485 - 158, 20, 20);
		g.setColor(Color.white);
		g.drawRoundRect(330, 158, 967 - 330, 485 - 158, 20, 20);
		ArrayList<Player> play = game.getPlayers();
		for(int i = 0; i<4; i++){
			Player player = play.get(i);
			player.setPos(i);
		}
		TreeMap<Integer, Player> s = new TreeMap<>();
		for(int i = 0; i<play.size(); i++){
			s.put(play.get(i).getScore(), play.get(i));
		}
		Set<Entry<Integer, Player>> entries = s.entrySet();
		int ind = 0;
		for (Map.Entry<Integer, Player> entry : entries) {
				System.out.println(entry.getKey() + "->"
								+ entry.getValue());
				int score = entry.getKey();
				Player p = entry.getValue();
				String color = p.getColor();
				int pos = p.getPos();
				
				g.drawString("Player " + pos, 600, 250 + (ind*15));
				g.drawString(score+"", 920, 250 + (ind*15));

				ind++;
		}
		
	}
    public void drawHexNumbers(Graphics g, Game game){
		for(int c = 0; c < 20; c++){
			for(int d = 0; d < 20; d++){
				Hex hex = game.getBoard().getHexes()[c][d];
				if(hex.getType()>8){
					//check the "number" of location tiles left and then display corresponding one 
					if(hex.getLoc() == 1){
					if(c%2 == 0)g.drawImage(locOne, 517 + d * (hexwidth - 2), 10 + c * (hexlength - 13),33, 33, null);
					else g.drawImage(locOne, 538 + d * (hexwidth - 2), 10 + c * (hexlength-13), 30, 30, null);
					}
					else if(hex.getLoc() == 2){
						if(c%2 == 0)g.drawImage(locTwo, 517 + d * (hexwidth - 2), 10 + c * (hexlength - 13),33, 33, null);
						else g.drawImage(locTwo, 538 + d * (hexwidth - 2), 10 + c * (hexlength-13), 30, 30, null);
					}
				}
			}		
		}
	}
    public void displayLocs(Graphics g, int arr[]){
        g.setFont(new Font("Castellar", 1, 15));
        g.setColor(Color.white);
        g.drawImage(locations, 24, 160, 457, 320, null);
        //game.curPlayer().resetLocs();
        for(int i = 0; i<arr.length; i++){
            if(arr[i]!=0){
                if(i<4)
                g.drawString(arr[i]+"", 216, 205+i*80);
                else
                g.drawString(arr[i]+"", 452, 205+(i-4)*80);
            }
            else{
                if(i<4)
                g.drawImage(gray, 27, 158+(80*i), 230, 80, null);
                else{
                g.drawImage(gray, 253, 158+(80*(i-4)), 230, 80, null);

                }
            }
        }
    }
    public BufferedImage getSector(int id){
		if(id == 1) return sector1;
		if(id == 2) return sector2;
		if(id == 3) return sector3;
		if(id == 4) return sector4;
		if(id == 5) return sector5;
		if(id == 6) return sector6;
		if(id == 7) return sector7;
		return sector8;
	}
    public BufferedImage settlementColor(String color){
		if(color.equals("orange")) return orangehouse;
		if(color.equals("black")) return blackhouse;
		if(color.equals("white")) return whitehouse;
		else return bluehouse;
	}

	public void drawSettlements(Graphics g){
		for(int c = 0; c < 20; c++){
			for(int d = 0; d < 20; d++){
				Hex board[][] = game.getBoard().getHexes();
				Hex hex = board[c][d];	
				if(hex.getColor().length() > 0){
					if(c%2 == 0)g.drawImage(settlementColor(hex.getColor()), 520 + d * (hexwidth -2), 24 + c * (hexlength - 13), hexwidth - 15, hexlength - 15, null);
					else g.drawImage(settlementColor(hex.getColor()), 538 + d * (hexwidth -2), 24 + c * (hexlength-13), hexwidth - 15, hexlength - 15, null);				
				}		
			}
		}
	}
	public void drawRemainingSettlements(Graphics g){
		int width = 81;
		setFontSize(g, 15);
		g.setColor(new Color(28, 35, 61));
		g.fillRoundRect(226, 122, width, 30, 10, 10);
		g.drawImage(settlementColor(game.getPlayers().get((game.curPlayer+ 1)% 4).getColor()),228, 124, 26, 26, null);
		g.setColor(Color.white);
		g.drawString("X " + game.getPlayers().get((game.curPlayer+ 1)% 4).getSettlement(), 260, 145);
		g.setColor(new Color(28, 35, 61));
		g.fillRoundRect(226 + width + 5, 122, width, 30, 10, 10);
		g.drawImage(settlementColor(game.getPlayers().get((game.curPlayer+ 2)% 4).getColor()),228 + width + 5, 124, 26, 26, null);
		g.setColor(Color.white);
		g.drawString("X " + game.getPlayers().get((game.curPlayer+ 2)% 4).getSettlement(), 260 + width + 5, 145);
		g.setColor(new Color(28, 35, 61));
		g.fillRoundRect(226 + 2 * width + 10, 122, width, 30, 10, 10);
		g.drawImage(settlementColor(game.getPlayers().get((game.curPlayer+ 3)% 4).getColor()),228 + 2 * width + 10, 124, 26, 26, null);
		g.setColor(Color.white);
		g.drawString("X " + game.getPlayers().get((game.curPlayer+ 3)% 4).getSettlement(), 260 + 2 * width + 10, 145);
	}												
}

