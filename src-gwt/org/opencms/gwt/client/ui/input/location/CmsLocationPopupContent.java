/*
 * This library is part of OpenCms -
 * the Open Source Content Management System
 *
 * Copyright (c) Alkacon Software GmbH (http://www.alkacon.com)
 *
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 2.1 of the License, or (at your option) any later version.
 *
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * Lesser General Public License for more details.
 *
 * For further information about Alkacon Software, please see the
 * company website: http://www.alkacon.com
 *
 * For further information about OpenCms, please see the
 * project website: http://www.opencms.org
 * 
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307  USA
 */

package org.opencms.gwt.client.ui.input.location;

import org.opencms.gwt.client.Messages;
import org.opencms.gwt.client.ui.input.CmsSelectBox;
import org.opencms.gwt.client.ui.input.CmsSuggestBox;
import org.opencms.gwt.client.ui.input.CmsTextBox;

import java.util.Map;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Element;
import com.google.gwt.event.logical.shared.SelectionEvent;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.SuggestOracle;

/**
 * The location picker map view.<p>
 */
public class CmsLocationPopupContent extends Composite {

    /**
     * @see com.google.gwt.uibinder.client.UiBinder
     */
    protected interface I_CmsLocationPopupContentUiBinder extends UiBinder<HTMLPanel, CmsLocationPopupContent> {
        // GWT interface, nothing to do here
    }

    /** The ui-binder instance for this class. */
    private static I_CmsLocationPopupContentUiBinder uiBinder = GWT.create(I_CmsLocationPopupContentUiBinder.class);

    /** The address suggest box. */
    @UiField(provided = true)
    CmsSuggestBox m_addressField;

    /** The address label. */
    @UiField
    Label m_addressLabel;

    /** The height field. */
    @UiField
    CmsTextBox m_heightField;

    /** The label field. */
    @UiField
    CmsTextBox m_latitudeField;

    /** The label label. */
    @UiField
    Label m_latitudeLabel;

    /** The longitude field. */
    @UiField
    CmsTextBox m_longitudeField;

    /** The longitude label. */
    @UiField
    Label m_longitudeLabel;

    /** The map canvas element. */
    @UiField
    Element m_mapCanvas;

    /** The mode field. */
    @UiField(provided = true)
    CmsSelectBox m_modeField;

    /** The mode label. */
    @UiField
    Label m_modeLabel;

    /** The map size label. */
    @UiField
    Label m_sizeLabel;

    /** The map type field. */
    @UiField(provided = true)
    CmsSelectBox m_typeField;

    /** The map type label. */
    @UiField
    Label m_typeLabel;

    /** The width field. */
    @UiField
    CmsTextBox m_widthField;

    /** The zoom field. */
    @UiField(provided = true)
    CmsSelectBox m_zoomField;

    /** The zoom label. */
    @UiField
    Label m_zoomLabel;

    /** The location picker. */
    private CmsLocationController m_controller;

    /**
     * Constructor.<p>
     * 
     * @param controller the location controller
     * @param addressOracle the address suggest oracle to use for the address suggest box
     * @param modeItems the available map modes
     * @param typeItems the available map types
     * @param zoomItems the available zoom levels
     */
    public CmsLocationPopupContent(
        CmsLocationController controller,
        SuggestOracle addressOracle,
        Map<String, String> modeItems,
        Map<String, String> typeItems,
        Map<String, String> zoomItems) {

        // create fields annotated as provided before running the ui-binder
        m_addressField = new CmsSuggestBox(addressOracle);
        m_modeField = new CmsSelectBox(modeItems);
        m_typeField = new CmsSelectBox(typeItems);
        m_zoomField = new CmsSelectBox(zoomItems);
        initWidget(uiBinder.createAndBindUi(this));
        m_controller = controller;
        initLabels();
        initFields();
    }

    /**
     * On address field value change.<p>
     * 
     * @param event the change event
     */
    @UiHandler("m_addressField")
    public void onAddressChange(ValueChangeEvent<String> event) {

        m_controller.onAddressChange(event.getValue());
    }

    /**
     * On address suggest selection.<p>
     * 
     * @param event the selection event
     */
    @UiHandler("m_addressField")
    public void onAddressSelection(SelectionEvent<SuggestOracle.Suggestion> event) {

        m_controller.onAddressChange(event.getSelectedItem());
    }

    /**
     * On height value change.<p>
     * 
     * @param event the change event
     */
    @UiHandler("m_heightField")
    public void onHeightChange(ValueChangeEvent<String> event) {

        m_controller.onHeightChange(event.getValue());
    }

    /**
     * On latitude value change.<p>
     * 
     * @param event the change event
     */
    @UiHandler("m_latitudeField")
    public void onLatitudeChange(ValueChangeEvent<String> event) {

        m_controller.onLatitudeChange(event.getValue());
    }

    /**
     * On longitude value change.<p>
     * 
     * @param event the change event
     */
    @UiHandler("m_longitudeField")
    public void onLongitudeChange(ValueChangeEvent<String> event) {

        m_controller.onLongitudeChange(event.getValue());
    }

    /**
     * On type value change.<p>
     * 
     * @param event the change event
     */
    @UiHandler("m_typeField")
    public void onTypeChange(ValueChangeEvent<String> event) {

        m_controller.onTypeChange(event.getValue());
    }

    /**
     * On width value change.<p>
     * 
     * @param event the change event
     */
    @UiHandler("m_widthField")
    public void onWidthChange(ValueChangeEvent<String> event) {

        m_controller.onWidthChange(event.getValue());
    }

    /**
     * On zoom value change.<p>
     * 
     * @param event the change event
     */
    @UiHandler("m_zoomField")
    public void onZoomChange(ValueChangeEvent<String> event) {

        m_controller.onZoomChange(event.getValue());
    }

    /**
     * Displays the location value fields.<p>
     * 
     * @param value the location value
     */
    protected void displayValues(CmsLocationValue value) {

        m_addressField.setTextValue(value.getAddress());
        m_latitudeField.setFormValueAsString(value.getLatitudeString());
        m_longitudeField.setFormValueAsString(value.getLongitudeString());
        m_heightField.setFormValueAsString("" + value.getHeight());
        m_widthField.setFormValueAsString("" + value.getWidth());
        m_zoomField.setFormValueAsString("" + value.getZoom());
        m_modeField.setFormValueAsString(value.getMode());
        m_typeField.setFormValueAsString(value.getType());
    }

    /**
     * Returns the map canvas element.<p>
     * 
     * @return the map canvas element
     */
    protected Element getMapCanvas() {

        return m_mapCanvas;
    }

    /**
     * Initializes the form fields.<p>
     */
    private void initFields() {

        m_latitudeField.setTriggerChangeOnKeyPress(true);
        m_longitudeField.setTriggerChangeOnKeyPress(true);
        m_heightField.setTriggerChangeOnKeyPress(true);
        m_widthField.setTriggerChangeOnKeyPress(true);
    }

    /**
     * Initializes the form labels.<p>
     */
    private void initLabels() {

        m_addressLabel.setText(Messages.get().key(Messages.GUI_LOCATION_ADDRESS_0));
        m_longitudeLabel.setText(Messages.get().key(Messages.GUI_LOCATION_LONGITUDE_0));
        m_latitudeLabel.setText(Messages.get().key(Messages.GUI_LOCATION_LATITUDE_0));
        m_sizeLabel.setText(Messages.get().key(Messages.GUI_LOCATION_SIZE_0));
        m_zoomLabel.setText(Messages.get().key(Messages.GUI_LOCATION_ZOOM_0));
        m_typeLabel.setText(Messages.get().key(Messages.GUI_LOCATION_TYPE_0));
        m_modeLabel.setText(Messages.get().key(Messages.GUI_LOCATION_MODE_0));
    }
}
