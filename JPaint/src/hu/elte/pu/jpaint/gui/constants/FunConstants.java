package hu.elte.pu.jpaint.gui.constants;

import java.io.*;
import java.util.ArrayList;
import java.util.Random;

public class FunConstants {

    private ArrayList<String> funTextContainer = new ArrayList<>();

    public FunConstants() {
        try {
            FileReader fileReader = new FileReader("src/hu/elte/pu/jpaint/gui/constants/randomText.txt");

            BufferedReader bufferedReader = new BufferedReader(fileReader);

            String line = null;
            while ((line = bufferedReader.readLine()) != null) {
                this.funTextContainer.add(line);
            }

        } catch (FileNotFoundException e) {
            System.out.println("Unable to open file, or file not found.");
        } catch (IOException e) {
            System.out.println("Error while reading the file.");
        }
    }
    
    public String returnOneFunString() {
        Random index = new Random();
        int randomIndex = index.nextInt(funTextContainer.size());
        
        return funTextContainer.get(randomIndex);
    }

}
