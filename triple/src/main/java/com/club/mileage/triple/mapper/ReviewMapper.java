package com.club.mileage.triple.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.club.mileage.triple.dto.ReviewDto;
import com.club.mileage.triple.dto.ReviewRequestDto;
import com.club.mileage.triple.dto.UserDto;

@Mapper
public interface ReviewMapper {
	void insertReview(ReviewDto reviewDto) throws Exception;
	void updateReview(ReviewDto reviewDto) throws Exception;
	void deleteReview(ReviewDto reviewDto) throws Exception;
	ReviewDto selectReview(ReviewDto reviewDto) throws Exception;
	UserDto selectUserMileage(ReviewRequestDto reviewRequestDto) throws Exception;
	int selectCntPlace(ReviewRequestDto reviewRequestDto) throws Exception;
	void insertReviewHistory(ReviewDto reviewDto) throws Exception; 
	int selectHistortSeq(ReviewDto reviewDto) throws Exception; 
	int selectBonus(ReviewRequestDto reviewRequestDto) throws Exception; 
	int selectExistReview(ReviewDto reviewDto) throws Exception;
}
