package it.extrasys.tesi.tagsystem.user_service.resources;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import it.extrasys.tesi.tagsystem.user_service.api.LoginDto;
import it.extrasys.tesi.tagsystem.user_service.api.NfcTagDto;
import it.extrasys.tesi.tagsystem.user_service.api.NfcUpdateDto;
import it.extrasys.tesi.tagsystem.user_service.api.UserDto;
import it.extrasys.tesi.tagsystem.user_service.db.jpa.entity.NfcTagEntity;
import it.extrasys.tesi.tagsystem.user_service.db.jpa.entity.UserEntity;
import it.extrasys.tesi.tagsystem.user_service.db.manager.UserManager;

// TODO: Auto-generated Javadoc
/**
 * The Class UserController.
 */
@RestController

public class UserController {

  /** The manager. */
  @Autowired
  private UserManager manager;

  /**
   * Adds the nfc.
   *
   * @param id
   *          the user id
   * @param nfcTagDto
   *          the nfc tag dto to be added
   * @return the user dto updated
   */
  @RequestMapping(value = "/users/{id}", method = RequestMethod.POST)
  public UserDto addNfc(@PathVariable Long id, @RequestBody NfcTagDto nfcTagDto) {
    NfcTagEntity newNfc = new NfcTagEntity(nfcTagDto, this.manager.findById(id));

    return this.manager.addNfc(newNfc).convertToDto();

  }

  /**
   * Adds a new user from a dto.
   *
   * @param userDto
   *          the user dto
   */
  @RequestMapping(value = "/users/", method = RequestMethod.POST)
  public UserDto addUser(@RequestBody UserDto userDto) {

    UserEntity userEntity = this.manager.addUser(new UserEntity(userDto));
    return userEntity.convertToDto();
  }

  /**
   * Find user by id.
   *
   * @param id
   *          the id
   * @return the user dto
   */
  @RequestMapping(value = "/users/{id}", method = RequestMethod.GET)
  public UserDto getUserById(@PathVariable Long id) {
    UserEntity userEntity = this.manager.findById(id);

    return userEntity.convertToDto();
  }

  /**
   * Find user by name service.
   *
   * @param name
   *          the name
   * @return the list
   */
  @RequestMapping(value = "/users/", method = RequestMethod.GET)
  public List<UserDto> getUserByName(@RequestParam String name) {
    List<UserEntity> entities = this.manager.findByName(name);
    List<UserDto> dtos = new ArrayList<>();
    for (UserEntity entity : entities) {
      dtos.add(entity.convertToDto());
    }
    return dtos;
  }

  /**
   * Login service.
   *
   * @param loginData
   *          the login data
   * @return the user dto
   */
  @RequestMapping(path = "/session")
  public UserDto login(@RequestBody LoginDto loginData) {
    try {
      return this.manager.findUser(new UserEntity(loginData)).convertToDto();
    } catch (Exception e) {
      e.printStackTrace();
      return null;
    }
  }

  /**
   * Update nfc.
   *
   * @param id
   *          the user id
   * @param nfcTagDto
   *          the nfc tag dto
   * @return the user updated
   */
  @RequestMapping(value = "/users/{id}", method = RequestMethod.PUT)
  public UserDto userNfcUpdate(@PathVariable Long id, @RequestBody NfcUpdateDto nfcTagDto) {
    NfcTagEntity oldNfc = new NfcTagEntity(nfcTagDto.getOlNfcTagDto(), this.manager.findById(id));
    NfcTagEntity newNfc = new NfcTagEntity(nfcTagDto.getNewNfcTagDto(), this.manager.findById(id));
    this.manager.updateNfc(oldNfc, newNfc);
    return this.manager.findById(id).convertToDto();

  }

  /**
   * Update nfc service.
   *
   * @param user
   *          the user
   * @return the user dto
   */
  @RequestMapping(value = "/users/", method = RequestMethod.PUT)
  public void userUpdate(@RequestBody UserDto user) {
    UserEntity userEntity = new UserEntity(user);
    this.manager.updateUser(userEntity);
    UserEntity updated = this.manager.findById(userEntity.getUserId());
    UserDto updatedDto = updated.convertToDto();
  }
}
