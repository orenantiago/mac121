
public class Ortho {

	public static boolean binarySearch(String [] array, String key) {
		return binarySearch(array, key, 0, array.length);
	}

	public static boolean binarySearch(String [] array, String key, int first, int last) {
		if(first >= last) return false;

		int mid = first + (last - first) / 2;
		int compare = array[mid].compareToIgnoreCase(key);

		if(compare > 0) return binarySearch(array, key, first, mid);
		else if(compare < 0) return binarySearch(array, key, mid + 1, last);
		else return true;
	}

	public static void main(String[] args) {
		
	}
}
