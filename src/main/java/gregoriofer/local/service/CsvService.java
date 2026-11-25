package gregoriofer.local.service;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

import gregoriofer.local.model.Registro;

public class CsvService {

    public List<Registro> leer(String rutaFichero) throws IOException {
        List<Registro> registros = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(rutaFichero))) {
            String linea;
            br.readLine();
            while ((linea = br.readLine()) != null) {
                String[] campos = linea.split(";");
                if (campos.length >= 4) {
                    Registro r = new Registro();
                    r.setIndicador(campos[0].trim());
                    r.setPais(campos[1].trim());
                    r.setValor(Double.parseDouble(campos[2].trim()));
                    r.setAnio(Integer.parseInt(campos[3].trim()));
                    registros.add(r);
                }
            }
        }
        return registros;
    }

    public void escribir(String rutaFichero, List<Registro> registros) throws IOException {
        File f = new File(rutaFichero);
        boolean nuevo = !f.exists() || f.length() == 0;
        try (PrintWriter pw = new PrintWriter(new FileWriter(rutaFichero, true))) {
            if (nuevo) {
                pw.println("indicador;pais;valor;anio");
            }
            for (Registro r : registros) {
                pw.println(r.getIndicador() + ";" + r.getPais() + ";" + r.getValor() + ";" + r.getAnio());
            }
        }
    }
}
