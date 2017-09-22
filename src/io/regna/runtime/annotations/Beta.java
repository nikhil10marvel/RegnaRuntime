package io.regna.runtime.annotations;

public @interface Beta {

    /**
     * Reason Field
     * @return The Reason for it being marked Beta
     */
    String reason();

    /** The Future Plans for it*/
    String futurePlans();

    /** Weather the field is deprecated or not */
    boolean deprecated();

}
