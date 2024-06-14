package com.multi.restapi.section02.responseentity;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.util.*;

@RestController
@RequestMapping("/entity")
public class ResponseEntityTestController {

//    https://restfulapi.net/resource-naming/
//    https://docs.spring.io/spring-framework/docs/current/javadoc-api/org/springframework/http/ResponseEntity.html
//    https://docs.spring.io/spring-framework/reference/web/webmvc/mvc-controller/ann-methods/return-types.html

    private List<UserDTO> users;
    public ResponseEntityTestController() {
        users = new ArrayList<>();

        users.add(new UserDTO(1, "user01", "pass01", "홍길동", new Date()));
        users.add(new UserDTO(2, "user02", "pass02", "유관순", new Date()));
        users.add(new UserDTO(3, "user03", "pass03", "이순신", new Date()));
    }

    @GetMapping("/users")
    public ResponseEntity<ResponseMessage> findAllUsers() {

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("application", "json", StandardCharsets.UTF_8));


        Map<String, Object> responseMap = new HashMap<>();
        responseMap.put("user", users);

        ResponseMessage responseMessage = new ResponseMessage(200, "조회 성공!", responseMap);

        // 빌더형태
        return new ResponseEntity<>(responseMessage, headers, HttpStatus.OK);
    }

    @GetMapping("/users/{userNo}")
    public ResponseEntity<ResponseMessage> findUserByNo(@PathVariable int userNo){
//        public ResponseEntity<ResponseMessage> findUserByNo(@PathVariable("userNo") int userNo){
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("application", "json", StandardCharsets.UTF_8));

        UserDTO foundUser = users.stream().filter(user -> user.getNo() == userNo).toList().get(0);

        System.out.println(foundUser);

        Map<String, Object> responseMap = new HashMap<>();
        responseMap.put("user", foundUser);
        /* 빌더 형태로도 사용 가능하다. */
        return ResponseEntity
                .ok()
                .headers(headers)
                .body(new ResponseMessage(200, "조회 성공!", responseMap));
    }
    @PostMapping("/users")
    public ResponseEntity<?> registUser(@RequestBody UserDTO newUser) throws URISyntaxException {

        System.out.println(newUser);

        int lastUserNo = users.get(users.size() - 1).getNo();
        newUser.setNo(lastUserNo + 1);
        newUser.setEnrollDate(new Date());
        users.add(newUser);
        System.out.println("newUser" + newUser);


        return ResponseEntity
                .created(URI.create("/entity/users/" + users.get(users.size() - 1).getNo()))
                .build();
    }

    @PutMapping("/users/{userNo}")
    public ResponseEntity<?> modifyUser(@RequestBody UserDTO modifyInfo, @PathVariable int userNo) throws URISyntaxException {
        System.out.println(modifyInfo);

        // userNo 와 일치하는 user 찾아서 정보 set
        UserDTO foundUser =
                users.stream().filter(user -> user.getNo() == userNo).toList().get(0);
        foundUser.setId(modifyInfo.getId());
        foundUser.setPwd(modifyInfo.getPwd());
        foundUser.setName(modifyInfo.getName());

        return ResponseEntity
                .created(URI.create("/entity/users/" + userNo))
                .build();
    }

    @DeleteMapping("/users/{userNo}")
    public ResponseEntity<?> removeUser(@PathVariable int userNo) {

        UserDTO foundUser = users.stream().filter(user -> user.getNo() == userNo).toList().get(0);
        users.remove(foundUser);

        return ResponseEntity
                .noContent() //204
                .build();
    }
}

