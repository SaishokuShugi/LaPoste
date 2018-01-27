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
	private boolean[]					ispassage		= new boolean[16];
	private Image[]						bgtex		= new Image[16];

	public PhasePlateform()
	{
	}

	private void initmap()
	{
		for (int i = 0; i < 4; i++)
			for (int j = 0; j < 4; j++)
				{
					int n = Game.random.nextInt(4);
					ispassage[j + 4 * i] = j == n;
				}

	}

	@Override
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException
	{
		bg = new Image("res/back.jpg");
		perso = new SpriteSheet("res/Postier.png", 42, 74);
		imgPerso = perso.getSprite(8, 1);
		Platforms.add(new Rectangle(0, Game.app.getHeight() - 10, Game.app.getWidth(), 20));
		Pperso = new Vector2f(100, 20);
		hitboxPerso = new Rectangle(Pperso.x, Pperso.y, 42, 74);
		Vperso = new Vector2f(0, 0);
	}

	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException
	{
		g.drawImage(bg, 0, 0);
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
