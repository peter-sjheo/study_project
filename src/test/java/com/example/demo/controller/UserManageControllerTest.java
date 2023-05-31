package com.example.demo.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;



//@WebMvcTest(UserManageController.class) //테스트를 위한 별도서비스를 둘 수 없다.
@SpringBootTest
@TestMethodOrder(MethodOrderer.MethodName.class)
public class UserManageControllerTest {

    @Autowired
    private WebApplicationContext ctx;    
    private MockMvc mockMvc;        
    
    @BeforeEach
    public void setup() {
        mockMvc = MockMvcBuilders.webAppContextSetup(ctx)
            .addFilters(new CharacterEncodingFilter("UTF-8", true)).build();            
    }

    /*
    @DisplayName("[POST-MethodSource]userGroup 저장 테스트")
    @ParameterizedTest
    @MethodSource("initUserGroup")
    public void _0_methodSourceUserGroupTest(UserGroup userGroup) throws Exception {
        
        //when        
        MockHttpServletRequestBuilder httpMethodCaller = MockMvcRequestBuilders.request(HttpMethod.POST, new URI("/user-group"));
        BaseApiResponse<UserGroup> baseApiResponse = this.whenUserGroupMethodCall(userGroup, httpMethodCaller);                    
        UserGroup savedUserGroup = baseApiResponse.getData();

        //then    
        assertThat(baseApiResponse.getCustomResultCode()).isEqualTo(CustomResultCode.RESPONSE_SUCCESS.getResultCode());
        assertThat(baseApiResponse.getCustomResultMsg()).isEqualTo(CustomResultCode.RESPONSE_SUCCESS.getResultMsg());
        assertThat(baseApiResponse.getHttpStatus()).isEqualTo(HttpStatus.OK);
        assertThat(baseApiResponse.getHttpResultMsg()).isEqualTo(HttpStatus.OK.getReasonPhrase());
        assertThat(userGroup.getUserGroupSeqId()).isEqualTo(savedUserGroup.getUserGroupSeqId());
    }


    @DisplayName("[POST-MethodSource]userId 저장 테스트")
    @ParameterizedTest
    @MethodSource("initUserId")
    public void _1_methodSourceUserIdTest(User user) throws Exception {
        
        //given
        

        //when
        MockHttpServletRequestBuilder httpUserMethodCaller = MockMvcRequestBuilders.request(HttpMethod.POST, new URI("/user"));
        BaseApiResponse<User> userBaseApiResponse = this.whenUserMethodCall(1L, user, httpUserMethodCaller);                    
        User savedUser = userBaseApiResponse.getData();
        
        UserGroup userGroup = new UserGroup(1L);
        userGroup.setUserList(new ArrayList<>());
        userGroup.addUser(user);
        MockHttpServletRequestBuilder httpUserGroupMethodCaller = MockMvcRequestBuilders.request(HttpMethod.POST, new URI("/user-group"));
        BaseApiResponse<UserGroup> userGroupBaseApiResponse = this.whenUserGroupMethodCall(userGroup, httpUserGroupMethodCaller);                    
        UserGroup savedUserGroup = userGroupBaseApiResponse.getData();    


        //then    
        assertThat(userBaseApiResponse.getCustomResultCode()).isEqualTo(CustomResultCode.RESPONSE_SUCCESS.getResultCode());
        assertThat(userBaseApiResponse.getCustomResultMsg()).isEqualTo(CustomResultCode.RESPONSE_SUCCESS.getResultMsg());
        assertThat(userBaseApiResponse.getHttpStatus()).isEqualTo(HttpStatus.OK);
        assertThat(userBaseApiResponse.getHttpResultMsg()).isEqualTo(HttpStatus.OK.getReasonPhrase());
        assertThat(user.getUserSeqId()).isEqualTo(savedUser.getUserSeqId());
        assertThat(user.getUserName()).isEqualTo(savedUser.getUserName());        
        //assertThat(user.getUserGroup().getUserGroupSeqId()).isEqualTo(savedUser.getUserGroup().getUserGroupSeqId());
    }
    

    


    


    @DisplayName("[GET-ValueSource]userId 조회 테스트")
    @ParameterizedTest
    @ValueSource(longs = {1L, 2L, 3L, 4L, 5L})
    public void _2_getValueSourceUserIdTest(Long userId) throws Exception {

        //when
        MockHttpServletRequestBuilder httpMethodCaller = MockMvcRequestBuilders.request(HttpMethod.GET, new URI("/user/" + userId.toString()));
        BaseApiResponse<User> baseApiResponse = this.whenUserMethodCall(userId, null, httpMethodCaller);                    
        User user = baseApiResponse.getData();

        //then    
        assertThat(baseApiResponse.getCustomResultCode()).isEqualTo(CustomResultCode.RESPONSE_SUCCESS.getResultCode());
        assertThat(baseApiResponse.getCustomResultMsg()).isEqualTo(CustomResultCode.RESPONSE_SUCCESS.getResultMsg());
        assertThat(baseApiResponse.getHttpStatus()).isEqualTo(HttpStatus.OK);
        assertThat(baseApiResponse.getHttpResultMsg()).isEqualTo(HttpStatus.OK.getReasonPhrase());
        assertThat(user.getUserSeqId()).isEqualTo(userId);
    }



    @DisplayName("[PUT-ValueSource]userId 업데이트 테스트")
    @ParameterizedTest
    @MethodSource("updateUserId")
    public void _3_putValueSourceUserIdTest(User user) throws Exception {

        //when
        MockHttpServletRequestBuilder httpMethodCaller = MockMvcRequestBuilders.request(HttpMethod.GET, new URI("/user/" + user.getUserSeqId().toString()));
        BaseApiResponse<User> baseApiResponse = this.whenUserMethodCall(user.getUserSeqId(), user, httpMethodCaller);                    
        User savedUser = baseApiResponse.getData();

        //then    
        assertThat(baseApiResponse.getCustomResultCode()).isEqualTo(CustomResultCode.RESPONSE_SUCCESS.getResultCode());
        assertThat(baseApiResponse.getCustomResultMsg()).isEqualTo(CustomResultCode.RESPONSE_SUCCESS.getResultMsg());
        assertThat(baseApiResponse.getHttpStatus()).isEqualTo(HttpStatus.OK);
        assertThat(baseApiResponse.getHttpResultMsg()).isEqualTo(HttpStatus.OK.getReasonPhrase());
        assertThat(user.getUserSeqId()).isEqualTo(savedUser.getUserSeqId());
        assertThat(user.getUserName()).isEqualTo(savedUser.getUserName());
    }


    @DisplayName("[DELETE-ValueSource]userId 삭제 테스트")
    @ParameterizedTest
    @ValueSource(longs = {1L, 2L, 3L, 4L, 5L})
    public void _4_deleteValueSourceUserIdTest(Long userId) throws Exception {

        //when
        MockHttpServletRequestBuilder httpMethodCaller = MockMvcRequestBuilders.request(HttpMethod.GET, new URI("/user/" + userId.toString()));
        BaseApiResponse<User> baseApiResponse = this.whenUserMethodCall(userId, null, httpMethodCaller);                    
        User user = baseApiResponse.getData();

        //then    
        assertThat(baseApiResponse.getCustomResultCode()).isEqualTo(CustomResultCode.RESPONSE_SUCCESS.getResultCode());
        assertThat(baseApiResponse.getCustomResultMsg()).isEqualTo(CustomResultCode.RESPONSE_SUCCESS.getResultMsg());
        assertThat(baseApiResponse.getHttpStatus()).isEqualTo(HttpStatus.OK);
        assertThat(baseApiResponse.getHttpResultMsg()).isEqualTo(HttpStatus.OK.getReasonPhrase());
        assertThat(user.getUserSeqId()).isEqualTo(userId);
    }


    
    private static Stream<Arguments> initUserGroup() {    
        return Stream.of(
            Arguments.of(new UserGroup())
        );
    }
    


    private static Stream<Arguments> initUserId() {            
        return Stream.of(
            Arguments.of(User.builder().build())
        );
    }

    

    private static Stream<Arguments> updateUserId() {
        return Stream.of(
            Arguments.of(User.builder().build())
        );
    }



    private BaseApiResponse<User> whenUserMethodCall(Long userId, User user, MockHttpServletRequestBuilder httpMethodCallRequest) {

        try {
                                    
            String userString = "";
            if(user != null) {
                userString = objMapper.writeValueAsString(user);
            }
            HttpHeaders httpheaders = new HttpHeaders();
            httpheaders.set("X-VERSION-ID", "0001");

            ResultActions resultActions = mockMvc.perform(httpMethodCallRequest
            .headers(httpheaders)
            .contentType(MediaType.APPLICATION_JSON)
            .content(userString)
            .accept(MediaType.APPLICATION_JSON));                                
            
            String apiResultString = resultActions.andReturn().getResponse().getContentAsString();            
            BaseApiResponse<User> baseApiResponse = objMapper.readValue(apiResultString, new TypeReference<BaseApiResponse<User>>() {});
            return baseApiResponse;
        } catch(Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }

    }




    private BaseApiResponse<UserGroup> whenUserGroupMethodCall(UserGroup userGroup, MockHttpServletRequestBuilder httpMethodCallRequest) {

        try {
            
            ObjectMapper objMapper = new ObjectMapper();

            String userGroupString = "";
            if(userGroup != null) {
                userGroupString = objMapper.writeValueAsString(userGroup);
            }
            
            HttpHeaders httpheaders = new HttpHeaders();
            httpheaders.set("X-VERSION-ID", "0001");

            ResultActions resultActions = mockMvc.perform(httpMethodCallRequest
            .headers(httpheaders)
            .contentType(MediaType.APPLICATION_JSON)
            .content(userGroupString)
            .accept(MediaType.APPLICATION_JSON));
                        
            resultActions.andDo(MockMvcResultHandlers.print());
            
            String apiResultString = resultActions.andReturn().getResponse().getContentAsString();            
            BaseApiResponse<UserGroup> baseApiResponse = objMapper.readValue(apiResultString, new TypeReference<BaseApiResponse<UserGroup>>() {});
            return baseApiResponse;
        } catch(Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }

    }

    */

}
