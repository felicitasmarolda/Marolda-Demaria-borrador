package nemo;

public class Down extends Comando {
	public static Character value = 'd';

	public void executeSpecific(Nemo nemo) {
		nemo.zCoord -= 1;
	}

	public Character value() {
		return 'd';
	}
}