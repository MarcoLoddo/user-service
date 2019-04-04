package it.extrasys.tesi.tagsystem.user_service.db.jpa.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import it.extrasys.tesi.tagsystem.user_service.db.jpa.entity.NfcTagEntity;
import it.extrasys.tesi.tagsystem.user_service.db.jpa.entity.UserEntity;

/**
 * The Interface NfcDao.
 */
public interface NfcDao extends JpaRepository<NfcTagEntity, Long> {

    /**
     * Find by user.
     *
     * @param user
     *            the user
     * @return the list
     */
    @Query("select n from NfcTags n where n.user = ?1")
    List<NfcTagEntity> findByUser(UserEntity user);
    /**
     * Find nfc by id.
     *
     * @param tag
     *            the tag
     * @return the nfc tag entity
     */
    @Query("select n from NfcTags n where nfcId = ?1")
    NfcTagEntity findNfcById(String tag);

}
