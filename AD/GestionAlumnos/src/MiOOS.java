import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;

public class MiOOS  extends ObjectOutputStream {
    public MiOOS(OutputStream out) throws IOException {
        super(out);
    }
    protected MiOOS() throws IOException {
        super();
    }

    protected void writeStreamHeader() throws IOException {

    }
}
