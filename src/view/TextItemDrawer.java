package view;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.font.FontRenderContext;
import java.awt.font.LineBreakMeasurer;
import java.awt.font.TextAttribute;
import java.awt.font.TextLayout;
import java.awt.geom.Rectangle2D;
import java.awt.image.ImageObserver;
import java.text.AttributedString;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import model.TextItem;
import model.SlideItem;
import model.Style;

public class TextItemDrawer extends ItemDrawerBridge {

	@Override
	// teken het item
		public void draw(int x, int y, float scale, Graphics g, ImageObserver o, SlideItem text)
	{
		TextItem itemToDraw = (TextItem) text;	
	
		Style myStyle = text.GetStyle();
				
			if (itemToDraw == null || itemToDraw.getText().length() == 0) {
				return;
			}
			
			List<TextLayout> layouts =  itemToDraw.getLayouts(g,scale);
			
			Point pen = new Point(x + (int)(myStyle.getIndent() * scale), 
					y + (int) (myStyle.getLeading() * scale));
			
			Graphics2D g2d = (Graphics2D)g;
			
			g2d.setColor(myStyle.getColor());
			
			Iterator<TextLayout> it = layouts.iterator();
			
			while (it.hasNext()) {
				TextLayout layout = it.next();
				pen.y += layout.getAscent();
				layout.draw(g2d, pen.x, pen.y);
				pen.y += layout.getDescent();
			}
	}	
}