import javax.swing.*;
import java.awt.Color;
import java.awt.Graphics;


public class Queen{

    private int row;
    private int column;
    private Queen neighbor;
    
    public Queen (int c, Queen n){
        row = 1;
        column = c;
        neighbor = n;
    }
    
    public boolean findSolution(){
        while (neighbor != null && neighbor.canAttack(row, column))
            if(! advance())
                return false;
        return true;
    }
    
    public boolean advance(){
        if (row < 8){
            row++;
            return findSolution();
        }
        if (neighbor != null){
            if(! neighbor.advance())
                return false;
            if(! neighbor.findSolution())
                return false;
        }else
            return false;
        row = 1;
        return findSolution();
    }
    
    private boolean canAttack(int testRow, int testColumn){
        int columnDifference = testColumn - column;
        if((row == testRow)|| 
                (row + columnDifference == testRow)||
                (row - columnDifference == testRow))
            return true;
        
        if (neighbor != null)
            return neighbor.canAttack(testRow, testColumn);
        return false;
    }
    
    public void paint(Graphics g){
        if (neighbor != null)
            neighbor.paint(g);
            
        int x = (row - 1) * 50 + 10;
        int y = (column - 1) * 50 + 40;
        
        g.drawLine(x+5, y+45, x+45, y+45);
        g.drawLine(x+5, y+45, x+5, y+5);
        g.drawLine(x+45, y+45, x+45, y+5);
        g.drawLine(x+5, y+35, x+45, y+35);
        g.drawLine(x+5, y+5, x+15, y+20);
        g.drawLine(x+15, y+20, x+25, y+5);
        g.drawLine(x+25, y+5, x+35, y+20);
        g.drawLine(x+35, y+20, x+45, y+5);
      //  g.drawLine(x+20, y+20, 10, 10);
    }
    
    public void foo(Queen arg, Graphics g){
        if (arg.row == 3)
            g.setColor(Color.red);
    }
}