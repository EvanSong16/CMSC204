public class ArraySum {
	public int sumOfArray(Integer[] a, int index) {
		if((index > a.length-1) || (index < 0) || (a[index] == null)) {
			return 0;
		}
		return a[index] + sumOfArray(a, (index+1));
	}
}