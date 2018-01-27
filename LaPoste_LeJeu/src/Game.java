import java.awt.Font;
import java.awt.FontFormatException;
import java.io.IOException;
import java.util.Random;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.UnicodeFont;
import org.newdawn.slick.font.effects.ColorEffect;
import org.newdawn.slick.state.StateBasedGame;

public class Game extends StateBasedGame
{
	protected static AppGameContainer	app;
	protected static Input				input;
	protected static Random				random	= new Random();
	protected static int				Difficultee;
	public static Font					UIFont1;
	public static UnicodeFont			slicker;

	public Game(String name)
	{
		super(name);
		// TODO Auto-generated constructor stub
	}

	@SuppressWarnings("unchecked")
	@Override
	public void initStatesList(GameContainer gc) throws SlickException
	{
		try
		{
			UIFont1 = java.awt.Font.createFont(java.awt.Font.TRUETYPE_FONT, org.newdawn.slick.util.ResourceLoader.getResourceAsStream("res/SLICKER.TTF"));
		} catch (FontFormatException e)
		{
			e.printStackTrace();
		} catch (IOException e)
		{
			e.printStackTrace();
		}
		UIFont1 = UIFont1.deriveFont(java.awt.Font.PLAIN, 25.f);
		slicker = new UnicodeFont(UIFont1);
		slicker.addAsciiGlyphs();
		slicker.getEffects().add(new ColorEffect(java.awt.Color.white));
		slicker.addAsciiGlyphs();
		slicker.loadGlyphs();
		input = gc.getInput();
		addState(new MenuStart());
		addState(new PhaseVoiture());
		addState(new Paramettres());

		// gc.setTargetFrameRate(120);

	}

	public static void main(String[] args)
	{
		// TODO Auto-generated method stub

		try
		{
			app = new AppGameContainer(new Game("jeu"));
			app.setDisplayMode(1600, 900, false);
			app.setShowFPS(true);
			app.start();
			app.setVSync(false);

		} catch (SlickException e)
		{
			e.printStackTrace();
		}
	}

}
