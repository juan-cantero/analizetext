package io.juanqui.analizetext.azure;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class AzureConfigTest {
    @Autowired
    private AzureConfig azureConfig;

    @Test
    public void readConfigVarsCorrectly() {
        System.out.println(azureConfig.getBaseUrl());
    }

}
