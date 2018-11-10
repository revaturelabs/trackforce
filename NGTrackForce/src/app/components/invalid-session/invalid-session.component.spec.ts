import { RouterTestingModule } from '@angular/router/testing';
import { FooterComponent } from './../footer/footer.component';
import { NavbarComponent } from './../navbar/navbar.component';
import { AppComponent } from './../../app.component';
import { FormsModule } from '@angular/forms';
import { MatProgressSpinnerModule } from '@angular/material/progress-spinner';
import { NotFoundComponent } from './../not-found/not-found.component';
import { AssociateViewComponent } from './../associate-view/associate-view.component';
import { AppRoutingModule } from './../../routing/app-routing.module';

import { RequestService } from './../../services/request-service/request.service';
import { HttpClientModule } from '@angular/common/http';
import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { InvalidSessionComponent } from './invalid-session.component';
import { LoginComponent } from '../login/login.component';

/**
 * [DEFECT: "[object ErrorEvent] thrown"]
 * 
 * This spec still does not successfully create the InvalidSessionComponent
 * to perform testing upon. Refer to issue 698 in the repository for the
 * details on what has been discovered in previous debugging attempts.
 */

describe('InvalidSessionComponent', () => {
  let component: InvalidSessionComponent;
  let fixture: ComponentFixture<InvalidSessionComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [
        InvalidSessionComponent,
        LoginComponent,
        AssociateViewComponent,
        NotFoundComponent,
        NavbarComponent,
        FooterComponent,
        AppComponent
      ],
      providers: [
        RequestService
      ],
      imports: [
        FormsModule,
        HttpClientModule,
        AppRoutingModule, 
        MatProgressSpinnerModule,
        RouterTestingModule
      ]
    })
    .compileComponents();

  }));

  beforeEach(() => {
    
    fixture = TestBed.createComponent(InvalidSessionComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();

  });

  it('should create', () => {
    expect(component).toBeDefined();
  });

  

});
