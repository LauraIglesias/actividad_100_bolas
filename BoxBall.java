import java.awt.*;
import java.awt.geom.*;
import java.util.Random;
/**
 * Las bolas de tipo BoxBall llevan siempre la misma velocidad (1 pixel cada vez),
 * lo que sucede es que cuando golpean el una de las paredes del rectángulo rebotan y cambian de direccion.
 * 
 * @author Laura
 * @version 5/04/2014
 */
public class BoxBall
{
    private Ellipse2D.Double circle;
    private Color color;
    private int diameter;
    private int xPosition;
    private int yPosition;
    private final int groundPosition;      // y position of ground
    private final int posicionArriba;
    private final int posicionDerecha; 
    private final int posicionIzquierda;
    private Canvas canvas;
    private int ySpeed;                // initial downward speed
    private int xSpeed;

    /**
     * Constructor for objects of class BouncingBall
     *
     * @param xPos  the horizontal coordinate of the ball
     * @param yPos  the vertical coordinate of the ball
     * @param ballDiameter  the diameter (in pixels) of the ball
     * @param ballColor  the color of the ball
     * @param groundPos  the position of the ground (where the wall will bounce)
     * @param drawingCanvas  pixeles que hay desde el principio del lienzo por arriba hasta la linea de abajo del rectangulo
     * @param posArriba pixeles que hay desde el principio del lienzo por arriba hasta la primera linea del rectangulo(la de arriba)
     * @param posDerecha pixeles que hay desde el principio del lienzo por la izquierda hasta la linea de la derecha del rectangulo
     * @param posIzquierda pixeles que hay desde el principio del lienzo por la izquierda hasta la linea de la izquierda del rectangulo
     * @param velocidadX boolean aleatorio para mover la bola en una cordenada moviendose positiva o negativa
     * @param velocidadY boolean aleatorio para mover la bola en una cordenada moviendose positiva o negativa
     */
    public BoxBall(int xPos, int yPos, int ballDiameter, Color ballColor,boolean velocidadY, boolean velocidadX,
    int groundPos, int posArriba, int posDerecha, int posIzquierda, Canvas drawingCanvas)
    {
        xPosition = xPos;
        yPosition = yPos;
        color = ballColor;
        diameter = ballDiameter;
        groundPosition = groundPos;
        posicionArriba = posArriba;
        posicionDerecha = posDerecha; 
        posicionIzquierda = posIzquierda;
        canvas = drawingCanvas;
        if(velocidadY){//true si la velocidad es Y
            ySpeed = 1; // aumenta la velocidad uno en dicha cordenada
        }
        else {
            ySpeed = -1;//disminuye la velocidad uno en dicha cordenada
        }
        if(velocidadX){//true si la velocidad es X
            xSpeed = 1; // aumenta la velocidad uno en dicha cordenada
        }
        else {
            xSpeed = -1;//disminuye la velocidad uno en dicha cordenada
        }
    }

    /**
     * Draw this ball at its current position onto the canvas.
     **/
    public void draw()
    {
        canvas.setForegroundColor(color);
        canvas.fillCircle(xPosition, yPosition, diameter);
    }

    /**
     * Erase this ball at its current position.
     **/
    public void erase()
    {
        canvas.eraseCircle(xPosition, yPosition, diameter);
    }    

    /**
     * Mueva esta bola de acuerdo con su posición y la velocidad y volver a dibujar.
     * Move this ball according to its position and speed and redraw.
     **/
    public void move()
    {
        // remove from canvas at the current position
        erase();

        // compute new position. calcular la nueva posición
        yPosition += ySpeed;
        xPosition += xSpeed; 

        // comprobar en y q la posicion es >= posicion del suelo - diametro(480-diametro+10(para que llegue al borde))  o  posicion es <= posicion de arriba(10)
        if((yPosition >= (groundPosition - diameter) + 10) || (yPosition <= posicionArriba ))
        {
            ySpeed = -ySpeed;
        }
         // comprobar en y q la posicion es >= posicion del derecha - diametro(580-diametro+10(para que llegue al borde))  o  posicion es <= posicion de izquierda(10)
        if((xPosition >= (posicionDerecha - diameter) + 10) || (xPosition <= posicionIzquierda ))
        {
            xSpeed = -xSpeed;
        }
        // draw again at new position
        draw();
    }  

    /**
     * return the horizontal position of this ball
     */
    public int getXPosition()
    {
        return xPosition;
    }

    /**
     * return the vertical position of this ball
     */
    public int getYPosition()
    {
        return yPosition;
    }
}
