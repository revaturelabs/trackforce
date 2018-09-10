"use strict";
exports.__esModule = true;
/**
 * - This is being used in the client-list.component, home.component, skillset.component,
 * and selected-status.component
 * - This is used in the creation of ngCharts and canvas elements
 * Note made 6/6/2018
 * Reviewed by Max
 *
 * - This maps to the MarketingStatusInfo model in Java.
 */
var SelectedStatusConstants = /** @class */ (function () {
    function SelectedStatusConstants() {
    }
    SelectedStatusConstants.TRAINING = "Training";
    SelectedStatusConstants.OPEN = "Open";
    SelectedStatusConstants.SELECTED = "Selected";
    SelectedStatusConstants.CONFIRMED = "Confirmed";
    SelectedStatusConstants.RESERVED = "Reserved";
    SelectedStatusConstants.MAPPED = "Mapped";
    SelectedStatusConstants.UNMAPPED = "Unmapped";
    SelectedStatusConstants.RESERVED_OPEN = "Reserved/Open";
    SelectedStatusConstants.DEPLOYED = "Deployed";
    SelectedStatusConstants.TRAINING_JSON = { id: 0, name: SelectedStatusConstants.TRAINING };
    SelectedStatusConstants.OPEN_JSON = { id: 1, name: SelectedStatusConstants.OPEN };
    SelectedStatusConstants.SELECTED_JSON = { id: 2, name: SelectedStatusConstants.SELECTED };
    SelectedStatusConstants.CONFIRMED_JSON = { id: 3, name: SelectedStatusConstants.CONFIRMED };
    SelectedStatusConstants.RESERVED_JSON = { id: 1, name: SelectedStatusConstants.RESERVED };
    SelectedStatusConstants.MAPPED_JSON = { id: 0, name: SelectedStatusConstants.MAPPED };
    SelectedStatusConstants.UNMAPPED_JSON = { id: 1, name: SelectedStatusConstants.UNMAPPED };
    SelectedStatusConstants.RESERVED_OPEN_JSON = { id: 1, name: SelectedStatusConstants.RESERVED_OPEN };
    SelectedStatusConstants.MAPPED_LABELS = [SelectedStatusConstants.TRAINING, SelectedStatusConstants.RESERVED, SelectedStatusConstants.SELECTED, SelectedStatusConstants.CONFIRMED];
    SelectedStatusConstants.UNMAPPED_LABELS = [SelectedStatusConstants.TRAINING, SelectedStatusConstants.OPEN, SelectedStatusConstants.SELECTED, SelectedStatusConstants.CONFIRMED];
    SelectedStatusConstants.DEPLOYED_LABELS = [SelectedStatusConstants.MAPPED, SelectedStatusConstants.UNMAPPED];
    SelectedStatusConstants.UNDEPLOYED_LABELS = [SelectedStatusConstants.MAPPED, SelectedStatusConstants.UNMAPPED];
    SelectedStatusConstants.CLIENT_LABELS = [SelectedStatusConstants.TRAINING, SelectedStatusConstants.RESERVED_OPEN, SelectedStatusConstants.SELECTED, SelectedStatusConstants.CONFIRMED];
    return SelectedStatusConstants;
}());
exports.SelectedStatusConstants = SelectedStatusConstants;
