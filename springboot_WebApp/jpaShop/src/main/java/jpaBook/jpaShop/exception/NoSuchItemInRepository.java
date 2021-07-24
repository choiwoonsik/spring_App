package jpaBook.jpaShop.exception;

public class NoSuchItemInRepository extends Exception{
	public NoSuchItemInRepository() {
		super();
	}

	public NoSuchItemInRepository(String message) {
		super(message);
	}

	public NoSuchItemInRepository(String message, Throwable cause) {
		super(message, cause);
	}

	public NoSuchItemInRepository(Throwable cause) {
		super(cause);
	}
}
