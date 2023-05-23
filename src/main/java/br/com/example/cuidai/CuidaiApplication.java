package br.com.example.cuidai;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;

@SpringBootApplication
public class CuidaiApplication {

    public static void main(String[] args) {
        SpringApplication.run(CuidaiApplication.class, args);
        openBrowser("http://localhost:8080");
    }

    private static void openBrowser(String url) {
        String os = System.getProperty("os.name").toLowerCase();
        Runtime runtime = Runtime.getRuntime();

        try {
            if (os.contains("win")) {
                runtime.exec("rundll32 url.dll,FileProtocolHandler " + url);
            } else if (os.contains("mac")) {
                runtime.exec("open " + url);
            } else if (os.contains("nix") || os.contains("nux") || os.contains("bsd")) {
                String[] browsers = {"xdg-open", "google-chrome", "firefox", "chromium-browser"};
                boolean found = false;
                for (String browser : browsers) {
                    try {
                        runtime.exec(new String[]{browser, url});
                        found = true;
                        break;
                    } catch (IOException e) {
                        // Ignore and try the next one
                    }
                }
                if (!found) {
                    throw new RuntimeException("No supported browser found on Linux.");
                }
            } else {
                throw new RuntimeException("Unsupported operating system: " + os);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
