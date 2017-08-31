
public class Ortho {

	public static boolean binarySearch(String [] array, String key) {
		//need to be lower case to match ordenation
		//String lowerCaseKey = key.toLowerCase();
		return binarySearch(array, key, 0, array.length);
	}

	public static boolean binarySearch(String [] array, String key, int first, int last) {
		if(first >= last) {
			//StdOut.println("Could not find " + key);
			return false;
		}
		int mid = (first + last) / 2;
		//String lowerCaseMid = array[mid].toLowerCase();
		int compare = array[mid].compareToIgnoreCase(key);

		if(compare > 0) {
			//StdOut.println(key + " < " + array[mid]);
			return binarySearch(array, key, first, mid);
		}
		else if(compare < 0) {
			//StdOut.println(key + " > " + array[mid]);
			return binarySearch(array, key, mid + 1, last);
		}
		else {
			return true;
		}
	}

	public static void main(String[] args) {
	  In in0 = new In(args[0]);
		String file0 = in0.readAll();
		String [] dictionary  = in0.readAllLines();

		boolean marked = (args.length > 1 && args[1].equals("-m")) ? true : false;

		String [] lines = StdIn.readAllLines();
		for(String line : lines) {
			String [] words = line.split("[^A-Za-z0-9'_-]");
			if(marked) {
				String output = line;
				for(String word : words)
					if(!binarySearch(dictionary, word))// StdOut.println(word);
						output = line.replaceAll(word, "*" + word + "*");


				StdOut.println(output);
					//String [] words = {"will", "BERTRAND", "exhaust", "caralho"};
			}
			 else
				for(String word : words)
					if(!binarySearch(dictionary, word))
						StdOut.println(word);
		}
	}
}
