package nemo;

public class ClosedCapsule extends Capsule {
	boolean state = false;

	public void openCapsule(Nemo nemo) {
		if (nemo.zCoord >= -1) {
			nemo.capsule = new OpenedCapsule();					
		}
		else {
			throw new UnsupportedOperationException( "La capsula no puede ser liberada" );
		} 
	}
	
	public boolean capsula() {
		return false;
	}
}