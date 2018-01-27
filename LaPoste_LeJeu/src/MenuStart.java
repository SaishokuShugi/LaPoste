import java.awt.Font;
import java.util.Iterator;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.UnicodeFont;
import org.newdawn.slick.font.effects.ColorEffect;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class MenuStart extends BasicGameState
{
	public static Image	startBackground, R4;

	private static int	choix	= 0;
	private int			time	= 0;

	@SuppressWarnings("unchecked")
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
		R4.draw(1000, 550);
		g.setColor(Color.yellow);
		g.fillRect(635, 445, 200, 50);
		g.fillRect(635, 545, 200, 50);
		g.fillRect(635, 645, 200, 50);
		Game.slicker.drawString(700, 460, "Start", Color.lightGray);
		Game.slicker.drawString(660, 560, "Parametres", Color.lightGray);
		Game.slicker.drawString(700, 660, "Exit", Color.lightGray);
		g.setColor(Color.blue);
		g.setLineWidth(5);
		g.drawRect(635, 445 + ((float) choix * 100), 200, 50);
	}

	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException
	{
		boolean down = Game.input.isKeyPressed(Input.KEY_DOWN), up = Game.input.isKeyPressed(Input.KEY_UP),
				enter = Game.input.isKeyPressed(Input.KEY_ENTER);
		if (down)
			choix++;
		else if (up & choix > 0)
			choix--;
		else if (up & choix == 0)
			choix = 2;
		choix %= 3;

		if (enter)
		{
			if (choix == 2)
			{
				System.exit(0);
			}
			Game.state = choix;
			sbg.enterState(choix);
		}
		time += delta;

	}

	@Override
	public int getID()
	{
		return 100;
	}

}
