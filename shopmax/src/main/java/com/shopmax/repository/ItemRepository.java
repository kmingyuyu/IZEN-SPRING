package com.shopmax.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.shopmax.constant.ItemSellStatus;
import com.shopmax.entity.Item;

//                                                   <해당 repository에 사용할 Entity , Entity클래스의 기본키(primary key) 타입>
public interface ItemRepository extends JpaRepository<Item, Long> {
//	select * from item where item_nm = ?
	List<Item> findByItemNm(String itemNm);
	
//	select * from item where item_nm = ? and item_sell_status = ? 
	List<Item> findByItemNmAndItemSellStatus(String itemNm , ItemSellStatus itemSellStatus);
	
	List<Item> findByPriceAfterAndPriceBefore(int price , int pirce);
	
	List<Item> findByRegTimeAfter(LocalDateTime regTime);
	
	List<Item> findByItemSellStatusIsNotNull();
	
	List<Item> findByItemDetailLike(String itemDetail);
	
	List<Item> findByItemNmOrItemDetail(String itemNm , String itemDetail);
	
	List<Item> findByPriceBeforeOrderByPriceAsc(int price);
	
	
//	JPQL(non native 쿼리) -> 컬럼명 , 테이블명은 entity 클래스 기준으로 작성한다.
	
	@Query("select i"
		+ " from Item i "
		+ " where i.itemDetail like %:itemDetail% "
		+ " order by i.price desc")
	List<Item> findByItemDetail(@Param("itemDetail") String itemDetail);
	
	
	
//	JPQL(native 쿼리) -> h2 데이터 베이스를 기준으로 쿼리문 작성
	@Query(value="select * "
			+ "from item "
			+ "where item_detail like %:itemDetail% "
			+ "order by price desc" , nativeQuery = true)
	List<Item> findByItemDetailNative(@Param("itemDetail") String itemDetail);
	
	
	@Query("select i "
			+ "from Item i "
			+ "where i.price >= :price ")
	List<Item> findByPrice(@Param("price") int price);
	
	@Query("select i "
			+ "from Item i "
			+ "where i.itemNm = :itemNm and i.itemSellStatus = :itemSellStatus ")
	List<Item> findByItemNmAndItemSellStatusQuiz(@Param("itemNm") String itemNm , @Param("itemSellStatus") ItemSellStatus itemSellStatus);
	
	
	
	
}
