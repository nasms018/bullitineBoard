package www.dream.bbs.framework.model;

public class PagingDTO {
	private static final int LIMIT = 2;
	private static final int BTN_CNT = 10;

	private int page;
	private long offset;

	int lastPage;
	int startPage;
	boolean prev;
	boolean next;

	public PagingDTO(int page) {
		this.page = page;
		offset = (this.page - 1) * LIMIT;
	}

	public long getOffset() {
		return offset;
	}

	public static int getLimit() {
		return LIMIT;
	}

	public int getLastPage() {
		return lastPage;
	}

	public int getStartPage() {
		return startPage;
	}

	public boolean isPrev() {
		return prev;
	}

	public boolean isNext() {
		return next;
	}
	// BTN_CNT 덩치 만큼씩 보여주는. 정수형 화면구성
	// 7 dataCount : 234 : 1, 2, 3 ... 10 >>
	// 21 dataCount : 234 : <<21, 22, 23, 24
	// 12 dataCount : 234 : <<11, 12, 13 ... 20 >>

	// 실수형 화면 구성. 현재 쪽 기준으로 앞 뒤 맞추기, 중앙 배치 구조
	// 7 dataCount : 234 : 3, 4, 5, 6, 7, 8, 9, 10, 11, 12 >>
	// 21 dataCount : 234 : << 15 ... 21 ... 24
	// 12 dataCount : 234 : << 8 ... 12 ... 17 >>

	public void buildPagination(long dataCount) {
		// 전체 쪽 수
		int totPageCount = (int) Math.ceil((float) dataCount / LIMIT);
		totPageCount = totPageCount < 1 ? 1 : totPageCount; // 전체 쪽수의 최소값
		lastPage = page + BTN_CNT / 2; // 마지막쪽 번호의 기본값산출
		if (lastPage < BTN_CNT)
			lastPage = BTN_CNT; // 뒤에 표현할 개수가 모자라면 늘려주자
		if (lastPage > totPageCount)
			lastPage = totPageCount; // 가상적인 마지막이 실제 마지막보다 크면 이를 조정
		startPage = lastPage - BTN_CNT;
		if (startPage < 1)
			startPage = 1;
		prev = startPage > 1;
		next = lastPage < totPageCount;
	}
}
