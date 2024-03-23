package com.confusedparrotfish.fluorescence.lib;

public class util {
    public static class dual<A,B> {
        public A a;
        public B b;
        public dual(A a, B b) {
            this.a = a;
            this.b = b;
        }
        public dual<A,B> a(A a) {
            this.a = a;
            return this;
        }
        public dual<A,B> b(B b) {
            this.b = b;
            return this;
        }
    }

    public static class option<T> {
        @SuppressWarnings("rawtypes")
        public static option none() {
            return new option<>(null);
        }
        public static <E> option<E> some(E t) {
            return new option<E>(t);
        }
        private T internal;
        private option(T p) {
            internal = p;
        }
        public boolean present() {
            return internal != null;
        }
        public T get() {
            return internal;
        }
    }
}
