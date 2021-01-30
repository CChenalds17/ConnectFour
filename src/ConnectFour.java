// Main method

public class ConnectFour {
    
    public static void main(String[] args) {
        Player player1 = new Player('x');
        Player player2 = new Player('o');

        Game myGame = new Game(player1, player2);

        // Single Player:
        myGame.playGame1P();
        // Two Player: 
        myGame.playGame2P();
        
    }

}
