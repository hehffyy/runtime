import java.util.Comparator;

public class ComparatorRowSort implements Comparator<RowSort> {
	@Override
	public int compare(RowSort o1, RowSort o2) {
		return o1.getOrder().compareTo(o2.getOrder());
	}
}
