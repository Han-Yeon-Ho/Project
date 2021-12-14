#include <Keypad.h>
#include <SoftwareSerial.h>

#define BT_DIR 52
#define BT_ENA 53
SoftwareSerial s(12,13); // SoftwareSerial(RX, TX)
byte buffer[1024]; // 데이터를 수신 받을 버퍼
int bufferPosition; // 버퍼에 데이타를 저장할 때 기록할 위치
 
char* secretCode = "1234";  // 비밀번호를 설정(여기선 1234)
int position = 0; 
int wrong = 0;
int speakerpin = 31; 
int note[] = {2093,2637,3136,4186}; //도레미파솔라시도
int note1[] = {4186,3136}; //도레미파솔라시도

const byte ROWS = 4;    // 행(rows) 개수
const byte COLS = 4;    // 열(columns) 개수
char keys[ROWS][COLS] = {
  {'1','2','3','A'},
  {'4','5','6','B'},
  {'7','8','9','C'},
  {'*','0','#','D'}
};
 
byte rowPins[ROWS] = {22, 24, 26, 28};   // R1, R2, R3, R4 단자가 연결된 아두이노 핀 번호
byte colPins[COLS] = {30, 32, 34, 36};   // C1, C2, C3, C4 단자가 연결된 아두이노 핀 번호
 
Keypad keypad = Keypad( makeKeymap(keys), rowPins, colPins, ROWS, COLS );
 
void setup() {

  Serial.begin(9600);
   Serial1.begin(9600);
  pinMode(BT_DIR, OUTPUT);
  pinMode(BT_ENA, OUTPUT);

  bufferPosition = 0; // 버퍼 위치 초기화
  s.begin(9600);
}
   
void loop() {
  
      if(s.read()=='s'){
               int elementCount = sizeof(note) / sizeof(int);
  for (int i=0; i < elementCount; i++)    //note를 play
  {
    tone(speakerpin,note[i],500);
    delay(250);
  }

              digitalWrite(BT_ENA, 1);
     digitalWrite(BT_DIR, 1);
     delay(2000);
     digitalWrite(BT_DIR, 0);
     digitalWrite(BT_ENA, 1);
     Serial1.
    }
    
      }
  
     if (Serial1.available())  {
        byte data = Serial1.read(); // 수신 받은 데이터 저장

    Serial1.write(data); // 수신된 데이터 시리얼 모니터로 출력
    buffer[bufferPosition++] = data; // 수신 받은 데이터를 버퍼에 저장

    
       if(data == '\n'){ // 문자열 종료 표시
     buffer[bufferPosition] = '\0';
 
     // 스마트폰으로 문자열 전송
    Serial1.write(buffer, bufferPosition);
     bufferPosition = 0;
    Serial.write(Serial1.read());
       }
    if(data == 'o'){
        int elementCount = sizeof(note) / sizeof(int);
  for (int i=0; i < elementCount; i++)    //note를 play
  {
    tone(speakerpin,note[i],500);
    delay(250);
  }

              digitalWrite(BT_ENA, 1);
     digitalWrite(BT_DIR, 1);
     delay(2000);
     digitalWrite(BT_DIR, 0);
     digitalWrite(BT_ENA, 1);
     Serial1.
    }
    
  

  if (Serial.available()) 
  {
    Serial1.write(Serial.read());
  }

     }
    
  char key = keypad.getKey();
 if((key >= '0' && key <= '9') || (key >= 'A' && key <='D')
    || (key == '*' || key == '#')){
  // 키패드에서 입력된 값을 조사하여 맞게 입력된 값일 경우(키패드에 있는 버튼이 맞을경우) 비교

    if(key == '*' || key == '#'){ // *, # 버튼을 눌렀을 경우
      position = 0; 
      wrong = 0; // 입력 초기화
      setLocked(true); // 잠금상태로 세팅
    } 
 
    else if(key == secretCode[position]){ // 해당 자리에 맞는 비밀번호가 입력됬을 경우
      position++; // 다음 자리로 넘어 감
      wrong = 0; // 비밀번호 오류 값을 0으로 만듬
    }
    
    else if(key != secretCode[position]){ // 해당 자리에 맞지 않는 비밀번호가 입력됬을 경우
      position = 0; // 비밀번호를 맞았을 경우를 0으로 만듬
      setLocked(true); // 잠금상태로 세팅


      wrong++; // 비밀번호 오류 값을 늘려준다
    }
  
    if(position == 4){ // 4자리 비밀번호가 모두 맞았을 경우
      setLocked(false); // 잠금상태를 해제 함
  
  int elementCount = sizeof(note) / sizeof(int);
  for (int i=0; i < elementCount; i++)    //note를 play
  {
    tone(speakerpin,note[i],500);
    delay(250);
  }

        digitalWrite(BT_ENA, 1);
     digitalWrite(BT_DIR, 1);
     delay(2000);
     digitalWrite(BT_DIR, 0);
     digitalWrite(BT_ENA, 1);


    }
    
    if(wrong == 4){ // 비밀번호 오류를 4번 했을 경우
      blink(); // Red LED를 깜빡여 준다.
      wrong = 0; // 비밀번호 오류 값을 0으로 만들어 준다.
      int elementCount = sizeof(note1) / sizeof(int);
  for (int i=0; i < elementCount; i++)    //note를 play
  {
    tone(speakerpin,note1[i],500);
    delay(450);
  }
    }
  }
  delay(100);
}
