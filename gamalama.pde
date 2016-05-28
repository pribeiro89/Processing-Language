/*
 * Instructions:
 * Click a few times on the white screen, or click and drag the mouse, to select a few squares;
 * Then press space and watch. :)
 */

int myWidth = 800;
int myHeight = 600;
int nFrames = 0;
int frames_per = 4;

color red = color(255, 0, 0);
color white = color(255);
int grey_color = 200;
int white_color = 255;

int [][] m;
int rows = 50;
int cols = 50;
int dia = 1;
boolean stopped;

void setup() {
  size(myWidth, myHeight);
  m = new int[rows][cols];
  for(int i = 0; i < rows; i++)
    for(int j = 0; j < cols; j++)
      m[i][j] = -1;
  stopped = false;
}

void draw() {
  background(white);
  if(stopped == true)
    update();
  drawMatrix(m, rows, cols);
  nFrames++;
}

void mouseClicked() {
  int w = width / cols;
  int h = height / rows;
  int mx = mouseX / w;
  int my = mouseY / h;
  m[my][mx] = 1;
  white_color = 0;
}

void mouseDragged() {
  int w = width / cols;
  int h = height / rows;
  int mx = mouseX / w;
  int my = mouseY / h;
  m[my][mx] = 1;
  white_color = 0;
}

void keyPressed() {
  if(key == ' ')
    stopped = !stopped;
}

void update() {
  if(nFrames%(60/frames_per) == 0)
    construct_nuvem(m, rows, cols, dia++);
}

void drawMatrix(int m[][], int r, int c) {
  rectMode(CENTER);
  textAlign(CENTER, CENTER);
  int w = width / c;
  int h = height / r;
  for (int i = 0; i < r; i++)
    for (int j = 0; j < c; j++)
      {
        float cx = j*w + w/2;
        float cy = i*h + h/2;
        //fill(200);
        noStroke();
        if(m[i][j] == -1)
          fill(white);
        else
          fill(white_color + m[i][j]*10);
        rect(cx, cy, w, h);
        fill(red);
        text(str(m[i][j]), cx, cy, w, h);
      }
}

int construct_nuvem(int m[][], int rows, int columns, int dia) {
  int u = 0;//variavel de controlo, se nao entrar em nenhum dos if ali de baixo quer dizer que nao alterou nenhum bloco
  for (int i = 0; i < rows; ++i) {
    for (int j = 0; j < columns; ++j)
    {
      if (m[i][j] == dia-1 && j+1< columns && m[i][j+1] == -1){
          m[i][j+1] = dia;
          u = 1;
      }
      if (m[i][j] == dia-1 && j-1>=0 && m[i][j-1] == -1){
          m[i][j-1] = dia;
          u = 1;
      }
      if(m[i][j]== dia-1 && i+1<rows && m[i+1][j] == -1){
          m[i+1][j] = dia;
          u = 1;
      }
      if(m[i][j]== dia-1 && i-1>=0 && m[i-1][j] == -1){
          m[i-1][j] = dia;
          u = 1;
      }
    }
  }
  return u;
}

/*
void construct_nuvem_dias(int m[][], int rows, int columns){
  int dia = 1;
  int k = 0;
  k = construct_nuvem(m, rows, columns, dia);
  while(k == 1){
    dia++;
    k = construct_nuvem(m, rows, columns, dia);
  }
}
*/
