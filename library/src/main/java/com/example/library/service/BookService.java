package com.example.library.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.example.library.constant.ImgChoiceOk;
import com.example.library.dto.BookFormDto;
import com.example.library.dto.BookSearchDto;
import com.example.library.entity.Book;
import com.example.library.entity.BookImg;
import com.example.library.repository.BookImgRepository;
import com.example.library.repository.BookRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class BookService {
	

	
	private final BookImgService bookImgService;
	
	private final BookRepository bookRepository;
	
	private final BookImgRepository bookImgRepository;
	
	public Long saveBook(BookFormDto bookFormDto , List<MultipartFile> bookImgFileList) throws Exception {
		
		Book book = bookFormDto.createBook();
		bookRepository.save(book);
		
		for(int i=0; i<bookImgFileList.size(); i++) {
			
			BookImg bookImg = new BookImg();
			bookImg.setBook(book);
			
			if(i==0) {
				bookImg.setImgChoiceOk(ImgChoiceOk.MAIN);
			}
			else if(i==1) {
				bookImg.setImgChoiceOk(ImgChoiceOk.SIDE);
			}
			else if(i==2) {
				bookImg.setImgChoiceOk(ImgChoiceOk.BACK);
			}
			else {
				bookImg.setImgChoiceOk(ImgChoiceOk.ETC);
			}
			
			bookImgService.saveBookImg(bookImg, bookImgFileList.get(i));
		}
		
		return book.getId();
	}
	
	
	
	
	
	
	@Transactional(readOnly = true)
	public Page<Book> getAdminBookPage(BookSearchDto bookSearchDto , Pageable pageable){
		Page<Book> bookPage = bookRepository.getAdminBookPage(bookSearchDto, pageable);
		return bookPage;
	}
	
	
	
	
	
	
}
