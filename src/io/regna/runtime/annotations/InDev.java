package io.regna.runtime.annotations;

public @interface InDev {

    String plans() default "Further Development";
    boolean safe();
}
