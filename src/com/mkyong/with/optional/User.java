package com.mkyong.with.optional;

import java.util.Optional;

/**
 * @author: caojj08267
 * @Desc:
 * @create: 2026-05-25 15:21
 **/

public class User {
    private Address address;
    public Optional<Address> getAddress() {
        return Optional.ofNullable(address);
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public static void main(String[] args) {
//        Optional<Optional<String>> nested = Optional.of(new User())
//                .map(user -> user.getAddress())  // 返回 Optional<Address>
//                .map(address -> address.getCity()); // 返回 Optional<Optional<String>> ❌

        Optional<String> city = Optional.of(new User())
                .flatMap(user -> user.getAddress())   // 返回 Optional<Address>
                .flatMap(address -> address.getCity()); // 直接返回 Optional<String> ✅

        if (city.isPresent()) {
            System.out.println("City: " + city.get());
        }
    }
}

class Address {
    private String city;
    public Optional<String> getCity() {
        return Optional.ofNullable(city);
    }

    public void setCity(String city) {
        this.city = city;
    }
}