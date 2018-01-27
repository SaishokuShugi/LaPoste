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
	protected SpriteSheet				perso;
	protected Vector2f					Pperso;
	final float							g			= 9.81f;
	private Rectangle					hitboxPerso;
	private static ArrayList<Rectangle>	Platforms	= new ArrayList<Rectangle>();
	private Vector2f					Vperso;
	private boolean						facingLeft	= false, onGround = false;
	private boolean[]					isvoid		= new boolean[16];
	private Image[]						bgtex		= new Image[16];

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
		//Platforms.add(new Rectangle(400+a,225+b,))
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
		//Platforms.add(new Rectangle(0, Game.app.getHeight() - 10, Game.app.getWidth(), 20));
		Pperso = new Vector2f(100, 20);
		hitboxPerso = new Rectangle(Pperso.x, Pperso.y, 42, 74);
		Vperso = new Vector2f(0, 0);
		initmap();

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
	}

	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException
	{
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
		if ((Game.input.isKeyDown(Input.KEY_SPACE) || Game.input.isKeyDown(Input.KEY_UP)) & onGround)
			{
				Vperso.y = -.5f;
				onGround = false;
			}
		Vector2f scaledV = new Vector2f(delta * Vperso.x, delta * Vperso.y);
		Vector2f opp = Pperso.copy();
		Pperso.add(scaledV);
		hitboxPerso.setLocation(Pperso);
		for (Rectangle rectangle : Platforms)
			{
				if (rectangle.intersects(hitboxPerso))
					{
						onGround = true;
						Pperso = opp;
						hitboxPerso.setLocation(Pperso);
						Vperso.y = -.03f;
						Vperso.x *= 0;
						break;
					}
			}
		if (Vperso.y > 0)
			onGround = false;
	}

	@Override
	public int getID()
	{

		return 5;
	}

}
