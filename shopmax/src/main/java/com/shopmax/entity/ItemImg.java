package com.shopmax.entity;

import com.shopmax.constant.RepImgYn;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity //엔티티 클래스로 정의 (필수)
@Table(name="item_img") //테이블 이름 지정
@Getter
@Setter
@ToString
public class ItemImg extends BaseEntity {
	
	@Id
	@Column(name="item_img_id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	private String imgName; //바뀐 이미지 파일명 (중복방지)
	
	private String oriImgName; //원본 이미지 파일명
	
	private String imgUrl; //이미지 조회 경로
	
	@Enumerated(EnumType.STRING)
	private RepImgYn repImgYn; //대표 이미지 여부
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="item_id")
	private Item item;
	
	public void updateItemImg(String oriImgName , String imgName , String imgUrl) {
		
		this.oriImgName =  oriImgName;
		this.imgName = imgName;
		this.imgUrl = imgUrl;
		
	}

	
}
