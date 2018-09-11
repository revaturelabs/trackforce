"use strict";
var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
exports.__esModule = true;
var core_1 = require("@angular/core");
var theme_constants_1 = require("../../constants/theme.constants");
var ng2_charts_options_model_1 = require("../../models/ng2-charts-options.model");
require("../../constants/selected-status.constants");
var selected_status_constants_1 = require("../../constants/selected-status.constants");
var HomeComponent = /** @class */ (function () {
    /**
      *@param {RequestService} rs
      * Service for handling requests to the back-end
      *
      *@param {DataSyncService} ds
      * Experimental service with BehaviorSubject
      * BehaviorSubject allows for real-time update of charts
      * Not fully implemented, so it is un-used
      *
      *@param {Router} router
      * Allows for re-direction to other components
      */
    function HomeComponent(rs, 
    // private ds: DataSyncService,
    router, as) {
        this.rs = rs;
        this.router = router;
        this.as = as;
        this.loading = true;
        this.labels = [];
        this.data = [];
        //Variables for chart settings
        this.undeployedLabels = selected_status_constants_1.SelectedStatusConstants.UNDEPLOYED_LABELS;
        this.mappedLabels = selected_status_constants_1.SelectedStatusConstants.MAPPED_LABELS;
        this.unmappedLabels = selected_status_constants_1.SelectedStatusConstants.UNMAPPED_LABELS;
        this.deployedLabels = selected_status_constants_1.SelectedStatusConstants.DEPLOYED_LABELS;
        this.mappedColors = theme_constants_1.ThemeConstants.MAPPED_COLORS;
        this.clientColors = theme_constants_1.ThemeConstants.CLIENT_COLORS;
        this.skillColors = theme_constants_1.ThemeConstants.SKILL_COLORS;
        this.deployedChartType = "pie";
        this.undeployedChartType = "pie";
        this.mappedChartType = "pie";
        this.unmappedChartType = "pie";
        // private options = ChartOptions.createOptionsLegend('right');
        this.unmappedOptions = ng2_charts_options_model_1.ChartOptions.createOptionsTitle('Unmapped', 24, '#121212', 'right');
        this.mappedOptions = ng2_charts_options_model_1.ChartOptions.createOptionsTitle('Mapped', 24, '#121212', 'right');
        this.deployedOptions = ng2_charts_options_model_1.ChartOptions.createOptionsTitle('Mapped vs. Unmapped (Deployed)', 24, '#121212', 'right');
        this.undeployedOptions = ng2_charts_options_model_1.ChartOptions.createOptionsTitle('Mapped vs. Unmapped (Not Deployed)', 24, '#121212', 'right');
        //end of chart settings
        // populate with dummy data to enable chart labels by default
        this.undeployedData = [0, 0];
        this.deployedData = [0, 0];
        this.mappedData = [0, 0, 0, 0];
        this.unmappedData = [0, 0, 0, 0];
    }
    HomeComponent.prototype.ngOnInit = function () {
        this.getCountForCharts();
    };
    HomeComponent.prototype.getCountForCharts = function () {
        var _this = this;
        this.as.getCountAssociates().subscribe(function (count) {
            _this.count = count;
            _this.undeployedData[0] = _this.count['counts'][0];
            _this.undeployedData[1] = _this.count['counts'][1];
            localStorage.setItem('undeployedData', JSON.stringify(_this.undeployedData));
            _this.deployedData[0] = _this.count['counts'][2];
            _this.deployedData[1] = _this.count['counts'][3];
            localStorage.setItem('deployedData', JSON.stringify(_this.deployedData));
            _this.unmappedData[0] = _this.count['counts'][4];
            _this.unmappedData[1] = _this.count['counts'][5];
            _this.unmappedData[2] = _this.count['counts'][6];
            _this.unmappedData[3] = _this.count['counts'][7];
            localStorage.setItem('unmappedData', JSON.stringify(_this.unmappedData));
            _this.mappedData[0] = _this.count['counts'][8];
            _this.mappedData[1] = _this.count['counts'][9];
            _this.mappedData[2] = _this.count['counts'][10];
            _this.mappedData[3] = _this.count['counts'][11];
            localStorage.setItem('mappedData', JSON.stringify(_this.mappedData));
            _this.loading = false;
        });
    };
    /**
     * @function MappedOnClick
     * @description When the "Mapped" chart is clicked
     * the global variable selectedStatus is
     * set to the label of the slice
     * clicked.
     */
    HomeComponent.prototype.mappedOnClick = function (evt) {
        if (evt.active[0] !== undefined) {
            //navigate to client-mapped component
            this.router.navigate(["client-mapped/" + evt.active[0]._index]);
        }
    };
    ;
    /**
     * @function UnmappedOnClick
     * @description When the "Unmapped" chart is clicked
     * the global variable selectedStatus is
     * set to the label of the slice
     * clicked.
     */
    HomeComponent.prototype.unmappedOnClick = function (evt) {
        if (evt.active[0] !== undefined) {
            this.router.navigate(["skillset/" + evt.active[0]._index]);
        }
    };
    HomeComponent.prototype.deployedOnClick = function (evt) {
        if (evt.active[0] !== undefined) {
            this.router.navigate(["deployed/" + evt.active[0]._index]);
        }
    };
    HomeComponent.prototype.undeployedOnClick = function (evt) {
        if (evt.active[0] !== undefined) {
            this.router.navigate(["undeployed/" + evt.active[0]._index]);
        }
    };
    HomeComponent.prototype.getUndeployedData = function () {
        return this.undeployedData;
    };
    HomeComponent.prototype.getDeployedData = function () {
        return this.deployedData;
    };
    HomeComponent.prototype.getMappedData = function () {
        return this.mappedData;
    };
    HomeComponent.prototype.getUnmappedData = function () {
        return this.unmappedData;
    };
    HomeComponent = __decorate([
        core_1.Component({
            selector: 'app-home',
            templateUrl: './home.component.html',
            styleUrls: ['./home.component.css']
        })
    ], HomeComponent);
    return HomeComponent;
}());
exports.HomeComponent = HomeComponent;
