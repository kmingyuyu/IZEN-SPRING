package com.shopmax.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import com.shopmax.constant.ItemSellStatus;
import com.shopmax.entity.Item;

@SpringBootTest // 빈 객체로 만듬 -> 스프링 컨테이너에 등록
@TestPropertySource(locations = "classpath:application-test.properties")
class ItemRepositoryTest {

	@Autowired
	ItemRepository itemRepository;

	@Disabled
	@Test
	@DisplayName("상품 저장 테스트")
	public void createItemTest() {
		Item item = new Item();
		item.setItemNm("테스트 상품");
		item.setPrice(10000);
		item.setItemDetail("테스트 상품 상세 설명");
		item.setItemSellStatus(ItemSellStatus.SELL);
		item.setStockNumber(100);
		item.setRegTime(LocalDateTime.now());
		item.setUpdateTime(LocalDateTime.now());
		
//		insert
		Item savedItem = itemRepository.save(item);
		System.out.println(savedItem.toString());
		
	}

	
//	여러개 저장하기
	@Test
	@DisplayName("상품 저장 테스트")
	public void createItemList() {

		for (int i = 1; i <= 10; i++) {
			Item item = new Item();
			item.setItemNm("테스트 상품" + i);
			item.setPrice(10000 + i);
			item.setItemDetail("테스트 상품 상세 설명" + i);
			item.setItemSellStatus(ItemSellStatus.SELL);
			item.setStockNumber(100);
			item.setRegTime(LocalDateTime.of(2023,1,1,12,12,40+i));
			item.setUpdateTime(LocalDateTime.now());

//		insert
			Item savedItem = itemRepository.save(item);
		}
	}
	
	@Disabled
	@Test
	@DisplayName("상품 조회 테스트")
	public void findByItemNmTest() {
		this.createItemList(); //데이터 10개를 insert
		List<Item> itemList = itemRepository.findByItemNm("테스트 상품1");
		
		for(Item item : itemList) {
			System.out.println(item.toString());
		}
		
	}
	
	@Disabled
	@Test
	@DisplayName("퀴즈1-1")
	public void quiz1_1Test() {
		this.createItemList();
		List<Item> itemList = itemRepository
				.findByItemNmAndItemSellStatus("테스트 상품1",  ItemSellStatus.SELL);
		
		for(Item item : itemList) {
			System.out.println(item.toString());
		}
	}
	
	@Disabled
	@Test
	@DisplayName("퀴즈1-2")
	public void quiz1_2Test() {
		this.createItemList();
		List<Item> itemList = itemRepository.findByPriceAfterAndPriceBefore(10004, 10008);
		
		for(Item item : itemList) {
			System.out.println(item.toString());
		}
		
	}
	
	@Disabled
	@Test
	@DisplayName("퀴즈1-3")
	public void quiz1_3Test() {
		this.createItemList();
		List<Item> itemList = itemRepository.findByRegTimeAfter(LocalDateTime.of(2023, 1, 1, 12, 12, 44));
		
		for(Item item : itemList) {
			System.out.println(item.toString());
		}
	}
	
	@Disabled
	@Test
	@DisplayName("퀴즈1-4")
	public void quiz1_4Test() {
		this.createItemList();
		List<Item> itemList = itemRepository.findByItemSellStatusIsNotNull();
		
		for(Item item : itemList) {
			System.out.println(item.toString());
		}
	}
	@Disabled
	@Test
	@DisplayName("퀴즈1-5")
	public void quiz1_5Test() {
		this.createItemList();
		List<Item> itemList = itemRepository.findByItemDetailLike("%설명1");
		
		for(Item item : itemList) {
			System.out.println(item.toString());
		}
	}
	
	@Disabled
	@Test
	@DisplayName("퀴즈1-6")
	public void quiz1_6Test() {
		this.createItemList();
		List<Item> itemList = itemRepository.findByItemNmOrItemDetail("테스트 상품1", "테스트 상품 상세 설명5");
		
		for(Item item : itemList) {
			System.out.println(item.toString());
		}
	}
	
	@Disabled
	@Test
	@DisplayName("퀴즈1-7")
	public void quiz1_7Test() {
		this.createItemList();
		List<Item> itemList = itemRepository.findByPriceBeforeOrderByPriceAsc(10005);
		
		for(Item item : itemList) {
			System.out.println(item.toString());
		}
	}
	@Disabled
	@Test
	@DisplayName("@query를 이용한 상품 조회 리스트")
	public void findByItemDetailTest() {
		this.createItemList();
		List<Item> itemList = itemRepository.findByItemDetail("테스트 상품 상세");
		
		for(Item item : itemList) {
			System.out.println(item.toString());
			
		}
	}
	
	@Disabled
	@Test
	@DisplayName("@query(native 쿼리)를 이용한 상품 조회 리스트")
	public void findByItemDetailByNativeTest() {
		this.createItemList();
		List<Item> itemList = itemRepository.findByItemDetailNative("테스트 상품 상세");
		
		for(Item item : itemList) {
			System.out.println(item.toString());
			
		}
	}
	
	@Disabled
	@Test
	@DisplayName("quiz2-1")
	public void quiz2_1Test() {
		this.createItemList();
		List<Item> itemList = itemRepository.findByPrice(10005);
		
		for(Item item : itemList) {
			System.out.println(item.toString());
			
		}
	}
	
	@Disabled
	@Test
	@DisplayName("quiz2-2")
	public void quiz2_2Test() {
		this.createItemList();
		List<Item> itemList = itemRepository.findByItemNmAndItemSellStatusQuiz("테스트 상품1" , ItemSellStatus.SELL);
		
		for(Item item : itemList) {
			System.out.println(item.toString());
			
		}
	}
	
	
	
	
}
