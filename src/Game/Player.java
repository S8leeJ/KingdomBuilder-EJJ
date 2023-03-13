package Game;

public class Player {
    String color; 
    int settlements;
    int cardType;
    int num;

    public Player(int num){
        this.num = num; 
    }
    public void setType(int x){
        cardType = x;
    }
    public void setColor(){
        if(num==1){
            color = "blue";
        }
        else if(num==2){
            color = "orange";
        }
        else if(num==3){
            color = "black";
        }
        else{
            color = "white";
        }
    }
    public int getType(){
        return cardType;
    }
    public String getColor(){
        return color;
    }
   
}
