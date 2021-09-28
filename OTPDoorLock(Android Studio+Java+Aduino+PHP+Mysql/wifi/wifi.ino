#include <ESP8266WiFi.h>
#include <ESP8266HTTPClient.h>
#include <SoftwareSerial.h>
 #include <time.h> 
#define SSID        "mo"  
#define PASSWORD    "01055396001"  
#define SERVERIP   "172.20.10.3"  
int timezone = 3; 
 time_t now;
 int dst = 0; 
 int data;
char* type;
String Ctime;
const char* ssid     = "Yeono";
const char* password = "00000000";
// String host = "192.168.43.58";
  String host = "172.20.10.3";
unsigned long previousMillis = 0;
 const long interval = 1000; 
 
const char* host2 = "172.20.10.12";
SoftwareSerial s(D6,D5);
const long interval2 = 5000;

extern volatile unsigned long timer0_millis; //타이머변수
unsigned long timeVal; //이전시간
unsigned long readTime; //현재타이머시간
int hour, mint, sec;
boolean state=false;

WiFiServer server(80);
WiFiClient client1;
WiFiClient client2;
HTTPClient http;

 
void setup() {

   s.begin(9600);
  Serial.begin(9600);
  WiFi.mode(WIFI_STA);
  WiFi.begin(ssid, password);
  while (WiFi.status() != WL_CONNECTED) {
    delay(500);
    Serial.print(".");
  }
  Serial.println("");
  Serial.print("Connecting to ");
  Serial.println(ssid);
  Serial.print("IP address: ");
  Serial.println(WiFi.localIP());
  server.begin();
  Serial.println("Server started");

 configTime(3 * 3600, 0, "pool.ntp.org", "time.nist.gov");
  Serial.println("\nWaiting for time");
  while (!time(nullptr)) {
    Serial.print(".");
    delay(1000);
  }
  Serial.println("");

}
 
void loop() {

   while(s.available()>0){

      data =s.read();
     
      Serial.println(data);
      if(data ==9 || data ==8){
        int de=8;
           s.write(de);
          if (client2.connect(host2, 9999)){
      Serial.print("Connected to: ");
      Serial.println(host2);

    /* Send "connected" to the server so it knows we are ready for data */
      client2.println("9"); //USE client.println()!!
         unsigned long timeout = millis();
    
         if(client2.readString()=="8"){
         Serial.println("Host message: \n");
   
            String dat=s.readString();
          client2.println(dat);
           Serial.println(dat);
           dat=s.readString();
           client2.println(dat);
           Serial.println("send pswd");          
           Serial.println(dat);

           if(client2.readString()=="7"){
            int de=7;

            s.write(de);
            Serial.println("correct password!");          
             Serial.println(de);          
           }
             while(client2.available() == 0)
    {
      if(millis() - timeout > 3000)
      {
        Serial.println("Timeout to server!");
       break;
      }
    }
           client2.stop();
   
   client2.stop();
   
   break;
  }
  
  
  
  
        }
      }

      
   else   if(data ==1 || data ==2|| data ==3 || data ==4){
       Serial.println("recv mega1");
       if(data==1){
        client1.println("bluetooth"); 
        type = "2#OTD#1";
       }
    else if(data==2){
       client1.println("keypad"); 
       type = "2#OTD#2";
    
       }
   else if(data==3){
       client1.println("otp"); 
       type = "2#OTD#3";
  
       }
       else if(data==4){
       client1.println("Fingerprint"); 
       type = "2#OTD#4";
   
       }
        Serial.println("recv mega");
  String a[3];
    
  time_t now = time(nullptr);
  struct tm * timeinfo;
  timeinfo = localtime(&now); 
  a[0]=String(timeinfo->tm_sec, DEC); 
  a[1]=String(timeinfo->tm_min, DEC); 
  a[2]=String((timeinfo->tm_hour+6), DEC);  
  if(timeinfo->tm_sec<10)      a[0]="0"+a[0];
  if(timeinfo->tm_min<10)      a[1]="0"+a[1];
  if(timeinfo->tm_hour+6>24)     {
    a[2]=String((timeinfo->tm_hour-18), DEC);  
    
    a[2]="0"+a[2];
  }
  if(timeinfo->tm_hour<10)     a[2]="0"+a[2];
  Ctime="";
  Ctime+=a[2];

  Ctime+=a[1];

  Ctime+=a[0];
  
  // 센서값 DB 전송 부분
  unsigned long currentMillis = millis();
  //int cutime=(int)millis();
  if(currentMillis - previousMillis >= interval2) {
    previousMillis = currentMillis;

    int temp = data;

    String phpHost ="AT+CIPSTART=\"TCP\",\"";
    phpHost += SERVERIP;  
    phpHost += "\",80"; 
    Serial.print("Connect to ");
    Serial.println(phpHost);
    phpHost = "http://172.20.10.3/insert_data.php?type=";
    phpHost +=temp;
//    phpHost +="&time=";
//    phpHost +=Ctime;
    phpHost +="\r\n";
    Serial.println(phpHost);
 //   phpHost = "GET /insert_data.php?temp="+temp+"&humidity="+temp+"\r\n";



    

    http.begin(client1, phpHost);
    http.setTimeout(1000);
    int httpCode = http.GET();
   
    if(httpCode > 0) {
      Serial.printf("GET code : %d\n\n", httpCode);
 
      if(httpCode == HTTP_CODE_OK) {
        String payload = http.getString();
        Serial.println(payload);
      }
    } 
    else {
      Serial.printf("GET failed, error: %s\n", http.errorToString(httpCode).c_str());
    }
    http.end();
  }
   }
   }
  
  // 웹서버 부분
  client1 = server.available();
  if(!client1) return;
 
  Serial.println("새로운 클라이언트");
  client1.setTimeout(5000);
 
  String request = client1.readStringUntil('\r');
  Serial.println("request: ");
  Serial.println(request);
 
  while(client1.available()) {
    client1.read();
     Serial.println("ot");
  }
 

 
  client1.print("HTTP/1.1 200 OK");
  client1.print("Content-Type: text/html\r\n\r\n");
  client1.print("<!DOCTYPE HTML>");
  client1.print("<html>");
  client1.print("<head>"); 
  client1.print("<meta charset=\"UTF-8\" http-equiv=\"refresh\" content=\"1\">");
  client1.print("<title>DHT senrsor test Webpage</title>");
  client1.print("</head>");
  client1.print("<body>");
  client1.print("<h2>DHT senrsor test Webpage</h2>");
  client1.print("<br>");
  client1.print("Temperature : ");

  client1.print(" °C");
  client1.print("<br>");
  client1.print("Humidity : ");

  client1.print(" %");
  client1.print("</body>");
  client1.print("</html>");
 
  Serial.println("클라이언트 연결 해제");
}
