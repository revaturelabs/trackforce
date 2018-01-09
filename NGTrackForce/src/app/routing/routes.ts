/**
 * @author Antony Lulciuc
 * @description Defines all possible navigatable routes for TrackForce application
 */

import { RouterModule, Routes } from '@angular/router';
import { HomeComponent } from '../components/home/home.component';
import { ClientMappedComponent } from '../components/client-mapped/client-mapped.component';
import { AssociateListComponent } from '../components/associate-list/associate-list.component';
import { BatchListComponent } from '../components/batch-list/batch-list.component';

/**
 * Place paths here 
 */
export const appRoutes: Routes = [
    {
        path: 'home',
        component: HomeComponent
    },
    {
        path: 'clientListing',
        component: ClientMappedComponent
    },
    {
        path: 'associateListing',
        component: AssociateListComponent
    },
    {
        path: 'batchListing',
        component: BatchListComponent
    }
]
