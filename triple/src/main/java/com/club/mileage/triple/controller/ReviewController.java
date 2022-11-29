package com.club.mileage.triple.controller;

import java.io.IOException;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.club.mileage.triple.service.ReviewService;
import com.club.mileage.triple.dto.ReviewDto;
import com.club.mileage.triple.dto.ReviewRequestDto;
import com.club.mileage.triple.dto.UserDto;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class ReviewController {
	
	private Logger log = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private ReviewService reviewService;

	/**
	 * 리뷰 API 함수 
	 * @param reviewRequestDto
	 * @param bindingResult
	 * @return ResponseEntity
	 */
			
	@RequestMapping(value = "/events", method = RequestMethod.POST)
	public ResponseEntity<?> apiReview( @RequestBody @Valid ReviewRequestDto reviewRequestDto, BindingResult bindingResult){
		
//		ReviewDto reviewDto = new ReviewDto();
//		String strPhotoIds = "";
		
		if(bindingResult.hasErrors()) {
			StringBuilder sb = new StringBuilder();
			bindingResult.getAllErrors().forEach(error-> {
				FieldError filed = (FieldError)error;
				String message = filed.getDefaultMessage();
				
				System.out.println("filed : " + filed.getField());
				System.out.println("message : " + message);
				
				sb.append("filed : " + filed.getField());
				sb.append("message : " + message);
				
			});
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(sb.toString());
		}
	
		try {
			reviewService.apiGateWay(reviewRequestDto);
		} catch (Exception e) {
			// TODO: handle exception
			return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body("fail : "+reviewRequestDto.getAction()+ " - "+ e.getMessage());
		}
		
		return ResponseEntity.status(HttpStatus.OK).body("success : " + reviewRequestDto.getAction());
	}
	
	/**
	 * 사용자 마일리지 조회
	 * @param userId
	 * @return ResponseEntity
	 * @throws Exception
	 */
	@RequestMapping(value = "/mileage/{userId}", method = RequestMethod.GET)
	public ResponseEntity<?> apiMileage(@PathVariable String userId) throws Exception{
		
		ReviewRequestDto reviewRequestDto = new ReviewRequestDto();
		reviewRequestDto.setUserId(userId);
		UserDto userDto = reviewService.selectUserMileage(reviewRequestDto);
		if(userDto == null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("userId : "+ userId + " Not Found" );
		}
		
		return ResponseEntity.status(HttpStatus.OK).body("userId"+"("+userId+") mileage : " + userDto.getMileage());

	}
}


