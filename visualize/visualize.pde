import com.maximgalushka.bible.*;

Gather g;
String verse;

void setup() {
  size(800, 600);
  //rect(10, 10, 780, 580);
  stroke(255, 0, 0);

  g = new Gather(5);
  verse = g.random();
}

void draw() {
  fill(0, 0, 0);
  text(verse, 20, 200);
}


void next() {
}

void mouseClicked() {
}


void keyPressed() {
  if (key == 'n') {
    verse = g.random();
  }
  else if (key == 's') {
    save("screen.png");
  }
}

