package it.extrasys.tesi.tagsystem.user_service.test;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Commit;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.transaction.AfterTransaction;

import it.extrasys.tesi.tagsystem.user_service.db.jpa.dao.NfcDao;
import it.extrasys.tesi.tagsystem.user_service.db.jpa.dao.UserDao;
import it.extrasys.tesi.tagsystem.user_service.db.jpa.entity.NfcTagEntity;
import it.extrasys.tesi.tagsystem.user_service.db.jpa.entity.UserEntity;

/**
 * The Class Test.
 */
@DataJpaTest
@RunWith(SpringRunner.class)
public class UserTest {

    @Autowired
    private UserDao userDao;
    @Autowired
    private NfcDao nfcDao;

    /**
     * Adds the nfc.
     */
    @Test
    @Commit
    public void addNfc() {
        addUser();
        UserEntity userEntity = this.userDao.findByEmailPassword("super@man",
                "clarkent");
        NfcTagEntity nfcTagEntity = new NfcTagEntity();
        nfcTagEntity.setNfcId("prova1");
        nfcTagEntity.setUser(userEntity);
        this.nfcDao.save(nfcTagEntity);
        this.userDao.save(userEntity);

        assertNotNull(this.nfcDao.findNfcById("prova1"));
    }

    private void addUser() {
        UserEntity userEntity = new UserEntity();
        userEntity.setEmail("super@man");
        userEntity.setFirstName("Clark");
        userEntity.setLastName("Kent");
        userEntity.setPassword("clarkent");
        NfcTagEntity nfcTagEntity = new NfcTagEntity();
        nfcTagEntity.setNfcId("provanfc");
        nfcTagEntity.setUser(userEntity);
        List<NfcTagEntity> list = new ArrayList<>();
        list.add(nfcTagEntity);

        userEntity.getNfcTags().addAll(list);

        this.userDao.save(userEntity);

        this.nfcDao.save(nfcTagEntity);

    }

    private void addUserNoNfc() {
        UserEntity userEntity = new UserEntity();
        userEntity.setEmail("super@man");
        userEntity.setFirstName("Clark");
        userEntity.setLastName("Kent");
        userEntity.setPassword("clarkent");
        this.userDao.save(userEntity);

    }
    /**
     * Adds the user.
     */
    @Test
    @Commit
    public void addUserTest() {
        addUser();
        assertNotNull(
                this.userDao.findByEmailPassword("super@man", "clarkent"));
    }

    /**
     * Find user no nfc.
     */
    @Test
    @Commit
    public void findUserNoNfc() {
        addUserNoNfc();
        UserEntity userDto = this.userDao.findByEmailPassword("super@man",
                "clarkent");
        System.out.println(userDto);
    }

    /**
     * Read nfc.
     */
    @Test
    @Commit
    public void readNfc() {
        addUser();
        UserEntity userEntity = this.userDao.findByEmailPassword("super@man",
                "clarkent");
        assertTrue(this.nfcDao.findByUser(userEntity).size() > 0);
    }

    /**
     * Setup.
     *
     * @throws Exception
     *             the exception
     */
    @Before
    public void setup() throws Exception {
        this.nfcDao.deleteAll();
        this.userDao.deleteAll();

    }

    /**
     * Show.
     */
    @AfterTransaction
    public void show() {

        System.out.println("\n\n\n\n\n");
        System.out.println(this.userDao.findAll());

        System.out.println("\n\n\n\n\n");
        System.out.println(this.nfcDao.findAll());
        System.out.println("\n\n\n\n\n");

    }

    /**
     * Update nfc.
     */
    @Test
    @Commit
    public void updateNfc() {
        addUser();
        NfcTagEntity oldNfc = this.nfcDao.findNfcById("provanfc");
        NfcTagEntity newNfc = new NfcTagEntity();

        newNfc.setNfcId("provanfc2");
        newNfc.setUser(oldNfc.getUser());
        this.nfcDao.delete(oldNfc);
        this.nfcDao.save(newNfc);
        assertTrue(this.nfcDao.findNfcById("provanfc2") != null);
    }

}
