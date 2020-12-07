package com.timmy.ks_tree.hotfix;

public class DoException implements IDo {
    @Override
    public String doSomething() {
        return "something error";
    }
}
