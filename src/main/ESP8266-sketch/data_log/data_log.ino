#include <ESP8266WiFi.h>
#include <ESP8266HTTPClient.h>
#include <ArduinoJson.h>

#define PIN_1 D5

const char *ssid = "WI_FI_NAME";
const char *password = "WI_FI_PASSWORD";
const char *logEndpoint = "http://192.168.1.246:8081/api/log";
const char *dateEndpoint = "http://192.168.1.246:8081/api/log/currentDate";
const char *testEndpoint = "https://random-data-api.com/api/v2/beers" ;


void setup() {
  // pinMode(PIN_1, OUTPUT);
  // digitalWrite(PIN_1, HIGH);

  Serial.begin(115200);

  // // Reset ESP8266
  // ESP.restart();

  // delay(1000);  // Give some time for the reset

  // Connect to Wi-Fi
  WiFi.begin(ssid, password);
  while (WiFi.status() != WL_CONNECTED) {
    delay(1000);
    Serial.println("Connecting to WiFi...");
  }

  Serial.println("Connected to WiFi");
}

void loop() {
  if (WiFi.status() == WL_CONNECTED) {
    // WiFiClient client; // Create a WiFiClient object
    // HTTPClient http;

    String currentDateEpochMs = getDate(dateEndpoint);

    // If the retrieved date is valid, 
    if (currentDateEpochMs != "") {
      logData(logEndpoint, 5, currentDateEpochMs);
    }
  }

  delay(600 * 1000);  // Wait for a moment before making the next request
}

String getDate( const char* url) {
  WiFiClient client; // Create a WiFiClient object
  HTTPClient http;
  
  // Print the URL before making the request
  Serial.print("Calling URL: ");
  Serial.println(dateEndpoint);
  
  // Make a GET request to the API
  http.begin(client, url);  // Pass the URL directly
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
  return payload;
}

void logData(const char* url, float voltage, String dateEpochMs) {
  WiFiClient client; // Create a WiFiClient object
  HTTPClient http;
  StaticJsonDocument<200> jsonDocument; //JSON document with a capacity of 200 bytes

  // Add float and long values to the JSON object
  jsonDocument["value"] = voltage;
  jsonDocument["dateTime"] = dateEpochMs; 

  // Serialize the JSON object to a string
  String jsonData;
  serializeJson(jsonDocument, jsonData);

  // Specify the target server and resource path
  http.begin(client, url);

  // Set headers
  http.addHeader("Content-Type", "application/json");

  // Make the POST request
  int httpResponseCode = http.POST(jsonData);

  // Read and print the server response
  String response = http.getString();
  Serial.println("POST Response Code: " + String(httpResponseCode));
  Serial.println("POST  Response: " + response);

  // End the request
  http.end();
}
