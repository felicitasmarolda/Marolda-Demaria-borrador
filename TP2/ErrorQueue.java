package queue;

public class ErrorQueue extends ErrorFiles{
	public Object head() {
		// TODO Auto-generated method stub
			throw new Error("Queue is empty");
	}
	public Object take() {
		// TODO Auto-generated method stub
			throw new Error("Queue is empty");
	}
}
