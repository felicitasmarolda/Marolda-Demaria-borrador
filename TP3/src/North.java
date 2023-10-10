package nemo;

public class North extends Cardinal {

	public void forward(Nemo nemo) {
		nemo.yCoord += 1;
	}

	public void left(Nemo nemo) {
		nemo.direction = new West();
	}

	public void right(Nemo nemo) {
		nemo.direction = new East();
	}

	public String direccion() {
		return "Norte";
	}	
}