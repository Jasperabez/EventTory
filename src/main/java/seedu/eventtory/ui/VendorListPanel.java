package seedu.eventtory.ui;

import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import seedu.eventtory.commons.core.LogsCenter;
import seedu.eventtory.model.vendor.Vendor;

/**
 * Panel containing the list of vendors.
 */
public class VendorListPanel extends UiPart<Region> {
    private static final String FXML = "VendorListPanel.fxml";
    private final Logger logger = LogsCenter.getLogger(VendorListPanel.class);

    @FXML
    private Label header;
    @FXML
    private ListView<Vendor> vendorListView;

    /**
     * Creates a {@code VendorListPanel} with the given {@code ObservableList}.
     */
    public VendorListPanel(ObservableList<Vendor> vendorList, String headerText) {
        super(FXML);
        vendorListView.setItems(vendorList);
        vendorListView.setCellFactory(listView -> new VendorListViewCell());
        header.setText(headerText);

        // Required to fix the issue of last item not being shown when scrolled to the end
        vendorListView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                vendorListView.scrollTo(vendorListView.getSelectionModel().getSelectedIndex());
            }
        });
    }

    /**
     * Set the header of the vendor list panel.
     */
    public void setHeader(String headerText) {
        header.setText(headerText);
    }

    /**
     * Custom {@code ListCell} that displays the graphics of a {@code Vendor} using a {@code VendorCard}.
     */
    class VendorListViewCell extends ListCell<Vendor> {
        @Override
        protected void updateItem(Vendor vendor, boolean empty) {
            super.updateItem(vendor, empty);

            if (empty || vendor == null) {
                setGraphic(null);
                setText(null);
            } else {
                setGraphic(new VendorCard(vendor, getIndex() + 1).getRoot());
            }
        }
    }

}
