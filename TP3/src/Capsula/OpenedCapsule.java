package nemo;

public class OpenedCapsule extends Capsule {

	public void openCapsule(Nemo nemo) {
		throw new UnsupportedOperationException( "La capsula no puede ser liberada" );
	}
	
	public boolean capsula() {
		return true;
	}
}
