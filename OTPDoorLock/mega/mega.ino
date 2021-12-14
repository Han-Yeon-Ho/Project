#include <Keypad.h>
#include <SoftwareSerial.h>

#define BT_DIR 52
#define BT_ENA 53

#define BT_OPEN 42

SoftwareSerial s(14, 15); // SoftwareSerial(RX, TX)
char buff[100];
byte buffer[1024]; // 데이터를 수신 받을 버퍼
int bufferPosition; // 버퍼에 데이타를 저장할 때 기록할 위치
char otp[] = "";
char* secretCode = "1234";  // 비밀번호를 설정(여기선 1234)
int oCount = 0;
int position = 0;
int wrong = 0;
int speakerpin = 31;
int inbyty2;
int bi = 0;
int note[] = {2093, 2637, 3136, 4186}; //도레미파솔라시도
int note1[] = {4186, 3136}; //도레미파솔라시도
int da ;
const byte ROWS = 4;    // 행(rows) 개수
const byte COLS = 4;    // 열(columns) 개수
char attempt[6] = {0, 0, 0, 0, 0, 0};
int z = 0;
int trig=0;
char keys[ROWS][COLS] = {
  {'1', '2', '3', 'A'},
  {'4', '5', '6', 'B'},
  {'7', '8', '9', 'C'},
  {'*', '0', '#', 'D'}
};

byte rowPins[ROWS] = {22, 24, 26, 28};   // R1, R2, R3, R4 단자가 연결된 아두이노 핀 번호
byte colPins[COLS] = {30, 32, 34, 36};   // C1, C2, C3, C4 단자가 연결된 아두이노 핀 번호

Keypad keypad = Keypad( makeKeymap(keys), rowPins, colPins, ROWS, COLS );

void setup() {
  //  BTSerial.begin(9600);
  Serial.begin(9600);
  Serial1.begin(9600);
  Serial2.begin(9600);
  Serial3.begin(9600);
  pinMode(BT_DIR, OUTPUT);
  pinMode(BT_ENA, OUTPUT);
   pinMode(BT_OPEN, INPUT_PULLUP);
  s.begin(9600);
  //      setLocked(true);
  bufferPosition = 0; // 버퍼 위치 초기화
  while (!Serial) {
    ; // wait for serial port to connect. Needed for native USB port only
  }
}

void loop() {
if (digitalRead(BT_OPEN) == LOW)
  {
    Serial.println("버튼으로 오픈");
    
 int elementCount = sizeof(note) / sizeof(int);
        for (int i = 0; i < elementCount; i++)  //note를 play
        {
          tone(speakerpin, note[i], 500);
          delay(250);
        }
        digitalWrite(BT_ENA, 1);
        digitalWrite(BT_DIR, 1);
        delay(2000);
        digitalWrite(BT_DIR, 0);
        digitalWrite(BT_ENA, 1);
    
  }
  char key1 = keypad.getKey();
  while (key1 == 'A') {
    Serial.println("A진입");
    char key = keypad.getKey();

    Serial.print(key);
    if ((key >= '0' && key <= '9') || (key >= 'A' && key <= 'D')
        || (key == '*' || key == '#')) {
      // 키패드에서 입력된 값을 조사하여 맞게 입력된 값일 경우(키패드에 있는 버튼이 맞을경우) 비교
      tone(speakerpin, note[2], 500);
      if (key == '*' || key == '#') { // *, # 버튼을 눌렀을 경우
        position = 0;
        wrong = 0; // 입력 초기화
        //      setLocked(true); // 잠금상태로 세팅
      }

      else if (key == secretCode[position]) { // 해당 자리에 맞는 비밀번호가 입력됬을 경우
        position++; // 다음 자리로 넘어 감
        wrong = 0; // 비밀번호 오류 값을 0으로 만듬

      }

      else if (key != secretCode[position]) { // 해당 자리에 맞지 않는 비밀번호가 입력됬을 경우
        position = 0; // 비밀번호를 맞았을 경우를 0으로 만듬
        //      setLocked(true); // 잠금상태로 세팅


        wrong++; // 비밀번호 오류 값을 늘려준다
      }
  if(key=='C'){
    trig=1;
    break;
  }

      if (position == 4) { // 4자리 비밀번호가 모두 맞았을 경우
        position = 0;
        wrong = 0; // 입력 초기화
        //    setLocked(false); // 잠금상태를 해제 함

        int elementCount = sizeof(note) / sizeof(int);
        for (int i = 0; i < elementCount; i++)  //note를 play
        {
          tone(speakerpin, note[i], 500);
          delay(250);
        }

        digitalWrite(BT_ENA, 1);
        digitalWrite(BT_DIR, 1);
        delay(2000);
        digitalWrite(BT_DIR, 0);
        digitalWrite(BT_ENA, 1);

        da = 2;
        Serial3.write(da);
        break;
      }


      if (wrong == 4) { // 비밀번호 오류를 4번 했을 경우
       
        wrong = 0; // 비밀번호 오류 값을 0으로 만들어 준다.
        int elementCount = sizeof(note1) / sizeof(int);
        for (int i = 0; i < elementCount; i++)  //note를 play
        {
          tone(speakerpin, note1[i], 500);
          delay(450);
        }
        break;
      }
      Serial.println("제발");



      delay(100);


    }

  }
  while (key1 == 'B')
  {
  
   
    readKeypad();
    if(trig==1){
      trig=0;
      break;
    }
    
    if (bi == 1) {
      bi = 0;
      //Serial.println("break통함");
      break;

    }
 
  while (Serial3.available() > 0) {
    inbyty2 = Serial3.read();
    Serial.println(inbyty2);
    if (inbyty2 == 7) {
      Serial.println("open the door!");
      da = 3;
      Serial3.write(da);
      int elementCount = sizeof(note) / sizeof(int);
      for (int i = 0; i < elementCount; i++)  //note를 play
      {
        tone(speakerpin, note[i], 500);
        delay(250);
      }

      digitalWrite(BT_ENA, 1);
      digitalWrite(BT_DIR, 1);
      delay(2000);
      digitalWrite(BT_DIR, 0);
      digitalWrite(BT_ENA, 1);
      inbyty2 = 0;
 
    }

   else if (inbyty2 == 8) {
              Serial.println("otp 확인");
              tone(speakerpin, note[2], 500);
              delay(250);
              tone(speakerpin, note[1], 500);
              tone(speakerpin, note[3], 500);


            }

  }



  if (Serial1.available())  {
    byte data = Serial1.read(); // 수신 받은 데이터 저장

    Serial1.write(data); // 수신된 데이터 시리얼 모니터로 출력

    if (data == 'o') {
      int elementCount = sizeof(note) / sizeof(int);
      for (int i = 0; i < elementCount; i++)  //note를 play
      {
        tone(speakerpin, note[i], 500);
        delay(250);
      }

      digitalWrite(BT_ENA, 1);
      digitalWrite(BT_DIR, 1);
      delay(2000);
      digitalWrite(BT_DIR, 0);
      digitalWrite(BT_ENA, 1);

      da = 1;
      Serial3.write(da);
    }
  }
  while (Serial2.available() > 0) {
    inbyty2 = Serial2.read();
    Serial.println(inbyty2);
    if (inbyty2 == 10) {
      Serial.println("open the door!");
      da = 4;
      Serial3.write(da);
      int elementCount = sizeof(note) / sizeof(int);
      for (int i = 0; i < elementCount; i++)  //note를 play
      {
        tone(speakerpin, note[i], 500);
        delay(250);
      }

      digitalWrite(BT_ENA, 1);
      digitalWrite(BT_DIR, 1);
      delay(2000);
      digitalWrite(BT_DIR, 0);
      digitalWrite(BT_ENA, 1);
      inbyty2 = 0;

    }

  }
  if (Serial.available())
  {
    Serial1.write(Serial.read());
  }


 
}
void readKeypad()
{

  char key = keypad.getKey();

  Serial.println(key);
  if (key != NO_KEY)
  {
    
    switch (key)
    {
      case '*':
        z = 0;
        break;

      case '#':
        delay(100);  // 처리를 위해 0.1초 동안 대기한다.

        break;
 
        break;

      default:
        tone(speakerpin, note[2], 500);
        if (key == 'B') {
          tone(speakerpin, note[3], 500);
         tone(speakerpin, note[3], 500);
        }
        if (key == 'A') {
          tone(speakerpin, note[3], 500);
          tone(speakerpin, note[3], 500);
          tone(speakerpin, note[3], 500);
        trig=1;
          break;
        }
      else  if (key == 'C') {
          tone(speakerpin, note[3], 500);
          tone(speakerpin, note[3], 500);
          tone(speakerpin, note[3], 500);
          trig=1;
          break;
        }
        if (key != 'B') {
          int data;

          attempt[z] = key;
          z++;
          if (z == 6) {
            tone(speakerpin, note[2], 500);
            delay(250);
            tone(speakerpin, note[1], 500);
            tone(speakerpin, note[3], 500);
            Serial.println("otp 요청");
            Serial3.write(9);
            
            Serial.println("보냄");
            // Serial.println(attempt);
            z = 0;
            String sendm = "";
            sendm += attempt;
            //char floatbuf[32]; // make this at least big enough for the whole string
            sendm.toCharArray(attempt, sizeof(attempt));
            float f = atof(attempt);

            Serial.println(attempt);
            Serial3.println(secretCode);
            Serial3.println(sendm);

            Serial.println(sendm);
            delay(3500);
            Serial.println(inbyty2);
            while (Serial3.read() > 0) {
              if (inbyty2 == 7) {
                da = 3;
                s.write(da);
                int elementCount = sizeof(note) / sizeof(int);
                for (int i = 0; i < elementCount; i++)  //note를 play
                {
                  tone(speakerpin, note[i], 500);
                  delay(250);
                }

                digitalWrite(BT_ENA, 1);
                digitalWrite(BT_DIR, 1);
                delay(2000);
                digitalWrite(BT_DIR, 0);
                digitalWrite(BT_ENA, 1);
              }
              bi = 1;
            }
          }
          delay(200);
          Serial.println(inbyty2);

        }
    }
  }
}
