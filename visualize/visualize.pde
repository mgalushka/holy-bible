import com.maximgalushka.bible.*;

Gather g;
String verse;
PFont mono;

void setup() {
  size(800, 400);
  
  fill(0);
  mono = loadFont("Arial-BoldItalicMT-20.vlw");
  textSize(15);
  textFont(mono);
  
  g = new Gather(3);
  verse = g.random();
}

void draw() {
  background(255);
  text(verse, 20, 100, 760, 300);
}


void mouseClicked() {
  verse = g.random();
}

void keyPressed() {
  if (key == 'n') {
    verse = g.random();
  }
  else if (key == 's') {
    save("screen.png");
  }
}

