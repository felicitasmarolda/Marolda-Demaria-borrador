package nemo;

public class Left extends Comando {
	public static Character value = 'l';

	public void executeSpecific(Nemo nemo) {
		nemo.direction.left(nemo);
	}

	public Character value() {
		return 'l';
	}
}