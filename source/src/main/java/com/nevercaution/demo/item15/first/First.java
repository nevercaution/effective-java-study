package com.nevercaution.demo.item15.first;

public class First {

    public void callPublic() {
        System.out.println("First.callPublic");
    }

    protected void callProtected() {
        System.out.println("First.callProtected");
    }

    void callPackagePrivate() {
        System.out.println("First.callPackagePrivate");
    }

    private void callPrivate() {
        System.out.println("First.callPrivate");
    }
}
