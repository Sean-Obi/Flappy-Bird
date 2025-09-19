
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.*;

public class FlappyBird extends JPanel implements ActionListener, KeyListener {
    int boardWidth = 360;
    int boardHeight = 640;

    // Images 
    Image backgroundImg;
    Image birdImg;
    Image topPipeImg;
    Image bottomPipeImg; 

    // Bird

    int birdX = boardWidth/8; // sets intial coordinates of the bird 
    int birdY = boardHeight/2;
    int birdWidth = 34;
    int birdHeight = 24;

    class Bird {
        int x = birdX;
        int y = birdY;
        int width = birdWidth;
        int height = birdHeight;
        Image img;
        Bird(Image img) {
            this.img = img;
        }

    }

    // Pipes 
    int pipeX = boardWidth; // sets the pipe to the RHS of the window
    int pipeY = 0; // top RHS of the window
    int pipeW = 64;
    int pipeH = 512;

    class Pipe {
        int x = pipeX;
        int y = pipeY;
        int width = pipeW;
        int height = pipeH;
        boolean passed = false;

        Image img;

        Pipe(Image img) {
            this.img = img;
        }
    }

    // Game logic
    Bird bird;
    int velocityX = -4; // moves pipes to the left and simulates pipes bird to the right 
    int velocityY = 0;
    int gravity = 1;

    ArrayList<Pipe> pipes;
    Random random = new Random();

    Timer gameLoop;
    Timer placePipesTimer;
    boolean gameOver = false;
    double score = 0;
    



    FlappyBird() {
        setPreferredSize(new Dimension(boardWidth, boardHeight));
        //setBackground(Color.blue);
        setFocusable(true); //JPanel only takes in key events 
        addKeyListener(this); // checks keyfunctions for next event 

        // Load Images 
        backgroundImg = new ImageIcon(getClass().getResource("./flappybirdbg.png")).getImage();
        birdImg = new ImageIcon(getClass().getResource("./flappybird.png")).getImage();
        topPipeImg = new ImageIcon(getClass().getResource("./toppipe.png")).getImage();
        bottomPipeImg = new ImageIcon(getClass().getResource("./bottompipe.png")).getImage();
        bird = new Bird(birdImg);
        pipes = new ArrayList<Pipe>();
        placePipesTimer = new Timer(1500, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                placePipes();
            }
            
        });

        placePipesTimer.start();
        gameLoop = new Timer(1000/60, this); //60 frames in 1000 ms
        gameLoop.start();

    }

    public void placePipes () {

        // pipeY = 0
        // Math.random gives a random number between 0-1. Scales pipeH
        int randomPipeY = (int)(pipeY - pipeH/4 - Math.random()*(pipeH/2));
        int openSpace = boardHeight/4;
        Pipe topPipe = new Pipe(topPipeImg);
        topPipe.y = randomPipeY;
        pipes.add(topPipe);
        Pipe bottomPipe = new Pipe(bottomPipeImg);
        bottomPipe.y = topPipe.y + pipeH + openSpace;
        pipes.add(bottomPipe);
        
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g); // super refers to parent class functions 
        draw(g);
    }

    public void draw(Graphics g) {
        g.drawImage(backgroundImg, 0, 0, boardWidth, boardHeight, null); // drawing always starts from top left corner of the window
        g.drawImage(bird.img, bird.x, bird.y, bird.width, bird.height, null);
        for(int i = 0; i < pipes.size(); i++) {
            Pipe pipe = pipes.get(i);
            g.drawImage(pipe.img, pipe.x, pipe.y, pipe.width, pipe.height, null);
        }

        g.setColor(Color.white);
        g.setFont(new Font("Arial", Font.PLAIN, 32));
        if(gameOver) {
            g.drawString("Game Over: " + String.valueOf((int)score), 10, 35);
        }
        else
            g.drawString(String.valueOf((int)score), 10, 35);

    }

    

    public void move() {
        velocityY += gravity;
        bird.y += velocityY;
        bird.y = Math.max(bird.y,0);

        for(int i = 0; i < pipes.size(); i++) {
            Pipe pipe = pipes.get(i);
            pipe.x += velocityX; // every frame moves each pipe to the left

            if(!pipe.passed && bird.x > pipe.x + pipe.width) {
                pipe.passed = true;
                score += 0.5;
            }
           
            if(collision(bird,pipe)) {
                gameOver = true;
            }
            
         
        }

        
        if(bird.y > boardHeight)
            gameOver = true;
    }

    public boolean collision(Bird a, Pipe b) {
        return a.x < b.x + b.width &&
        a.x + a.width > b.x &&
        a.y < b.y + b.height &&
        a.y + a.height > b.y;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        // the action perfromed every 16.6 ms is changing the frame
        move(); // every frame updates the birds y pos
        repaint(); // repaints the frame from the draw method 
        if(gameOver){
            placePipesTimer.stop();
            gameLoop.stop();
        }
            
    }

   

    @Override
    public void keyPressed(KeyEvent e) {
        // All keys
        if(e.getKeyCode() == KeyEvent.VK_SPACE) {
            velocityY = -9;
            if(gameOver) {
                bird.y = birdY;
                velocityY = 0;
                pipes.clear();
                gameOver = false;
                score = 0;
                gameLoop.start();
                placePipesTimer.start();
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        
        
    }

     @Override
    public void keyTyped(KeyEvent e) {
        // Only character keys
        
    }
}
    

