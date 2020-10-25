package gameManipulators;

import fieldBlocks.FieldBlock;

import java.awt.*;
import java.util.List;

public class GameDrawer  {

    public void drawGrid(Graphics g, int squareSideSize, int unitSize) {
        g.setColor(Color.gray);
        for (int unitBorder = 0;
             unitBorder < squareSideSize;
             unitBorder+= unitSize) {
            g.drawLine(unitBorder, 0, unitBorder, squareSideSize);
            g.drawLine(0, unitBorder, squareSideSize, unitBorder);
        }
    }

    public void drawFieldObjects(Graphics g,
                                 int unitSize,
                                 List<FieldBlock> fieldBlockList) {
        fieldBlockList.forEach((fieldBlock) -> fieldBlock.draw(g, unitSize));
    }

}
