package nemo;

public class Right extends Comando {
	public static Character value = 'r';

	public void executeSpecific(Nemo nemo) {
		nemo.direction.right(nemo);
	}

	public Character value() {
		return 'r';
	}
}