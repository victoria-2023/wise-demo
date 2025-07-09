package com.victoria.wisedemo.validation;

import java.util.regex.Pattern;

public class AddressService {
    // Simplified EU-friendly postal code: 4–10 alphanumeric
    private static final Pattern POSTAL = Pattern.compile("^[A-Z0-9]{4,10}$");

    public boolean isValid(Address addr) {
        if (addr == null) return false;

        // Country: exactly two letters
        String cc = addr.countryCode();
        if (cc == null || cc.trim().length() != 2) return false;

        // Street: non-blank after trimming
        String st = addr.street();
        if (st == null || st.trim().isEmpty()) return false;

        // Postal: uppercase, no spaces, matches length
        String pc = addr.postalCode();
        if (pc == null) return false;
        return POSTAL.matcher(pc.trim().toUpperCase()).matches();
    }
}
