/**
 * @author Antony Lulciuc
 * @description Defines all possible navigatable routes for TrackForce application
 */

import { RouterModule, Routes } from '@angular/router';
import { HomeComponent } from '../components/home/home.component';

/**
 * Place paths here 
 */
export const appRoutes: Routes = [
    {
        path: 'home',
        component: HomeComponent
    }
]
