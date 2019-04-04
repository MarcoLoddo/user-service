package it.extrasys.tesi.tagsystem.user_service.db.jpa.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import it.extrasys.tesi.tagsystem.user_service.db.jpa.entity.NfcTagEntity;
import it.extrasys.tesi.tagsystem.user_service.db.jpa.entity.UserEntity;
// TODO: Auto-generated Javadoc

/**
 * The Interface UserDao.
 */
public interface UserDao extends JpaRepository<UserEntity, Long> {

    /**
     * Find by email password.
     *
     * @param mail
     *            the mail
     * @param pwd
     *            the pwd
     * @return the user entity
     */
    @Query("SELECT u FROM Users u LEFT JOIN FETCH u.nfcTags n WHERE u.email = ?1 AND u.password = ?2")
    UserEntity findByEmailPassword(String mail, String pwd);

    /**
     * Find by id.
     *
     * @param id
     *            the id
     * @return the user entity
     */
    @Query("SELECT u FROM Users u LEFT JOIN FETCH u.nfcTags n WHERE u.userId = ?1")
    UserEntity findById(Long id);

    /**
     * Find user by name.
     *
     * @param fname
     *            the fname
     * @return the list
     */
    @Query("SELECT u FROM Users u WHERE u.name LIKE ?1")
    List<UserEntity> findUserByName(String fname);

    /**
     * Gets the user by nfc.
     *
     * @param nfc
     *            the nfc
     * @return the user by nfc
     */
    @Query("select n.user from NfcTags n where nfcId = ?1")
    UserEntity getUserByNfc(NfcTagEntity nfc);

}
