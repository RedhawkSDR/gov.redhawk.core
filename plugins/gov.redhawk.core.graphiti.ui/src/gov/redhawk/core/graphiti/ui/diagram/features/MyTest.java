/*******************************************************************************
 * This file is protected by Copyright. 
 * Please refer to the COPYRIGHT file distributed with this source distribution.
 *
 * This file is part of REDHAWK IDE.
 *
 * All rights reserved.  This program and the accompanying materials are made available under 
 * the terms of the Eclipse Public License v1.0 which accompanies this distribution, and is available at 
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/
package gov.redhawk.core.graphiti.ui.diagram.features;

import org.eclipse.graphiti.mm.algorithms.styles.Point;
import org.eclipse.graphiti.mm.algorithms.styles.StylesFactory;
import org.eclipse.zest.layouts.InvalidLayoutConfiguration;
import org.eclipse.zest.layouts.LayoutAlgorithm;
import org.eclipse.zest.layouts.LayoutEntity;
import org.eclipse.zest.layouts.LayoutRelationship;
import org.eclipse.zest.layouts.LayoutStyles;
import org.eclipse.zest.layouts.algorithms.HorizontalTreeLayoutAlgorithm;
import org.eclipse.zest.layouts.exampleStructures.SimpleNode;
import org.junit.Assert;
import org.junit.Test;

public class MyTest {

	// CHECKSTYLE:OFF
	@Test
	public void myTest() throws InvalidLayoutConfiguration {
		LayoutAlgorithm layoutAlgorithm = new HorizontalTreeLayoutAlgorithm(LayoutStyles.NO_LAYOUT_NODE_RESIZING);
		
		SimpleNode entity1 = new SimpleNode("Object1", 0, 0, 10, 51);
		SimpleNode entity2 = new SimpleNode("Object2", 10, 10, 10, 10);
		LayoutRelationship[] connections = new LayoutRelationship[0];
		LayoutEntity[] entities = { entity1, entity2 };
		
		final int xBounds = 100;
		final int yBounds = 100;
		Point diagramBounds = StylesFactory.eINSTANCE.createPoint();
		diagramBounds.setX(xBounds);
		diagramBounds.setY(yBounds);
		layoutAlgorithm.applyLayout(entities, connections, 0, 0, diagramBounds.getX(), diagramBounds.getY(), false, false);
		
		for (LayoutEntity entity : entities) {
			System.err.println(" --> " + entity);
			System.err.println("W: " + entity.getWidthInLayout());
			System.err.println("H: " + entity.getHeightInLayout());
			System.err.println("X: " + entity.getXInLayout());
			System.err.println("Y: " + entity.getYInLayout());
		}
		
		// if an objs Y is > than another objs Y, but < than its Y + Height, AND the bounds are large enough
		// that all objects should be able to fit, then overlap has occurred.  
		boolean overlapTop = entity2.getY() > entity1.getY();
		boolean overlapBottom = entity2.getY() < (entity1.getY() + entity1.getHeight());
		boolean allObjsFit = (entity1.getHeight() + entity2.getHeight()) < yBounds;
		Assert.assertFalse("I'm a bad bad algorithm", overlapTop && overlapBottom && allObjsFit);  
	}
}
