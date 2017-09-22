package io.regna.runtime;

import io.regna.rt.Core;
import io.regna.runtime.core.ReflectiveLoader;

import java.util.Arrays;

/**
 * The Main class for The RegnaRuntime
 * This class uses <code>{@link ReflectiveLoader}</code>
 */
public class Main {

    /**
     * The entry point
     * @param args The Command Line Arguments
     */
    public static void main(String[] args) {
        String directory = args[0]; // Get the directory
        String program = args[1];   // Get the program
        // Load the program
        Core.create();  // Create or Instantiate the core
        ReflectiveLoader loader = new ReflectiveLoader(directory);
	    loader.runProgram(Arrays.copyOfRange(args, 2, args.length), program);
	    Core.instance.exit(0);
    }
}
