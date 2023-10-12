package queue;

public abstract class NonErrorQueue extends ErrorFiles{
//	private Object cosa;
//	public  NonErrorQueue (Object cargo){
//		cosa = cargo;
//	}
//	public Object Devuelve() {
//		return cosa;
//	}
	public Object head() {
		return objetos.get(0);
}
	public Object take() {
		return objetos.remove(0);
}
}


