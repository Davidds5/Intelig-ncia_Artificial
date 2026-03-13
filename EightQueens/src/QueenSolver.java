import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class QueenSolver extends JFrame{
    
    public static void main(String [] args){
        QueenSolver world = new QueenSolver();
        world.show();
    }
    
    private Queen lastQueen = null;
    
    public QueenSolver(){
        setTitle("Jogo das 8 Rainhas");
        setSize(600,500);
        for(int i=1; i<=8; i++){
            lastQueen = new Queen(i, lastQueen);
            lastQueen.findSolution();
        }
        
        addMouseListener(new MouseKeeper());
        addWindowListener(new CloseQuit());
    }
    
    public void paint(Graphics g){
        super.paint(g);
        
        for (int i=0; i<=8; i++){
            g.drawLine(50*i + 10, 40, 50*i + 10, 440);
            g.drawLine(10, 50*i + 40, 410, 50*i + 40);
        }
        
        g.drawString("Clique para ver a próxima solução!!!", 20, 470);
        
        lastQueen.paint(g);
    }
    
    
    private class CloseQuit extends WindowAdapter{
        public void windowClosing(WindowEvent e){
            System.exit(0);
        }
    }

    
    private class MouseKeeper extends MouseAdapter{
        public void mousePressed(MouseEvent e){
            lastQueen.advance();
            repaint();
        }
    }
}