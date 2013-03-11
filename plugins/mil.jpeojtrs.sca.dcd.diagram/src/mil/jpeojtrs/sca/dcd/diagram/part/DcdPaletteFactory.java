/** 
 * This file is protected by Copyright. 
 * Please refer to the COPYRIGHT file distributed with this source distribution.
 * 
 * This file is part of REDHAWK IDE.
 * 
 * All rights reserved.  This program and the accompanying materials are made available under 
 * the terms of the Eclipse Public License v1.0 which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html.
 *
 */

// BEGIN GENERATED CODE
package mil.jpeojtrs.sca.dcd.diagram.part;

import java.util.Collections;
import java.util.List;

import mil.jpeojtrs.sca.dcd.diagram.providers.DcdElementTypes;

import org.eclipse.gef.Tool;
import org.eclipse.gef.palette.PaletteContainer;
import org.eclipse.gef.palette.PaletteDrawer;
import org.eclipse.gef.palette.PaletteRoot;
import org.eclipse.gef.palette.ToolEntry;
import org.eclipse.gmf.runtime.diagram.ui.tools.UnspecifiedTypeConnectionTool;
import org.eclipse.gmf.runtime.diagram.ui.tools.UnspecifiedTypeCreationTool;
import org.eclipse.gmf.runtime.emf.type.core.IElementType;

/**
 * @generated
 */
public class DcdPaletteFactory {

	/**
	 * @generated
	 */
	public void fillPalette(PaletteRoot paletteRoot) {
		paletteRoot.add(createBaseTypes1Group());
	}

	/**
	 * Creates "Base Types" palette tool group
	 * @generated
	 */
	private PaletteContainer createBaseTypes1Group() {
		PaletteDrawer paletteContainer = new PaletteDrawer(
				Messages.BaseTypes1Group_title);
		paletteContainer.setId("createBaseTypes1Group"); //$NON-NLS-1$
		paletteContainer.add(createComponentInstantiation1CreationTool());
		paletteContainer.add(createComponentPlacement2CreationTool());
		paletteContainer.add(createInterface3CreationTool());
		paletteContainer.add(createPartitioning4CreationTool());
		paletteContainer.add(createProvidesPort5CreationTool());
		paletteContainer.add(createUsesPort6CreationTool());
		paletteContainer.add(createConnectInterface7CreationTool());
		return paletteContainer;
	}

	/**
	 * @generated
	 */
	private ToolEntry createComponentInstantiation1CreationTool() {
		NodeToolEntry entry = new NodeToolEntry(
				Messages.ComponentInstantiation1CreationTool_title,
				Messages.ComponentInstantiation1CreationTool_desc,
				Collections
						.singletonList(DcdElementTypes.DcdComponentInstantiation_3002));
		entry.setId("createComponentInstantiation1CreationTool"); //$NON-NLS-1$
		entry.setSmallIcon(DcdDiagramEditorPlugin
				.findImageDescriptor("/mil.jpeojtrs.sca.partitioning.edit/icons/full/obj16/ComponentInstantiation.gif")); //$NON-NLS-1$
		entry.setLargeIcon(entry.getSmallIcon());
		return entry;
	}

	/**
	 * @generated
	 */
	private ToolEntry createComponentPlacement2CreationTool() {
		NodeToolEntry entry = new NodeToolEntry(
				Messages.ComponentPlacement2CreationTool_title,
				Messages.ComponentPlacement2CreationTool_desc,
				Collections
						.singletonList(DcdElementTypes.DcdComponentPlacement_3001));
		entry.setId("createComponentPlacement2CreationTool"); //$NON-NLS-1$
		entry.setSmallIcon(DcdDiagramEditorPlugin
				.findImageDescriptor("/mil.jpeojtrs.sca.partitioning.edit/icons/full/obj16/ComponentPlacement.gif")); //$NON-NLS-1$
		entry.setLargeIcon(entry.getSmallIcon());
		return entry;
	}

	/**
	 * @generated
	 */
	private ToolEntry createInterface3CreationTool() {
		NodeToolEntry entry = new NodeToolEntry(
				Messages.Interface3CreationTool_title,
				Messages.Interface3CreationTool_desc,
				Collections
						.singletonList(DcdElementTypes.ComponentSupportedInterfaceStub_3005));
		entry.setId("createInterface3CreationTool"); //$NON-NLS-1$
		entry.setSmallIcon(DcdDiagramEditorPlugin
				.findImageDescriptor("/mil.jpeojtrs.sca.partitioning.edit/icons/full/obj16/ComponentSupportedInterface.gif")); //$NON-NLS-1$
		entry.setLargeIcon(entry.getSmallIcon());
		return entry;
	}

	/**
	 * @generated
	 */
	private ToolEntry createPartitioning4CreationTool() {
		NodeToolEntry entry = new NodeToolEntry(
				Messages.Partitioning4CreationTool_title,
				Messages.Partitioning4CreationTool_desc,
				Collections.singletonList(DcdElementTypes.DcdPartitioning_2001));
		entry.setId("createPartitioning4CreationTool"); //$NON-NLS-1$
		entry.setSmallIcon(DcdDiagramEditorPlugin
				.findImageDescriptor("/mil.jpeojtrs.sca.partitioning.edit/icons/full/obj16/Partitioning.gif")); //$NON-NLS-1$
		entry.setLargeIcon(entry.getSmallIcon());
		return entry;
	}

	/**
	 * @generated
	 */
	private ToolEntry createProvidesPort5CreationTool() {
		NodeToolEntry entry = new NodeToolEntry(
				Messages.ProvidesPort5CreationTool_title,
				Messages.ProvidesPort5CreationTool_desc,
				Collections
						.singletonList(DcdElementTypes.ProvidesPortStub_3004));
		entry.setId("createProvidesPort5CreationTool"); //$NON-NLS-1$
		entry.setSmallIcon(DcdDiagramEditorPlugin
				.findImageDescriptor("/mil.jpeojtrs.sca.partitioning.edit/icons/full/obj16/ProvidesPort.gif")); //$NON-NLS-1$
		entry.setLargeIcon(entry.getSmallIcon());
		return entry;
	}

	/**
	 * @generated
	 */
	private ToolEntry createUsesPort6CreationTool() {
		NodeToolEntry entry = new NodeToolEntry(
				Messages.UsesPort6CreationTool_title,
				Messages.UsesPort6CreationTool_desc,
				Collections.singletonList(DcdElementTypes.UsesPortStub_3003));
		entry.setId("createUsesPort6CreationTool"); //$NON-NLS-1$
		entry.setSmallIcon(DcdDiagramEditorPlugin
				.findImageDescriptor("/mil.jpeojtrs.sca.partitioning.edit/icons/full/obj16/UsesPort.gif")); //$NON-NLS-1$
		entry.setLargeIcon(entry.getSmallIcon());
		return entry;
	}

	/**
	 * @generated
	 */
	private ToolEntry createConnectInterface7CreationTool() {
		LinkToolEntry entry = new LinkToolEntry(
				Messages.ConnectInterface7CreationTool_title,
				Messages.ConnectInterface7CreationTool_desc,
				Collections
						.singletonList(DcdElementTypes.DcdConnectInterface_4001));
		entry.setId("createConnectInterface7CreationTool"); //$NON-NLS-1$
		entry.setSmallIcon(DcdDiagramEditorPlugin
				.findImageDescriptor("/mil.jpeojtrs.sca.partitioning.edit/icons/full/obj16/ConnectInterface.gif")); //$NON-NLS-1$
		entry.setLargeIcon(entry.getSmallIcon());
		return entry;
	}

	/**
	 * @generated
	 */
	private static class NodeToolEntry extends ToolEntry {

		/**
		 * @generated
		 */
		private final List<IElementType> elementTypes;

		/**
		 * @generated
		 */
		private NodeToolEntry(String title, String description,
				List<IElementType> elementTypes) {
			super(title, description, null, null);
			this.elementTypes = elementTypes;
		}

		/**
		 * @generated
		 */
		public Tool createTool() {
			Tool tool = new UnspecifiedTypeCreationTool(elementTypes);
			tool.setProperties(getToolProperties());
			return tool;
		}
	}

	/**
	 * @generated
	 */
	private static class LinkToolEntry extends ToolEntry {

		/**
		 * @generated
		 */
		private final List<IElementType> relationshipTypes;

		/**
		 * @generated
		 */
		private LinkToolEntry(String title, String description,
				List<IElementType> relationshipTypes) {
			super(title, description, null, null);
			this.relationshipTypes = relationshipTypes;
		}

		/**
		 * @generated
		 */
		public Tool createTool() {
			Tool tool = new UnspecifiedTypeConnectionTool(relationshipTypes);
			tool.setProperties(getToolProperties());
			return tool;
		}
	}
}
