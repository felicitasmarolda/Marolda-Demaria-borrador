package nemo;

public abstract class HeightsSlots {
	public abstract void goUp(Nemo submarino);
}
package nemo.Depths;

import nemo.Nemo;

public abstract class DepthState {
	public abstract void sendNemoUp(Nemo nemo);
	public abstract void sendNemoDown(Nemo nemo);
	public abstract void shootCapsuleFromThisDepth(Nemo nemo);
	
}
