package testingWeb.services.utils;

import org.json.JSONObject;
import org.json.JSONTokener;

import testingWeb.models.TheWebElement;

import static testingWeb.tests.base.BaseTest.jsonModels;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.Path;
import java.util.stream.Stream;

public class TheJsonModels {
      public static void loadJsonModels(String fileName) {
        try {
            jsonModels = new JSONObject();
            // Parsing files
            LoadJsonFiles(fileName);
        }
        catch(Exception e) {
            jsonModels = new JSONObject();
            System.out.println("Error al crear el Objeto Json");
            System.out.println(e.getMessage());
        }

    }

    private static void LoadJsonFiles(String directoryPath) {
    	
        try {
            Files.walk(Paths.get(directoryPath))
                    .filter(Files::isRegularFile)
                    .filter(path -> path.toString().endsWith(".json"))
                    .forEach(TheJsonModels::processJsonFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    
    }

    private static void processJsonFile(Path filePath) {
        try {
            // Parsing file
            FileReader content = new FileReader(filePath.toString());
            // Obtener contenido del archivo
            BufferedReader bufferedReader = new BufferedReader(content);
            String contenido = "";
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                contenido += line + "\n";
            }
            // Obtener el nombre del directorio anterior al archivo
            String directoryName = "";
            Path parentDirectory = filePath.getParent();
            if (parentDirectory != null) {
                directoryName = parentDirectory.getFileName().toString();
            }
            // Obtener el nombre del archivo
            String fileName = filePath.getFileName().toString();
            // fileName = fileName.replaceFirst("[.][^.]+$", "");
            // Agregar Nuevo Objeto Json al Objeto Json Principal
            JSONTokener tokener = new JSONTokener(contenido); // No lanzará error si la cadena es incorrecta
            JSONObject obj = new JSONObject(tokener); // Si lanzará error si la cadena es incorrecta
            jsonModels.put((directoryName + "-" + fileName), obj);
        }
        catch(Exception e) {
            System.out.println("Error al cargar el Archivo Json");
            System.out.println(e.getMessage());
        }
    }

    public static TheWebElement getWebElementFromJSON(String nombreTheWebElement) {
        try {
            String callingClassName = new Throwable().getStackTrace()[1].getClassName();
            int lastDotIndex = callingClassName.lastIndexOf(".");
            int secondLastDotIndex = callingClassName.lastIndexOf(".", lastDotIndex - 1);
            String packageName = callingClassName.substring(secondLastDotIndex + 1, lastDotIndex);
            String className = callingClassName.substring(lastDotIndex + 1);
            className = Stream.of(className.split("\\$")).findFirst().orElse(""); // Asegura que si es una subclase se obtenga solo el nombre de la clase en el nivel más alto
            return getWebElementFromJSON("identifiers", className, nombreTheWebElement);
        } catch(Exception e) {
            System.out.println("The Web Element no encontrado en el Models Json");
            System.out.println("OBJETO:"+nombreTheWebElement);
            System.out.println(e.getMessage());
            return new TheWebElement(null, null, null, null, null);
        }
    }

    private static TheWebElement getWebElementFromJSON(String modulo, String pantalla, String nombreTheWebElement) {
        try {
            String key = modulo+"-"+pantalla+".json";
            Object valorId = jsonModels.getJSONObject(key).getJSONObject(nombreTheWebElement).get("id");
            Object valorName = jsonModels.getJSONObject(key).getJSONObject(nombreTheWebElement).get("name");
            Object valorType = jsonModels.getJSONObject(key).getJSONObject(nombreTheWebElement).get("type");
            Object valorText = jsonModels.getJSONObject(key).getJSONObject(nombreTheWebElement).get("text");
            Object valorXPath = jsonModels.getJSONObject(key).getJSONObject(nombreTheWebElement).get("xPath");
            return new TheWebElement(valorId.toString(), valorName.toString(), valorType.toString(), valorText.toString(), valorXPath.toString());
        } catch(Exception e) {
            System.out.println("The Web Element no encontrado en el Models Json");
            System.out.println(e.getMessage());
            return new TheWebElement(null, null, null, null, null);
        }
    }
}
