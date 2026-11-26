package gregoriofer.local.service;

import java.io.*;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import gregoriofer.local.model.Registro;

public class JsonService {

    private final Gson gson = new GsonBuilder().setPrettyPrinting().create();

    public List<Registro> leer(String rutaFichero) throws IOException {
        try (Reader reader = new FileReader(rutaFichero)) {
            Type tipo = new TypeToken<List<Registro>>(){}.getType();
            List<Registro> registros = gson.fromJson(reader, tipo);
            return registros != null ? registros : new ArrayList<>();
        }
    }

    public void escribir(String rutaFichero, List<Registro> registros) throws IOException {
        List<Registro> existentes;
        File f = new File(rutaFichero);
        if (f.exists() && f.length() > 0) {
            existentes = leer(rutaFichero);
        } else {
            existentes = new ArrayList<>();
        }
        existentes.addAll(registros);
        try (Writer writer = new FileWriter(rutaFichero)) {
            gson.toJson(existentes, writer);
        }
    }
}
