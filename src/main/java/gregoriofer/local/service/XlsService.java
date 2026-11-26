package gregoriofer.local.service;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import gregoriofer.local.model.Registro;

public class XlsService {

    public List<Registro> leer(String rutaFichero) throws IOException {
        List<Registro> registros = new ArrayList<>();
        try (FileInputStream fis = new FileInputStream(rutaFichero);
             Workbook wb = new XSSFWorkbook(fis)) {
            Sheet hoja = wb.getSheetAt(0);
            for (int i = 1; i <= hoja.getLastRowNum(); i++) {
                Row fila = hoja.getRow(i);
                if (fila == null) continue;
                Registro r = new Registro();
                r.setIndicador(fila.getCell(0).getStringCellValue());
                r.setPais(fila.getCell(1).getStringCellValue());
                r.setValor(fila.getCell(2).getNumericCellValue());
                r.setAnio((int) fila.getCell(3).getNumericCellValue());
                registros.add(r);
            }
        }
        return registros;
    }

    public void escribir(String rutaFichero, List<Registro> registros) throws IOException {
        Workbook wb;
        Sheet hoja;
        File f = new File(rutaFichero);

        if (f.exists()) {
            try (FileInputStream fis = new FileInputStream(f)) {
                wb = new XSSFWorkbook(fis);
            }
            hoja = wb.getSheetAt(0);
        } else {
            wb = new XSSFWorkbook();
            hoja = wb.createSheet("Datos");
            Row cabecera = hoja.createRow(0);
            cabecera.createCell(0).setCellValue("Indicador");
            cabecera.createCell(1).setCellValue("Pais");
            cabecera.createCell(2).setCellValue("Valor");
            cabecera.createCell(3).setCellValue("Anio");
        }

        int siguiente = hoja.getLastRowNum() + 1;
        for (Registro r : registros) {
            Row fila = hoja.createRow(siguiente++);
            fila.createCell(0).setCellValue(r.getIndicador());
            fila.createCell(1).setCellValue(r.getPais());
            fila.createCell(2).setCellValue(r.getValor());
            fila.createCell(3).setCellValue(r.getAnio());
        }

        try (FileOutputStream fos = new FileOutputStream(f)) {
            wb.write(fos);
        }
        wb.close();
    }
}
