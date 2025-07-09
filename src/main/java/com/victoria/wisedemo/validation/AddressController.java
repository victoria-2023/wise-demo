package com.victoria.wisedemo.validation;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/validate")
public class AddressController {

    private final AddressService service = new AddressService();

    /**
     * POST /api/validate
     *  {
     *    "countryCode":"NL",
     *    "postalCode":"1012WX",
     *    "street":"Dam Square 1"
     *  }
     * → 200 OK { "valid": true } or { "valid": false }
     */
    @PostMapping
    public ResponseEntity<ValidationResult> validate(@RequestBody Address addr) {
        boolean ok = service.isValid(addr);
        return ResponseEntity.ok(new ValidationResult(ok));
    }

    /** Simple JSON wrapper for the boolean result */
    public static record ValidationResult(boolean valid) {}
}
