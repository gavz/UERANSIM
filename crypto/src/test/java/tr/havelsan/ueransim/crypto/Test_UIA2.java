/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.crypto;

import com.google.gson.reflect.TypeToken;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import tr.havelsan.ueransim.utils.Json;
import tr.havelsan.ueransim.utils.Utils;
import tr.havelsan.ueransim.utils.bits.Bit;
import tr.havelsan.ueransim.utils.bits.BitString;
import tr.havelsan.ueransim.utils.octets.Octet4;
import tr.havelsan.ueransim.utils.octets.OctetString;

import java.util.Map;

public class Test_UIA2 {

    @Test
    public void test1() {
        testForFile("crypto/testdata/uia2/test1.json");
    }

    @Test
    public void test2() {
        testForFile("crypto/testdata/uia2/test2.json");
    }

    @Test
    public void test3() {
        testForFile("crypto/testdata/uia2/test3.json");
    }

    @Test
    public void test4() {
        testForFile("crypto/testdata/uia2/test4.json");
    }

    @Test
    public void test5() {
        testForFile("crypto/testdata/uia2/test5.json");
    }

    @Test
    public void test6() {
        testForFile("crypto/testdata/uia2/test6.json");
    }

    private void testForFile(String testFile) {
        Map<String, String> json = Json.fromJson(Utils.getResourceString(testFile), new TypeToken<Map<String, String>>() {
        }.getType());

        var count = new Octet4(json.get("count").replace(" ", ""));
        var fresh = new Octet4(json.get("fresh").replace(" ", ""));
        var direction = new Bit(Integer.parseInt(json.get("direction")));

        var messageHex = json.get("message").replace(" ", "");
        int messageBitLength = Integer.parseInt(json.get("length"));
        var message = BitString.fromHex(messageHex, messageBitLength);

        var key = new OctetString(json.get("key").replace(" ", ""));
        var expected = new Octet4(json.get("result").replace(" ", ""));

        var result = UIA2.computeMac(count, fresh, direction, message, key);
        Assert.assertEquals(expected, result);
    }
}
