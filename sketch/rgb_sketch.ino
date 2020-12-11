char command;
String string;
boolean ledon = false;

int redPin = 9;
int greenPin = 10;
int bluePin = 11;

void setup() {
  Serial.begin(9600);

  pinMode(redPin, OUTPUT);
  pinMode(greenPin, OUTPUT);
  pinMode(bluePin, OUTPUT);
  setColor(0, 0, 0);
}

void loop() {
  if (Serial.available() > 0) {
    string = "";
    setColor(0, 0, 0);
  }
  
  while(Serial.available() > 0) {
    command = ((byte)Serial.read());
    
    if(command == ':') {
      break;
    } else {
      string += command;
    }
    
    delay(1);
  }
  
  if(string == "orange") {
    setColor(255, 165, 0); // red
    delay(1000);
    ledon = true;
  }

  if(string == "yellow") {
    setColor(255, 0, 255); // purple
    delay(1000);
    ledon = true;
  }

  if(string == "red") {
    setColor(255, 0, 0); // red
    delay(1000);
    ledon = true;
  }

  if(string == "white") {
    setColor(255, 255, 255); // red
    delay(1000);
    ledon = true;
  }

  if(string == "blue") {
    setColor(0, 255, 0); // green
    delay(1000);
    ledon = true;
  }

  if(string == "green") {
    setColor(0, 0, 255); // blue
    delay(1000);
    ledon = true;
  }

  if(string == "purple") {
    setColor(255, 255, 0); // blue
    delay(1000);
    ledon = true;
  }

  if(string == "down") {
    setColor(0, 0, 0);
    delay(100);
    ledon = true;
  }

  if(string == "low") {
    setColor(0, 64, 0);
    delay(100);
    ledon = true;
  }

  if(string == "mid") {
    setColor(0, 127, 0);
    delay(100);
    ledon = true;
  }

  if(string == "high") {
    setColor(0, 255, 0);
    delay(100);
    ledon = true;
  }
  
  if(string =="TF") {
      setColor(0, 0, 0);
      ledon = false;
      Serial.println(string);
  }
  
  if ((string.toInt()>=0)&&(string.toInt()<=255)) {
    if (ledon==true) {
      setColor(0, 0, 0);
      Serial.println(string);
      delay(10);
    }
  }
}

void setColor(int red, int green, int blue) {
  analogWrite(redPin, 0+red);
  analogWrite(greenPin, 0+green);
  analogWrite(bluePin, 0+blue);
}
