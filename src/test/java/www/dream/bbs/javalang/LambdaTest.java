package www.dream.bbs.javalang;

import java.util.stream.IntStream;

public class LambdaTest {

	public static void main(String[] args) {
		long s = IntStream.range(0, 10).filter(a->a%2==0).count();
		System.out.println(s);
	}

}
