#include <ESP8266WiFi.h>
#include <ESP8266HTTPClient.h>
#include <ArduinoJson.h>

#define PIN_1 D5
#define analogInputPin A0
#define MEASURE_TYPE VOLTAGE

// const char *ssid = "TIM-20231077";
// const char *password = "CK6Jqj2omIXbfwYITRobv5rt";
const char *ssid = "Infostrada-0CA800";
const char *password = "KHXTMN77ME";
// const char *logEndpoint = "http://192.168.1.248:8081/api/log";
const char *logEndpoint = "https://sensor-data-logger.onrender.com/api/log";


void setup() {
  pinMode(PIN_1, OUTPUT);

  Serial.begin(115200);

  // Reset ESP8266
  // ESP.reset();

  // delay(1000);  // Give some time for the reset

  // Connect to Wi-Fi
  WiFi.begin(ssid, password);
  while (WiFi.status() != WL_CONNECTED) {
    delay(1000);
    Serial.println("Connecting to WiFi...");
  }

  Serial.println("Connected to WiFi");

  // Check internet connectivity
  if (checkInternet()) {
    Serial.println("Connected to the internet");
  } else {
    Serial.println("Not connected to the internet");
  }
}

void loop() {
  if (WiFi.status() == WL_CONNECTED) {
    digitalWrite(PIN_1, HIGH);
    int sensorValue = analogRead(analogInputPin);
    Serial.print("Digital voltage read: ");
    Serial.println(sensorValue);
    logData(logEndpoint, convertDigitalToRealValue(sensorValue, 30));
  }

  delay(30 * 1000);  // Wait for a moment before making the next request
}

void logData(const char* url, float voltage) {
  digitalWrite(PIN_1, LOW);

  WiFiClientSecure client; // Create a WiFiClient object
  client.setInsecure();
  client.connect(url, 443);

  HTTPClient http;

  // WiFiClient client; // Create a WiFiClient object
  // HTTPClient http;

  StaticJsonDocument<200> jsonDocument; //JSON document with a capacity of 200 bytes

  // Add float and long values to the JSON object
  jsonDocument["value"] = voltage;
  jsonDocument["measureType"] = MEASURE_TYPE;

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
  digitalWrite(PIN_1, HIGH);
}

float convertDigitalToRealValue(int digitalValue, float maximumAnalogInput) {
  return ((float) digitalValue / 1024.0) * maximumAnalogInput;
}

bool checkInternet() {
  HTTPClient http;
  WiFiClientSecure client; // Create a WiFiClient object
  client.setInsecure();
  client.connect("https://www.google.com", 443);

  // Use a known server that is likely to be available
  http.begin(client, "https://www.google.com");

  int httpCode = http.GET();

  // Check for a successful HTTP response (status code 200)
  if (httpCode == HTTP_CODE_OK) {
    return true;
  } else {
    return false;
  }

  http.end();
}
