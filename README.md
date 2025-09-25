# Flappy Bird (Java Swing Edition)

A simple clone of the classic **Flappy Bird** game built using **Java Swing and AWT**. This project demonstrates key programming concepts including game loops, animations, image rendering, object collision, and user input handling in a lightweight desktop application.

---

## 🎮 Demo

![Flappy Bird Java Screenshot](./screenshot.png)

> A bird, gravity, and some deadly pipes. Press **Space** to fly!

---

## ✨ Features

- Classic Flappy Bird mechanics
- Smooth animation with 60 FPS game loop
- Procedural pipe generation with randomized heights
- Simple collision detection
- Score tracking
- Game restart on spacebar after game over
- Lightweight Java Swing GUI

---

## ⚙️ Installation

1. **Clone the repository**
   ```bash
   git clone https://github.com/your-username/flappy-bird-java.git
   cd flappy-bird-java
   ```

2. **Add Image Assets**

   Make sure the following images are placed in the same directory as the source file:

   ```
   /flappy-bird-java/
   ├── flappybird.png
   ├── flappybirdbg.png
   ├── toppipe.png
   └── bottompipe.png
   ```

3. **Compile the Code**
   ```bash
   javac FlappyBird.java
   ```

4. **Run the Game**
   ```bash
   java FlappyBird
   ```

---

## 🕹 Gameplay Controls

| Key        | Action               |
|------------|----------------------|
| `Spacebar` | Make the bird jump / restart game |

---

## ⚙️ Configuration

You can tweak the following game settings in the source code:

| Variable         | Purpose                         | Default |
|------------------|----------------------------------|---------|
| `gravity`        | Gravity applied to the bird      | `1`     |
| `velocityY`      | Bird's jump velocity             | `-9`    |
| `velocityX`      | Speed of pipe movement           | `-4`    |
| `placePipesTimer`| Interval for pipe generation     | `1500ms`|

---

## 📦 Dependencies

This project only uses **standard Java libraries**:

- `javax.swing.*`
- `java.awt.*`
- `java.util.*`

> ✅ No external dependencies or frameworks required.

---

## 🧾 Project Structure

```bash
flappy-bird-java/
├── FlappyBird.java     # Main game logic
├── flappybird.png      # Bird sprite
├── flappybirdbg.png    # Background image
├── toppipe.png         # Top pipe image
├── bottompipe.png      # Bottom pipe image
└── README.md           # Project documentation
```

---

## 🛠 Troubleshooting

- **Image not loading?**
  - Ensure image files are in the correct directory and named properly.
  - Try using absolute paths if `getResource()` fails.

- **Nothing happens on run?**
  - Make sure your class has a `main()` method to instantiate `FlappyBird` inside a JFrame (this is missing in the current version).

### ➕ Example `main()` method:
```java
public static void main(String[] args) {
    JFrame frame = new JFrame("Flappy Bird");
    FlappyBird game = new FlappyBird();
    frame.add(game);
    frame.pack();
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setResizable(false);
    frame.setVisible(true);
}
```

---

