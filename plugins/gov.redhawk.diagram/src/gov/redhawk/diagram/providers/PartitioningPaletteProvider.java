/** 
 * This file is protected by Copyright. 
 * Please refer to the COPYRIGHT file distributed with this source distribution.
 * 
 * This file is part of REDHAWK IDE.
 * 
 * All rights reserved.  This program and the accompanying materials are made available under 
 * the terms of the Eclipse Public License v1.0 which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html.
 *
 */
package gov.redhawk.diagram.providers;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import mil.jpeojtrs.sca.partitioning.provider.PartitioningEditPlugin;

import org.eclipse.gef.Tool;
import org.eclipse.gef.palette.PaletteDrawer;
import org.eclipse.gef.palette.PaletteRoot;
import org.eclipse.gef.palette.ToolEntry;
import org.eclipse.gmf.runtime.diagram.ui.providers.DefaultPaletteProvider;
import org.eclipse.gmf.runtime.diagram.ui.tools.UnspecifiedTypeCreationTool;
import org.eclipse.gmf.runtime.emf.type.core.IElementType;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.IEditorPart;

/**
 * @since 3.0
 * 
 */
public abstract class PartitioningPaletteProvider extends DefaultPaletteProvider {

	private static final String TOOL_EDIT_PLUGIN_ID = "/" + PartitioningEditPlugin.getPlugin().getSymbolicName();

	@Override
	public void contributeToPalette(final IEditorPart editor, final Object content, final PaletteRoot root, final Map predefinedEntries) {
		final PaletteDrawer drawer = new PaletteDrawer("Find By");
		drawer.setId("findBy");
		root.add(drawer);
		drawer.add(createFindByCreationTool());
		drawer.add(createNamingServiceCreationTool());
		drawer.add(createDomainFinderCreationTool());
	}

	private ToolEntry createDomainFinderCreationTool() {
		final NodeToolEntry entry = new NodeToolEntry("Domain Finder", "Domain Finder", Collections.singletonList(PartitioningElementTypes.DomainFinder));
		entry.setId("createDomainFinder11CreationTool"); //$NON-NLS-1$
		entry.setSmallIcon(findImageDescriptor(PartitioningPaletteProvider.TOOL_EDIT_PLUGIN_ID + "/icons/full/obj16/DomainFinder.gif")); //$NON-NLS-1$
		entry.setLargeIcon(entry.getSmallIcon());
		return entry;
	}

	protected abstract ImageDescriptor findImageDescriptor(String string);

	private ToolEntry createNamingServiceCreationTool() {
		final NodeToolEntry entry = new NodeToolEntry("Naming Service", "Naming Service", Collections.singletonList(PartitioningElementTypes.NamingService));
		entry.setId("createNamingService6CreationTool"); //$NON-NLS-1$
		entry.setSmallIcon(findImageDescriptor(PartitioningPaletteProvider.TOOL_EDIT_PLUGIN_ID + "/icons/full/obj16/NamingService.gif")); //$NON-NLS-1$
		entry.setLargeIcon(entry.getSmallIcon());
		return entry;
	}

	private ToolEntry createFindByCreationTool() {
		final NodeToolEntry entry = new NodeToolEntry("FindBy", "FindBy", Collections.singletonList(PartitioningElementTypes.FindByStub));
		entry.setId("createFindBy4CreationTool"); //$NON-NLS-1$
		entry.setSmallIcon(findImageDescriptor(PartitioningPaletteProvider.TOOL_EDIT_PLUGIN_ID + "/icons/full/obj16/FindBy.gif")); //$NON-NLS-1$
		entry.setLargeIcon(entry.getSmallIcon());
		return entry;
	}

	private static class NodeToolEntry extends ToolEntry {

		private final List<IElementType> elementTypes;

		private NodeToolEntry(final String title, final String description, final List<IElementType> elementTypes) {
			super(title, description, null, null);
			this.elementTypes = elementTypes;
		}

		@Override
		public Tool createTool() {
			final Tool tool = new UnspecifiedTypeCreationTool(this.elementTypes);
			tool.setProperties(getToolProperties());
			return tool;
		}
	}
}
