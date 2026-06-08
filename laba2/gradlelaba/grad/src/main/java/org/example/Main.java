package org.example;
import org.apache.commons.lang3.math.NumberUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Scanner;
public class Main {
   private static final Logger logger = LoggerFactory.getLogger(Main.class);static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.print("Input string: ");
        String str1 = in.nextLine();
        Boolean result = NumberUtils.isDigits(str1);
        System.out.println(result);
        logger.info("результат: {}", result);
        String capitalized = StringProcessor.capitalizeString(str1);
        logger.info("С заглавной: {}", capitalized);
      ;

        }
    }

/*import java.io.InputStream;
import java.util.Properties;

public class Main {
    public static void main(String[] args) {
        Properties props = new Properties();
        try (InputStream input = Main.class.getResourceAsStream("/build-passport.properties")) {
            if (input != null) {
                props.load(input);
                System.out.println("=== Build Passport ===");
                System.out.println("User: " + props.getProperty("user.name"));
                System.out.println("OS: " + props.getProperty("os.name"));
                System.out.println("Java: " + props.getProperty("java.version"));
                System.out.println("Build Time: " + props.getProperty("build.time"));
                System.out.println("Message: " + props.getProperty("greeting"));
                System.out.println("=====================");
            } else {
                System.out.println("build-passport.properties не найден");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}*/


