import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class PhasePlateform extends BasicGameState
{
	protected Image bg,imgPerso;
	protected SpriteSheet perso;
	protected float persoX,persoY;
	final float g = 9.81f;

	public PhasePlateform()
	{
	}

	@Override
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException
	{
		bg = new Image("res/back.jpg");
		perso = new SpriteSheet("res/Postier.png", 30, 54);
		imgPerso = perso.getSprite(0, 0);
		
	}

	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException
	{
		g.drawImage(bg, 0, 0);
	}

	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException
	{
		
	}

	@Override
	public int getID()
	{

		return 5;
	}

}
