package nemo;

public class South extends Cardinal {

	public void forward(Nemo nemo) {
		nemo.yCoord -= 1;
	}

	public void left(Nemo nemo) {
		nemo.direction = new East();
	}

	public void right(Nemo nemo) {
		nemo.direction = new West();
	}	
	
	public String direccion() {
		return "Sur";
	}	
}