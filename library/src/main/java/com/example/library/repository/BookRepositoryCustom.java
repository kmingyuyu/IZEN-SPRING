package com.example.library.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.example.library.dto.BookSearchDto;
import com.example.library.dto.MainBookDto;
import com.example.library.entity.Book;

public interface BookRepositoryCustom {

	Page<Book> getAdminBookPage(BookSearchDto bookSearchDto , Pageable pageable);
	
	Page<MainBookDto> getMainBookNewPage(BookSearchDto bookSearchDto , Pageable pageable);
	
	Page<MainBookDto> getMainBookCountPage(BookSearchDto bookSearchDto , Pageable pageable);
	
	
}
