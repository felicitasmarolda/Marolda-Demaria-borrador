package nemo;

public class Forward extends Comando {
	public static Character value = 'f';

	public void executeSpecific(Nemo nemo) {
		nemo.direction.forward(nemo);
	}

	public Character value() {
		return 'f';
	}
}