#include <ESP8266WiFi.h>
#include <ESP8266HTTPClient.h>

const char *ssid = "WI_FI_NAME";
const char *password = "WI_FI_PASSWORD";

const char *testEndpoint = "https://random-data-api.com/api/v2/beers" ;



void setup() {
  Serial.begin(115200);

    // Connect to Wi-Fi
  WiFi.begin(ssid, password);
  while (WiFi.status() != WL_CONNECTED) {
    delay(1000);
    Serial.println("Connecting to WiFi...");
  }

  Serial.println("Connected to WiFi");
}

void loop() {
  Serial.println("Test String");
  if (WiFi.status() == WL_CONNECTED) {
    WiFiClientSecure client; // Create a WiFiClient object
    client.setInsecure();
    client.connect(testEndpoint, 443);

    HTTPClient http;

    // Make a GET request to the API
    http.begin(client, testEndpoint);  // Pass the URL directly
    http.addHeader("Content-Type", "application/json");  // Optional, set the content type if needed
    int httpCode = http.GET();
    String payload = "";

    if (httpCode > 0) {
      if (httpCode == HTTP_CODE_OK) {
        payload = http.getString();

        Serial.println("Response: " + payload);
      } else {
        Serial.println("HTTP error code: " + String(httpCode));
      }
    } else {
      Serial.println("Failed to connect to API");
    }
    http.end();

  }

  delay(1000);  // Wait for a moment before making the next request

}
