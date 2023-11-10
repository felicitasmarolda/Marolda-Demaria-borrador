package nemo.Depths;

import nemo.Nemo;

public class DeepSea extends DepthState{

	public void sendNemoUp(Nemo nemo) {
		nemo.executeUpwardsSlideMovement();
	}

	public void sendNemoDown(Nemo nemo) {
		nemo.executeDownwardsSlideMovement(new DeepSea());
	}
	
	public void shootCapsuleFromThisDepth(Nemo nemo) {
		throw new Error( "Intento de expulsión de cápsula en condiciones no óptimas. Nemo ha implosionado." );
	}
}