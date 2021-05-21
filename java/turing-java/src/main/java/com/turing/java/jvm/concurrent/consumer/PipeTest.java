package com.turing.java.jvm.concurrent.consumer;

import java.io.IOException;
import java.io.PipedReader;
import java.io.PipedWriter;

public class PipeTest {
    public static void main(String[] args) throws IOException {
        PipedWriter pipedWriter = new PipedWriter();
        PipedReader pipedReader = new PipedReader();
        pipedWriter.connect(pipedReader);
        Thread printThread = new Thread(new Print(pipedReader), "PrintThread");
        printThread.start();
        int receive = 0;
        try {
            while ((receive = System.in.read()) != -1) {
                pipedWriter.write(receive);
            }
        } finally {
            pipedWriter.close();
        }
    }


    static class Print implements Runnable {

        private PipedReader in;

        public Print(PipedReader in) {
            this.in = in;
        }

        @Override
        public void run() {
            int receive = 0;
            try {
                while ((receive = in.read()) != -1) {
                    System.out.print((char) receive);
                }
            } catch (IOException e) {

            }
        }
    }
}

