import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { UserService } from '../../services/user-service/user.service';
import { UsernameUpdateRoutingModule } from './usernameupdate-routing.module'
import { UsernameUpdateComponent } from './usernameupdate.component';

@NgModule({
    declarations: [
        UsernameUpdateComponent
    ],
    providers: [
        UserService
    ],
    imports: [
        CommonModule,
        FormsModule,
        UsernameUpdateRoutingModule
    ]
})

export class UsernameUpdateModule { }
