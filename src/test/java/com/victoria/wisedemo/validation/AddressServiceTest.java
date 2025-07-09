package com.victoria.wisedemo.validation;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;  // import JUnit 5 assertions

class AddressServiceTest {

    private final AddressService svc = new AddressService();

    @Test
    void nullAddressIsInvalid() {
        assertFalse(svc.isValid(null));
    }

    @Test
    void validWhenAllFieldsCorrect() {
        Address good = new Address("NL", "1012WX", "Dam Square 1");
        assertTrue(svc.isValid(good));
    }
}
