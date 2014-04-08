import java.awt.Color;
import java.util.ArrayList;
import java.util.Random;
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
     * El radio y color de las bolas debe ser aleatorio. 
     * Su posicion de inicio tambi�n debe ser aleatoria, pero siempre de la mitad de la pantalla hacia la izquierda.
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
        ArrayList <BouncingBall> arrayBolas = new ArrayList<>();//para guardar las bolas creadas
        for(int i = 0; i < bolas; i++){
            Random aleatorio = new Random();
            int x = aleatorio.nextInt(300);// Si lo que queremos es acotar el rango, podemos pasar el l�mite como par�metro del m�todo .nextInt(valor)
            int y = aleatorio.nextInt(250);

            int radio = aleatorio.nextInt(100);

            int r = aleatorio.nextInt(256); //2^8 bits = 256 colores gama rojo
            int g = aleatorio.nextInt(256);//2^8 bits = 256 colores gama verde
            int b = aleatorio.nextInt(256);//2^8 bits = 256 colores gama azul

            BouncingBall bola = new BouncingBall(x, y, radio,new Color(r,g,b), ground, myCanvas);
            arrayBolas.add(bola);
            bola.draw();
        }

        // make them bounce
        boolean finished =  false;
        while(!finished) {
            myCanvas.wait(50);           // small delay
            for(BouncingBall bola : arrayBolas){
                bola.move();
                if(bola.getXPosition() >= 550) {
                    finished = true;
                }
            }
            // stop once ball has travelled a certain distance on x axis
            /* if(ball.getXPosition() >= 550 || ball2.getXPosition() >= 550) {
            finished = true;
            }*/
        }
    }

    /**
     * Este m�todo debe dibujar un rect�ngulo en la pantalla y tantas bolas como se indiquen como par�metro en la invocaci�n del m�todo
     * La posici�n inicial, el color y la direccion de cada bola debe fijarse de manera aleatoria.
     * @param numBolas numero de bolas que quiera crear el usuario
     */
    public void boxBounce(int numBolas)
    {
        int ground = 480;   // position of the ground line
        int arriba = 580;
        int derecha = 10;
        int izquierda = 10;

        myCanvas.setVisible(true);

        // draw the ground
        myCanvas.fillRectangle(izquierda, derecha, arriba, ground);

        // crate and show the balls
        ArrayList <BoxBall> arrayBolas = new ArrayList<>();//para guardar las bolas creadas
        for(int i = 0; i < numBolas; i++){
            Random aleatorio = new Random();
            int x = aleatorio.nextInt(300);// Si lo que queremos es acotar el rango, podemos pasar el l�mite como par�metro del m�todo .nextInt(valor)
            int y = aleatorio.nextInt(250);

            int radio = aleatorio.nextInt(100);

            int r = aleatorio.nextInt(256); //2^8 bits = 256 colores gama rojo
            int g = aleatorio.nextInt(256);//2^8 bits = 256 colores gama verde
            int b = aleatorio.nextInt(256);//2^8 bits = 256 colores gama azul

            boolean velocidadX = aleatorio.nextBoolean();
            boolean velocidadY = aleatorio.nextBoolean();

            BoxBall bola = new BoxBall(x, y, radio,new Color(r,g,b), velocidadY, velocidadX, ground, arriba, derecha, izquierda, myCanvas);
            arrayBolas.add(bola);
            bola.draw();
        }

        // make them bounce
        boolean finished =  false;
        while(!finished) {
            myCanvas.wait(50);           // small delay
            for(BoxBall bola : arrayBolas){
                bola.move();
            }
        }
    }
}
