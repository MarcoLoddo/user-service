package it.extrasys.tesi.tagsystem.user_service.db.jpa.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import it.extrasys.tesi.tagsystem.user_service.api.NfcTagDto;

/**
 * Nfc tag entity class.
 *
 * @author marco
 *
 */
@Entity(name = "NfcTags")
@Table(name = "nfctags")
public class NfcTagEntity {
    @Id
    @Column(name = "nfc_id", unique = true)
    private String nfcId;
    @ManyToOne
    @JoinColumn(name = "user_Id")
    private UserEntity user;

    private boolean disabled;

    /**
     * Instantiates a new nfc tag entity.
     */
    public NfcTagEntity() {
        // TODO Auto-generated constructor stub
    }
    /**
     * Instantiates a new nfc tag entity.
     *
     * @param dto
     *            the dto
     * @param user
     *            the user
     */
    public NfcTagEntity(NfcTagDto dto, UserEntity user) {
        this.nfcId = dto.getNfcId();
        this.disabled = dto.isDisabled();
        this.user = user;
    }
    public String getNfcId() {
        return this.nfcId;
    }
    public UserEntity getUser() {
        return this.user;
    }
    public boolean isDisabled() {
        return this.disabled;
    }
    public void setDisabled(boolean disabled) {
        this.disabled = disabled;
    }
    public void setNfcId(String value) {
        this.nfcId = value;
    }
    public void setUser(UserEntity user) {
        this.user = user;
    }
    @Override
    public String toString() {
        return this.nfcId + ", disabled=" + this.disabled + ", " + this.user;
    }
}
