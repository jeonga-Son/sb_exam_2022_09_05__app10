package com.ll.exam.app10;

import com.ll.exam.app10.app.member.controller.MemberController;
import com.ll.exam.app10.app.member.entity.Member;
import com.ll.exam.app10.app.member.service.MemberService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.web.client.RestTemplate;

import java.io.InputStream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class MemberTests {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private MemberService memberService;

    @Test
    @DisplayName("회원의 수")
    void t2() {
        long count = memberService.count();
        assertThat(count).isGreaterThan(0);
    }

    @Test
    @DisplayName("user1로 로그인 후 프로필페이지에 접속하면 user1의 이메일이 보여야 한다.")
    // 테스트시 Mock 유저로는 MemberContext 객체가 생성되지 않기때문에 발생하는 버그를 @WithUserDetail 로 해결
    @WithUserDetails("user1") // 이 테스트를 진행할 때 user1 회원으로 로그인하고 테스트를 한다. (memberContext를 사용하기 때문에 변경)
    void t3() throws Exception {
        // mockMvc로 로그인 처리
        ResultActions resultActions = mvc
                .perform(
                        get("/member/profile")
                )
                .andDo(print());

        resultActions
                .andExpect(status().is2xxSuccessful())
                .andExpect(handler().handlerType(MemberController.class))
                .andExpect(handler().methodName("showProfile"))
                .andExpect(content().string(containsString("user1@test.com")));
    }

    @Test
    @DisplayName("user4로 로그인 후 프로필페이지에 접속하면 user4의 이메일이 보여야 한다.")
    // 테스트시 Mock 유저로는 MemberContext 객체가 생성되지 않기때문에 발생하는 버그를 @WithUserDetail 로 해결
    @WithUserDetails("user4") // 이 테스트를 진행할 때 user4 회원으로 로그인하고 테스트를 한다. (memberContext를 사용하기 때문에 변경)
    void t4() throws Exception {
        // mockMvc로 로그인 처리
        ResultActions resultActions = mvc
                .perform(
                        get("/member/profile")
                )
                .andDo(print());

        resultActions
                .andExpect(status().is2xxSuccessful())
                .andExpect(handler().handlerType(MemberController.class))
                .andExpect(handler().methodName("showProfile"))
                .andExpect(content().string(containsString("user4@test.com")));
    }

    @Test
    @DisplayName("회원가입")
    @Rollback(false)
    void t5() throws Exception {
        String testUploadFileUrl = "https://picsum.photos/200/300";
        String originalFileName = "test.png";

        // wget
        // RestTemplate은 Spring에서 HTTP 통신을 RESTful 형식에 맞게 손쉬운 사용을 제공해주는 템플릿이다.
        // HTTP 요청 후 JSON, XML, String 과 같은 응답을 받을 수 있는 템플릿
        RestTemplate restTemplate = new RestTemplate();
        // ResponseEntity란, httpentity를 상속받는, 결과 데이터와 HTTP 상태 코드를 직접 제어할 수 있는 클래스이다.
        ResponseEntity<Resource> response = restTemplate.getForEntity(testUploadFileUrl, Resource.class);
        // InputStream은 외부에서 데이터를 읽는 역할을 수행하고, OutputStream은 외부로 데이터를 출력하는 역할을 수행한다.
        InputStream inputStream = response.getBody().getInputStream();

        MockMultipartFile profileImg = new MockMultipartFile(
                "profileImg",
                originalFileName,
                "img/png",
                inputStream
        );

        // 회원가입(MVC MOCK)
        // when
        ResultActions resultActions = mvc.perform(
                        multipart("/member/join")
                                .file(profileImg)
                                .param("username", "user5")
                                .param("password", "1234")
                                .param("email", "user5@test.com")
                                .characterEncoding("UFT-8"))
                .andDo(print());

        resultActions
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/member/profile"))
                .andExpect(handler().handlerType(MemberController.class))
                .andExpect(handler().methodName("join"));

        Member member = memberService.getMemberById(5L);

        assertThat(member).isNotNull();

        memberService.removeProfileImg(member);
    }
}
