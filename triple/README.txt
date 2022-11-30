* Lombok 사용
 - ecliipse.ini 파일에 아래 내용 추가
	-javaagent:{파일경로}\lombok.jar

DB : MySQL57

* 테이블 생성 : 
 - 리뷰 테이블 
	CREATE TABLE `tb_review` (
	  `reviewId` varchar(100) NOT NULL,
	  `userId` varchar(100) NOT NULL,
	  `placeId` varchar(100) NOT NULL,
	  `content` varchar(500) NOT NULL,
	  `attachedPhotoIds` varchar(500) DEFAULT NULL,
	  `reviewMileage` int(11) DEFAULT NULL,
	  `isBonus` varchar(1) DEFAULT 'N',
	  `createDateTime` datetime DEFAULT NULL,
	  `UpdateDateTime` datetime DEFAULT NULL,
	  PRIMARY KEY (`reviewId`,`userId`,`placeId`)
	) ENGINE=InnoDB DEFAULT CHARSET=utf8

 - 리뷰 히스토리 테이블
	CREATE TABLE `tb_review_history` (
	  `reviewId` varchar(100) NOT NULL,
	  `userId` varchar(100) NOT NULL,
	  `placeId` varchar(100) NOT NULL,
	  `seq` int(11) NOT NULL,
	  `action` varchar(45) DEFAULT NULL,
	  `content` varchar(500) DEFAULT NULL,
	  `attachedPhotoIds` varchar(500) DEFAULT NULL,
	  `reviewMileage` int(11) DEFAULT NULL,
	  `createDateTime` datetime DEFAULT NULL,
	  PRIMARY KEY (`reviewId`,`userId`,`placeId`,`seq`)
	) ENGINE=InnoDB DEFAULT CHARSET=utf8


* 테스트
 url : localhost:8080/events/
 - 리뷰 작성 : 
 	Ex)
 	{
	"type": "REVIEW",
	"action": "ADD", 
	"reviewId": "240a0658-dc5f-4878-9381-ebb7b2667772",
	"content": "좋아요!",
	"attachedPhotoIds": ["e4d1a64e-a531-46de-88d0-ff0ed70c0bb8", "afb0cef2-851d-4a50-bb07-9cc15cbdc332"],
	"userId": "3ede0ef2-92b7-4817-a5f3-0c575361f745",
	"placeId": "2e4baf1c-5acb-4efb-a1af-eddada31b00f"
	}
	
	
 -리뷰 수정
	{
	"type": "REVIEW",
	"action": "MOV", 
	"reviewId": "240a0658-dc5f-4878-9381-ebb7b2667772",
	"content": "좋아요!",
	"attachedPhotoIds": ["e4d1a64e-a531-46de-88d0-ff0ed70c0bb8", "afb0cef2-851d-4a50-bb07-9cc15cbdc332"],
	"userId": "3ede0ef2-92b7-4817-a5f3-0c575361f745",
	"placeId": "2e4baf1c-5acb-4efb-a1af-eddada31b00f"
	}
	
	
 -리뷰 삭제
	{
	"type": "REVIEW",
	"action": "MOV", 
	"reviewId": "240a0658-dc5f-4878-9381-ebb7b2667772",
	"content": "좋아요!",
	"attachedPhotoIds": ["e4d1a64e-a531-46de-88d0-ff0ed70c0bb8", "afb0cef2-851d-4a50-bb07-9cc15cbdc332"],
	"userId": "3ede0ef2-92b7-4817-a5f3-0c575361f745",
	"placeId": "2e4baf1c-5acb-4efb-a1af-eddada31b00f"
	}	
 
 - 포인트 조회 API 
  url : localhost:8080/mileage/{userId}
 	
 	Ex) localhost:8080/mileage/3ede0ef2-92b7-4817-a5f3-0c575361f745
 
 	
