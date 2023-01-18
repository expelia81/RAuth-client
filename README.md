# AuthorizationClient
purpose making it possible to authorize by just using annotations in the MSA environment.




### 구현 요구 사항
1. Access Token (혹은 Code)를 가지지 않은 유저를 Key Cloak Login으로 redirection.
2. Code를 가지고 있다면 해당 Code로 Key Cloak을 조회하여 권한 습득
3. 