import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.FileWriter;
import java.io.IOException;

public class GeneradorArchivo {

    public void guardarJSON(Moneda moneda) throws IOException {

        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String nombreArchivo = "moneda_" + moneda.base_code() + ".json";

        try (FileWriter file = new FileWriter(nombreArchivo)) {
            file.write(gson.toJson(moneda));
        }
    }
}