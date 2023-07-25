package com.example.library.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.example.library.constant.ImgChoiceOk;
import com.example.library.dto.BookFormDto;
import com.example.library.dto.BookImgDto;
import com.example.library.dto.BookSearchDto;
import com.example.library.dto.MainBookDto;
import com.example.library.entity.Book;
import com.example.library.entity.BookImg;
import com.example.library.repository.BookImgRepository;
import com.example.library.repository.BookRepository;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class BookService {
	

	
	private final BookImgService bookImgService;
	
	private final BookRepository bookRepository;
	
	private final BookImgRepository bookImgRepository;
	
//	상품등록
	public Long saveBook(BookFormDto bookFormDto , List<MultipartFile> bookImgFileList) throws Exception {
		
//		상품등록
		Book book = bookFormDto.createBook();
		bookRepository.save(book);
		
//		이미지 등록
		for(int i=0; i<bookImgFileList.size(); i++) {
			
			BookImg bookImg = new BookImg();
			bookImg.setBook(book);
			
//			1번째 메인 / 2번쨰 사이드 / 3번쨰 뒷면 / 4번째 기타
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
	
//	아이디로 book 가져오기
	@Transactional(readOnly = true)
	public BookFormDto getBookDetail(Long bookId) {
		
		List<BookImg> bookImgList = bookImgRepository.findByBookIdOrderByIdAsc(bookId);
		
		List<BookImgDto> bookImgDtoList = new ArrayList<>();
		
		for(BookImg bookImg : bookImgList) {
			BookImgDto bookImgDto = BookImgDto.of(bookImg);
			bookImgDtoList.add(bookImgDto);
		}
		
		Book book = bookRepository.findById(bookId)
					.orElseThrow(EntityNotFoundException::new);
		
		BookFormDto bookFormDto = BookFormDto.of(book);
		
		bookFormDto.setBookImgDtoList(bookImgDtoList);
		
		return bookFormDto;
		
	}
	
	
	
	@Transactional(readOnly = true)
	public Page<Book> getAdminBookPage(BookSearchDto bookSearchDto , Pageable pageable){
		Page<Book> bookPage = bookRepository.getAdminBookPage(bookSearchDto, pageable);
		return bookPage;
	}
	
	@Transactional(readOnly = true)
	public Page<MainBookDto> getMainBookNewPage(BookSearchDto bookSearchDto , Pageable pageable) {
		Page<MainBookDto> mainBookPage = bookRepository.getMainBookNewPage(bookSearchDto, pageable);
		return mainBookPage;
		
	}
	
	@Transactional(readOnly = true)
	public Page<MainBookDto> getMainBookCountPage(BookSearchDto bookSearchDto , Pageable pageable) {
		Page<MainBookDto> mainBookPage = bookRepository.getMainBookCountPage(bookSearchDto, pageable);
		return mainBookPage;
		
	}

	
	
}
