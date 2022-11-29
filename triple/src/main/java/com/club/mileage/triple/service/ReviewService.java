package com.club.mileage.triple.service;

import com.club.mileage.triple.dto.ReviewDto;
import com.club.mileage.triple.dto.ReviewRequestDto;
import com.club.mileage.triple.dto.UserDto;

public interface ReviewService {
	void insertReview(ReviewDto reviewDto) throws Exception;
	void updateReview(ReviewDto reviewDto) throws Exception;
	void deleteReview(ReviewDto reviewDto) throws Exception;
	ReviewDto selectReview(ReviewDto reviewDto) throws Exception;
	UserDto selectUserMileage(ReviewRequestDto reviewRequestDto) throws Exception;
	int selectCntPlace(ReviewRequestDto reviewRequestDto) throws Exception;
	void insertReviewHistory(ReviewDto reviewDto) throws Exception;
	int selectBonus(ReviewRequestDto reviewRequestDto) throws Exception;
	void apiGateWay(ReviewRequestDto reviewRequestDto) throws Exception;
	int selectExistReview(ReviewDto reviewDto) throws Exception;
}
