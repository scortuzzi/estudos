import java.util.UUID;

public class Uuid {

    public static void main(String[] args) {


        UUID uuid = UUID.randomUUID();
        String meuUuid = uuid.toString();

        System.out.println(meuUuid);
    }
}
