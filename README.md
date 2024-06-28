## Slang Back-End Repository

Organization:
https://github.com/Capstone-Week-4

AI:
https://github.com/Capstone-Week-4/Slang_AI
https://github.com/Capstone-Week-4/data_gathering

FrontEnd:
https://github.com/Capstone-Week-4/frontend

Backend:
https://github.com/airoca/Slang-Backend

---

## API 명세

| DESCRIPTION | METHOD | URI | REQUEST | RESPONSE |
| --- | --- | --- | --- | --- |
| 회원 가입 | POST | /register | User 객체 | User 객체 |
| 로그인 | POST | /login | {"id": "testid", "password": "1234", “name”:”testname”} | Map<String, Object> |
| 친구 요청 보내기 | POST | /friend/request | {"senderId": "test1", "receiverId": "test2"} | ResponseEntity |
| 친구 요청 수락 | POST | /friend/accept | {"senderId": "test1", "receiverId": "test2"} | ResponseEntity |
| 친구 요청 거절 | POST | /friend/decline | {"senderId": "test1", "receiverId": "test2"} | ResponseEntity |
| 보낸 친구 요청 목록 조회 | GET | /friend/requests/sent/{userId} | Path variable: userId | List<FriendRequest> |
| 받은 친구 요청 목록 조회 | GET | /friend/requests/received/{userId} | Path variable: userId | List<FriendRequest> |
| 친구 목록 조회 | GET | /friends/{userId} | Path variable: userId | List<Friend> |
| 채팅 대화 생성 | GET | /gpt/chat | Query parameter: words (List<String>) | String (생성된 문장) |
| 모든 사용자 정보 반환 | GET | /users | - | List<User> |
| 특정 사용자 정보 반환 | GET | /user/{userId} | Path variable: userId | User 객체 |
| 학습 후 포인트 업데이트 | POST | /updatePoint | {"userId": "test1", "category": "animal", "point": 10} | List<PointRecord>|
| 전체 랭킹 | GET | /rank | Path variable: userId | List<Point> |
| 특정 사용자 랭크 | GET | /rank/{userId} | Path variable: userId | Int |
| 특정 사용자 프로필 | GET | /profile/{userId} | Path variable: userId | UserProfile 객체 |
