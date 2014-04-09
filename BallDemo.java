import java.awt.Color;
import java.util.ArrayList;
import java.util.Random;
/**
 * Class BallDemo - a short demonstration showing animation with the 
 * Canvas class. 
 *
 * @author Michael KÃ¶lling and David J. Barnes
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
     * el usuario introduzca por parámetro cuántas bolas quiere que aparezcan en pantalla. 
     * El diametro y color de las bolas debe ser aleatorio. 
     * Su posicion de inicio también debe ser aleatoria, pero siempre de la mitad de la pantalla hacia la izquierda.
     * La animación debe terminar cuando alguna bola se salga del suelo por la derecha. 
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
            int x = aleatorio.nextInt(300);// Si lo que queremos es acotar el rango, podemos pasar el límite como parámetro del método .nextInt(valor)
            int y = aleatorio.nextInt(250);

            int diametro = aleatorio.nextInt(100);

            int r = aleatorio.nextInt(256); //2^8 bits = 256 colores gama rojo
            int g = aleatorio.nextInt(256);//2^8 bits = 256 colores gama verde
            int b = aleatorio.nextInt(256);//2^8 bits = 256 colores gama azul

            BouncingBall bola = new BouncingBall(x, y, diametro,new Color(r,g,b), ground, myCanvas);
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
     * Este método debe dibujar un rectángulo en la pantalla y tantas bolas como se indiquen como parámetro en la invocación del método
     * La posición inicial, el color y la direccion de cada bola debe fijarse de manera aleatoria.
     * @param numBolas numero de bolas que quiera crear el usuario
     */
    public void boxBounce(int numBolas)
    {
        int ground = 480;   // pixeles que hay desde el principio del lienzo por arriba hasta la linea de abajo del rectangulo
        int arriba = 10;//pixeles que hay desde el principio del lienzo por arriba hasta la primera linea del rectangulo(la de arriba)
        int derecha = 580;//pixeles que hay desde el principio del lienzo por la izquierda hasta la linea de la derecha del rectangulo
        int izquierda = 10;//pixeles que hay desde el principio del lienzo por la izquierda hasta la linea de la izquierda del rectangulo

        myCanvas.setVisible(true);

        // draw un rectangulo
        myCanvas.fillRectangle(izquierda, arriba, derecha, ground);

        // creamos el array de bolas
        ArrayList <BoxBall> arrayBolas = new ArrayList<>();//para guardar las bolas creadas
        //hacemos tantas bolas como nos han pasado por parametro
        for(int i = 0; i < numBolas; i++){
            Random aleatorio = new Random();
            //cordenadas x e y donde aparece la bola recogidas de forma aleatoria
            int x = aleatorio.nextInt(300);// Si lo que queremos es acotar el rango, podemos pasar el límite como parámetro del método .nextInt(valor)
            int y = aleatorio.nextInt(250);

            //diametro de la bola recogido de forma aleatoria
            int diametro = aleatorio.nextInt(100);

            //color de la bola de forma aleatoria rgb
            int r = aleatorio.nextInt(256); //2^8 bits = 256 colores gama rojo
            int g = aleatorio.nextInt(256);//2^8 bits = 256 colores gama verde
            int b = aleatorio.nextInt(256);//2^8 bits = 256 colores gama azul

            //si la velocidadX es true se mueve en la x positivamente, si es false se mueve en las y negativamente - aleatoriamente
            boolean velocidadX = aleatorio.nextBoolean();
             //si la velocidadY es true se mueve en la y positivamente, si es false se mueve en las y negativamente - aleatoriamente
            boolean velocidadY = aleatorio.nextBoolean();

            //creamos la bola
            BoxBall bola = new BoxBall(x, y, diametro,new Color(r,g,b), velocidadY, velocidadX, ground, arriba, derecha, izquierda, myCanvas);
            //añadida la bola al array
            arrayBolas.add(bola);
            //dibujamos la bola
            bola.draw();
        }

        // make them bounce
        boolean finished =  false;
        while(!finished) {
            myCanvas.wait(30);           //velocidad de las bolas (menor numero más rapido)
            for(BoxBall bola : arrayBolas){//recorremos el array de bolas
                bola.move();//movemos cada bola
            }
        }
    }
}
