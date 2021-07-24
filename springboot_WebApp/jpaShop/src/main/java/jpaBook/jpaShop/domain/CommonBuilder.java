package jpaBook.jpaShop.domain;

/**
 * DTO Builder 패턴 적용 공용 인터페이스
 * @param <T>
 */
public interface CommonBuilder<T> {
	T build();
}
