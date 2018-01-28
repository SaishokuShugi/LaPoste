import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class PhasePlateform extends BasicGameState
{
	protected Image						bg, imgPerso;
	protected SpriteSheet				perso, persoL, persoR, persoJ;
	protected Vector2f					Pperso;
	final float							g				= 9.81f;
	private Rectangle					hitboxPerso;
	private static ArrayList<Rectangle>	Platforms		= new ArrayList<Rectangle>();
	private static ArrayList<Rectangle>	Portes			= new ArrayList<Rectangle>();
	public static ArrayList<String>		Destinataire	= new ArrayList<String>();
	public static ArrayList<String>		Proprietaire	= new ArrayList<String>();
	public static ArrayList<String>		Lettre			= new ArrayList<String>();
	public static int					porteOpen		= 0;
	private Vector2f					Vperso;
	@SuppressWarnings("unused")
	private boolean						facingLeft		= false, onGround = false;
	private boolean[]					isvoid			= new boolean[16];
	private Image[]						bgtex			= new Image[16];
	protected float						timeP			= 0;
	private SpriteSheet					bgvoid, bgmaison, bgscales;

	public PhasePlateform()
	{
	}

	private void initmap()
	{
		bgtex[0] = bgmaison.getSprite(Game.random.nextInt(2), Game.random.nextInt(2));
		bgtex[1] = bgvoid.getSprite(Game.random.nextInt(2), Game.random.nextInt(2));
		bgtex[2] = bgmaison.getSprite(Game.random.nextInt(2), Game.random.nextInt(2));
		bgtex[3] = bgmaison.getSprite(Game.random.nextInt(2), Game.random.nextInt(2));
		bgtex[4] = bgvoid.getSprite(Game.random.nextInt(2), Game.random.nextInt(2));
		bgtex[5] = bgscales.getSprite(1, 1);
		bgtex[6] = bgmaison.getSprite(Game.random.nextInt(2), Game.random.nextInt(2));
		bgtex[7] = bgmaison.getSprite(Game.random.nextInt(2), Game.random.nextInt(2));
		bgtex[8] = bgscales.getSprite(0, 1);
		bgtex[9] = bgmaison.getSprite(Game.random.nextInt(2), Game.random.nextInt(2));
		bgtex[10] = bgvoid.getSprite(Game.random.nextInt(2), Game.random.nextInt(2));
		bgtex[11] = bgmaison.getSprite(Game.random.nextInt(2), Game.random.nextInt(2));
		bgtex[12] = bgmaison.getSprite(Game.random.nextInt(2), Game.random.nextInt(2));
		bgtex[13] = bgmaison.getSprite(Game.random.nextInt(2), Game.random.nextInt(2));
		bgtex[14] = bgscales.getSprite(1, 0);
		bgtex[15] = bgmaison.getSprite(Game.random.nextInt(2), Game.random.nextInt(2));

		isvoid[0] = false;
		isvoid[1] = true;
		isvoid[2] = false;
		isvoid[3] = false;
		isvoid[4] = true;
		isvoid[5] = false;
		isvoid[6] = false;
		isvoid[7] = false;
		isvoid[8] = false;
		isvoid[9] = false;
		isvoid[10] = true;
		isvoid[11] = false;
		isvoid[12] = false;
		isvoid[13] = false;
		isvoid[14] = false;
		isvoid[15] = false;

		for (int i = 0; i < 4; i++)
			for (int j = 0; j < 4; j++)
				{
					if (!isvoid[j + i * 4])
						Platforms.add(new Rectangle(i * Game.app.getWidth() * .25f,
								(j + 1) * Game.app.getHeight() * .25f, Game.app.getWidth() * .25f, 1));
				}

		Platforms.add(new Rectangle(400 + 74, 225 + 10, 100, 1));
		Platforms.add(new Rectangle(400 + 75, 225 + 113, 99, 1));
		Platforms.add(new Rectangle(400 + 218, 225 + 60, 105, 1));
		Platforms.add(new Rectangle(400 + 221, 225 + 150, 101, 1));
		Platforms.add(new Rectangle(0 + 24, 450 + 3, 16, 1));
		Platforms.add(new Rectangle(0 + 80, 450 + 35, 41, 1));
		Platforms.add(new Rectangle(0 + 181, 450 + 79, 23, 1));
		Platforms.add(new Rectangle(0 + 147, 450 + 150, 25, 1));
		Platforms.add(new Rectangle(0 + 207, 450 + 142, 17, 1));
		Platforms.add(new Rectangle(0 + 263, 450 + 114, 17, 1));
		Platforms.add(new Rectangle(0 + 317, 450 + 78, 17, 1));
		Platforms.add(new Rectangle(0 + 372, 450 + 51, 18, 1));
		Platforms.add(new Rectangle(0 + 311, 450 + 18, 18, 1));
		Platforms.add(new Rectangle(0 + 256, 450 + 3, 16, 1));
		Platforms.add(new Rectangle(800 + 6, 675 + 5, 18, 1));
		Platforms.add(new Rectangle(800 + 57, 675 + 13, 21, 1));
		Platforms.add(new Rectangle(800 + 109, 675 + 18, 26, 1));
		Platforms.add(new Rectangle(800 + 183, 675 + 58, 40, 1));
		Platforms.add(new Rectangle(800 + 186, 675 + 142, 34, 1));
		Platforms.add(new Rectangle(800 + 273, 675 + 19, 26, 1));
		Platforms.add(new Rectangle(800 + 341, 675 + 18, 26, 1));
		Platforms.add(new Rectangle(800 + 384, 675 + 5, 14, 1));

		Portes.add(new Rectangle(207, 147, 31, 61));
		Portes.add(new Rectangle(1007, 147, 31, 61));
		Portes.add(new Rectangle(1407, 147, 31, 61));
		Portes.add(new Rectangle(1007, 397, 31, 61));
		Portes.add(new Rectangle(1407, 397, 31, 61));
		Portes.add(new Rectangle(607, 597, 31, 61));
		Portes.add(new Rectangle(1407, 597, 31, 61));
		Portes.add(new Rectangle(207, 822, 31, 61));
		Portes.add(new Rectangle(607, 822, 31, 61));
		Portes.add(new Rectangle(1407, 822, 31, 61));

	}

	private void initdestinataire() throws IOException
	{
		BufferedReader bf = new BufferedReader(new FileReader("res/Dico.txt"));
		while (bf.readLine() != null)
			{
				Proprietaire.add(bf.readLine());
			}
		for (int i = 0; i < 20; i++)
			{
				Proprietaire.set(i, Proprietaire.get(Game.random.nextInt(Proprietaire.size())));
			}
		for (int j = 0; j < 10; j++)
			{
				Destinataire.add(Proprietaire.get(j));
			}
		for (int k = 0; k < Game.nlettres; k++)
			{
				Lettre.add(Destinataire.get(Game.random.nextInt(Destinataire.size())));
			}

	}

	@Override
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException
	{
		bg = new Image("res/back.jpg");
		bgvoid = new SpriteSheet("res/Rien.png", 400, 225);
		bgmaison = new SpriteSheet("res/Maison.png", 400, 225);
		bgscales = new SpriteSheet("res/Plateforme.png", 400, 225);

		perso = new SpriteSheet("res/Postier.png", 42, 74);
		imgPerso = perso.getSprite(8, 1);
		// Platforms.add(new Rectangle(0, Game.app.getHeight() - 10,
		// Game.app.getWidth(), 20));
		Pperso = new Vector2f(100, 20);
		hitboxPerso = new Rectangle(Pperso.x + 10, Pperso.y + 15, 42 - 20, 74 - 15);
		Vperso = new Vector2f(0, 0);
		initmap();
		try
			{
				initdestinataire();
			} catch (IOException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
	}

	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException
	{
		for (int i = 0; i < 4; i++)
			for (int j = 0; j < 4; j++)
				{
					g.drawImage(bgtex[j + 4 * i], 400 * j, 225 * i);
				}
		g.drawImage(imgPerso, Pperso.x, Pperso.y);
		//g.draw(hitboxPerso);
		Game.shuriken.draw(0, 0);
		Game.slicker.drawString(20, 5, " X " + Integer.toString(Game.nlettres));
	}

	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException
	{

		if ((Game.input.isKeyPressed(Input.KEY_ESCAPE) || Game.input.isKeyPressed(Input.KEY_P)) & timeP > 250
				|| Game.nlettres <= 0)
			{
				timeP = 0;
				sbg.enterState(4);
			}
		timeP += delta;
		Vperso.scale(.999f);
		Vperso.y += .001 * delta;
		if (Game.input.isKeyDown(Input.KEY_LEFT))
			{
				Vperso.x = -.3f;
				facingLeft = true;
			}
		if (Game.input.isKeyDown(Input.KEY_RIGHT))
			{
				Vperso.x = .3f;
				facingLeft = false;
			}

		if (Game.input.isKeyPressed(Input.KEY_ENTER))
			{
				for (Rectangle p : Portes)
					{
						if (hitboxPerso.intersects(p))
							{
								porteOpen = Portes.indexOf(p);
								System.out.println(porteOpen);
								sbg.enterState(7);
							}
					}
			}

		if ((Game.input.isKeyDown(Input.KEY_SPACE) || Game.input.isKeyDown(Input.KEY_UP)) & onGround)
			{
				Vperso.y = -.5f;
				onGround = false;
			}
		Vector2f scaledV = new Vector2f(delta * Vperso.x, delta * Vperso.y);
		Vector2f opp = Pperso.copy();

		Pperso.add(scaledV);
		hitboxPerso.setBounds(Pperso.x + 10, Pperso.y + 40, 42 - 20, 74 - 40);
		for (Rectangle rectangle : Platforms)
			{
				if (rectangle.intersects(hitboxPerso))
					{
						onGround = true;
						Pperso = opp;
						hitboxPerso.setLocation(Pperso);
						Vperso.y = Vperso.y > 0. ? -.03f : .03f;
						Vperso.x *= 0;
						break;
					}
			}
		if (Vperso.y > 0)
			onGround = false;
		Pperso.x = Pperso.x < 0 ? 0 : Pperso.x > Game.app.getWidth() - 40 ? Game.app.getWidth() - 40 : Pperso.x;
	}

	@Override
	public int getID()
	{

		return 5;
	}

}
