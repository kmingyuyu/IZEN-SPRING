package com.shopmax.dto;

import org.modelmapper.ModelMapper;

import com.shopmax.constant.RepImgYn;
import com.shopmax.entity.ItemImg;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ItemImgDto {
	
	private Long id;
	
	private String imgName;
	
	private String oriImgName;
	
	private String imgUrl;
	
	private RepImgYn repImgYn;
	
	private static ModelMapper modelMapper = new ModelMapper();
	
//	entity -> dto 로 변환
	public static ItemImgDto of(ItemImg itemImg) {
		return modelMapper.map(itemImg, ItemImgDto.class);
	}
	
	
	
}
