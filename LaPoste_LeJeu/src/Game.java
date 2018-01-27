import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

public class Game extends StateBasedGame {
	protected static AppGameContainer app;

	public Game(String name) {
		super(name);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void initStatesList(GameContainer gc) throws SlickException {
		// TODO Auto-generated method stub

		addState(new MenuStart());
		addState(new PhaseVoiture());

		// gc.setTargetFrameRate(120);

	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		try {
			app = new AppGameContainer(new Game("jeu"));
			app.setDisplayMode(1600, 900, false);
			app.setShowFPS(true);
			app.start();
			app.setVSync(false);

		} catch (SlickException e) {
			e.printStackTrace();
		}
	}

}
