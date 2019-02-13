package com.creditscore.utils;


import com.google.common.hash.Hashing;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;

import java.nio.charset.Charset;

@RunWith(JUnitPlatform.class)
public class TestHashing {

    @Test
    public void testMd5Hashing() {

        Assertions.assertEquals("8c6976e5b5410415bde908bd4dee15dfb167a9c873fc4bb8a81f6f2ab448a918", Hashing.sha256().hashString("admin", Charset.defaultCharset()).toString());
        Assertions.assertEquals("04f8996da763b7a969b1028ee3007569eaf3a635486ddab211d512c85b9df8fb", Hashing.sha256().hashString("user", Charset.defaultCharset()).toString());
    }
}
