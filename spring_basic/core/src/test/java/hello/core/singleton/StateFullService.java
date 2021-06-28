package hello.core.singleton;

public class StateFullService {

	private int price; // 가격을 갖고있는다. (상태 유지)

	public void order(String name, int price) {
		System.out.println("name = " + name + " price" + price);
		this.price = price;
	}

	public int getPrice() {
		return price;
	}
}
