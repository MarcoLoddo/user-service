package it.extrasys.tesi.tagsystem.user_service.db.manager;

import java.util.List;

import it.extrasys.tesi.tagsystem.user_service.db.jpa.entity.NfcTagEntity;
import it.extrasys.tesi.tagsystem.user_service.db.jpa.entity.UserEntity;

/**
 * The Interface of the UserManager.
 */
public interface UserManager {

    /**
     * Adds the nfc.
     *
     * @param nfcTagEntity
     *            the nfc tag entity
     * @return the user entity
     */
    UserEntity addNfc(NfcTagEntity nfcTagEntity);

    /**
     * Adds the user.
     *
     * @param user
     *            the user
     */
    UserEntity addUser(UserEntity user);

    /**
     * Find by id.
     *
     * @param id
     *            the id
     * @return the user entity
     */
    UserEntity findById(Long id);

    /**
     * Find by name.
     *
     * @param name
     *            the name
     * @return the list
     */
    List<UserEntity> findByName(String name);

    /**
     * Find nfc of user.
     *
     * @param user
     *            the user
     * @return the list
     */
    List<NfcTagEntity> findNfcOfUser(UserEntity user);

    /**
     * Find user.
     *
     * @param user
     *            the user
     * @return the user entity
     */
    UserEntity findUser(UserEntity user);

    /**
     * Update nfc.
     *
     * @param oldNfc
     *            the old nfc
     * @param newNfc
     *            the new nfc
     */
    void updateNfc(NfcTagEntity oldNfc, NfcTagEntity newNfc);

    /**
     * Update user.
     *
     * @param user
     *            the user
     */
    void updateUser(UserEntity user);

    /**
     * User list by name.
     *
     * @param name
     *            the name
     * @return the list
     */
    List<UserEntity> userListByName(String name);
}
