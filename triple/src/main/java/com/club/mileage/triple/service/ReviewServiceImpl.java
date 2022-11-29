package com.club.mileage.triple.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.club.mileage.triple.dto.ReviewDto;
import com.club.mileage.triple.dto.ReviewRequestDto;
import com.club.mileage.triple.dto.UserDto;
import com.club.mileage.triple.mapper.ReviewMapper;

@Service
public class ReviewServiceImpl implements ReviewService {
	
	@Autowired
	private ReviewMapper reviewMapper;
	
	private Logger log = LoggerFactory.getLogger(this.getClass());

	@Override
	public void insertReview(ReviewDto reviewDto) throws Exception {
		// TODO Auto-generated method stub
		reviewMapper.insertReview(reviewDto);
		this.insertReviewHistory(reviewDto);
	}

	@Override
	public void updateReview(ReviewDto reviewDto) throws Exception {
		// TODO Auto-generated method stub
		reviewMapper.updateReview(reviewDto);
		this.insertReviewHistory(reviewDto);
	}

	@Override
	public void deleteReview(ReviewDto reviewDto) throws Exception {
		// TODO Auto-generated method stub
		reviewMapper.deleteReview(reviewDto);
		this.insertReviewHistory(reviewDto);	
	}

	@Override
	public ReviewDto selectReview(ReviewDto reviewDto) throws Exception {
		// TODO Auto-generated method stub
		return reviewMapper.selectReview(reviewDto);
	}

	@Override
	public UserDto selectUserMileage(ReviewRequestDto reviewRequestDto) throws Exception {
		// TODO Auto-generated method stub
		return reviewMapper.selectUserMileage(reviewRequestDto);
	}

	@Override
	public int selectCntPlace(ReviewRequestDto reviewRequestDto) throws Exception {
		// TODO Auto-generated method stub
		return reviewMapper.selectCntPlace(reviewRequestDto);
	
	}

	@Override
	public void insertReviewHistory(ReviewDto reviewDto) throws Exception {
		// TODO Auto-generated method stub
		int seq= reviewMapper.selectHistortSeq(reviewDto);
		reviewDto.setSeq(seq);
		reviewMapper.insertReviewHistory(reviewDto);
	}

	@Override
	public int selectBonus(ReviewRequestDto reviewRequestDto) throws Exception {
		// TODO Auto-generated method stub
		return reviewMapper.selectBonus(reviewRequestDto);
		
	}
	
	@Override
	public int selectExistReview(ReviewDto reviewDto) throws Exception {
		// TODO Auto-generated method stub
		return reviewMapper.selectExistReview(reviewDto);
	}
	

	@Transactional
	@Override
	public void apiGateWay(ReviewRequestDto reviewRequestDto)throws Exception {

		int mileage = 0;
		ReviewDto reviewDto = new ReviewDto();
		String strPhotoIds = "";
		StringBuilder sb = new StringBuilder();
		
		if(reviewRequestDto.getContent().length() > 0) {			
			mileage = mileage + 1;
			sb.append("내용 점수 = 1");
 		}
    	if(reviewRequestDto.getAttachedPhotoIds() != null && reviewRequestDto.getAttachedPhotoIds().length > 0) {		
    		mileage = mileage + 1;
    		sb.append(",사진 점수 = 1");
		}
    	if(this.selectBonus(reviewRequestDto) == 1) {
    		mileage = mileage + 1;
			reviewDto.setIsBonus("Y");
			sb.append(",기존 보너스 점수 = 1");
    	}else if(this.selectCntPlace(reviewRequestDto) == 0) {	
			mileage = mileage + 1;
			reviewDto.setIsBonus("Y");
			sb.append(",보너스 점수 = 1");
		}else {
			reviewDto.setIsBonus("N");
			sb.append(",보너스 점수 = 0");
		}
    	log.debug(sb.toString()+", 총점수 = "+ mileage);
    	

		if(reviewRequestDto.getAttachedPhotoIds() != null && reviewRequestDto.getAttachedPhotoIds().length > 0) {
			for(int i =0 ; reviewRequestDto.getAttachedPhotoIds().length > i ; i++) {
				if(i == reviewRequestDto.getAttachedPhotoIds().length-1) {
					strPhotoIds = strPhotoIds + reviewRequestDto.getAttachedPhotoIds()[i];
				}else {
					strPhotoIds = strPhotoIds + reviewRequestDto.getAttachedPhotoIds()[i] + ",";
				}
			}
		}
		
		reviewDto.setAttachedPhotoIds(strPhotoIds);
		reviewDto.setContent(reviewRequestDto.getContent());
		reviewDto.setAction(reviewRequestDto.getAction());
		reviewDto.setPlaceId(reviewRequestDto.getPlaceId());
		reviewDto.setReviewId(reviewRequestDto.getReviewId());
		reviewDto.setUserId(reviewRequestDto.getUserId());
		reviewDto.setReviewMileage(mileage);
		
		switch (reviewRequestDto.getAction()) {
		case "ADD": {
			if(this.selectExistReview(reviewDto) == 0) {
				this.insertReview(reviewDto);
			}else{
				 throw new IllegalStateException("이미 작성된 리뷰가 있습니다.");
			}
			break;
		}
		case "MOV": {
			ReviewDto getReviewDto = this.selectReview(reviewDto);
			if(getReviewDto != null)
			{
				this.updateReview(reviewDto);
			}else {
				 throw new IllegalStateException("수정할 리뷰가 없습니다.");
			}
			break;
		}
		case "DELETE": {
			ReviewDto getReviewDto = this.selectReview(reviewDto);
			if(getReviewDto != null)
			{
				this.deleteReview(reviewDto);
			}else {
				throw new IllegalStateException("삭제할 리뷰가 없습니다.");
			}
			
			break;
		}
		default:
			throw new IllegalArgumentException(reviewRequestDto.getAction() + "은 잘못된 요청입니다. ");
		}
	}

	
}
