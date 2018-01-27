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
	public static Image			startBackground;

	private static int			choix	= 0;

	@SuppressWarnings("unchecked")
	@Override
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException
	{
		// TODO Auto-generated method stub
		startBackground = new Image("/res/testMenuBackground.png");
		Game.Jazzy.loop();
	}

	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException
	{
		startBackground.draw(75, 0);
		//g.setColor(new Color(255, 255, 255, 100));
		//g.fillRect(0, 0, Game.app.getWidth(), Game.app.getHeight());
		Game.slicker.drawString(700, 400, "Start", Color.lightGray);
		Game.slicker.drawString(660, 500, "Paramettres", Color.lightGray);
		g.setColor(Color.lightGray);
		g.drawRect(635, 385 + ((float) choix * 100), 200, 50);
		for (int i = 0; i < 100; i++)
		{
			for (int j = 0; j < 100; j++)
			{
				Game.boom.draw(60*i, 60*j);
			}
		}
	}

	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int g) throws SlickException
	{
		boolean down = Game.input.isKeyPressed(Input.KEY_DOWN), up = Game.input.isKeyPressed(Input.KEY_UP),
				enter = Game.input.isKeyPressed(Input.KEY_ENTER);
		if (down)
			choix++;
		else if (up & choix > 0)
			choix--;
		else if (up & choix == 0)
			choix = 1;
		choix %= 2;

		if (enter)
		{
			sbg.enterState(choix);
		}

	}

	@Override
	public int getID()
	{
		return 100;
	}

}
