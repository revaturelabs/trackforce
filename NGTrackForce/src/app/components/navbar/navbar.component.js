"use strict";
var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
exports.__esModule = true;
var core_1 = require("@angular/core");
/**
  * Controls the nav bar
  */
var NavbarComponent = /** @class */ (function () {
    function NavbarComponent(router, authService, navbarService) {
        this.router = router;
        this.authService = authService;
        this.navbarService = navbarService;
        this.username = '';
    }
    NavbarComponent.prototype.ngOnInit = function () {
        // this.navbarDisplay();
    };
    NavbarComponent.prototype.ngOnChanges = function () {
        // this.navbarDisplay();
    };
    NavbarComponent.prototype.ngAfterContentChecked = function () {
        this.navbarDisplay();
    };
    /**
      * Removes user from localStorage and re-routes to login screen
      */
    NavbarComponent.prototype.logout = function () {
        this.isLoggedIn = false;
        this.isAdmin = false;
        this.isTrainer = false;
        this.isAssociate = false;
        this.user = null;
        this.authService.logout();
        // linked to /login page directly on anchor for testing purposes
        //this.router.navigateByUrl('/login');
    };
    NavbarComponent.prototype.navbarDisplay = function () {
        this.user = this.authService.getUser();
        //Role checks
        // only role check if there is already a user
        if (this.user !== null && this.user !== undefined) {
            this.isLoggedIn = true;
            this.username = this.user.username;
            if (this.user.role === 1) {
                this.isAdmin = true;
                this.isSales = false;
                this.isStaging = false;
                this.isTrainer = false;
                this.isAssociate = false;
            }
            else if (this.user.role === 3) {
                this.isAdmin = false;
                this.isSales = true;
                this.isStaging = false;
                this.isTrainer = false;
                this.isAssociate = false;
            }
            else if (this.user.role === 4) {
                this.isAdmin = false;
                this.isSales = false;
                this.isStaging = true;
                this.isTrainer = false;
                this.isAssociate = false;
            }
            else if (this.user.role === 2) {
                this.isAdmin = false;
                this.isSales = false;
                this.isStaging = false;
                this.isTrainer = true;
                this.isAssociate = false;
            }
            else if (this.user.role === 5) {
                this.isAdmin = false;
                this.isSales = false;
                this.isStaging = false;
                this.isTrainer = false;
                this.isAssociate = true;
            }
        }
    };
    NavbarComponent = __decorate([
        core_1.Component({
            selector: 'app-navbar',
            templateUrl: './navbar.component.html',
            styleUrls: ['./navbar.component.css']
        })
    ], NavbarComponent);
    return NavbarComponent;
}());
exports.NavbarComponent = NavbarComponent;
