package game;

import java.io.*;
import java.util.*;

public class Test
{
	private boolean exit;
    private boolean running;
    private boolean save = false;
    private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    Board board = Game.getInstance().getBoard();
    
    public Test()
    {
    	exit = false;
    	running = false;
    	//...
    }
    
    /**
     *  Ez a f�ggv�ny kommunik�l a felhaszn�l�val,
     *  Itt lehet kiv�lasztani melyik tesztesetet szeretn�nk futtatni.
     */
    public boolean run()
    {
    	while(!exit)
    	{
    		Scanner sc = new Scanner(System.in);
    		int cmd;
    		boolean wrong = true;
    		
    		while(wrong) {
    			wrong = false;
    			System.out.println("Test mode: ON");
    			System.out.println("What do you want to do?\n 1: AddPiece | 2: AddBear | 3: AddItem | 4: GiveItem | 5: CreateTile | 6: SnowStorm | 0: Test OFF\n");
    			cmd = sc.nextInt();
    			
    			int tile;
    			String type;
    			int id;
    			
    			switch (cmd) {
    			case 1:
    				System.out.println("Onto which tile would you like to add the piece? (0 - 24)");
    				tile = sc.nextInt();
    				System.out.println("Which kind of piece would you like to add? (1: Eskimo | 2: Explorer)");
    				int p = sc.nextInt();
    				System.out.println("What colour do you want your piece to be? (1: RED | 2: YELLOW | 3: PURPLE | 4: GREEN | 5: CYAN | 6: BLUE");
    				id = sc.nextInt();
    				System.out.println("Adding new piece...");
    				TestAddPiece(tile, p, Colour.values()[id - 1]);
    				break;
    				
    			case 2:
    				System.out.println("Onto which tile would you like to add the bear? (0 - 24)");
    				tile = sc.nextInt();
    				System.out.println("Adding bear...");
    				TestAddBear(tile);			
    				break;
    				
    			case 3:
    				System.out.println("Onto which tile would you like to add the item? (0 - 24)");
    				tile = sc.nextInt();
    				System.out.println("Which kind of item would you like to add? (Part | Food | Suit | Rope | Tent | Shovel | Wooden)");
    				type = sc.nextLine();	
    				System.out.println("Adding item...");
    				TestAddItem(tile, type);
    				break;
    				
    			case 4:
    				System.out.println("Which piece would you like to give the item to? (1: RED | 2: YELLOW | 3: PURPLE | 4: GREEN | 5: CYAN | 6: BLUE");
    				id = sc.nextInt();
    				System.out.println("Which kind of item would you like to add? (Part | Food | Suit | Rope | Tent | Shovel | Wooden)");
    				type = sc.nextLine();
    				System.out.println("Giving item...");
    				TestGiveItem(Colour.values()[id - 1], type);
    				break;
    				
    			case 5:
    				System.out.println("Which kind of tile would you like to create? (1: Ice | 2: Unstable | 3: Hole");
    				int t = sc.nextInt();
    				System.out.println("Where do you want to create your tile? (0 - 24)");
    				tile = sc.nextInt();
    				if(t == 1) TestCreateIce(tile);
    				else if(t == 2) {
    					System.out.println("How many would you like your Unstable's capacity to be? (1 - 5)");
        				int cap = sc.nextInt();
    					TestCreateUnstable(tile, cap);
    				}
    				else if(t == 3) TestCreateHole(tile);
    				else System.out.println("Incorrect tile type input");
    				break;
    			
    			case 6:
    				System.out.println("Initiating snowstorm");
    				TestSnowStorm();				
    				break;
    				
    			case 0:
    				exit = true;
    				return true;
    				
    			case -1:
    				exit = true;
    				break;
    				
    			default:
    				wrong = true;
    				System.out.println("Incorrect input!");
    				break;
    			}
    			if (!Game.getInstance().getTestStart())
    	    		exit = true;
    		}
    	}
    	return false;
    }

	/**
	 *  Teszteset
     *  Egy megadott egyedi sz�n� �s t�pus� (eszkim�/sarkkutat�) b�but helyez a j�t�kt�bl�ra.
     *  @param tile A mez� ahova helyezni szeretn�nk
     *  @param type T�pusa, ami lehet eszkim� vagy sarkkutat�
     *  @param id A b�bu sz�ne
     */
    private void TestAddPiece(int tile, int type, Colour id) {
    	try {   		
            if (tile > 24 || tile < 0) {					//Ha nem a p�lya r�sze a megadott mez�, error
                throw new Exception();
            }
            
            if(type == 1) {							
            	Eskimo eskimo = new Eskimo();				//Eskimo l�trehoz�sa
            	eskimo.setActionPoints(5);					//Akci�pontjainak be�ll�t�sa
            	eskimo.setColour(id);						//Be�ll�tjuk az azonos�t�j�t, ami a sz�ne
            	eskimo.moved(board.getTiles().get(tile));	//Odamozgatjuk l�nyeg�ben a moved-al a megadott mez�re
            	board.getPieces().add(eskimo);				//Majd v�g�l a piecek t�mbj�hez ad�sa
            }
            else if(type == 2) {
            	Explorer explorer = new Explorer();			//Explorer l�trehoz�sa
            	explorer.setActionPoints(5);				//Akci�pontjainak be�ll�t�sa
            	explorer.setColour(id);						//Be�ll�tjuk az azonos�t�j�t, ami a sz�ne
            	explorer.moved(board.getTiles().get(tile));	//Odamozgatjuk l�nyeg�ben a moved-al a megadott mez�re
            	board.getPieces().add(explorer);			//Majd v�g�l a piecek t�mbj�hez ad�sa
            }
            else {
            	throw new IOException();					//Ha nem Eskimot vagy Explorert adtunk meg, error
            }
    	} catch (IOException e) {
    		System.out.println("Given piece type does not exist");
    	} catch (Exception e) {
    		System.out.println("Given tile is not part of the board");
    	}
    }
    
    /**
	 *  Teszteset
     *  Lehelyezz�k a medv�t a megadott mez�re
     *  @param tile A mez� ahova helyezni szeretn�nk
     */
    private void TestAddBear(int tile) {
    	try {
            if (tile > 24 || tile < 0) {							//Ha nem a p�lya r�sze a megadott mez�, error
                throw new Exception();
            }
            Bear bear = new Bear();
            bear.moved(board.getTiles().get(tile));					//Odamozgatjuk l�nyeg�ben a moved-al a megadott mez�re
    	} catch (Exception e) {
    		System.out.println("Given tile is not part of the board");
    	}
    }
    
    /**
	 *  Teszteset
     *  Egy v�laszthat� fajt�j� t�rgyat helyez a megadott mez�be.
     *  @param tile A mez� amibe helyezni szeretn�nk
     *  @param type A t�rgy t�pusa
     */
    private void TestAddItem(int tile, String type) {
    	try {    		
    		if (tile > 24 || tile < 0) {							//Ha nem a p�lya r�sze a megadott mez�, error
                throw new Exception();
            }
    		
    		switch(type) {											//Hozz�adjuk a megadott mez�h�z a megadott t�pus� itemet
	    		case "Part":
	    			board.getTiles().get(tile).setItem(new Part());
	    			break;
	    		case "Food":
	    			board.getTiles().get(tile).setItem(new Food());
	    			break;
	    		case "Suit":
	    			board.getTiles().get(tile).setItem(new Suit());
	    			break;
	    		case "Rope":
	    			board.getTiles().get(tile).setItem(new Rope());
	    			break;
	    		case "Tent":
	    			board.getTiles().get(tile).setItem(new Tent());
	    			break;
	    		case "Shovel":
	    			board.getTiles().get(tile).setItem(new Shovel());
	    			break;
	    		case "Wooden":
	    			board.getTiles().get(tile).setItem(new Wooden());
	    			break;
	    		default:
	    			throw new IOException();
    		}
    	} catch (IOException e) {
    		System.out.println("Given item type does not exist");
    	} catch (Exception e) {
    		System.out.println("Given tile is not part of the board");
    	} 
    }
    
    /**
	 *  Teszteset
     *  Egy v�laszthat� fajt�j� t�rgyat ad a sz�n szerint v�lasztott b�bunak
     *  @param id A b�bu sz�ne aminek adni szeretn�nk
     *  @param type A t�rgy t�pusa
     */
    private void TestGiveItem(Colour id, String type) {
    	try {
    		Item item;
    		
    		switch(type) {									//L�trehozzuk a megadott t�pus� itemet
	    		case "Part":
	    			item = new Part();
	    			break;
	    		case "Food":
	    			item = new Food();
	    			break;
	    		case "Suit":
	    			item = new Suit();
	    			break;
	    		case "Rope":
	    			item = new Rope();
	    			break;
	    		case "Tent":
	    			item = new Tent();
	    			break;
	    		case "Shovel":
	    			item = new Shovel();
	    			break;
	    		case "Wooden":
	    			item = new Wooden();
	    			break;
	    		default:
	    			throw new IOException();
    		}
    		
    		boolean found = false;									//temp v�ltoz� annak ellen�rz�s�re, hogy l�tezik-e az adott sz�n� piece
    		for(int i = 0; i < board.getPieces().size(); i++) {		//Keress�k a megadott sz�n� piecet
    			if(board.getPieces().get(i).getColour() == id) {
    				board.getPieces().get(i).addItem(item);			//Ha megtal�ltuk nekiadjuk az itemet
    				found = true;									//�s be�ll�tjuk, hogy megtal�ltuk						
    			}
    		}
    		if(!found)												//Ha nincs olyan sz�n� b�bu, exception3
    			throw new Exception();
    	} catch (IOException e) {
    		System.out.println("Given item type does not exist");
    	} catch (Exception e) {
    		System.out.println("Given piece does not exist");
    	} 
    }
    
    /**
	 *  Teszteset
     *  L�trehoz egy j�gt�bl�t a megadott mez� hely�n
     *  @param tile A mez� aminek fajt�j�t ki szeretn�nk cser�lni
     */
    private void TestCreateIce(int tile) {
    	try {
	    	if (tile > 24 || tile < 0) {							//Ha nem a p�lya r�sze a megadott mez�, error
	            throw new Exception();
	        }
	    	board.getTiles().set(tile, new Ice(9, 0));			//Be�ll�tjuk a megadott mez�t ice-ra									Ice-n�l capacity???
    	} catch (Exception e) {
    		System.out.println("Given piece does not exist");
    	}
    }
    
    /**
	 *  Teszteset
     *  L�trehoz egy instabil j�gt�bl�t a megadott mez� hely�n
     *  @param tile A mez� aminek fajt�j�t ki szeretn�nk cser�lni
     */
    private void TestCreateUnstable(int tile, int cap) {
    	try {
	    	if (tile > 24 || tile < 0) {								//Ha nem a p�lya r�sze a megadott mez�, error
	            throw new Exception();
	        }
	    	board.getTiles().set(tile, new Unstable(cap, 0));			//Be�ll�tjuk a megadott mez�t unstable-re
    	} catch (Exception e) {
    		System.out.println("Given tile is not part of the board");
    	}
    }
    
    /**
	 *  Teszteset
     *  L�trehoz egy lyukat a megadott mez� hely�n
     *  @param tile A mez� aminek fajt�j�t ki szeretn�nk cser�lni
     */
    private void TestCreateHole(int tile) {
    	try {
	    	if (tile > 24 || tile < 0) {								//Ha nem a p�lya r�sze a megadott mez�, error
	            throw new Exception();
	        }
	    	board.getTiles().set(tile, new Unstable(0, 0));				//Be�ll�tjuk a megadott mez�t hole-ra
    	} catch (Exception e) {
    		System.out.println("Given tile is not part of the board");
    	}
    }
    
    /**
	 *  Teszteset
     *  H�vihar
     */
    private void TestSnowStorm()
    {
    	Game.getInstance().snowStorm();
    }
    
}
