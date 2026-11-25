package gregoriofer.local.service;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.*;

import gregoriofer.local.model.Registro;

public class XmlService {

    public List<Registro> leer(String rutaFichero) throws Exception {
        List<Registro> registros = new ArrayList<>();
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document doc = builder.parse(new File(rutaFichero));
        doc.getDocumentElement().normalize();

        NodeList nodos = doc.getElementsByTagName("registro");
        for (int i = 0; i < nodos.getLength(); i++) {
            Element elem = (Element) nodos.item(i);
            Registro r = new Registro();
            r.setIndicador(elem.getElementsByTagName("indicador").item(0).getTextContent());
            r.setPais(elem.getElementsByTagName("pais").item(0).getTextContent());
            r.setValor(Double.parseDouble(elem.getElementsByTagName("valor").item(0).getTextContent()));
            r.setAnio(Integer.parseInt(elem.getElementsByTagName("anio").item(0).getTextContent()));
            registros.add(r);
        }
        return registros;
    }

    public void escribir(String rutaFichero, List<Registro> registros) throws Exception {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document doc;
        Element raiz;

        File f = new File(rutaFichero);
        if (f.exists()) {
            doc = builder.parse(f);
            raiz = doc.getDocumentElement();
        } else {
            doc = builder.newDocument();
            raiz = doc.createElement("registros");
            doc.appendChild(raiz);
        }

        for (Registro r : registros) {
            Element elem = doc.createElement("registro");

            Element indicador = doc.createElement("indicador");
            indicador.setTextContent(r.getIndicador());
            elem.appendChild(indicador);

            Element pais = doc.createElement("pais");
            pais.setTextContent(r.getPais());
            elem.appendChild(pais);

            Element valor = doc.createElement("valor");
            valor.setTextContent(String.valueOf(r.getValor()));
            elem.appendChild(valor);

            Element anio = doc.createElement("anio");
            anio.setTextContent(String.valueOf(r.getAnio()));
            elem.appendChild(anio);

            raiz.appendChild(elem);
        }

        TransformerFactory tf = TransformerFactory.newInstance();
        Transformer transformer = tf.newTransformer();
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
        transformer.transform(new DOMSource(doc), new StreamResult(f));
    }
}
