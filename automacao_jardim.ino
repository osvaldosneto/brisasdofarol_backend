#include <ESP8266WiFi.h>
#include <WiFiClientSecure.h>

#ifndef STASSID
#define STASSID "your-ssid"
#define STAPSK  "your-ghpassword"
#define ledVerde D3       //led verde sinalização ok
#define ledVermelho D2    //led vermelho de problemas
#define ledRele D4        //led rele
#define valvula D1        //valvula
#endif

const char* ssid = "Brisas do Farol_ext";
const char* password = "bris@s9156";
const char* host = "locacaobrisasdofarol.herokuapp.com";
const int httpsPort = 443;
const char fingerprint[] PROGMEM = "94 FC F6 23 6C 37 D5 E7 92 78 3C 0B 5F AD 0C E4 9E FD 9E A8";

void setup() {
  
  Serial.begin(115200);
  pinMode(ledVerde, OUTPUT);        //definindo portas para led de verificação
  pinMode(ledVermelho, OUTPUT);     //definindo portas para led de verificação
  pinMode(ledRele, OUTPUT);         //definindo portas para led rele
  pinMode(valvula, OUTPUT);         //definindo portas para valvula
  
  inicializaLed();
  inativa();
  
  Serial.println();
  Serial.print("connectando em ");
  Serial.println(ssid);
  
  WiFi.mode(WIFI_STA);
  WiFi.begin(ssid, password);
  
  while (WiFi.status() != WL_CONNECTED) {
    delay(1000);
    Serial.print(".");
  }
  
  Serial.println();
  Serial.print("WiFi connectado no ip ");
  Serial.println(WiFi.localIP());
  Serial.println();
  ativa();
 
}

void loop() {
  
  WiFiClientSecure client;
  String url = "/jardim";
  String line = "";
  int indexofS = 0;

  while (WiFi.status() != WL_CONNECTED) {
    inativa();
    delay(1000);
    Serial.print(".");
  }

  ativa();
  Serial.print("Conectando a ");
  Serial.println(host);
  Serial.printf("Fingerprint '%s'\n", fingerprint);
  
  client.setFingerprint(fingerprint);
  if (!client.connect(host, httpsPort)) {
    Serial.println("connection failed");
    inativa();
    return;
  }
  
  Serial.print("request URL: ");
  Serial.println(url);

  client.print(String("GET ") + url + " HTTP/1.1\r\n" + "Host: " + host + "\r\n" + "User-Agent: ESP8266\r\n" + "Connection: close\r\n\r\n");
  Serial.println("request enviado");
  
  while (client.connected()) {
    ativa();
    line = client.readStringUntil('\n');
    if (line == "\r") {
      Serial.println("headers received");
      break;
    }
  }
  
  client.readStringUntil('\n');
  line = client.readStringUntil('\n');
  if (line.startsWith("{\"id\":")) {
    Serial.print("\n");
    Serial.println("esp8266/Arduino CI successfull!");
    indexofS = line.indexOf('s');
    ativa();
  } else {
    Serial.println("esp8266/Arduino CI has failed");
    inativa();
  }

  if(line[indexofS + 9] == 'A'){
    //ativando rele
    digitalWrite(ledRele, HIGH);
    digitalWrite(valvula, HIGH);
  } else if (line[indexofS + 9] == 'I'){
    //desliga rele
    digitalWrite(ledRele, LOW);
    digitalWrite(valvula, LOW);
  } else {
    inativa();
  }
  Serial.print("Char 20 : ");
  Serial.print(line[indexofS + 9]);
  delay(15000);

}

void inicializaLed(){
  digitalWrite(ledVerde, HIGH);
  digitalWrite(ledVermelho, HIGH);       
  digitalWrite(ledRele, HIGH);
  delay(800);
  digitalWrite(ledVerde, LOW);
  digitalWrite(ledVermelho, LOW);       
  digitalWrite(ledRele, LOW);
  delay(800);
  digitalWrite(ledVerde, HIGH);
  digitalWrite(ledVermelho, HIGH);       
  digitalWrite(ledRele, HIGH);
  delay(800);
  digitalWrite(ledVerde, LOW);
  digitalWrite(ledVermelho, LOW);       
  digitalWrite(ledRele, LOW);
  delay(800);
  digitalWrite(ledVerde, HIGH);
  digitalWrite(ledVermelho, HIGH);       
  digitalWrite(ledRele, HIGH);
  delay(800);
  digitalWrite(ledVerde, LOW);
  digitalWrite(ledVermelho, LOW);       
  digitalWrite(ledRele, LOW);
  delay(800);
}

void ativa(){
  digitalWrite(ledVerde, HIGH);
  digitalWrite(ledVermelho, LOW);   
}

void inativa(){
  digitalWrite(ledVerde, LOW);
  digitalWrite(ledVermelho, HIGH);
  digitalWrite(ledRele, LOW);
  digitalWrite(valvula, LOW);
}
