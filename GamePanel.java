package play;


import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.MouseInfo;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;

import game.GameData;
import items.Food;
import items.Player;
import items.Poison;
import items.PowerDown;
import items.PowerUp;
import items.Snake;




public class GamePanel extends JPanel implements KeyListener, ActionListener, Runnable, MouseListener, MouseMotionListener
{
	Thread t;
	public void init()
	{              
		t = new Thread(this);
		t.start();
	}

	public void run()
	{   
		try
		{      
			while(true)
			{
				repaint();
				Thread.sleep(3000);
			}
		}
		catch(Exception e)
		{
		}       
	}

	public static int playerIndex;

	private ImageIcon[] rightMouth;
	private ImageIcon[] leftMouth;
	private ImageIcon[] upMouth;
	private ImageIcon[] downMouth;

	private Timer timer;

	private int keyDelay = 0;
	private long[] powerUpSec;
	private long[] powerDownSec;
	private int delay = 200;

	private long startTime;



	private ImageIcon[] snakeImage;;

	private int moves = 0;


	private ImageIcon header;

	private boolean power = false;

	private boolean[] powerUp;
	private boolean[] powerDown;

	private PowerUp[] powerUpList;
	private PowerDown[] powerDownList;


	Rectangle windowBounds;

	private Player player;
	private Random rand = new Random();

	private ArrayList<Food> foods = new ArrayList<Food>();
	private ArrayList<Poison> poisons = new ArrayList<Poison>();
	private ArrayList<Snake> snakes = new ArrayList<Snake>();
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public GamePanel(Rectangle windowBounds, int playerIndex){

		this.playerIndex = playerIndex;

		addKeyListener(this);
		addMouseListener(this);
		addMouseMotionListener(this);
		setFocusable(true);
		setFocusTraversalKeysEnabled(false);
		player = GameData.getInstance().getPlayers().get(playerIndex);

		rightMouth = new ImageIcon[GameData.getInstance().getPlayers().size()];
		leftMouth = new ImageIcon[GameData.getInstance().getPlayers().size()];
		downMouth = new ImageIcon[GameData.getInstance().getPlayers().size()];
		upMouth = new ImageIcon[GameData.getInstance().getPlayers().size()];
		snakeImage = new ImageIcon[GameData.getInstance().getPlayers().size()];

		powerUpSec = new long[GameData.getInstance().getPlayers().size()];
		powerDownSec = new long[GameData.getInstance().getPlayers().size()];

		powerUp = new boolean[GameData.getInstance().getPlayers().size()];
		powerDown = new boolean[GameData.getInstance().getPlayers().size()];

		powerUpList = new PowerUp[GameData.getInstance().getPlayers().size()];
		powerDownList = new PowerDown[GameData.getInstance().getPlayers().size()];



		for(int i = 0; i<GameData.getInstance().getPlayers().size();i++) {
			for(int j=0;j<GameData.getInstance().getPlayers().get(i).getFoods().length;j++) {
				foods.add(GameData.getInstance().getPlayers().get(i).getFoods()[j]);
			}
		}

		for(int i = 0; i<GameData.getInstance().getPlayers().size();i++) {
			for(int j=0;j<GameData.getInstance().getPlayers().get(i).getPoisons().length;j++) {
				poisons.add(GameData.getInstance().getPlayers().get(i).getPoisons()[j]);
			}
		}

		for(int i = 0; i<GameData.getInstance().getPlayers().size();i++) {
			snakes.add(GameData.getInstance().getPlayers().get(i).getS());
		}

		for(int i = 0; i<GameData.getInstance().getPlayers().size();i++) {
			powerUp[i] = false;
		}

		for(int i = 0; i<GameData.getInstance().getPlayers().size();i++) {
			powerDown[i] = false;
		}

		for(int i = 0; i<GameData.getInstance().getPlayers().size();i++) {
			powerUpSec[i] = 0;
		}

		for(int i = 0; i<GameData.getInstance().getPlayers().size();i++) {
			powerDownSec[i] = 0;
		}



		for(int i = 0; i<GameData.getInstance().getPlayers().size();i++) {
			rightMouth[i]= null;
			leftMouth[i]= null;
			upMouth[i]=null;
			downMouth[i]=null;
			snakeImage[i]=null;
			powerUpList[i]=null;
			powerDownList[i]=null;
		}




		timer = new Timer(delay, this);
		timer.start();
		startTime = System.currentTimeMillis();

	}

	public void paint(Graphics g) {

		Graphics2D g2 = (Graphics2D) g;
		g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);

		for(int i = 0; i<powerUp.length;i++) {
			if(powerUp[i]) {
				powerUpSec[i] = 5 - (System.currentTimeMillis() - powerUpSec[i]) / 1000;
			}
		}
		for(int i = 0; i<powerUpSec.length;i++) {
			if(powerUpSec[i] == 0) {
				powerUp[i] = false;
			}
		}

		for(int i = 0; i<powerDown.length;i++) {
			if(powerDown[i]) {
				powerDownSec[i] = 5 - (System.currentTimeMillis() - powerDownSec[i]) / 1000;
			}
		}
		for(int i = 0; i<powerDownSec.length;i++) {
			if(powerDownSec[i] == 0) {
				powerDown[i] = false;
			}
		}

		if(moves == 0) {
			player.getS().getSnakeXlength()[0]=100;
			player.getS().getSnakeXlength()[1]=75;
			player.getS().getSnakeXlength()[2]=50;

			player.getS().getSnakeYlength()[0]=200;
			player.getS().getSnakeYlength()[1]=200;
			player.getS().getSnakeYlength()[2]=200;
		}

		g.setColor(Color.white);
		g.fillRect(10, 10,getBounds().width -20, 107);

		header = new ImageIcon("header.png");
		header.paintIcon(this, g, 20, 11);

		g.setColor(Color.white);
		g.drawRect(10, 127, getBounds().width -20, getBounds().height - 132);

		g.setColor(Color.DARK_GRAY);
		g.fillRect(11, 128, getBounds().width -22, getBounds().height - 134);


		for(int i=0; i<rightMouth.length;i++) {
			if(GameData.getInstance().getPlayers().get(i).getS().getColor() == 0) {
				rightMouth[i] = new ImageIcon("rightmouth.png");
				rightMouth[i].paintIcon(this, g, GameData.getInstance().getPlayers().get(i).getS().getSnakeXlength()[0], GameData.getInstance().getPlayers().get(i).getS().getSnakeYlength()[0]);
			}else if(GameData.getInstance().getPlayers().get(i).getS().getColor() == 1) {
				rightMouth[i] = new ImageIcon("rightmouth1.png");
				rightMouth[i].paintIcon(this, g, GameData.getInstance().getPlayers().get(i).getS().getSnakeXlength()[0], GameData.getInstance().getPlayers().get(i).getS().getSnakeYlength()[0]);
			}
			else if(GameData.getInstance().getPlayers().get(i).getS().getColor() == 2) {
				rightMouth[i] = new ImageIcon("rightmouth2.png");
				rightMouth[i].paintIcon(this, g, GameData.getInstance().getPlayers().get(i).getS().getSnakeXlength()[0], GameData.getInstance().getPlayers().get(i).getS().getSnakeYlength()[0]);
			}
			else if(GameData.getInstance().getPlayers().get(i).getS().getColor() == 3) {
				rightMouth[i] = new ImageIcon("rightmouth3.png");
				rightMouth[i].paintIcon(this, g, GameData.getInstance().getPlayers().get(i).getS().getSnakeXlength()[0], GameData.getInstance().getPlayers().get(i).getS().getSnakeYlength()[0]);
			}else if(GameData.getInstance().getPlayers().get(i).getS().getColor() == 4) {
				rightMouth[i] = new ImageIcon("rightmouth4.png");
				rightMouth[i].paintIcon(this, g, GameData.getInstance().getPlayers().get(i).getS().getSnakeXlength()[0], GameData.getInstance().getPlayers().get(i).getS().getSnakeYlength()[0]);
			}
		}




		for(int index=0; index<GameData.getInstance().getPlayers().size();index++) {
			for(int i=0;i<GameData.getInstance().getPlayers().get(index).getFoods().length;i++) {  
				g.setColor(GameData.getInstance().getPlayers().get(index).getFoods()[i].getColor());
				g.fillOval(GameData.getInstance().getPlayers().get(index).getFoods()[i].getxPos(), GameData.getInstance().getPlayers().get(index).getFoods()[i].getyPos(), GameData.getInstance().getPlayers().get(index).getFoods()[i].getsize(), GameData.getInstance().getPlayers().get(index).getFoods()[i].getsize());
			}
			for(int i=0;i<GameData.getInstance().getPlayers().get(index).getPoisons().length;i++) {        
				g.setColor(GameData.getInstance().getPlayers().get(index).getPoisons()[i].getColor());
				g.fillOval(GameData.getInstance().getPlayers().get(index).getPoisons()[i].getxPos(), GameData.getInstance().getPlayers().get(index).getPoisons()[i].getyPos(), GameData.getInstance().getPlayers().get(index).getPoisons()[i].getsize(), GameData.getInstance().getPlayers().get(index).getPoisons()[i].getsize());
			}
			powerUpList[index]=GameData.getInstance().getPlayers().get(index).getPowerUps();
			powerDownList[index]=GameData.getInstance().getPlayers().get(index).getPowerDowns();
			if(GameData.getInstance().getPlayers().get(index).getPowerUps()!=null) {
				g.setColor(GameData.getInstance().getPlayers().get(index).getPowerUps().getColor());
				g.fillOval(GameData.getInstance().getPlayers().get(index).getPowerUps().getxPos(), GameData.getInstance().getPlayers().get(index).getPowerUps().getyPos(), GameData.getInstance().getPlayers().get(index).getPowerUps().getsize(), GameData.getInstance().getPlayers().get(index).getPowerUps().getsize());
			}
			if(GameData.getInstance().getPlayers().get(index).getPowerDowns()!=null) {
				g.setColor(GameData.getInstance().getPlayers().get(index).getPowerDowns().getColor());
				g.fillOval(GameData.getInstance().getPlayers().get(index).getPowerDowns().getxPos(), GameData.getInstance().getPlayers().get(index).getPowerDowns().getyPos(), GameData.getInstance().getPlayers().get(index).getPowerDowns().getsize(), GameData.getInstance().getPlayers().get(index).getPowerDowns().getsize());
			}



		}

		for(int i = 0; i<player.getS().getLengthOfSnake(); i++) {

			if(i==0 && player.getS().isRight()) {
				if(player.getS().getColor() == 0) {
					rightMouth[playerIndex] = new ImageIcon("rightmouth.png");
					rightMouth[playerIndex].paintIcon(this, g, player.getS().getSnakeXlength()[0], player.getS().getSnakeYlength()[0]);
				}else if(player.getS().getColor() == 1) {
					rightMouth[playerIndex] = new ImageIcon("rightmouth1.png");
					rightMouth[playerIndex].paintIcon(this, g, player.getS().getSnakeXlength()[0], player.getS().getSnakeYlength()[0]);
				}
				else if(player.getS().getColor() == 2) {
					rightMouth[playerIndex] = new ImageIcon("rightmouth2.png");
					rightMouth[playerIndex].paintIcon(this, g, player.getS().getSnakeXlength()[0], player.getS().getSnakeYlength()[0]);
				}
				else if(player.getS().getColor() == 3) {
					rightMouth[playerIndex] = new ImageIcon("rightmouth3.png");
					rightMouth[playerIndex].paintIcon(this, g, player.getS().getSnakeXlength()[0], player.getS().getSnakeYlength()[0]);
				}else if(player.getS().getColor() == 4) {
					rightMouth[playerIndex] = new ImageIcon("rightmouth4.png");
					rightMouth[playerIndex].paintIcon(this, g, player.getS().getSnakeXlength()[0], player.getS().getSnakeYlength()[0]);
				}

			}
			if(i==0 && player.getS().isLeft()) {
				if(player.getS().getColor() == 0) {
					leftMouth[playerIndex] = new ImageIcon("leftmouth.png");
					leftMouth[playerIndex].paintIcon(this, g, player.getS().getSnakeXlength()[0], player.getS().getSnakeYlength()[0]);
				}else if(player.getS().getColor() == 1) {
					leftMouth[playerIndex] = new ImageIcon("leftmouth1.png");
					leftMouth[playerIndex].paintIcon(this, g, player.getS().getSnakeXlength()[0], player.getS().getSnakeYlength()[0]);
				}
				else if(player.getS().getColor() == 2) {
					leftMouth[playerIndex] = new ImageIcon("leftmouth2.png");
					leftMouth[playerIndex].paintIcon(this, g, player.getS().getSnakeXlength()[0], player.getS().getSnakeYlength()[0]);
				}
				else if(player.getS().getColor() == 3) {
					leftMouth[playerIndex] = new ImageIcon("leftmouth3.png");
					leftMouth[playerIndex].paintIcon(this, g, player.getS().getSnakeXlength()[0], player.getS().getSnakeYlength()[0]);
				}else if(player.getS().getColor() == 4) {
					leftMouth[playerIndex] = new ImageIcon("leftmouth4.png");
					leftMouth[playerIndex].paintIcon(this, g, player.getS().getSnakeXlength()[0], player.getS().getSnakeYlength()[0]);
				}
			}
			if(i==0 && player.getS().isDown()) {
				if(player.getS().getColor() == 0) {
					downMouth[playerIndex] = new ImageIcon("downmouth.png");
					downMouth[playerIndex].paintIcon(this, g, player.getS().getSnakeXlength()[0], player.getS().getSnakeYlength()[0]);
				}else if(player.getS().getColor() == 1) {
					downMouth[playerIndex] = new ImageIcon("downmouth1.png");
					downMouth[playerIndex].paintIcon(this, g, player.getS().getSnakeXlength()[0], player.getS().getSnakeYlength()[0]);
				}
				else if(player.getS().getColor() == 2) {
					downMouth[playerIndex] = new ImageIcon("downmouth2.png");
					downMouth[playerIndex].paintIcon(this, g, player.getS().getSnakeXlength()[0], player.getS().getSnakeYlength()[0]);
				}
				else if(player.getS().getColor() == 3) {
					downMouth[playerIndex] = new ImageIcon("downmouth3.png");
					downMouth[playerIndex].paintIcon(this, g, player.getS().getSnakeXlength()[0], player.getS().getSnakeYlength()[0]);
				}else if(player.getS().getColor() == 4) {
					downMouth[playerIndex] = new ImageIcon("downmouth4.png");
					downMouth[playerIndex].paintIcon(this, g, player.getS().getSnakeXlength()[0], player.getS().getSnakeYlength()[0]);
				}
			}
			if(i==0 && player.getS().isUp()) {
				if(player.getS().getColor() == 0) {
					upMouth[playerIndex] = new ImageIcon("upmouth.png");
					upMouth[playerIndex].paintIcon(this, g, player.getS().getSnakeXlength()[0], player.getS().getSnakeYlength()[0]);
				}else if(player.getS().getColor() == 1) {
					upMouth[playerIndex] = new ImageIcon("upmouth1.png");
					upMouth[playerIndex].paintIcon(this, g, player.getS().getSnakeXlength()[0], player.getS().getSnakeYlength()[0]);
				}
				else if(player.getS().getColor() == 2) {
					upMouth[playerIndex] = new ImageIcon("upmouth2.png");
					upMouth[playerIndex].paintIcon(this, g, player.getS().getSnakeXlength()[0], player.getS().getSnakeYlength()[0]);
				}
				else if(player.getS().getColor() == 3) {
					upMouth[playerIndex] = new ImageIcon("upmouth3.png");
					upMouth[playerIndex].paintIcon(this, g, player.getS().getSnakeXlength()[0], player.getS().getSnakeYlength()[0]);
				}else if(player.getS().getColor() == 4) {
					upMouth[playerIndex] = new ImageIcon("upmouth4.png");
					upMouth[playerIndex].paintIcon(this, g, player.getS().getSnakeXlength()[0], player.getS().getSnakeYlength()[0]);
				}
			}

			if(i != 0) {
				if(player.getS().getColor() == 0) {
					ImageIcon snake = new ImageIcon("snakeimage.png");
					snakeImage[playerIndex]=snake;
					snakeImage[playerIndex].paintIcon(this, g, player.getS().getSnakeXlength()[i], player.getS().getSnakeYlength()[i]);
				}else if(player.getS().getColor() == 1) {
					ImageIcon snake = new ImageIcon("snakeimage1.png");
					snakeImage[playerIndex]=snake;
					snakeImage[playerIndex].paintIcon(this, g, player.getS().getSnakeXlength()[i], player.getS().getSnakeYlength()[i]);
				}
				else if(player.getS().getColor() == 2) {
					ImageIcon snake = new ImageIcon("snakeimage2.png");
					snakeImage[playerIndex]=snake;
					snakeImage[playerIndex].paintIcon(this, g, player.getS().getSnakeXlength()[i], player.getS().getSnakeYlength()[i]);
				}
				else if(player.getS().getColor() == 3) {
					ImageIcon snake = new ImageIcon("snakeimage3.png");
					snakeImage[playerIndex]=snake;
					snakeImage[playerIndex].paintIcon(this, g, player.getS().getSnakeXlength()[i], player.getS().getSnakeYlength()[i]);
				}else if(player.getS().getColor() == 4) {
					ImageIcon snake = new ImageIcon("snakeimage4.png");
					snakeImage[playerIndex]=snake;
					snakeImage[playerIndex].paintIcon(this, g, player.getS().getSnakeXlength()[i], player.getS().getSnakeYlength()[i]);
				}
			}

		}
		for(int i=0;i<foods.size(); i++) {        
			if(Math.abs(foods.get(i).getxPos()-player.getS().getSnakeXlength()[0])<25 && Math.abs(foods.get(i).getyPos()-player.getS().getSnakeYlength()[0])<25) {
				player.getS().setLengthOfSnake(player.getS().getLengthOfSnake()+1 );
				player.setScore(player.getScore()+1);
				int x = rand.nextInt(34);
				int y = rand.nextInt(17);

				float r = rand.nextFloat();
				float gC = rand.nextFloat();
				float b = rand.nextFloat();
				Food f = new Food(0,0,25,Color.DARK_GRAY);
				Food p = new Food(f.getxPositions()[x],f.getyPositions()[y],f.getsize(), new Color(r,gC,b));
				player.getFoods()[i]=p;
				foods.set(i, p);
				g.setColor(foods.get(i).getColor());
				g.fillOval(f.getxPositions()[x], f.getyPositions()[y], foods.get(i).getsize(), foods.get(i).getsize());

				int pos = rand.nextInt(2);
				System.out.println("pos"+pos);
				if(pos == 0) {
					power = true;
				}else {
					power = false;
				}

				if(power) {
					int pos2= rand.nextInt(2);
					System.out.println("pos2"+pos);
					if(pos2 == 0 && !powerUp[playerIndex]) {
						int aPos = rand.nextInt(30);
						int bPos = rand.nextInt(12); 
						PowerUp pu = new PowerUp(aPos,bPos);
						player.setPowerUps(new PowerUp(pu.getxPositions()[aPos],pu.getyPositions()[bPos]));
						powerUpList[playerIndex] =new PowerUp(pu.getxPositions()[aPos],pu.getyPositions()[bPos]);
						powerUp[playerIndex] = true;
						powerUpSec[playerIndex] = System.currentTimeMillis();
						delay = 100;
						timer.setDelay(delay);
					}else if (pos2 == 1 && !powerDown[playerIndex]){
						int aPos = rand.nextInt(30);
						int bPos = rand.nextInt(12);
						PowerDown pd = new PowerDown(aPos,bPos);
						player.setPowerDowns(new PowerDown(pd.getxPositions()[aPos],pd.getyPositions()[bPos]));
						powerDownList[playerIndex]=new PowerDown(pd.getxPositions()[aPos],pd.getyPositions()[bPos]);
						powerDown[playerIndex] = true;
						powerDownSec[playerIndex] = System.currentTimeMillis();
						delay = 300;
						timer.setDelay(delay);
					}		

				}
			}
		}
		for(int i=0;i<poisons.size(); i++) { 
			if(Math.abs(poisons.get(i).getxPos()-player.getS().getSnakeXlength()[0])<25 && Math.abs(poisons.get(i).getyPos()-player.getS().getSnakeYlength()[0])<25) {
				player.getS().setLengthOfSnake(player.getS().getLengthOfSnake()-1 );
				player.setScore(player.getScore()-1);
				int x = rand.nextInt(34);
				int y = rand.nextInt(17);

				Food f = new Poison(0,0);
				Poison p = new Poison(f.getxPositions()[x],f.getyPositions()[y]);
				player.getPoisons()[i]= p;
				poisons.set(i, p);
				g.setColor(player.getPoisons()[i].getColor());
				g.fillOval(f.getxPositions()[x], f.getyPositions()[y], player.getPoisons()[i].getsize(), player.getPoisons()[i].getsize());

				int pos = rand.nextInt(2);
				System.out.println("pos"+pos);
				if(pos == 0) {
					power = true;
				}else {
					power = false;
				}

				if(power) {
					int pos2= rand.nextInt(2);
					System.out.println("pos2"+pos);
					if(pos2 == 0 && !powerUp[playerIndex]) {
						int aPos = rand.nextInt(30);
						int bPos = rand.nextInt(12); 
						PowerUp pu = new PowerUp(aPos,bPos);
						player.setPowerUps(new PowerUp(pu.getxPositions()[aPos],pu.getyPositions()[bPos]));
						powerUpList[playerIndex]=new PowerUp(pu.getxPositions()[aPos],pu.getyPositions()[bPos]);
						powerUp[playerIndex] = true;
						powerUpSec[playerIndex] = System.currentTimeMillis();


					}else if (pos2 == 1 && !powerDown[playerIndex]){
						int aPos = rand.nextInt(30);
						int bPos = rand.nextInt(12);
						PowerDown pd = new PowerDown(aPos,bPos);
						player.setPowerDowns(new PowerDown(pd.getxPositions()[aPos],pd.getyPositions()[bPos]));
						powerDownList[playerIndex]=new PowerDown(pd.getxPositions()[aPos],pd.getyPositions()[bPos]);
						powerDown[playerIndex] = true;
						powerDownSec[playerIndex] = System.currentTimeMillis();

					}		

				}
			}
		}

		if(player.getPowerDowns()!=null) {
			if(Math.abs(player.getPowerDowns().getxPos()-player.getS().getSnakeXlength()[0])<25 && Math.abs(player.getPowerDowns().getyPos()-player.getS().getSnakeYlength()[0])<25) {
				player.getS().setLengthOfSnake(player.getS().getLengthOfSnake()-1 );
				player.setScore(player.getScore()-1);
				powerDown[playerIndex] = false;
				powerDownSec[playerIndex] = 0;
				player.setPowerDowns(null);
				delay = 300;
				timer.setDelay(delay);
			}

		}

		if(player.getPowerUps()!=null) {
			if(Math.abs(player.getPowerUps().getxPos()-player.getS().getSnakeXlength()[0])<25 && Math.abs(player.getPowerUps().getyPos()-player.getS().getSnakeYlength()[0])<25) {
				player.getS().setLengthOfSnake(player.getS().getLengthOfSnake()+1 );
				player.setScore(player.getScore()+1);
				powerUp[playerIndex] = false;
				powerUpSec[playerIndex] = 0;
				player.setPowerUps(null);
				delay = 100;
				timer.setDelay(delay);
			}
		}
		for(int i = 0; i<powerUp.length;i++) {
			if(powerUp[i]) {
				g.setColor(GameData.getInstance().getPlayers().get(i).getPowerUps().getColor());
				g.fillOval(GameData.getInstance().getPlayers().get(i).getPowerUps().getxPos(), GameData.getInstance().getPlayers().get(i).getPowerUps().getyPos(), GameData.getInstance().getPlayers().get(i).getPowerUps().getsize(), GameData.getInstance().getPlayers().get(i).getPowerUps().getsize());

			}

			
		}
		
		for(int i=0; i<powerDown.length;i++) {
			if(powerDown[i]) {
				g.setColor(GameData.getInstance().getPlayers().get(i).getPowerDowns().getColor());
				g.fillOval(GameData.getInstance().getPlayers().get(i).getPowerDowns().getxPos(), GameData.getInstance().getPlayers().get(i).getPowerDowns().getyPos(), GameData.getInstance().getPlayers().get(i).getPowerDowns().getsize(), GameData.getInstance().getPlayers().get(i).getPowerDowns().getsize());
			}
		}


		g.setColor(Color.BLACK);
		g.setFont(new Font("arial",Font.PLAIN,14));
		g.drawString("Score: " + player.getScore(), getBounds().width - 150, 50);

		g.setColor(Color.BLACK);
		g.setFont(new Font("arial",Font.PLAIN,14));
		g.drawString("Length: " + player.getS().getLengthOfSnake(), getBounds().width - 150, 70);

		g.setColor(Color.BLACK);
		g.setFont(new Font("arial",Font.PLAIN,14));
		long sec = (System.currentTimeMillis()-startTime)/1000;
		g.drawString("Time: " + sec, getBounds().width - 150, 90);

		g.setColor(Color.BLACK);
		g.setFont(new Font("arial",Font.PLAIN,14));
		g.drawString("Speed: " + delay, getBounds().width - 150, 110);

		if(player.getS().getLengthOfSnake()<3) {
			player.getS().setLeft(false);
			player.getS().setRight(false);
			player.getS().setDown(false);
			player.getS().setUp(false);
			g.setColor(Color.WHITE);
			g.setFont(new Font("arial",Font.BOLD,50));
			g.drawString("GAME OVER", getBounds().width/2 - 120, 134 + (getBounds().height-134)/2);
			ArrayList<Double> scores = new ArrayList<Double>();
			ArrayList<String> usernames = new ArrayList<String>();
			for(int i =0; i<GameData.getInstance().getPlayers().size();i++) {
				scores.add(GameData.getInstance().getPlayers().get(i).getScore());
				usernames.add(GameData.getInstance().getPlayers().get(i).getUsername());
			}
			for(int i =0; i<GameData.getInstance().getPlayers().size() - 1;i++) {
				if(scores.get(i)<scores.get(i+1)) {
					double temp = scores.get(i);
					String ts = usernames.get(i);
					scores.set(i, scores.get(i+1));
					scores.set(i+1, temp);
					usernames.set(i, usernames.get(i+1));
					usernames.set(i+1, ts);
				}
			}
			int height = 134 + (getBounds().height-134)/2;
			for(int i =0; i<GameData.getInstance().getPlayers().size();i++) {
				height = height + 10;
				g.setColor(Color.WHITE);
				g.setFont(new Font("arial",Font.PLAIN,10));
				g.drawString(i+"."+usernames.get(i)+":"+scores.get(i), getBounds().width/2 - 120, height);
			}
			
		}

		g.dispose();
	}

	public void actionPerformed(ActionEvent e) {
		timer.start();
		if(player.getS().isRight()) {
			for(int i = player.getS().getLengthOfSnake() -1; i>=0; i--) {
				player.getS().getSnakeYlength()[i+1] = player.getS().getSnakeYlength()[i];
			}
			for(int i = player.getS().getLengthOfSnake() -1; i>=0; i--) {

				if(i==0) {
					player.getS().getSnakeXlength()[i]=player.getS().getSnakeXlength()[i]+25;
				}
				else {
					player.getS().getSnakeXlength()[i]=player.getS().getSnakeXlength()[i-1];
				}
				if(player.getS().getSnakeXlength()[i] > getBounds().width - 40 ) {
					player.getS().getSnakeXlength()[i] = 11;
				}
			}
			repaint();

		}
		if(player.getS().isLeft()) {
			for(int i = player.getS().getLengthOfSnake() -1; i>=0; i--) {
				player.getS().getSnakeYlength()[i+1] = player.getS().getSnakeYlength()[i];
			}
			for(int i = player.getS().getLengthOfSnake() -1; i>=0; i--) {

				if(i==0) {
					player.getS().getSnakeXlength()[i]=player.getS().getSnakeXlength()[i]-25;
				}
				else {
					player.getS().getSnakeXlength()[i]=player.getS().getSnakeXlength()[i-1];
				}
				if(player.getS().getSnakeXlength()[i] < 11 ) {
					player.getS().getSnakeXlength()[i] = getBounds().width - 40;
				}
			}
			repaint();

		}
		if(player.getS().isUp()) {
			for(int i = player.getS().getLengthOfSnake() -1; i>=0; i--) {
				player.getS().getSnakeXlength()[i+1] = player.getS().getSnakeXlength()[i];
			}
			for(int i = player.getS().getLengthOfSnake() -1; i>=0; i--) {

				if(i==0) {
					player.getS().getSnakeYlength()[i]=player.getS().getSnakeYlength()[i]-25;
				}
				else {
					player.getS().getSnakeYlength()[i]=player.getS().getSnakeYlength()[i-1];
				}
				if(player.getS().getSnakeYlength()[i] < 128) {
					player.getS().getSnakeYlength()[i] = getBounds().height - 40;
				}
			}
			repaint();

		}
		if(player.getS().isDown()) {
			for(int i = player.getS().getLengthOfSnake() -1; i>=0; i--) {
				player.getS().getSnakeXlength()[i+1] = player.getS().getSnakeXlength()[i];
			}
			for(int i = player.getS().getLengthOfSnake() -1; i>=0; i--) {

				if(i==0) {
					player.getS().getSnakeYlength()[i]=player.getS().getSnakeYlength()[i]+25;
				}
				else {
					player.getS().getSnakeYlength()[i]=player.getS().getSnakeYlength()[i-1];
				}
				if(player.getS().getSnakeYlength()[i] > getBounds().height - 40) {
					player.getS().getSnakeYlength()[i] = 128;
				}
			}
			repaint();

		}

	}

	public void keyPressed(KeyEvent e) {
		keyDelay ++;
		if(keyDelay > 3 ) {
			delay = 100;
			timer.setDelay(delay);
		}

		if(e.getKeyCode() == KeyEvent.VK_RIGHT) {
			moves++;
			player.getS().setRight(true);
			if(!player.getS().isLeft()) {
				player.getS().setRight(true);
			}else {
				player.getS().setRight(false);
				player.getS().setLeft(true);
			}

			player.getS().setUp(false);
			player.getS().setDown(false);

		}
		if(e.getKeyCode() == KeyEvent.VK_LEFT) {
			moves++;
			player.getS().setLeft(true);
			if(!player.getS().isRight()) {
				player.getS().setLeft(true);
			}else {
				player.getS().setLeft(false);
				player.getS().setRight(true);
			}

			player.getS().setUp(false);
			player.getS().setDown(false);
		}
		if(e.getKeyCode() == KeyEvent.VK_UP) {
			moves++;
			player.getS().setUp(true);
			if(!player.getS().isDown()) {
				player.getS().setUp(true);
			}else {
				player.getS().setUp(false);
				player.getS().setDown(true);
			}

			player.getS().setLeft(false);
			player.getS().setRight(false);
		}
		if(e.getKeyCode() == KeyEvent.VK_DOWN) {
			moves++;
			player.getS().setDown(true);
			if(!player.getS().isUp()) {
				player.getS().setDown(true);
			}else {
				player.getS().setDown(false);
				player.getS().setUp(true);
			}

			player.getS().setLeft(false);
			player.getS().setRight(false);
		}
	}

	public void keyReleased(KeyEvent e) {
		delay = 200;
		timer.setDelay(delay);

	}

	public void keyTyped(KeyEvent e) {

	}

	@Override
	public void mouseClicked(MouseEvent e) {
		keyDelay ++;
		if(keyDelay > 0 ) {
			delay = 100;
			timer.setDelay(delay);
		}

	}

	@Override
	public void mouseEntered(MouseEvent e) {
	}

	@Override
	public void mouseExited(MouseEvent e) {
		delay = 200;
		timer.setDelay(delay);
	}

	@Override
	public void mousePressed(MouseEvent e) {
		keyDelay ++;
		if(keyDelay > 0 ) {
			delay = 100;
			timer.setDelay(delay);
		}
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		delay = 200;
		timer.setDelay(delay);
	}

	@Override
	public void mouseDragged(MouseEvent e) {

	}

	@Override
	public void mouseMoved(MouseEvent e) {
		if(MouseInfo.getPointerInfo().getLocation().getX()>player.getS().getSnakeXlength()[0] ) {
			moves++;
			player.getS().setRight(true);
			if(!player.getS().isLeft()) {
				player.getS().setRight(true);
			}else {
				player.getS().setRight(false);
				player.getS().setLeft(true);
			}

			player.getS().setUp(false);
			player.getS().setDown(false);

		}

		if(MouseInfo.getPointerInfo().getLocation().getX()<player.getS().getSnakeXlength()[0]) {
			moves++;
			player.getS().setLeft(true);
			if(!player.getS().isRight()) {
				player.getS().setLeft(true);
			}else {
				player.getS().setLeft(false);
				player.getS().setRight(true);
			}

			player.getS().setUp(false);
			player.getS().setDown(false);

		}
		if(MouseInfo.getPointerInfo().getLocation().getY()>player.getS().getSnakeYlength()[0]) {
			moves++;
			player.getS().setUp(true);
			if(!player.getS().isDown()) {
				player.getS().setUp(true);
			}else {
				player.getS().setUp(false);
				player.getS().setDown(true);
			}

			player.getS().setLeft(false);
			player.getS().setRight(false);

		}
		if(MouseInfo.getPointerInfo().getLocation().getY()<player.getS().getSnakeYlength()[0]) {
			moves++;
			player.getS().setDown(true);
			if(!player.getS().isUp()) {
				player.getS().setDown(true);
			}else {
				player.getS().setDown(false);
				player.getS().setUp(true);
			}

			player.getS().setLeft(false);
			player.getS().setRight(false);
		}
	}

}
