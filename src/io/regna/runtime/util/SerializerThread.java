package io.regna.runtime.util;

import io.regna.runtime.annotations.Beta;
import io.regna.runtime.annotations.InDev;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;

@Beta(deprecated = false, reason = "Not Tested or approved", futurePlans = "Vigorous Testing and enhancement")
@InDev(safe = false)
public class SerializerThread extends Thread{

    public static class SerializerStream {

        OutputStream outputStream;
        boolean done;

        public SerializerStream(OutputStream outputStream, boolean done) {
            this.outputStream = outputStream;
            this.done = done;
        }
    }

    Object src;
    SerializerStream output;

    public SerializerThread(Object object, SerializerStream output){
        src = object;
        this.output = output;
    }

    @Override
    public void run(){
        try (ObjectOutputStream oos = new ObjectOutputStream(output.outputStream)) {
            oos.writeObject(src);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
