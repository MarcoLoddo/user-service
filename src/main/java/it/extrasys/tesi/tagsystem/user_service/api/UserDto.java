package it.extrasys.tesi.tagsystem.user_service.api;

import java.util.List;

/**
 * Dto for user entity.
 *
 * @author marco
 *
 */
public class UserDto {
    private Long userId;
    private String name;
    private String email;
    private String password;
    private List<NfcTagDto> nfcTags;

    public String getEmail() {
        return this.email;
    }

    public String getName() {
        return this.name;
    }

    public List<NfcTagDto> getNfcTags() {
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

    /**
     * Sets the name.
     *
     * @param name
     *            the new name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Sets the nfc tags.
     *
     * @param nfcTags
     *            the new nfc tags
     */
    public void setNfcTags(List<NfcTagDto> nfcTags) {
        this.nfcTags = nfcTags;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

}
