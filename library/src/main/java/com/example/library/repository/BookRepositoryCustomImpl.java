package com.example.library.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.thymeleaf.util.StringUtils;

import com.example.library.constant.StockOk;
import com.example.library.dto.BookSearchDto;
import com.example.library.entity.Book;
import com.example.library.entity.QBook;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.Wildcard;
import com.querydsl.jpa.impl.JPAQueryFactory;

import jakarta.persistence.EntityManager;

public class BookRepositoryCustomImpl implements BookRepositoryCustom {

	private JPAQueryFactory queryFactory;

	public BookRepositoryCustomImpl(EntityManager em) {
		this.queryFactory = new JPAQueryFactory(em);
	}

	private BooleanExpression regDtsAfter(String searchDateType) {
		LocalDateTime dateTime = LocalDateTime.now();
		
	if(StringUtils.equals("all", searchDateType) || searchDateType == null) 
		return null;
	else if(StringUtils.equals("1d", searchDateType))
		dateTime = dateTime.minusDays(1); //현재 날짜로부터 1일전
	else if(StringUtils.equals("1w", searchDateType))
		dateTime = dateTime.minusWeeks(1); //1주일 전
	else if(StringUtils.equals("1m", searchDateType))
		dateTime = dateTime.minusMonths(1); //1달전
	else if(StringUtils.equals("6m", searchDateType))
		dateTime = dateTime.minusMonths(6); //6개월전
	
	 return QBook.book.regTime.after(dateTime);
	}
	
	private BooleanExpression searchStockOkEq(StockOk stockOk) {
		
		return stockOk == null ? null : QBook.book.stockOk.eq(stockOk);
		
	}
	
	private BooleanExpression searchByLike(String searchBy , String searchQuery) {
		
		if(StringUtils.equals("bookName", searchBy)) {
			return QBook.book.bookName.like("%" + searchQuery + "%");
		} else if(StringUtils.equals("createdBy", searchBy)) {
			return QBook.book.createdBy.like("%" + searchQuery + "%");
		}
			
		return null;
		
	}
	
	
	
	@Override
	public Page<Book> getAdminBookPage(BookSearchDto bookSearchDto, Pageable pageable) {

		List<Book> content = queryFactory.selectFrom(QBook.book)
				.where(regDtsAfter(bookSearchDto.getSearchDateType()), 
						searchStockOkEq(bookSearchDto.getStockOk()),
						searchByLike(bookSearchDto.getSearchBy(), bookSearchDto.getSearchQuery()))
				.orderBy(QBook.book.id.desc())
				.offset(pageable.getOffset())
				.limit(pageable.getPageSize())
				.fetch();
		
		long total = queryFactory.select(Wildcard.count)
					.from(QBook.book)
					.where(regDtsAfter(bookSearchDto.getSearchDateType()),
							searchStockOkEq(bookSearchDto.getStockOk()),
							searchByLike(bookSearchDto.getSearchBy(), bookSearchDto.getSearchQuery()))
					.fetchOne();
		

		return new PageImpl<>(content , pageable , total);
	}

}