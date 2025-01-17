package kr.or.nextit.exam;

public class OddOrEven {

	public static void main(String[] args) {
		int start = 50;
		int end = 100;

		printOdd(start, end);
		System.out.printf("\n");
		printOdd02(start, end);
		System.out.printf("\n");
		printOdd03(start, end);

	}

	static void printOdd(int start, int end) {

		while (start != end) {
			if (start % 2 != 0) {
				System.out.printf("%d ", start);
			}
			start++;
		}
	}

	static void printOdd02(int start, int end) {
		int flag = (start % 2 != 0) ? 1 : -1;

		while (start != end) {
			if (flag > 0) {
				System.out.printf("%d ", start);
			}
			start++;
			flag *= -1;
		}
	}

	static void printOdd03(int start, int end) {
		int flag = start & 1;

		while (start != end) {
			if (flag > 0) {
				System.out.printf("%d ", start);
			}
			start++;
			flag ^= 1;
		}
	}
}
