package it.extrasys.tesi.tagsystem.user_service.test.unity;

import static org.junit.Assert.assertNotNull;

import java.io.IOException;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.mock.http.MockHttpOutputMessage;
import org.springframework.stereotype.Component;

/**
 * The Class JsonConverterImpl.
 */
@Component
public class JsonConverterImpl implements JsonConverter {

    /** The mapping jackson 2 http message converter. */
    private HttpMessageConverter mappingJackson2HttpMessageConverter;

    /**
     * Sets the converters.
     *
     * @param converters
     *            the new converters
     */
    @Autowired
    void setConverters(HttpMessageConverter<?>[] converters) {

        this.mappingJackson2HttpMessageConverter = Arrays.asList(converters)
                .stream()
                .filter(hmc -> hmc instanceof MappingJackson2HttpMessageConverter)
                .findAny().orElse(null);

        assertNotNull("the JSON message converter must not be null",
                this.mappingJackson2HttpMessageConverter);
    }
    /*
     * (non-Javadoc)
     * 
     * @see
     * it.extrasys.tesi.tagsystem.order_service.test.integration.JsonConverter#
     * json(java.lang.Object)
     */
    @Override
    public String json(Object o) throws IOException {
        MockHttpOutputMessage mockHttpOutputMessage = new MockHttpOutputMessage();
        this.mappingJackson2HttpMessageConverter.write(o,
                MediaType.APPLICATION_JSON, mockHttpOutputMessage);
        return mockHttpOutputMessage.getBodyAsString();
    }
}
