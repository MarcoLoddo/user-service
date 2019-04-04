package it.extrasys.tesi.tagsystem.user_service.db.jpa.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import it.extrasys.tesi.tagsystem.user_service.api.LoginDto;
import it.extrasys.tesi.tagsystem.user_service.api.NfcTagDto;
import it.extrasys.tesi.tagsystem.user_service.api.UserDto;

/**
 * User entity class for passing data in the system.
 *
 * @author marco
 *
 */
@Entity(name = "Users")
@Table(name = "users")
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long userId;

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    private String name;
    private String surname;
    private String email;
    private String password;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
    private List<NfcTagEntity> nfcTags = new ArrayList<>();

    /**
     * Constructor.
     */
    public UserEntity() {

    }
    /**
     * Constructor to build an entity based on login data.
     */
    public UserEntity(LoginDto loginDto) {
        this.email = loginDto.getEmail();
        this.password = loginDto.getPassword();
    }
    /**
     * Constructor to build an entity based on a Dto passed.
     */
    public UserEntity(UserDto userDto) {
        this.userId = userDto.getUserId();
        this.name = userDto.getName();
        this.email = userDto.getEmail();
        this.password = userDto.getPassword();
        if (userDto.getNfcTags() != null) {
            List<NfcTagEntity> nfcs = new ArrayList<>();
            for (NfcTagDto nfc : userDto.getNfcTags()) {
                NfcTagEntity newEntity = new NfcTagEntity();
                newEntity.setNfcId(nfc.getNfcId());
                newEntity.setDisabled(nfc.isDisabled());
                nfcs.add(newEntity);
            }
            this.nfcTags = nfcs;
        }

    }
    /**
     * Conversion of nfc tag entity list to dto list.
     */
    public List<NfcTagDto> convertNfcTagsToDto() {
        List<NfcTagDto> nfcs = new ArrayList<>();
        for (NfcTagEntity nfc : getNfcTags()) {
            NfcTagDto newDto = new NfcTagDto();
            newDto.setNfcId(nfc.getNfcId());
            newDto.setDisabled(nfc.isDisabled());
            nfcs.add(newDto);
        }
        return nfcs;
    }
    /**
     * Conversion of user entity to dto .
     */
    public UserDto convertToDto() {
        UserDto userDto = new UserDto();
        userDto.setEmail(getEmail());
        userDto.setPassword(getPassword());
        userDto.setUserId(getUserId());
        userDto.setName(this.name);
        if (this.nfcTags == null) {
            userDto.setNfcTags(new ArrayList<NfcTagDto>());
        } else {
            userDto.setNfcTags(convertNfcTagsToDto());
        }

        return userDto;
    }

    public String getEmail() {
        return this.email;
    }

    public String getFirstName() {
        return this.name;
    }

    public String getLastName() {
        return this.surname;
    }

    public List<NfcTagEntity> getNfcTags() {
        return this.nfcTags;
    }

    public String getPassword() {
        return this.password;
    }

    public Long getUserId() {
        return this.userId;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setFirstName(String firstName) {
        this.name = firstName;
    }

    public void setLastName(String lastName) {
        this.surname = lastName;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "User [id=" + this.userId + ", firstName=" + this.name
                + ", lastName=" + this.surname + ", email=" + this.email
                + "\n\n";
    }
}
