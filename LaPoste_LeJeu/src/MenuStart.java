import java.awt.Font;

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
	public static Font			UIFont1;
	public static UnicodeFont	slicker;
	private static int			choix	= 0;

	@SuppressWarnings("unchecked")
	@Override
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException
	{
		// TODO Auto-generated method stub
		startBackground = new Image("/res/testMenuBackground.png");
		try
		{
			UIFont1 = java.awt.Font.createFont(java.awt.Font.TRUETYPE_FONT, org.newdawn.slick.util.ResourceLoader.getResourceAsStream("res/SLICKER.TTF"));
			UIFont1 = UIFont1.deriveFont(java.awt.Font.PLAIN, 25.f); // You can change "PLAIN" to "BOLD" or "ITALIC"...
																		// and 16.f is the size of your font
			slicker = new UnicodeFont(UIFont1);
			slicker.addAsciiGlyphs();
			slicker.getEffects().add(new ColorEffect(java.awt.Color.white)); // You can change your color here, but you
																				// can also change it in the render{ ...
								 												// }
			slicker.addAsciiGlyphs();
			slicker.loadGlyphs();
		} catch (Exception e)
		{
			e.printStackTrace();
		}

	}

	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException
	{
		startBackground.draw(75, 0);
		g.setColor(new Color(255, 255, 255, 100));
		g.fillRect(0, 0, Game.app.getWidth(), Game.app.getHeight());
		slicker.drawString(700, 400, "Start", Color.lightGray);
		slicker.drawString(660, 500, "Paramettres", Color.lightGray);
		g.setColor(Color.lightGray);
		g.drawRect(635, 385 + ((float) choix * 100), 200, 50);
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
			choix = 4;
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
