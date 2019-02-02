import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { UserService } from '../../services/user-service/user.service';
import { PasswordUpdateRoutingModule } from './passwordupdate-routing.module'
import { PasswordUpdateComponent } from './passwordupdate.component';

@NgModule({
    declarations: [
        PasswordUpdateComponent
    ],
    providers: [
        UserService
    ],
    imports: [
        CommonModule,
        FormsModule,
        PasswordUpdateRoutingModule
    ]
})

export class PasswordUpdateModule { }
