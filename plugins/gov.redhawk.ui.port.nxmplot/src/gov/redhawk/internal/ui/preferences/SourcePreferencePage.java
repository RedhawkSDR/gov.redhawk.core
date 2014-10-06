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
package gov.redhawk.internal.ui.preferences;

import gov.redhawk.ui.port.nxmblocks.PlotNxmBlock;
import gov.redhawk.ui.port.nxmplot.INxmBlock;
import gov.redhawk.ui.port.nxmplot.PlotPageBook2;
import gov.redhawk.ui.port.nxmplot.preferences.PlotPreferences;

import java.awt.Color;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.preference.PreferencePage;
import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.jface.viewers.CheckStateChangedEvent;
import org.eclipse.jface.viewers.CheckboxCellEditor;
import org.eclipse.jface.viewers.CheckboxTableViewer;
import org.eclipse.jface.viewers.ColorCellEditor;
import org.eclipse.jface.viewers.ColumnLabelProvider;
import org.eclipse.jface.viewers.ColumnWeightData;
import org.eclipse.jface.viewers.EditingSupport;
import org.eclipse.jface.viewers.ICheckStateListener;
import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.TableLayout;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.ImageData;
import org.eclipse.swt.graphics.PaletteData;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;

/**
 * 
 */
public class SourcePreferencePage extends PreferencePage {

//	private PlotPageBook2 pageBook;
	private List<INxmBlock> sourceBlocks;
	private CheckboxTableViewer streamsViewer;
	private PlotNxmBlock plotBlock;
	private Map<String, Color> streamColors = new HashMap<String, Color>();
	private Map<String, Boolean> streamShows = new HashMap<String, Boolean>();
	private OverridableIntegerFieldEditor frameSizeEditor;

	public SourcePreferencePage(String label, PlotPageBook2 pageBook, List<INxmBlock> sourceBlocks) {
		super(label);
		setDescription("Modify how this particular source is being plotted.");
		this.sourceBlocks = sourceBlocks;
//		this.pageBook = pageBook;
	}

	@Override
	protected Control createContents(Composite parent) {
		Composite main = new Composite(parent, SWT.None);
		main.setLayout(new GridLayout(1, false));

		final Table streamsTable = new Table(main, SWT.BORDER | SWT.VIRTUAL | SWT.CHECK | SWT.FULL_SELECTION);
		streamsTable.setLayoutData(GridDataFactory.fillDefaults().span(3, 5).grab(true, true).create());
		streamsTable.setLinesVisible(true);
		streamsTable.setHeaderVisible(true);
		TableLayout streamsLayout = new TableLayout();
		streamsLayout.addColumnData(new ColumnWeightData(12, 20, true));
		streamsLayout.addColumnData(new ColumnWeightData(60, 200, true));
		streamsLayout.addColumnData(new ColumnWeightData(30, 50, true));
		streamsTable.setLayout(streamsLayout);
		TableColumn checkColumn = new TableColumn(streamsTable, SWT.NONE);
		checkColumn.setText("Show");
		TableColumn idColumn = new TableColumn(streamsTable, SWT.NONE);
		idColumn.setText("Stream ID");
		TableColumn colorColumn = new TableColumn(streamsTable, SWT.NONE);
		colorColumn.setText("Color");
		streamsViewer = new CheckboxTableViewer(streamsTable);
		streamsViewer.addCheckStateListener(new ICheckStateListener() {

			@Override
			public void checkStateChanged(CheckStateChangedEvent event) {
				String streamID = (String) event.getElement();
				streamShows.put(streamID, new Boolean(event.getChecked()));
			}
			
		});
		streamsViewer.setContentProvider(new IStructuredContentProvider() {
			@Override
			public Object[] getElements(Object inputElement) {
				return getAllStreamIds().toArray(new String[0]);
			}
			
			@Override
			public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
			}

			@Override
			public void dispose() {
			}
		});
		
		Composite buttons = new Composite(main, SWT.NONE);
		buttons.setLayout(new GridLayout(2, false));
		
		Button showButton = new Button(buttons, SWT.PUSH);
		showButton.setText("Show All");
		showButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				for (String streamID: getAllStreamIds()) {
					streamShows.put(streamID, Boolean.TRUE);
				}
				streamsViewer.setAllChecked(true);
			}
		});

		Button hideButton = new Button(buttons, SWT.PUSH);
		hideButton.setText("Hide All");
		hideButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				for (String streamID: getAllStreamIds()) {
					streamShows.put(streamID, Boolean.FALSE);
				}
				streamsViewer.setAllChecked(false);
			}
		});

		TableViewerColumn checkViewer = new TableViewerColumn(streamsViewer, checkColumn);
		checkViewer.setEditingSupport(new EditingSupport(streamsViewer) {

			private Map<String, CheckboxCellEditor> checkEditors = new HashMap<String, CheckboxCellEditor>();
			@Override
			protected CellEditor getCellEditor(Object element) {
				CheckboxCellEditor checker = checkEditors.get((String) element);
				if (checker == null) {
					checker = new CheckboxCellEditor(streamsTable);
					checkEditors.put((String) element, checker);
				}
				return checker;
			}

			@Override
			protected boolean canEdit(Object element) {
				return true;
			}

			@Override
			protected Object getValue(Object element) {
				String streamID = (String) element;
				Boolean shouldShow = streamShows.get(streamID);
				if (shouldShow == null) {
					shouldShow = new Boolean(plotBlock.isStreamShown(streamID));
					streamShows.put(streamID, shouldShow);
				}
				return shouldShow;
			}

			@Override
			protected void setValue(Object element, Object value) {
				String streamID = (String) element;
				boolean shouldShow = ((Boolean) value).booleanValue();
				streamShows.put(streamID, new Boolean(shouldShow));
				streamsViewer.setChecked(element, shouldShow);
				getViewer().update(element, null);
			}
			
		});
		checkViewer.setLabelProvider(new ColumnLabelProvider() {
			@Override
			public String getText(Object element) {
				String streamID = (String) element;
				Boolean shouldShow = streamShows.get(streamID);
				if (shouldShow == null) {
					shouldShow = new Boolean(plotBlock.isStreamShown(streamID));
					streamShows.put(streamID, shouldShow);
				}
				streamsViewer.setChecked(element, shouldShow.booleanValue());
				return ""; 
			}
		});
		TableViewerColumn idViewer = new TableViewerColumn(streamsViewer, idColumn);
		idViewer.setLabelProvider(new ColumnLabelProvider() {
			@Override
			public String getText(Object element) {
				if (element instanceof String) {
					return (String) element;
				}
				return super.getText(element);
			}
		});
		TableViewerColumn colorViewer = new TableViewerColumn(streamsViewer, colorColumn);
		colorViewer.setEditingSupport(new EditingSupport(streamsViewer) {
			private Map<String, ColorCellEditor> colorEditors = new HashMap<String, ColorCellEditor>();
			@Override
			protected CellEditor getCellEditor(Object element) {
				ColorCellEditor streamColor = colorEditors.get((String) element);
				if (streamColor == null) {
					streamColor = new ColorCellEditor(streamsTable);
					colorEditors.put((String) element, streamColor);
				}
				return streamColor;
			}

			@Override
			protected boolean canEdit(Object element) {
				return true;
			}

			@Override
			protected Object getValue(Object element) {
				Color currentColor = getStreamColor((String) element);
				return new RGB(currentColor.getRed(), currentColor.getGreen(), currentColor.getBlue());
			}

			@Override
			protected void setValue(Object element, Object value) {
				RGB newRGB = (RGB) value;
				Color newColor = new Color(newRGB.red, newRGB.green, newRGB.blue);
				setStreamColor((String) element, newColor);
				getViewer().update(element, null);
			}
			
		});
		colorViewer.setLabelProvider(new ColumnLabelProvider() {
			@Override
			public String getText(Object element) {
				return streamColorText((String) element);
			}
			
			@Override
			public Image getImage(Object element) {
				return streamColorImage((String) element);
			}
			
		});
		plotBlock = getPlotBlock();
		streamsViewer.setInput(plotBlock);
		Composite field = new Composite(main, SWT.NONE);
		field.setLayout(new GridLayout(2, false));
		field.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).create());
//		createFrameSizeField(field);
		for (INxmBlock block: sourceBlocks) {
			if (block instanceof PlotNxmBlock) {
				frameSizeEditor = createFrameSizeField(block, field);
				break;
			}
		}
		return main;
	}

	private OverridableIntegerFieldEditor createFrameSizeField(INxmBlock block, Composite parent) {
		OverridableIntegerFieldEditor frameSizeField = new OverridableIntegerFieldEditor(PlotPreferences.FRAMESIZE.getName(),
			PlotPreferences.FRAMESIZE_OVERRIDE.getName(), "&Framesize:", parent);
		frameSizeField.setErrorMessage("Framesize must be a positive integer >= 2");
		frameSizeField.setValidRange(2, Integer.MAX_VALUE);
		frameSizeField.setPage(this);
		frameSizeField.setPreferenceStore(block.getPreferences());
		frameSizeField.fillIntoGrid(parent, 2);
		frameSizeField.load();
		return frameSizeField;
	}

	private List<String> getAllStreamIds() {
		return plotBlock.getStreamIDs();
	}
	
	private PlotNxmBlock getPlotBlock() {
		for (int index = sourceBlocks.size() - 1; index >= 0; --index) {
			if (sourceBlocks.get(index) instanceof PlotNxmBlock) {
				return (PlotNxmBlock) sourceBlocks.get(index);
			}
		}
		return null;
	}
	
	private Color getStreamColor(String streamID) {
		Color retval = streamColors.get(streamID);
		if (retval == null) {
			retval = plotBlock.getStreamLineColor(streamID);
			streamColors.put(streamID, retval);
		}
		return retval;
	}
	
	private void setStreamColor(String streamID, Color streamColor) {
		streamColors.put(streamID, streamColor);
	}
	
	private Image streamColorImage(String streamID) {
		Color currentColor = getStreamColor(streamID);
		PaletteData colorPalette = new PaletteData(0xFF0000, 0xFF00, 0xFF);
		colorPalette.redShift = -16;
		colorPalette.greenShift = -8;
		colorPalette.blueShift = 0;
		ImageData colorData = new ImageData(1, 1, 24, colorPalette);
		colorData.alpha = 255;
		colorData.setPixel(0, 0, (currentColor.getRed() << 16) + (currentColor.getGreen() << 8) + currentColor.getBlue());
		return new Image(Display.getCurrent(), colorData.scaledTo(15, 15));
	}
	
	private String streamColorText(String streamID) {
		Color currentColor = getStreamColor(streamID);
		return "(" + currentColor.getRed() + "," + currentColor.getGreen() + "," + currentColor.getBlue() + ")";
	}
	
	@Override
	public boolean performOk() {
		if (frameSizeEditor != null) {
			frameSizeEditor.store();
		}
		for (String streamID: getAllStreamIds()) {
			Color streamColor = streamColors.get(streamID);
			if (streamColor != null) {
				plotBlock.setStreamLineColor(streamID, streamColor);
			}
			Boolean streamShowing = streamShows.get(streamID);
			if (streamShowing != null) {
				boolean rawValue = streamShowing.booleanValue();
				if (rawValue) {
					plotBlock.showStream(streamID);
				} else {
					plotBlock.hideStream(streamID);
				}
			}
		}
		return super.performOk();
	}
	
	@Override
	protected void performDefaults() {
		List<String> streamIDs = getAllStreamIds();
		for (String streamID: streamIDs) {
			streamShows.put(streamID, Boolean.TRUE);
			setStreamColor(streamID, plotBlock.getStreamDefaultLineColor(streamID));
		}
		streamsViewer.update(streamIDs.toArray(), null);
		frameSizeEditor.loadDefault();
		super.performDefaults();
	}
	
}
