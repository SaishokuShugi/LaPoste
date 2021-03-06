import java.awt.Font;
import java.awt.FontFormatException;
import java.io.File;
import java.io.IOException;
import java.util.Random;

//import org.newdawn.slick.Animation;
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.Music;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.UnicodeFont;
import org.newdawn.slick.font.effects.ColorEffect;
import org.newdawn.slick.state.StateBasedGame;

public class Game extends StateBasedGame
{
	protected static AppGameContainer	app;
	protected static Input				input;
	protected static Random				random;
	public static Font					UIFont1;
	public static UnicodeFont			slicker, titre;
	public static Music					Jazzy;
	public static Sound					pouet;
	public static Animation				boom;
	public static int					state;
	public static int					nlettres	= 20;
	public static Image					shuriken;
	public static int					score =0;
	public static double 				timeinit=System.currentTimeMillis();

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
				UIFont1 = java.awt.Font.createFont(java.awt.Font.TRUETYPE_FONT,
						org.newdawn.slick.util.ResourceLoader.getResourceAsStream("res/SLICKER.TTF"));
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
		slicker.getEffects().add(new ColorEffect(java.awt.Color.BLUE));
		slicker.addAsciiGlyphs();
		slicker.loadGlyphs();
		UIFont1 = UIFont1.deriveFont(java.awt.Font.PLAIN, 38.f);
		titre = new UnicodeFont(UIFont1);
		titre.addAsciiGlyphs();
		titre.getEffects().add(new ColorEffect(java.awt.Color.BLUE));
		titre.addAsciiGlyphs();
		titre.loadGlyphs();
		input = gc.getInput();
		Jazzy = new Music("res/Blusy.ogg");
		pouet = new Sound("res/pouetpouet-SF.ogg");
		boom = new Animation(new SpriteSheet("res/explosion.png", 128, 128), 100);
		random = new Random();
		shuriken = new Image("res/enveloppeShuriken.png");
		

		addState(new MenuStart());
		addState(new PhaseVoiture());
		addState(new PhasePlateform());
		addState(new Parametres());
		addState(new Pause());
		addState(new GameOver());
		addState(new PhaseCourrier());
		addState(new Score());
		
		
		Voiture.voituresImg = new SpriteSheet("res/SpriteSheetCar.png", 128, 128);
		nlettres /= (Parametres.difficulte + 1);

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
