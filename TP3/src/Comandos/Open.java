package nemo;

public class Open extends Comando {
	public static Character value = 'm';

	public void executeSpecific(Nemo nemo) {
		nemo.capsule.openCapsule(nemo);
	}

	public Character value() {
		return 'm';
	}
}