package com.travix.medusa.busyflights.utils.urls;

import lombok.Getter;

@Getter
public enum SupplierURL {

    TOUGH_JET("http://localhost:8080/toughjet/flights"),
    CRAZY_AIR("http://localhost:8080/crazyair/flights");

    private String url;

    SupplierURL(String url) {
        this.url = url;
    }
}
