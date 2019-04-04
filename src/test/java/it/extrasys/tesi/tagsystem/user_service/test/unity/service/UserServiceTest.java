package it.extrasys.tesi.tagsystem.user_service.test.unity.service;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyLong;
import static org.mockito.Matchers.anyString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import it.extrasys.tesi.tagsystem.user_service.UserManagingApplication;
import it.extrasys.tesi.tagsystem.user_service.api.LoginDto;
import it.extrasys.tesi.tagsystem.user_service.api.NfcTagDto;
import it.extrasys.tesi.tagsystem.user_service.api.NfcUpdateDto;
import it.extrasys.tesi.tagsystem.user_service.api.UserDto;
import it.extrasys.tesi.tagsystem.user_service.db.jpa.entity.NfcTagEntity;
import it.extrasys.tesi.tagsystem.user_service.db.jpa.entity.UserEntity;
import it.extrasys.tesi.tagsystem.user_service.db.manager.UserManager;
import it.extrasys.tesi.tagsystem.user_service.test.unity.JsonConverter;

// TODO: Auto-generated Javadoc
/**
 * The Class OrderServiceIntegrationTest.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = UserManagingApplication.class)
@WebAppConfiguration
public class UserServiceTest {

  /** The content type. */
  private MediaType contentType = new MediaType(MediaType.APPLICATION_JSON.getType(),
      MediaType.APPLICATION_JSON.getSubtype(), Charset.forName("utf8"));

  /** The mock mvc. */
  private MockMvc mockMvc;

  /** The web application context. */
  @Autowired
  private WebApplicationContext webApplicationContext;

  @Autowired
  private JsonConverter jsonConverter;

  /** The conf managing. */
  @MockBean
  private UserManager userManager;;

  /**
   * Setup.
   *
   * @throws Exception
   *           the exception
   */
  @Before
  public void setup() throws Exception {
    this.mockMvc = MockMvcBuilders.webAppContextSetup(this.webApplicationContext).build();
  }

  private UserDto createUserDto() {
    UserDto userEntity = new UserDto();

    userEntity.setEmail("prova@prova");
    userEntity.setName("clark");
    userEntity.setPassword("password");
    return userEntity;
  }

  private UserEntity createUserEntity() {
    UserEntity userEntity = new UserEntity();

    userEntity.setEmail("prova@prova");
    userEntity.setFirstName("clark");
    userEntity.setPassword("password");
    return userEntity;
  }

  int idTest = 0;

  private NfcTagEntity createNfcEntity() {
    NfcTagEntity nfcTagEntity = new NfcTagEntity();
    nfcTagEntity.setNfcId("nfcprovatest" + (this.idTest++));

    return nfcTagEntity;

  }

  private NfcTagDto toNfcDto(NfcTagEntity entity) {
    NfcTagDto nfcTagEntity = new NfcTagDto();
    nfcTagEntity.setNfcId(entity.getNfcId());
    nfcTagEntity.setDisabled(entity.isDisabled());
    return nfcTagEntity;

  }

  private NfcTagDto createNfcDto() {
    NfcTagDto nfcTagEntity = new NfcTagDto();
    nfcTagEntity.setNfcId("nfcprovatest" + this.idTest);

    return nfcTagEntity;

  }

  private LoginDto createLoginDto() {
    LoginDto loginDto = new LoginDto();
    loginDto.setEmail("prova@prova");
    loginDto.setPassword("superman");
    return loginDto;

  }

  private void mockIdUserDto(UserDto testUser) {
    testUser.setUserId(1L);

  }

  private void mockIdUser(UserEntity testUser) {
    testUser.setUserId(1L);

  }

  private UserEntity mockNfc(UserEntity user, NfcTagEntity nfc) {

    user.getNfcTags().add(nfc);
    return user;

  }

  @Test
  public void addUserTest() throws Exception {

    this.idTest = 0;
    UserEntity testUser = createUserEntity();
    mockIdUser(testUser);
    Mockito.when(this.userManager.addUser(any(UserEntity.class))).thenReturn(testUser);

    this.mockMvc
        .perform(post("/users/").contentType(this.contentType).content(this.jsonConverter.json(createUserDto())))
        .andExpect(status().isOk()).andExpect(jsonPath("$.userId", is(1))).andExpect(jsonPath("$.name", is("clark")));
  }

  @Test
  public void addNfcToUserTest() throws Exception {

    this.idTest = 0;
    UserEntity testUser = createUserEntity();
    mockIdUser(testUser);
    Mockito.when(this.userManager.addNfc(any(NfcTagEntity.class))).thenReturn(mockNfc(testUser, createNfcEntity()));

    this.mockMvc
        .perform(post("/users/1").contentType(this.contentType).content(this.jsonConverter.json(createNfcDto())))
        .andExpect(status().isOk()).andExpect(jsonPath("$.userId", is(1))).andExpect(jsonPath("$.name", is("clark")))
        .andExpect(jsonPath("$.nfcTags", Matchers.hasSize(1)))
        .andExpect(jsonPath("$.nfcTags[0].nfcId", is("nfcprovatest0")));

    // un proxy mock va ridefinito se va riusato, altrimenti è come se fosse
    // già stato chiamato
    Mockito.when(this.userManager.addNfc(any(NfcTagEntity.class))).thenReturn(mockNfc(testUser, createNfcEntity()));
    this.mockMvc
        .perform(post("/users/1").contentType(this.contentType).content(this.jsonConverter.json(createNfcDto())))
        .andExpect(status().isOk()).andExpect(jsonPath("$.userId", is(1))).andExpect(jsonPath("$.name", is("clark")))
        .andExpect(jsonPath("$.nfcTags", Matchers.hasSize(2)))
        .andExpect(jsonPath("$.nfcTags[0].nfcId", is("nfcprovatest0")))
        .andExpect(jsonPath("$.nfcTags[1].nfcId", is("nfcprovatest1")));
  }

  @Test
  public void replaceNfcTest() throws Exception {

    this.idTest = 0;
    UserEntity testUser = createUserEntity();
    mockIdUser(testUser);
    NfcTagEntity nfcTagEntity = createNfcEntity();
    Mockito.when(this.userManager.addNfc(any(NfcTagEntity.class))).thenReturn(mockNfc(testUser, nfcTagEntity));

    this.mockMvc
        .perform(post("/users/1").contentType(this.contentType).content(this.jsonConverter.json(createNfcDto())))
        .andExpect(status().isOk()).andExpect(jsonPath("$.userId", is(1))).andExpect(jsonPath("$.name", is("clark")))
        .andExpect(jsonPath("$.nfcTags", Matchers.hasSize(1)))
        .andExpect(jsonPath("$.nfcTags[0].nfcId", is("nfcprovatest0")));

    NfcUpdateDto updateDto = new NfcUpdateDto();
    updateDto.setOlNfcTagDto(toNfcDto(nfcTagEntity));
    nfcTagEntity.setDisabled(true);
    updateDto.setNewNfcTagDto(toNfcDto(nfcTagEntity));

    Mockito.when(this.userManager.findById(anyLong())).thenReturn(testUser);
    Mockito.doNothing().when(this.userManager).updateNfc(any(NfcTagEntity.class), any(NfcTagEntity.class));
    this.mockMvc.perform(put("/users/1").contentType(this.contentType).content(this.jsonConverter.json(updateDto)))
        .andExpect(status().isOk()).andExpect(jsonPath("$.userId", is(1))).andExpect(jsonPath("$.name", is("clark")))
        .andExpect(jsonPath("$.nfcTags", Matchers.hasSize(1))).andExpect(jsonPath("$.nfcTags[0].disabled", is(true)));

    nfcTagEntity = createNfcEntity();
    // un proxy mock va ridefinito se va riusato, altrimenti è come se fosse
    // già stato chiamato
    Mockito.when(this.userManager.addNfc(any(NfcTagEntity.class))).thenReturn(mockNfc(testUser, createNfcEntity()));
    this.mockMvc
        .perform(post("/users/1").contentType(this.contentType).content(this.jsonConverter.json(createNfcDto())))
        .andExpect(status().isOk()).andExpect(jsonPath("$.userId", is(1))).andExpect(jsonPath("$.name", is("clark")))
        .andExpect(jsonPath("$.nfcTags", Matchers.hasSize(2)))
        .andExpect(jsonPath("$.nfcTags[0].nfcId", is("nfcprovatest0")))
        .andExpect(jsonPath("$.nfcTags[1].nfcId", is("nfcprovatest2")));
  }

  @Test
  public void getUserByIdTest() throws Exception {

    UserEntity testUser = createUserEntity();
    mockIdUser(testUser);

    Mockito.when(this.userManager.findById(anyLong())).thenReturn(testUser);
    this.mockMvc.perform(get("/users/1").contentType(this.contentType)).andExpect(status().isOk())
        .andExpect(content().contentType(this.contentType)).andExpect(jsonPath("$.userId", is(1)))
        .andExpect(jsonPath("$.name", is("clark")));
  }

  @Test
  public void getUserByNameTest() throws Exception {

    UserEntity testUser = createUserEntity();
    mockIdUser(testUser);
    List<UserEntity> list = new ArrayList<>();
    list.add(testUser);
    Mockito.when(this.userManager.findByName(anyString())).thenReturn(list);
    this.mockMvc.perform(get("/users/?name=clark").contentType(this.contentType)).andExpect(status().isOk())
        .andExpect(content().contentType(this.contentType)).andExpect(jsonPath("$", Matchers.hasSize(1)))
        .andExpect(jsonPath("$[0].userId", is(1))).andExpect(jsonPath("$[0].name", is("clark")));
  }

  @Test
  public void getLoginSessionTest() throws Exception {

    LoginDto loginDto = createLoginDto();
    UserEntity userEntity = createUserEntity();
    mockIdUser(userEntity);
    Mockito.when(this.userManager.findUser(any(UserEntity.class))).thenReturn(userEntity);
    this.mockMvc.perform(post("/session/").contentType(this.contentType).content(this.jsonConverter.json(loginDto)))
        .andExpect(status().isOk()).andExpect(content().contentType(this.contentType))
        .andExpect(jsonPath("$.userId", is(1))).andExpect(jsonPath("$.name", is("clark")));
  }

}
