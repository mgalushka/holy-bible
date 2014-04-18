import com.maximgalushka.bible;

Gather g = new Gather();
String verse;

void setup() {
  size(800, 600);
  rect(10, 10, 780, 580);
  //stroke(0, 0, 0, 100);
  
  verse = g.random();
}

void draw() {
  text(200, 200, verse);
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

