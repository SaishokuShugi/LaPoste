package poste;

public class PaperParticle
{
	public int		t0;
	public float	x, y;

	public PaperParticle()
	{
		t0 = PhaseVoiture.time;
		x = PhaseVoiture.VoitureX;
		y = PhaseVoiture.VoitureY + 110;
	}

}
