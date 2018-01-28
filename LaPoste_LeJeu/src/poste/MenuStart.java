package poste;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class MenuStart extends BasicGameState
{
	public static Image	startBackground, R4;

	private static int	choix	= 0;
	private int			time	= 0, vx = 1000;
	private boolean		go		= false;

	@Override
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException
	{
		// TODO Auto-generated method stub
		startBackground = new Image("/res/back.jpg");
		R4 = new Image("/res/411UuE5sRPL.png");
		Game.Jazzy.loop();
	}

	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException
	{
		startBackground.draw(0, 0);
		R4.draw(vx, 550);
		g.setColor(Color.yellow);
		g.fillRect(435, 300, 600, 100);
		g.fillRect(635, 545, 200, 50);
		g.fillRect(635, 645, 200, 50);
		g.fillRect(635, 745, 200, 50);
		Game.slicker.drawString(700, 560, "Start", Color.lightGray);
		Game.slicker.drawString(660, 660, "Parametres", Color.lightGray);
		Game.slicker.drawString(700, 760, "Exit", Color.lightGray); 
		Game.titre.drawString(600, 330, "SuperMailBoy");

		g.setColor(Color.blue);
		g.setLineWidth(5);
		g.drawRect(635, 545 + ((float) choix * 100), 200, 50);
		g.setLineWidth(8);
		g.drawRect(435, 300, 600, 100);
	}

	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException
	{
		boolean down = Game.input.isKeyPressed(Input.KEY_DOWN), up = Game.input.isKeyPressed(Input.KEY_UP),
				enter = Game.input.isKeyPressed(Input.KEY_ENTER);

		if (enter)
		{
			go = true;
			Game.pouet.play();
		}

		if (go & time < 2000)
		{
			time += delta;
			vx += delta;
		} else if (go)
		{
			time = 0;
			go = false;
			vx = 1000;

			if (choix == 2)
			{
				System.exit(0);
			}
			Game.state = choix;
			sbg.enterState(choix);
		} else
		{
			if (down)
				choix++;
			else if (up & choix > 0)
				choix--;
			else if (up & choix == 0)
				choix = 2;
			choix %= 3;
		}
	}

	@Override
	public int getID()
	{
		return 100;
	}

}
