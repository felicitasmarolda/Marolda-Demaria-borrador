package nemo;

public class East extends Cardinal {

	public void forward(Nemo nemo) {
		nemo.xCoord += 1;
	}

	public void left(Nemo nemo) {
		nemo.direction = new North();
	}

	public void right(Nemo nemo) {
		nemo.direction = new South();
	}	
	
	public String direccion() {
		return "Este";
	}	
}