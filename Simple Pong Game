import ddf.minim.*;
//--------------------------------------------------------------------
// Simple Pong Game
// Has a cat surprise when a player wins a point!
//--------------------------------------------------------------------

import ddf.minim.*;
import gifAnimation.*;

Minim minim;
AudioPlayer catsound;
AudioPlayer player;
Gif myAnimation;
Gif otherAnimation;

int seconds = 0;
int myWidth = 800;
int myHeight = 600;
float diameter = 20;
float courtWidth = 600;
float courtHeight = 400;
float lineHeight = 50;

float dx, dy, rdy, ldy;
float speed;
float v, d;
int semaforo = 0; //para controlar o Enter que a bola vai para o centro e o Enter que a bola começa a andar
int jogada = 1; //guarda quem irá fazer a jogada, como o player 1 começa sempre, começa a 1
float angle = 0; //onde ira ser guardado o valor do angulo quando a bola bate na raquete

Circle c1;
Court q1;
Racket r1, r2;

color white = color(255, 255, 255);
color black = color(0, 0, 0);

boolean stopped;

void setup()
{
  size(myWidth, myHeight);
  speed = 2;
  dx = myWidth/2;
  dy = myHeight/2;
  rdy = 0;
  ldy = 0;
  c1 = new Circle (dx, dy, diameter, white);
  q1 = new Court (dx, dy, courtWidth, courtHeight, white);
  r1 = new Racket (dx + courtWidth/2 - 10, dy, lineHeight, white); //raquete da direita
  r2 = new Racket (dx - courtWidth/2 + 10, dy, lineHeight, white); //raquete da esquerda
  r1.p = 0; //pontos iniciais de cada jogador, raquete direita
  r2.p = 0; //raquete esquerda
  stopped = true;
  
  minim = new Minim(this); 
  player = minim.loadFile("som_1_do.wav");
  catsound = minim.loadFile("kitten4.wav");
  myAnimation = new Gif(this, "cat.gif");
  otherAnimation = new Gif(this, "dancing-cat.gif");
  myAnimation.play();
  otherAnimation.play();
}

void keyPressed(){
  if(key == ' ')
    stopped = !stopped;
  if(key == ENTER && (c1.x == q1.x || c1.y == q1.y) && semaforo == 0){//quer dizer que estamos no inicio do jogo ou de uma nova jogada
    println("começa de novo");
    //Ver quem serve primeiro
    //se estamos no inicio do jogo, quer dizer que nao ha pontos
    if(r2.p == 0 && r1.p == 0){
      //logo nao pode ir para a esquerda por isso nao tem valores negativos, no circulo trignometrico o -PI/4 e PI/4 sao para a direita
      c1.setVelocity(cos(random(-PI/4, PI/4)) , sin(random(-PI/4, PI/4)));
      c1.setSpeed(speed);
    }
    //se foi o jogador 1 a ganhar anterioremente vai para o lado do player 2
    else if(jogada == 1){
      //logo nao pode ir para a esquerda por isso nao tem valores negativos
      c1.setVelocity(cos(random(-PI/4, PI/4)) , sin(random(-PI/4, PI/4)));
      c1.setSpeed(speed);
    }
    else{
      //logo nao pode ir para a direita porque foi o jogador 2 que ganhou, no circulo trignometrico o -3PI/4 e 3PI/4 sao para a esquerda
      c1.setVelocity(cos(random(-3*PI/4, -3*PI/4)) , sin(random(-PI/4, PI/4)));
      c1.setSpeed(speed);
    }   
  }  
  if(key == ENTER && (c1.x >= q1.x + q1.w/2 || c1.x <= q1.x - q1.w/2)){ //para quando se clicar no enter, à esquerda e direita, poder fazer reset
    dx = myWidth/2;
    dy = myHeight/2;
    c1 = new Circle (dx, dy, diameter, white);
    semaforo = 0; //metemos o semaforo igual a zero para poder contar novamente os pontos e começarmos novamente um jogo
  }
  rdy = (int(key == 'l') - int(key == 'p'))* 3;
  ldy = (int(key == 'a') - int(key == 'q'))* 3;
}

/*
void mouseClicked()
{
  dx = mouseX - c1.x;
  dy = mouseY - c1.y;
  if(c1.vx == 0 && c1.vy == 0)
  {
    // se for o primeiro mouseclick inicia a velociade a 1
    c1.setVelocity(cos(PI/4) , sin(PI/4));
    c1.setSpeed(speed);
  }
  //calcula a distancia entre o ponto actual e o ponto onde queremos ir
  d = sqrt(sq(dx) + sq(dy));
  //magnitude das velocidades na direcao x e y, que e igual a velocidade actual independete da direcao 
  v = sqrt(sq(c1.vx) + sq(c1.vy));
  //define a nova velocidade para a direcao x
  c1.vx = (dx) * v / d;
  //define a nova velocidade para a direcao y
  c1.vy = (dy) * v / d;
}
*/

void update()
{
  c1.bounceInsideCourt(q1, r1, r2, c1);
  //Caso o player 1 ganhe o ponto
  if(c1.x >= q1.x + q1.w/2 && semaforo == 0){//se ultrapassa a zona da direita o player 1 ganha um ponto e metemos o semaforo desligado 
    r2.p += 1;
    jogada = 1;//quer dizer que na proxima jogada sera o player 1 a servir
    semaforo = 1;
    catsound.play();
    catsound.rewind();
    println("Player 1º:", r2.p);
  }
  //Caso o player 2 ganhe o ponto
  else if(c1.x <= q1.x - q1.w/2 && semaforo == 0){//se ultrapassa a zona da esquerda o player 2 ganha um ponto e metemos o semaforo desligado
    r1.p += 1;
    jogada = 2;//quer dizer que na proxima jogada sera o player 2 a servir
    semaforo = 1;
    catsound.play();
    catsound.rewind();
    println("Player 2º", r1.p);
  }
  c1.travel(speed);
  if(keyPressed){
    r1.move(0, rdy);
    r2.move(0, ldy);
  }
}

void draw()
{
  background(0);
  if(stopped == true)
    update();
  c1.draw();
  q1.draw();
  r1.draw();   
  r2.draw();
  imageMode(CENTER);
  if(semaforo == 1 && jogada == 1){
    image(myAnimation, q1.x, q1.y);
  }
  if(semaforo == 1 && jogada == 2){
    image(otherAnimation, q1.x, q1.y);
  }
  textSize(32);
  textAlign(CENTER);
  text("Pong!", myWidth/2, 40);
  textSize(26);
  textAlign(LEFT);
  text("Player 1:", myWidth/2 - courtWidth/2, 70);
  text(r2.p, myWidth/2 - courtWidth/2+120, 70);
  text("Player 2:", myWidth - (myWidth/2 - courtWidth/4), 70);
  text(r1.p, myWidth - (myWidth/2 - courtWidth/4)+120, 70);
}

//----------------------------------------------------------
//---------------------CLASSES------------------------------
//----------------------------------------------------------

class Racket
{
  float x;
  float y;
  float h;
  color c;
  int p; //pontos
  
  Racket(float x, float y, float h, color c)
  {
    this.x = x;
    this.y = y;
    this.h = h;
    this.c = c;
  }
  
  void ballsHitsRacket(Circle c)
  {
    //Se a distancia total entre o centro e um dos extremos e 100% entao uma das distancias entre esse valor vai ser sempre uma percetagem menor
    //E se 100% -> PI/4 uma percentagem menor vai dar um valor menor de angulo (regra dos 3 simples)
    if((c.y <= y + h/2 && c.y >= y) || (c.y-c.d/2 <= y + h/2 && c.y >= y)){ //para baixo c.y para o Y do centro da bola e c.y-c.d/2 para quando o canto da bola bate na raquete
      player.play();
      player.rewind();
      //c.y - y;//distancia entre o centro e onde a bola bateu na raquete
      //(y + h/2) - y;//distancia entre o extremo e o centro da raquete
      angle = PI/4 * (c.y-y) / ((y + h/2)-y); //aplicado as contas de cima para ser mais rapido
      v = sqrt(sq(c1.vx) + sq(c1.vy)); //magnitude das velocidades na direcao x e y, que e igual a velocidade actual independete da direcao 
      if(c.vx > 0){ //Para saber que ira bater na raquete da esquerda ou da direita
        c.vx = -v * cos(angle);
      }
      else {
        c.vx = v * cos(angle);
      }
      c.vy = v * sin(angle);
    }
    if((c.y >= y - h/2 && c.y < y)|| (c.y+c.d/2 > y - h/2 && c.y < y) ){ //Para cima
      player.play();
      player.rewind();
      angle = PI/4 * (y-c.y) / (y-(y-h/2));
      v = sqrt(sq(c1.vx) + sq(c1.vy)); //magnitude das velocidades na direcao x e y, que e igual a velocidade actual independete da direcao 
      if(c.vx > 0){ //Para saber que ira bater na raquete da esquerda ou da direita
        c.vx = -v * cos(angle);
      }
      else {
        c.vx = v * cos(angle);
      }
      c.vy = -v * sin(angle);
    }
  }
  
  void move(float dx, float dy)
  {
    x += dx;
    y += dy;
  }
  
  void draw()
  {
    pushMatrix();
    strokeWeight(6);
    stroke(c);
    line(x, y+h/2, x, y-h/2);
    popMatrix();
  }
}

//---------------------------------------------------------------

class Court
{
  float x; // x coordinate of center
  float y; // y coordinate of center
  float w; //width
  float h; //height
  color c;
  
  Court(float x, float y, float w, float h, color c)
  {
    this.x = x;
    this.y = y;
    this.w = w;
    this.h = h;
    this.c = c;
  }
  
  void draw()
  {
    pushMatrix();
    stroke(c);
    strokeWeight(3);
    noFill();
    rectMode(CENTER);
    rect(x, y, w, h);
    popMatrix();
  }
}

//---------------------------------------------------------------

class Circle
{
  float x;  // x coordinate of center
  float y;  // y coordinate of center
  float d;  // diameter
  color c;  // color
  float vx; // velocity along x-axis
  float vy; // velocity along y-axis 

  Circle(float x, float y, float d, color c)
  {
    this.x = x;
    this.y = y;
    this.d = d;
    this.c = c;
    this.vx = 0;
    this.vy = 0;
  }

  boolean contains(float x, float y)
  {
    return sq(this.x-x)+sq(this.y-y) <= sq(this.d) / 4;
  } 

  void move(float dx, float dy)
  {
    x += dx;
    y += dy;
  }

  void moveTo(float x, float y)
  {
    this.x = x;
    this.y = y;
  }

  void travel(float t)
  {
    move(vx*t, vy*t);
  }

  void setVelocity(float vx, float vy)
  {
    this.vx = vx;
    this.vy = vy;
  }

  void setSpeed(float s)
  {
    float s0 = sqrt(sq(vx) + sq(vy));
    if (s0 > 0)
      setVelocity(s * vx / s0, s * vy / s0);
  }

  
  boolean bounceInsideCourt(Court q, Racket r1, Racket r2, Circle c)
  {
    if (y - d/2 <= q.y - q.h/2)
    {
      y = q.y - q.h/2 + d/2;
      vy = -vy;
    }
    if (y + d/2 >= q.y + q.h/2)
    {
      y = q.y + q.h/2 - d/2;
      vy = -vy;
    }
    if (x - d/2 <= r2.x && y + d/2 <= r2.y + r2.h && y - d/2 >= r2.y - r2.h && x - d/2 >= q.x - q.w/2) //lado esquerdo
    {
      r2.ballsHitsRacket(c);
      return true;
    }
    else if (x + d/2 >= r1.x && y + d/2 <= r1.y + r1.h && y - d/2 >= r1.y - r1.h && x + d/2 <= q.x + q.w/2) //lado direito
    {
      r1.ballsHitsRacket(c);
      return true;
    }
    else
      return false;
  }

  void draw()
  {
    pushMatrix();
    noStroke();
    fill(c);
    ellipse(x, y, d, d);
    popMatrix();
  }
}
