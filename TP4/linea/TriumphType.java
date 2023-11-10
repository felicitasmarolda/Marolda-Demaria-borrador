package linea;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public abstract class TriumphType {

	static List<TriumphType> possibleModes = new ArrayList<TriumphType>(Arrays.asList(
			  new TypeA(),
			  new TypeB(),
			  new TypeC()));
	
	public static TriumphType typeFor(Character typeChar) {
		return possibleModes.stream()
			    			.filter(typeInstance -> typeInstance.canHandle(typeChar))
			    			.toList()
			    			.get(0);
	}
	
	public abstract boolean canHandle(Character typeChar);
	public abstract boolean redWonInCurrentType(Line line);
	public abstract boolean blueWonInCurrentType(Line line);
}
