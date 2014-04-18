int lastX, lastY, lastT;
int frame = 0;
float s = 0;
int delta = 100;
float v = 0;

void setup() {
  size(400, 400);
  rect(10, 10, 380, 380);
  stroke(0, 0, 0, 100);
}

void draw() {
  int m = millis();  
  if (m >= frame) {
    v = speed(lastX, lastY, mouseX, mouseY, m - lastT);
    frame += 100;
    lastX = mouseX;
    lastY = mouseY;
    lastT = m;
  }
  else {
    ellipse(mouseX, mouseY, v*15, v*15);
  }
}

float speed(int px, int py, int nx, int ny, int time) {
  return dist(px, py, nx, ny)/time;
}

void next() {
}

void mouseClicked() {
}


void keyPressed() {
  if (key == 's') {
    save("screen.png");
  }
}

