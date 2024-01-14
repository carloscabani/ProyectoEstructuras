/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import Clases.Contacto;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

/**
 *
 * @author Abeni
 */
public class FileManager {

    public static void writeContactToFile(String filePath, Contacto contacto) {
        // Lógica para escribir datos de contacto en el archivo especificado
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, true))) {
            String cadena = //Creoo la cadena según el formato que yo requiera
            writer.write(cadena);
            writer.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void writeAdditionalFieldsToFile(String filePath, Contacto camposAdicionales) {
        // Lógica para escribir campos adicionales en el archivo especificado
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, true))) {
            String cadena = //Creoo la cadena según el formato que yo requiera
            writer.write(cadena);
            writer.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
}