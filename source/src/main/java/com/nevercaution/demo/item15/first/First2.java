package com.nevercaution.demo.item15.first;

public class First2 {

    public static void main(String[] args) {
        First first = new First();

        first.callPublic();
        first.callPackagePrivate();
        first.callProtected();
        first.callPrivate();
    }
}
