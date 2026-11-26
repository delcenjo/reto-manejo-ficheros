package gregoriofer.local.service;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

import org.apache.jena.rdf.model.*;

import gregoriofer.local.model.Registro;

public class RdfService {

    private static final String NS = "http://gregoriofer.local/datos#";

    public List<Registro> leer(String rutaFichero) throws IOException {
        List<Registro> registros = new ArrayList<>();
        Model modelo = ModelFactory.createDefaultModel();
        try (InputStream is = new FileInputStream(rutaFichero)) {
            modelo.read(is, null, "RDF/XML");
        }

        Property pIndicador = modelo.createProperty(NS + "indicador");
        Property pPais = modelo.createProperty(NS + "pais");
        Property pValor = modelo.createProperty(NS + "valor");
        Property pAnio = modelo.createProperty(NS + "anio");

        ResIterator iter = modelo.listSubjects();
        while (iter.hasNext()) {
            Resource recurso = iter.next();
            Registro r = new Registro();
            if (recurso.hasProperty(pIndicador))
                r.setIndicador(recurso.getProperty(pIndicador).getString());
            if (recurso.hasProperty(pPais))
                r.setPais(recurso.getProperty(pPais).getString());
            if (recurso.hasProperty(pValor))
                r.setValor(recurso.getProperty(pValor).getDouble());
            if (recurso.hasProperty(pAnio))
                r.setAnio(recurso.getProperty(pAnio).getInt());
            registros.add(r);
        }
        return registros;
    }

    public void escribir(String rutaFichero, List<Registro> registros) throws IOException {
        Model modelo = ModelFactory.createDefaultModel();
        modelo.setNsPrefix("datos", NS);

        File f = new File(rutaFichero);
        if (f.exists()) {
            try (InputStream is = new FileInputStream(f)) {
                modelo.read(is, null, "RDF/XML");
            }
        }

        int id = (int) modelo.size() + 1;
        for (Registro r : registros) {
            Resource recurso = modelo.createResource(NS + "registro_" + id++);
            recurso.addProperty(modelo.createProperty(NS + "indicador"), r.getIndicador());
            recurso.addProperty(modelo.createProperty(NS + "pais"), r.getPais());
            recurso.addLiteral(modelo.createProperty(NS + "valor"), r.getValor());
            recurso.addLiteral(modelo.createProperty(NS + "anio"), r.getAnio());
        }

        try (OutputStream os = new FileOutputStream(f)) {
            modelo.write(os, "RDF/XML");
        }
    }
}
