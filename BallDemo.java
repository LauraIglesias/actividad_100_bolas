import java.awt.Color;
import java.util.ArrayList;

/**
 * Class BallDemo - a short demonstration showing animation with the 
 * Canvas class. 
 *
 * @author Michael Kölling and David J. Barnes
 * @version 2011.07.31
 */

public class BallDemo   
{
    private Canvas myCanvas;

    /**
     * Create a BallDemo object. Creates a fresh canvas and makes it visible.
     */
    public BallDemo()
    {
        myCanvas = new Canvas("Ball Demo", 600, 500);
    }

    /**
     * Simulate two bouncing balls
     * el usuario introduzca por par�metro cu�ntas bolas quiere que aparezcan en pantalla. 
     * El radio y color de las bolas debe ser aleatorio. Su posicion de inicio tambi�n debe ser aleatoria,
     * pero siempre de la mitad de la pantalla hacia la izquierda.
     * La animaci�n debe terminar cuando alguna bola se salga del suelo por la derecha. 
     * @parm bolas numero de bolas que quieres que aparezcan
     */
    public void bounce(int bolas)
    {
        int ground = 400;   // position of the ground line

        myCanvas.setVisible(true);

        // draw the ground
        myCanvas.drawLine(50, ground, 550, ground);

        // crate and show the balls
        BouncingBall ball = new BouncingBall(50, 50, 16, Color.BLUE, ground, myCanvas);
        ball.draw();
        BouncingBall ball2 = new BouncingBall(70, 80, 20, Color.RED, ground, myCanvas);
        ball2.draw();

        ArrayList <BouncingBall> arrayBolas = new ArrayList<>();//para guardar las bolas creadas
        for(int i = 0; i < bolas; i++){
            BouncingBall bola = new BouncingBall(50, 50, 16, Color.BLUE, ground, myCanvas);
            arrayBolas.add(bola);
            bola.draw();
        }

        // make them bounce
        boolean finished =  false;
        while(!finished) {
            myCanvas.wait(50);           // small delay
            ball.move();
            ball2.move();
            
            // stop once ball has travelled a certain distance on x axis
            if(ball.getXPosition() >= 550 || ball2.getXPosition() >= 550) {
                finished = true;
            }
        }
    }
}
