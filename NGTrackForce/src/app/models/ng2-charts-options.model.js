"use strict";
/**
 * @Author chart style for stronger typing while constructing ng2-charts options/colors
 *
 * As a whole, this model is used in batch-list.component, home.component
 * This file contains the model for SideValues and ChartOptions,
 * as well as the methods createOptionsSpacing, createOptionsTitle, and createOptionsLegend
 * Note made 6/7/2018
 * Reviewed by Max
 */
exports.__esModule = true;
/**
 * Used to represent padding and margin in chart styles
 *
 */
var SideValues = /** @class */ (function () {
    function SideValues(left, right, top, bottom) {
        this.left = left;
        this.right = right;
        this.top = top;
        this.bottom = bottom;
    }
    return SideValues;
}());
exports.SideValues = SideValues;
/**
 * chart options factory for ng2-charts
 */
var ChartOptions = /** @class */ (function () {
    function ChartOptions() {
    }
    /**
     * convenience method for creating chart options with
     * padding and margin options
     *
     * @param {SideValues} padding
     * @param {SideValues} margin
     * @param {string} legendPosition
     * @param {boolean} isResponsive
     * @param {boolean} maintainAspectRatio
     * @returns {ChartOptions}
     */
    ChartOptions.createOptionsSpacing = function (padding, margin, legendPosition, isResponsive, maintainAspectRatio) {
        var options = new ChartOptions();
        options.legend = { display: true, position: legendPosition };
        options.layout = { padding: padding, margin: margin };
        options.responsive = isResponsive;
        options.maintainAspectRatio = maintainAspectRatio;
        return options;
    };
    ;
    /**
     * convenience method for construction options with legend and title options
     *
     * @param {string} title
     * @param {number} titleFontSize
     * @param {string} titleColor
     * @param {string} legendPosition
     * @returns {ChartOptions}
     */
    ChartOptions.createOptionsTitle = function (title, titleFontSize, titleColor, legendPosition) {
        var options = new ChartOptions();
        options.title = { display: true, text: title, fontSize: titleFontSize, fontColor: titleColor };
        options.legend = { display: true, position: legendPosition };
        return options;
    };
    /**
     * convenience method for construction options with legend options
     *
     * @param {string} legendPosition
     * @returns {ChartOptions}
     */
    ChartOptions.createOptionsLegend = function (legendPosition) {
        var options = new ChartOptions();
        options.legend = { display: true, position: legendPosition };
        return options;
    };
    return ChartOptions;
}());
exports.ChartOptions = ChartOptions;
