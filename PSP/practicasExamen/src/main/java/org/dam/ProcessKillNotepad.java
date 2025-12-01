package org.dam;

import java.io.IOException;

public class ProcessKillNotepad {
    public static void main(String[] args) {
        String termial = "xed";
        ProcessBuilder pb = new ProcessBuilder(termial);
        try {
            Process p = pb.start();
            Thread.sleep(5000);
            p.destroy();
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    }
}
