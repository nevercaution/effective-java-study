package com.nevercaution.demo.item15;

import com.nevercaution.demo.item15.first.First;

import java.util.Collections;

public class MainClass {

    public static void main(String[] args) {
        First first = new First();

        first.callPublic();
        first.callPackagePrivate();
        first.callProtected();
        first.callPrivate();

        Collections.unmodifiableList()
    }
}
