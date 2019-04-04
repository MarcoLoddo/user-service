package it.extrasys.tesi.tagsystem.user_service.api;

// TODO: Auto-generated Javadoc
/**
 * The Class NfcTagDto.
 */
public class NfcTagDto {

    /** status. */
    private boolean disabled;

    /** The nfc id. */
    private String nfcId;

    /**
     * Gets the nfc id.
     *
     * @return the nfc id
     */
    public String getNfcId() {
        return this.nfcId;
    }

    /**
     * Checks if is disabled.
     *
     * @return true, if is disabled
     */
    public boolean isDisabled() {
        return this.disabled;
    }

    /**
     * Sets disabled status.
     *
     * @param disabled
     *            the new disabled
     */
    public void setDisabled(boolean disabled) {
        this.disabled = disabled;
    }

    /**
     * Sets the nfc id.
     *
     * @param nfcId
     *            the new nfc id
     */
    public void setNfcId(String nfcId) {
        this.nfcId = nfcId;
    }
}
