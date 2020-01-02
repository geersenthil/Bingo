//Elizabeth & Geerthika
import java.util.Random;
import java.awt.*;
import javax.swing.*;
import javax.swing.event.*;
import java.awt.event.*;


 
public class BingoGUI {

	private static String frameName = "Win";
	private static JFrame popUp = new JFrame(frameName); //change frame name if win or loss using a ternary before displaying it
	private static String win = "Yay the computer loses this time", lose = "Well. The human race had a good run as top of the food chain.", callNum;
 	private static JPanel popUpPanel; //change its content based on win or lose
	private static JPanel gamePanel;    
    private static MenuItem i1, i2, i3;
    private static boolean compWin = false, playerWin = false, cheat = false, x;
    private static String[][] playerCard1 = new String[5][5]; 
	private static  String[][] playerCard2 = new String[5][5];
	private static JFrame frame = new JFrame("BINGO");
	private static JPanel playerBoard;
	private static JPanel computerBoard;
	
	public static void main(String[] args) {
		
			JFrame frame = new JFrame("BINGO");
			gamePanel = new JPanel();
			JPanel callPanel = new JPanel(new GridLayout(5, 15));
			playerBoard = new JPanel();
			computerBoard = new JPanel();
			JPanel playerPanelCard1 = new JPanel (new GridLayout(6, 5));
			JPanel playerPanelCard2 = new JPanel (new GridLayout(6, 5));
			JPanel computerPanelCard1 = new JPanel (new GridLayout(6, 5));
			JPanel computerPanelCard2 = new JPanel (new GridLayout(6, 5));
			String[][] compCard1 = new String[5][5];
			String[][] compCard2 = new String[5][5];
	
	        for (int i = 0; i < 75; i++) {
	        	JLabel callBoardLabel = new JLabel(String.valueOf(i + 1));
	        	callPanel.add(callBoardLabel);
	        }
	        gamePanel.add(callPanel);
	        compCardGen(compCard1, computerPanelCard1);
			playerCardGen(playerCard1, playerPanelCard1);
			compCardGen(compCard2, computerPanelCard2);
			playerCardGen(playerCard2, playerPanelCard2);
			gamePanel.add(playerBoard);
			gamePanel.add(computerBoard);       
	        MenuBar mb = new MenuBar();  
	        Menu menu = new Menu("File");         
	        i1 = new MenuItem("New Game", new MenuShortcut('N') );  
	        i2 = new MenuItem("Call bingo", new MenuShortcut('B'));  
	        i3 = new MenuItem("Exit", new MenuShortcut('E'));          
	        i1.addActionListener(new MenuBarListener()); 
	        i2.addActionListener(new MenuBarListener());
	        i3.addActionListener(new MenuBarListener());     
	        menu.add(i1);  
	        menu.add(i2);  
	        menu.add(i3);  
	        mb.add(menu);
	        frame.setMenuBar(mb);
	        gamePanel.setPreferredSize(new Dimension(500, 500));
			gamePanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
			frame.setVisible(true);
			frame.setResizable(true);
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			frame.setContentPane(gamePanel);
			frame.pack();
			int delay = 1000 / 4;
			Timer timer = new Timer(delay, new CallListener());
			timer.start();
			/*String[][] callBoard = {{"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15"},{"16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30"},{"31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45"},{"46", "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59", "60"},{"61", "62", "63", "64", "65", "66", "67", "68", "69", "70", "71", "72", "73", "74", "75"}};
	        card(playerCard1);
			card(playerCard2);
			card(compCard1);
			card(compCard2);
			while (!compWin && !playerWin) {
				callNum = call(callBoard); 
				turn = true;
				while (turn) {
					System.out.printf("%n" + "Enter 1 to daub, 2 to call bingo or 3 to end your turn.");
					int choice = keyboard.nextInt();
					switch (choice) {
						case 1:
							if (daub(playerCard1, callNum, playerCard1.length) | daub(playerCard2, callNum, playerCard2.length)) {
								System.out.println("You have successfully daubbed.");
							} else {
								System.out.printf("%n" + "What did I say. I hope this was a error, but either way....");
								cheat = true;
							}
							break;
						case 2:
							if (bingoCheck(playerCard1) || bingoCheck(playerCard2)){
								System.out.println("BINGO!");
								playerWin = true;
							} else {
								System.out.printf("%n" + "What did I say. I always know.");
								cheat = true;
							}
							break;
						default:
							turn = false;
							break;
					}
				}
				if (!cheat) {
					compWin = computerTurn(compCard1, compCard2, callNum);
				} else {
					break;
				}
			}
			System.out.printf("%n" + "Player Cards:");
			display(playerCard1, playerCard2);
			System.out.printf("%n" + "Computer Cards:");
			display(compCard1, compCard2);
			if (cheat == false && playerWin == true) {
				System.out.printf("%n" + "You win!");
			} else {
			System.out.printf("%n" + "Computer Wins.");
			} */
	} 
		
	public static void playerCardGen (String[][] card, JPanel board) {
		String word = "BINGO";
		card(card);
		for (int i = 0; i < word.length(); i++) {
			JLabel label = new JLabel(String.valueOf(word.charAt(i)));
			board.add(label);
		}
		for (int row = 0; row < card.length; row++) {
			for (int col = 0; col < card.length; col++) {
				JButton button; 
				button = new JButton(card[row][col]);
				button.setActionCommand(board + card[row][col]);
				button.addActionListener(new Buttons());
				if (card[row][col] == "*") {
				//deactivation code
				}
				button.setPreferredSize(new Dimension(50, 50));
				board.add(button);
			}
		}
		playerBoard.add(board);
	}
	
	public static void compCardGen (String[][] card, JPanel board) {
		String word = "BINGO";
		card(card);
		for (int i = 0; i < word.length(); i++) {
			JLabel label; 
			label = new JLabel(String.valueOf(word.charAt(i)));
			board.add(label);
		}
		for (int row = 0; row < card.length; row++) {
			for (int col = 0; col < card.length; col++) {
				JLabel label = new JLabel(card[row][col]);
				if (card[row][col] == "*") {
				//deactivation code
				}
				board.add(label);
			
			}
		}
		computerBoard.add(board);
	}
	
	public static void card(String[][] card) {
            int [][] list = new int [5][5];
            for (int col = 0; col < list.length; col++) {
                for (int row = 0; row < list.length; row++) {
                int ran = col * 15 + (int) (Math.random()* 15 + 1);
                while(exist(list,col,row,ran)) {
                    ran = col * 15 + (int) (Math.random()* 15 + 1);
                }
                list[row][col]= ran;
                }
            }
            copy(card,list);
	}
	
	public static boolean exist(int[][] a, int col, int row, int target ){//target is the random number
            int pos = 0;
            boolean found = false;
            while ((pos < row )&&(!found)){// only check the number above the random number in the column
                found = (a[pos][col] == target);
                pos++;
            }
            return found;
        }
        
	public static void copy (String[][] a, int [][] b){
            for (int i=0; i <5; i++){
                for (int j = 0; j<5; j++){
                    a[i][j] = b[i][j] + "";// converting int array to string array
                }
            }
            a[2][2] = "*";
        }
        
	public static boolean daub(String[][] card, String call, int maxCol) {
	 	for (int col = 0; col < maxCol; col++) {
			for (int row = 0; row < card.length; row++) {
				if (call.equals(card[row][col])){ 
	 				card[row][col] = "*";
	 				return true;
	 			}
	 		}
	 	} 	
	 	return false;
	}
	
	public static boolean bingoCheck(String[][] card) {
		for (int col = 0; col < card.length; col++) { 
			if (card[0][col].equals("*") && card[1][col].equals("*") && card[2][col] .equals("*") && card[3][col].equals("*") && card[4][col] .equals("*")) {
		 		return true;
			}
		}
		for (int row = 0; row < card.length; row++) { 
			if (card[row][0].equals("*")&& card[row][1].equals("*")&& card[row][2] .equals("*") && card[row][3].equals("*") && card[row][4] .equals("*")) {
		 		return true;
			}
		}
		if (card[0][0].equals("*") && card[1][1].equals("*") && card[2][2].equals("*") && card[3][3] .equals("*") && card[4][4].equals("*")) {
			return true;
		} else if (card[4][0].equals("*") && card[3][1].equals("*") && card[2][2] .equals("*") && card[1][3] .equals("*") && card[0][4].equals("*")) {
			return true;
		}
		return false;
	}
	
	public static String call(String[][] callBoard) {
		Random generator = new Random();
		String call, callVoice;
		int callInt, maxAmount = 14;
		do {
			callInt = generator.nextInt(74) + 1;
			call = String.valueOf(callInt);
		} while (daub(callBoard, call, maxAmount) != true); //makes sure it doesn't call by accident
		if (callInt < 16) { 
			callVoice = "B" + call;
		} else if (callInt < 31) {
			callVoice = "I" + call;
		} else if (callInt < 46) {
			callVoice = "N" + call;
		} else if (callInt < 61) {
			callVoice = "G" + call;
		} else {
			callVoice = "O" + call;
		}
		return callVoice;
	}
	
	public static boolean computerTurn(String[][] card, String[][] card2, String call) {
		if (daub(card, call, card.length) | daub(card2, call, card2.length)) {
			if (bingoCheck(card) || bingoCheck(card2)) {
				return true;
			}
		}
		return false;
	}
	
	private static class MenuBarListener implements ActionListener {
		
		public void actionPerformed(ActionEvent event) {
			if (event.getSource() == i1){
				if (bingoCheck(playerCard1) || bingoCheck(playerCard2)) {
					playerWin = true;
				} else {
					System.out.printf("%n" + "What did I say. I always know.");
					cheat = true;
				}
			} else if (event.getSource() == i2) {
				x = true;
			} else if (event.getSource() == i3) {
				System.exit(0);
			}
		}
    }
    
	private static class CallListener implements ActionListener {
		
		public void actionPerformed(ActionEvent event) {
			//Get this to do the call, works on delay from timer!			
		}
    }
	
	private static class Buttons implements ActionListener {

		public void actionPerformed(ActionEvent event) {
			String num = event.getActionCommand();
			if (num == callNum.substring(1) & (daub(playerCard1, callNum, playerCard1.length) || daub(playerCard2, callNum, playerCard2.length))) {
				((JButton)event.getSource()).setEnabled(false);
			} else {
				cheat = true;
			}			
		}
    }
}