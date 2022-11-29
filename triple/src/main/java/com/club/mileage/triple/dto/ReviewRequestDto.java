package com.club.mileage.triple.dto;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.springframework.validation.annotation.Validated;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter 
@Setter
public class ReviewRequestDto {

	@NotBlank(message = "tpye을 입력해주세요.")
	private String type;
	@NotNull(message = "action을 입력해주세요.")
	private String action;
	@NotNull(message = "reviewId을 입력해주세요.")
	private String reviewId;
	@NotNull(message = "content을 입력해주세요.")
	private String content;
	private String[] attachedPhotoIds;
	@NotNull(message = "userId을 입력해주세요.")
	private String userId;
	@NotNull(message = "placeId을 입력해주세요.")
	private String placeId;
}
