/**
 * @author Antony Lulciuc
 * @description Defines all possible navigatable routes for TrackForce application
 */

import { RouterModule, Routes } from '@angular/router';
import {FormComponent} from "../components/form-component/form.component";
/**
 * Place paths here 
 */
export const appRoutes: Routes = [
    {
        path: 'form/:id', //The url that will trigger the injection
        component: FormComponent //the component to be injected.
    }

]
