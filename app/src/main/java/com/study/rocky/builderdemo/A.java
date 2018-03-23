package com.study.rocky.builderdemo;

/**
 * Created by Administrator on 2018/3/22 0022.
 */

public class A {
    private Builder builder;

    private A(Builder builder) {
        this.builder = builder;
    }

    public static class Builder {
        private String name;
        private String number;
        private String price;

        public Builder setName(String name) {
            this.name = name;
            return this;
        }

        public Builder setNumber(String number) {
            this.number = number;
            return this;
        }

        public Builder setPrice(String price) {
            this.price = price;
            return this;
        }

        public A build() {
            return new A(this);
        }
    }
}
