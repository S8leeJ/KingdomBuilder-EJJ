import java.awt.*;
import java.awt.image.*;
import javax.imageio.ImageIO;
import Board.Hex;
import Board.LocationTiles;
import Game.Game;
import java.util.*;

public class KingdomHelper {
    private static BufferedImage sector1,sector2, gray, sector3, sector4, sector5, sector6, sector7, sector8, hexagon, background, blackhouse, bluehouse, orangehouse, whitehouse, backTerrain, locOne, locTwo, locations;
  	public int hexwidth = 38, hexlength = 44;
	Game game;
    public KingdomHelper(Game game){
		this.game = game;
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
			background =   ImageIO.read(getClass().getResourceAsStream("/Board/Images/background.jpg"));
			sector1 = ImageIO.read(getClass().getResourceAsStream("/Board/BoardImages/sector1.png"));
			hexagon = ImageIO.read(getClass().getResourceAsStream("/Board/Images/hexagon.png"));
			backTerrain =  ImageIO.read(getClass().getResourceAsStream("/Card/TerrainImages/KB-Card-Back.png"));
			locOne =  ImageIO.read(getClass().getResourceAsStream("/Board/Images/1.png"));
			locTwo =  ImageIO.read(getClass().getResourceAsStream("/Board/Images/2.png"));
			locations = ImageIO.read(getClass().getResourceAsStream("/Board/Images/Locations.PNG"));
			gray = ImageIO.read(getClass().getResourceAsStream("/Board/Images/darkrect.png"));
	
        } catch (Exception e) {
            // TODO: handle exception
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
				else{
					board[c][d].setGray(true);
				}		
			}
		}
	}
    
    public void drawObjective(Graphics g, ArrayList<BufferedImage> objCard){
		g.drawImage(objCard.get(0), 12, 13, 65, 100, null);
		g.drawImage(objCard.get(1), 77, 13, 65, 100, null);
		g.drawImage(objCard.get(2), 142, 13, 65, 100, null);
		g.drawImage(backTerrain, 27, 503, 94, 150, null);
		g.setFont(new Font("Castellar", 1, 15));
		g.setColor(Color.white);
	}
	public void drawViewCards(Graphics g, ArrayList<BufferedImage> objCard){
		//System.out.println("emthod is called");
		int cardsize = 150;
		//System.out.println((int)(cardsize * (double)(3/2)));
		g.drawImage(objCard.get(0), 27, 160, cardsize, (int)(cardsize * ((double)3/2)), null);
		g.drawImage(objCard.get(1),  27+cardsize, 160, cardsize, (int)(cardsize *((double)3/2)), null);
		g.drawImage(objCard.get(2), 27 + 2*cardsize, 160, cardsize,  (int)(cardsize *((double)3/2)), null);
		g.setColor(Color.black);
		//g.fillRect(0, 0, 500, 500);
		//g.drawImage(objCard.get(0), 50, 50, null);

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
