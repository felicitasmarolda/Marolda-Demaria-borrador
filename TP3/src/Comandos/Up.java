package nemo;

public class Up extends Comando {
	public static Character value = 'u';

	public void executeSpecific(Nemo nemo) {
		if (nemo.zCoord <= -1) {
			nemo.zCoord += 1;			
		}
	}

	public Character value() {
		return 'u';
	}
}